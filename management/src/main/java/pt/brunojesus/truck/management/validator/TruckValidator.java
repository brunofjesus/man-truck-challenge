package pt.brunojesus.truck.management.validator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.management.service.IApplicationService;
import pt.brunojesus.truck.management.service.IClassificationService;
import pt.brunojesus.truck.management.service.IColorService;
import pt.brunojesus.truck.management.service.IFuelTypeService;
import pt.brunojesus.truck.model.domain.Application;
import pt.brunojesus.truck.model.domain.Color;
import pt.brunojesus.truck.model.domain.Truck;

@Component("truckValidator")
@AutoLogger
public class TruckValidator implements Consumer<Truck> {

	private final IClassificationService classificationService;
	private final IFuelTypeService fuelTypeService;
	private final IApplicationService applicationService;
	private final IColorService colorService;

	@Autowired
	public TruckValidator(IClassificationService classificationService, IFuelTypeService fuelTypeService,
			IApplicationService applicationService, IColorService colorService) {
		super();
		this.classificationService = classificationService;
		this.fuelTypeService = fuelTypeService;
		this.applicationService = applicationService;
		this.colorService = colorService;
	}

	@Override
	public void accept(Truck t) {
		if (t == null) {
			throw new IllegalArgumentException("Truck cannot be null");
		}

		if (t.getClassification() != null) {
			try {
				classificationService.findById(t.getClassification().getId());
			} catch (NoSuchElementException nse) {
				throw new ValidationException("Invalid classification id: " + t.getClassification().getId());
			}
		}

		if (t.getFuelType() != null) {
			try {
				fuelTypeService.findById(t.getFuelType().getId());
			} catch (NoSuchElementException nse) {
				throw new ValidationException("Invalid fuel type id: " + t.getFuelType().getId());
			}
		}

		if (CollectionUtils.isEmpty(t.getRelTruckApplications()) == false) {
			List<Integer> applicationIds = t.getRelTruckApplications().stream()
					.map(rel -> rel.getId().getApplicationId()).collect(Collectors.toList());

			List<Application> foundApplications = applicationService.findAllByIds(applicationIds);

			if (CollectionUtils.isEmpty(foundApplications)) {
				throw new ValidationException("Invalid applications: All");
			} else if (applicationIds.size() != foundApplications.size()) {
				Set<Integer> foundApplicationIds = foundApplications.stream().map(app -> app.getId())
						.collect(Collectors.toSet());

				List<String> notFoundApplicationIds = CollectionUtils
						.itemsNotInList(applicationIds, foundApplicationIds).stream().map(String::valueOf)
						.collect(Collectors.toList());

				throw new ValidationException("Invalid application ids: " + String.join(", ", notFoundApplicationIds));
			}
		}

		if (CollectionUtils.isEmpty(t.getRelTruckColors()) == false) {
			List<Integer> colorIds = t.getRelTruckColors().stream().map(rel -> rel.getId().getColorId())
					.collect(Collectors.toList());

			List<Color> foundColors = colorService.findAllByIds(colorIds);

			if (CollectionUtils.isEmpty(foundColors)) {
				throw new ValidationException("Invalid colors: All");
			} else if (colorIds.size() != foundColors.size()) {
				Set<Integer> foundColorIds = foundColors.stream().map(color -> color.getId())
						.collect(Collectors.toSet());

				List<String> notFoundColorIds = CollectionUtils.itemsNotInList(colorIds, foundColorIds).stream()
						.map(String::valueOf).collect(Collectors.toList());

				throw new ValidationException("Invalid color ids: " + String.join(", ", notFoundColorIds));
			}
		}
	}

}
