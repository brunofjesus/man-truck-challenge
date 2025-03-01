package pt.brunojesus.truck.management.validator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Application;
import pt.brunojesus.truck.model.domain.Classification;
import pt.brunojesus.truck.model.domain.Color;
import pt.brunojesus.truck.model.domain.FuelType;
import pt.brunojesus.truck.model.domain.Truck;

/**
 * The Class TruckValidator.
 * 
 * Validates if the {@link pt.brunojesus.truck.model.domain.Truck} is valid
 * 
 * @see pt.brunojesus.truck.management.service.IClassificationService
 * @see pt.brunojesus.truck.management.service.IFuelTypeService
 * @see pt.brunojesus.truck.management.service.IApplicationService
 * @see pt.brunojesus.truck.management.service.IColorService
 */
@Component("truckValidator")
@AutoLogger
public class TruckValidator implements Consumer<Truck> {

	private final GenericService<Classification, Integer> classificationService;
	private final GenericService<FuelType, Integer> fuelTypeService;
	private final GenericService<Application, Integer> applicationService;
	private final GenericService<Color, Integer> colorService;

	@Autowired
	public TruckValidator(
			@Qualifier("classificationService") GenericService<Classification, Integer> classificationService,
			@Qualifier("fuelTypeService") GenericService<FuelType, Integer> fuelTypeService,
			@Qualifier("applicationService") GenericService<Application, Integer> applicationService,
			@Qualifier("colorService") GenericService<Color, Integer> colorService) {
		super();
		this.classificationService = classificationService;
		this.fuelTypeService = fuelTypeService;
		this.applicationService = applicationService;
		this.colorService = colorService;
	}

	@Override
	public void accept(Truck truck) {
		if (truck == null) {
			throw new IllegalArgumentException("Truck cannot be null");
		}

		validateClassification(truck);

		validateFuelType(truck);

		validateRelTruckApplications(truck);

		validateRelTruckColors(truck);
	}

	private void validateRelTruckColors(Truck truck) {
		if (CollectionUtils.isEmpty(truck.getRelTruckColors()) == false) {
			List<Integer> colorIds = truck.getRelTruckColors().stream().map(rel -> rel.getId().getColorId())
					.collect(Collectors.toList());

			List<Color> foundColors = colorService.findAllByIds(colorIds);

			if (CollectionUtils.isEmpty(foundColors)) {
				throw new ValidationException("Invalid colors: All");
			} else if (colorIds.size() != foundColors.size()) {
				Set<Integer> foundColorIds = foundColors.stream().map(color -> color.getId())
						.collect(Collectors.toSet());

				List<String> notFoundColorIds = CollectionUtils.itemsNotInCollection(colorIds, foundColorIds).stream()
						.map(String::valueOf).collect(Collectors.toList());

				throw new ValidationException("Invalid color ids: " + String.join(", ", notFoundColorIds));
			}
		}
	}

	private void validateRelTruckApplications(Truck truck) {
		if (CollectionUtils.isEmpty(truck.getRelTruckApplications()) == false) {
			List<Integer> applicationIds = truck.getRelTruckApplications().stream()
					.map(rel -> rel.getId().getApplicationId()).collect(Collectors.toList());

			List<Application> foundApplications = applicationService.findAllByIds(applicationIds);

			if (CollectionUtils.isEmpty(foundApplications)) {
				throw new ValidationException("Invalid applications: All");
			} else if (applicationIds.size() != foundApplications.size()) {
				Set<Integer> foundApplicationIds = foundApplications.stream().map(app -> app.getId())
						.collect(Collectors.toSet());

				List<String> notFoundApplicationIds = CollectionUtils
						.itemsNotInCollection(applicationIds, foundApplicationIds).stream().map(String::valueOf)
						.collect(Collectors.toList());

				throw new ValidationException("Invalid application ids: " + String.join(", ", notFoundApplicationIds));
			}
		}
	}

	private void validateFuelType(Truck truck) {
		if (truck.getFuelType() != null) {
			try {
				fuelTypeService.findById(truck.getFuelType().getId());
			} catch (NoSuchElementException nse) {
				throw new ValidationException("Invalid fuel type id: " + truck.getFuelType().getId());
			}
		}
	}

	private void validateClassification(Truck truck) {
		if (truck.getClassification() != null) {
			try {
				classificationService.findById(truck.getClassification().getId());
			} catch (NoSuchElementException nse) {
				throw new ValidationException("Invalid classification id: " + truck.getClassification().getId());
			}
		}
	}

}
