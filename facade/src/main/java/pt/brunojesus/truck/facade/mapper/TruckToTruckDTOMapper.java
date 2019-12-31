package pt.brunojesus.truck.facade.mapper;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pt.brunojesus.truck.codegen.dto.TruckDTO;
import pt.brunojesus.truck.model.domain.Truck;

@Component("truckToTruckDTOMapper")
public class TruckToTruckDTOMapper implements Function<Truck, TruckDTO> {

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public TruckDTO apply(Truck truck) {
		TruckDTO truckDTO = modelMapper.map(truck, TruckDTO.class);
	    return truckDTO;
	}

}
