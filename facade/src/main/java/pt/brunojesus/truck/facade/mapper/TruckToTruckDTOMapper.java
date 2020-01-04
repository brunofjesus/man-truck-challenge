package pt.brunojesus.truck.facade.mapper;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.brunojesus.truck.codegen.dto.ApplicationDTO;
import pt.brunojesus.truck.codegen.dto.ClassificationDTO;
import pt.brunojesus.truck.codegen.dto.ColorDTO;
import pt.brunojesus.truck.codegen.dto.FuelTypeDTO;
import pt.brunojesus.truck.codegen.dto.TruckDTO;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.model.domain.RelTruckApplication;
import pt.brunojesus.truck.model.domain.RelTruckColor;
import pt.brunojesus.truck.model.domain.Truck;

/**
 * The Class TruckToTruckDTOMapper.
 * 
 * Maps a {@link pt.brunojesus.truck.model.domain.Truck} to a
 * {@link pt.brunojesus.truck.codegen.dto.TruckDTO} object
 * 
 * @see pt.brunojesus.truck.model.domain.Truck
 * @see pt.brunojesus.truck.codegen.dto.TruckDTO
 * @see pt.brunojesus.truck.facade.mapper.IGenericMapper;
 * @see com.fasterxml.jackson.databind.ObjectMapper
 * @see pt.brunojesus.truck.facade.mapper.DateToOffsetDateTimeMapper
 */
@Component("truckToTruckDTOMapper")
public class TruckToTruckDTOMapper implements Function<Truck, TruckDTO> {

	private final ObjectMapper objectMapper;
	private final IGenericMapper genericMapper;
	private final Function<Date, OffsetDateTime> dateToOffsetDateTimeMapper;

	private final static Predicate<Object> notNull = (o) -> o != null;

	@Autowired
	public TruckToTruckDTOMapper(ObjectMapper objectMapper, IGenericMapper genericMapper,
			@Qualifier("dateToOffsetDateTimeMapper") Function<Date, OffsetDateTime> dateToOffsetDateTimeMapper) {

		this.objectMapper = objectMapper;
		this.genericMapper = genericMapper;
		this.dateToOffsetDateTimeMapper = dateToOffsetDateTimeMapper;
	}

	@Override
	public TruckDTO apply(Truck truck) {
		TruckDTO result = null;
		Truck d = objectMapper.convertValue(truck, Truck.class); // detach from hibernate without triggering lazy loads

		result = new TruckDTO() //
				.id(d.getId()) //
				.model(d.getModel()) //
				.horsepower(d.getHorsepower()) //
				.displacement(d.getDisplacement()) //
				.modifiedAt(dateToOffsetDateTimeMapper.apply(d.getModificationTimestamp())) //
				.fuelType(genericMapper.map(d.getFuelType(), FuelTypeDTO.class)) //
				.classification(genericMapper.map(d.getClassification(), ClassificationDTO.class)); //

		// Applications
		if (CollectionUtils.isEmpty(d.getRelTruckApplications()) == false) {
			List<ApplicationDTO> applicationDTOs = d.getRelTruckApplications().stream() //
					.map(RelTruckApplication::getApplication) //
					.filter(notNull) //
					.map(application -> genericMapper.map(application, ApplicationDTO.class)) //
					.collect(Collectors.toList());

			result.setApplications(applicationDTOs);
		}

		// Colors
		if (CollectionUtils.isEmpty(d.getRelTruckColors()) == false) {
			List<ColorDTO> colorDTOs = d.getRelTruckColors().stream() //
					.map(RelTruckColor::getColor) //
					.filter(notNull) //
					.map(color -> genericMapper.map(color, ColorDTO.class)) //
					.collect(Collectors.toList());

			result.setColors(colorDTOs);
		}

		return result;
	}

}
