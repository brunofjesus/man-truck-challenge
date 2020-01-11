package pt.brunojesus.truck.management.service.impl;

import java.util.List;
import java.util.function.Consumer;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.foundation.exception.ResourceNotFoundException;
import pt.brunojesus.truck.management.service.ITruckService;
import pt.brunojesus.truck.model.domain.Truck;
import pt.brunojesus.truck.persistence.repository.ITruckRepository;

/**
 * The Class TruckService.
 * 
 * @see pt.brunojesus.truck.persistence.repository.ITruckRepository
 * @see pt.brunojesus.truck.management.validator.TruckValidator
 */
@Service
@AutoLogger
public class TruckService implements ITruckService {

	private final ITruckRepository truckRepository;
	private final Consumer<Truck> truckValidator;

	@Autowired
	public TruckService(ITruckRepository truckRepository, @Qualifier("truckValidator") Consumer<Truck> truckValidator) {

		this.truckRepository = truckRepository;
		this.truckValidator = truckValidator;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Truck> findAll() {
		return truckRepository.findAll(true, true, true, true, Order.asc(Truck.PROPERTY_MODEL));
	}

	@Override
	@Transactional(readOnly = true)
	public Truck getOne(long id) {
		return truckRepository.getOne(id, true, true, true, true);
	}

	@Override
	@Transactional
	public Truck update(@NonNull Truck updatedTruck) throws ResourceNotFoundException {
		truckValidator.accept(updatedTruck);

		Truck dbTruck = getOne(updatedTruck.getId());

		if (dbTruck == null) {
			throw new ResourceNotFoundException("Cannot find truck");
		}

		return truckRepository.update(updatedTruck);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Truck save(Truck truck) {
		truckValidator.accept(truck);

		if (truck.getId() > 0) {
			throw new ValidationException("Cannot specify id to insert");
		}

		Truck result = truckRepository.save(truck);

		return result;
	}

	@Override
	@Transactional
	public void deleteById(Long truckId) throws ResourceNotFoundException {

		Truck dbTruck = getOne(truckId);

		if (dbTruck == null) {
			throw new ResourceNotFoundException("Cannot find truck");
		}

		truckRepository.delete(dbTruck);
	}
}
