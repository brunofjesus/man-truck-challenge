package pt.brunojesus.truck.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pt.brunojesus.truck.management.service.ITruckService;
import pt.brunojesus.truck.model.domain.Truck;
import pt.brunojesus.truck.persistence.repository.ITruckRepository;

@Service
public class TruckService implements ITruckService {
	
	private ITruckRepository truckRepository;
	
	public TruckService(@Qualifier("truckRepository") ITruckRepository truckRepository) {
		this.truckRepository = truckRepository;
	}

	@Override
	public List<Truck> findAll() {
		return truckRepository.findAll(Sort.by(Direction.ASC, Truck.PROPERTY_MODEL));
	}

}
