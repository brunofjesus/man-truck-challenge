package pt.brunojesus.truck.facade.api;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import pt.brunojesus.truck.codegen.api.TrucksApi;
import pt.brunojesus.truck.codegen.dto.TruckDTO;
import pt.brunojesus.truck.facade.mapper.TruckDTOToTruckMapper;
import pt.brunojesus.truck.model.domain.Truck;

@RestController
public class TruckController implements TrucksApi {

	Function<Truck, TruckDTO> truckToTruckDTOMapper;

	Function<TruckDTO, Truck> truckDTOToTruckMapper;

	@Autowired
	public TruckController( //
			@Qualifier("truckToTruckDTOMapper") Function<Truck, TruckDTO> truckToTruckDTOMapper, //
			@Qualifier("truckDTOToTruckMapper") Function<TruckDTO, Truck> truckDTOToTruckMapper) {
		
		super();
		
		this.truckToTruckDTOMapper = truckToTruckDTOMapper;
		this.truckDTOToTruckMapper = truckDTOToTruckMapper;
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		// TODO Auto-generated method stub
		return TrucksApi.super.getRequest();
	}

	@Override
	public Optional<String> getAcceptHeader() {
		// TODO Auto-generated method stub
		return TrucksApi.super.getAcceptHeader();
	}

	@Override
	public ResponseEntity<Void> createTruck() {
		// TODO Auto-generated method stub
		return TrucksApi.super.createTruck();
	}

	@Override
	public ResponseEntity<Void> deleteTruck(Long truckId) {
		// TODO Auto-generated method stub
		return TrucksApi.super.deleteTruck(truckId);
	}

	@Override
	public ResponseEntity<TruckDTO> getTruckById(Long truckId) {
		// TODO Auto-generated method stub
		return TrucksApi.super.getTruckById(truckId);
	}

	@Override
	public ResponseEntity<List<TruckDTO>> listTrucks() {
		// TODO Auto-generated method stub
		return TrucksApi.super.listTrucks();
	}

	@Override
	public ResponseEntity<Void> updateTruck(Long truckId) {
		// TODO Auto-generated method stub
		return TrucksApi.super.updateTruck(truckId);
	}

}
