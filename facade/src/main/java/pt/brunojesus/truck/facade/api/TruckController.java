package pt.brunojesus.truck.facade.api;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.TrucksApi;
import pt.brunojesus.truck.codegen.dto.TruckDTO;
import pt.brunojesus.truck.foundation.exception.NotFoundException;
import pt.brunojesus.truck.management.service.ITruckService;
import pt.brunojesus.truck.model.domain.Truck;

@RestController
public class TruckController implements TrucksApi {

	private final ITruckService truckService;
	private final Function<Truck, TruckDTO> truckToTruckDTOMapper;
	private final Function<TruckDTO, Truck> truckDTOToTruckMapper;

	@Autowired
	public TruckController( //
			ITruckService truckService, //
			@Qualifier("truckToTruckDTOMapper") Function<Truck, TruckDTO> truckToTruckDTOMapper, //
			@Qualifier("truckDTOToTruckMapper") Function<TruckDTO, Truck> truckDTOToTruckMapper) {

		super();

		this.truckService = truckService;
		this.truckToTruckDTOMapper = truckToTruckDTOMapper;
		this.truckDTOToTruckMapper = truckDTOToTruckMapper;
	}

	@Override
	public ResponseEntity<Void> createTruck(@Valid TruckDTO body) {
		Truck truck = truckDTOToTruckMapper.apply(body);

		truckService.save(truck);

		return ResponseEntity.created(URI.create("/trucks/" + truck.getId())).build();
	}

	@Override
	public ResponseEntity<Void> deleteTruck(Long truckId) {
		try {
			truckService.deleteById(truckId);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<TruckDTO> getTruckById(Long truckId) {
		Truck truck = truckService.getOne(truckId);

		if (truck == null) {
			return ResponseEntity.notFound().build();
		}

		TruckDTO result = truckToTruckDTOMapper.apply(truck);

		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<List<TruckDTO>> listTrucks() {
		List<Truck> trucks = truckService.findAll();

		List<TruckDTO> result = trucks.stream() //
				.map(truckToTruckDTOMapper::apply) //
				.collect(Collectors.toList());

		return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<Void> updateTruck(Long truckId, @Valid TruckDTO body) {
		body.setId(truckId);

		Truck truck = truckDTOToTruckMapper.apply(body);

		try {
			truckService.update(truck);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

}
