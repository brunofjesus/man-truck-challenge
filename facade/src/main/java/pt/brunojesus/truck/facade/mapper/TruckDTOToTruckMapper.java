package pt.brunojesus.truck.facade.mapper;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.brunojesus.truck.codegen.dto.TruckDTO;
import pt.brunojesus.truck.model.domain.Truck;

@Component("truckDTOToTruckMapper")
public class TruckDTOToTruckMapper implements Function<TruckDTO, Truck> {

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Truck apply(TruckDTO truckDTO) {
		Truck truck = modelMapper.map(truckDTO, Truck.class);
	    return truck;
	}

}
