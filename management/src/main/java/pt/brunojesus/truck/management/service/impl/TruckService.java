package pt.brunojesus.truck.management.service.impl;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.foundation.exception.ResourceNotFoundException;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.management.service.IRelTruckApplicationService;
import pt.brunojesus.truck.management.service.IRelTruckColorService;
import pt.brunojesus.truck.management.service.ITruckService;
import pt.brunojesus.truck.model.domain.RelTruckApplication;
import pt.brunojesus.truck.model.domain.RelTruckApplicationId;
import pt.brunojesus.truck.model.domain.RelTruckColor;
import pt.brunojesus.truck.model.domain.RelTruckColorId;
import pt.brunojesus.truck.model.domain.Truck;
import pt.brunojesus.truck.persistence.repository.ITruckRepository;

@Service
@AutoLogger
public class TruckService implements ITruckService {

	private final ITruckRepository truckRepository;
	private final IRelTruckApplicationService relTruckApplicationService;
	private final IRelTruckColorService relTruckColorService;

	@Autowired
	public TruckService(ITruckRepository truckRepository, IRelTruckApplicationService relTruckApplicationService,
			IRelTruckColorService relTruckColorService) {

		this.truckRepository = truckRepository;
		this.relTruckApplicationService = relTruckApplicationService;
		this.relTruckColorService = relTruckColorService;
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

		Truck dbTruck = getOne(updatedTruck.getId());

		if (dbTruck == null) {
			throw new ResourceNotFoundException("Cannot find truck");
		}

		// Update many to many relations
		CollectionUtils.itemsNotInList(dbTruck.getRelTruckApplications(), updatedTruck.getRelTruckApplications())
				.stream().forEach(relTruckApplicationService::delete);

		CollectionUtils.itemsNotInList(updatedTruck.getRelTruckApplications(), dbTruck.getRelTruckApplications())
				.stream().map(r -> RelTruckApplication.builder().id(r.getId()).build())
				.forEach(relTruckApplicationService::save);

		CollectionUtils.itemsNotInList(dbTruck.getRelTruckColors(), updatedTruck.getRelTruckColors()).stream()
				.forEach(relTruckColorService::delete);

		CollectionUtils.itemsNotInList(updatedTruck.getRelTruckColors(), dbTruck.getRelTruckColors()).stream()
				.map(r -> RelTruckColor.builder().id(r.getId()).build()).forEach(relTruckColorService::save);

		return truckRepository.update(updatedTruck);
	}

	@Override
	@Transactional
	public Truck save(Truck truck) {

		if (truck.getId() > 0) {
			throw new ValidationException("Cannot specify id to insert");
		}

		List<RelTruckApplication> relTruckApplications = truck.getRelTruckApplications();
		List<RelTruckColor> relTruckColors = truck.getRelTruckColors();

		Truck result = truckRepository.save(truck);

		if (CollectionUtils.isEmpty(relTruckApplications) == false) {
			relTruckApplications.stream()
					.map(r -> RelTruckApplication.builder()
							.id(new RelTruckApplicationId(result.getId(), r.getId().getApplicationId())).build())
					.forEach(relTruckApplicationService::save);
		}

		if (CollectionUtils.isEmpty(relTruckColors) == false) {
			relTruckColors
					.stream().map(r -> RelTruckColor.builder()
							.id(new RelTruckColorId(result.getId(), r.getId().getColorId())).build())
					.forEach(relTruckColorService::save);
		}

		return result;
	}

	@Override
	@Transactional
	public void deleteById(Long truckId) throws ResourceNotFoundException {

		Truck dbTruck = getOne(truckId);

		if (dbTruck == null) {
			throw new ResourceNotFoundException("Cannot find truck");
		}

		if (CollectionUtils.isEmpty(dbTruck.getRelTruckApplications()) == false) {
			dbTruck.getRelTruckApplications().stream().forEach(relTruckApplicationService::delete);
		}

		if (CollectionUtils.isEmpty(dbTruck.getRelTruckColors()) == false) {
			dbTruck.getRelTruckColors().stream().forEach(relTruckColorService::delete);
		}

		truckRepository.delete(dbTruck);
	}
}
