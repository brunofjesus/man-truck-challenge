package pt.brunojesus.truck.facade.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pt.brunojesus.truck.codegen.dto.TruckDTO;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.model.domain.Application;
import pt.brunojesus.truck.model.domain.Classification;
import pt.brunojesus.truck.model.domain.Color;
import pt.brunojesus.truck.model.domain.FuelType;
import pt.brunojesus.truck.model.domain.RelTruckApplication;
import pt.brunojesus.truck.model.domain.RelTruckApplicationId;
import pt.brunojesus.truck.model.domain.RelTruckColor;
import pt.brunojesus.truck.model.domain.RelTruckColorId;
import pt.brunojesus.truck.model.domain.Truck;

/**
 * The Class TruckDTOToTruckMapper.
 * 
 * Maps a {@link pt.brunojesus.truck.codegen.dto.TruckDTO} to a
 * {@link pt.brunojesus.truck.model.domain.Truck} object
 * 
 * @see pt.brunojesus.truck.codegen.dto.TruckDTO
 * @see pt.brunojesus.truck.model.domain.Truck
 * @see pt.brunojesus.truck.facade.mapper.IGenericMapper;
 * @see pt.brunojesus.truck.facade.mapper.OffsetDateTimeToTimestampMapper
 */
@Component("truckDTOToTruckMapper")
public class TruckDTOToTruckMapper implements Function<TruckDTO, Truck> {

	private final IGenericMapper genericMapper;
	private final Function<OffsetDateTime, Timestamp> offsetDateTimeToTimestampMapper;

	@Autowired
	public TruckDTOToTruckMapper(IGenericMapper genericMapper, //
			@Qualifier("offsetDateTimeToTimestampMapper") Function<OffsetDateTime, Timestamp> offsetDateTimeToTimestampMapper) {

		this.genericMapper = genericMapper;
		this.offsetDateTimeToTimestampMapper = offsetDateTimeToTimestampMapper;
	}

	@Override
	public Truck apply(TruckDTO truckDTO) {
		Truck result = Truck.builder() //
				.id(truckDTO.getId()) //
				.model(truckDTO.getModel()) //
				.horsepower(truckDTO.getHorsepower()) //
				.displacement(truckDTO.getDisplacement()) //
				.modificationTimestamp(offsetDateTimeToTimestampMapper.apply(truckDTO.getModifiedAt())) //
				.fuelType(genericMapper.map(truckDTO.getFuelType(), FuelType.class)) //
				.classification(genericMapper.map(truckDTO.getClassification(), Classification.class)).build();

		if (CollectionUtils.isEmpty(truckDTO.getApplications()) == false) {
			List<RelTruckApplication> relTruckApplications = truckDTO.getApplications().stream() //
					.map(dto -> genericMapper.map(dto, Application.class))
					.map(application -> RelTruckApplication.builder().application(application).truck(result)
							.id(new RelTruckApplicationId(result.getId(), application.getId())).build())
					.collect(Collectors.toList());

			result.setRelTruckApplications(relTruckApplications);
		}

		if (CollectionUtils.isEmpty(truckDTO.getColors()) == false) {
			List<RelTruckColor> relTruckColors = truckDTO.getColors().stream() //
					.map(dto -> genericMapper.map(dto, Color.class))
					.map(color -> RelTruckColor.builder().color(color).truck(result)
							.id(new RelTruckColorId(result.getId(), color.getId())).build())
					.collect(Collectors.toList());

			result.setRelTruckColors(relTruckColors);
		}

		return result;
	}
}
