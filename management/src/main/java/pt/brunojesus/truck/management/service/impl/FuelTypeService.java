package pt.brunojesus.truck.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.management.service.IFuelTypeService;
import pt.brunojesus.truck.model.domain.FuelType;
import pt.brunojesus.truck.persistence.repository.IFuelTypeRepository;

/**
 * The Class FuelTypeService.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IFuelTypeRepository
 */
@Service
@AutoLogger
public class FuelTypeService implements IFuelTypeService {

	private final IFuelTypeRepository fuelTypeRepository;

	@Autowired
	public FuelTypeService(IFuelTypeRepository fuelTypeRepository) {
		super();
		this.fuelTypeRepository = fuelTypeRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FuelType> findAll() {
		return CollectionUtils.fromIterable(fuelTypeRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public FuelType findById(int id) {
		return fuelTypeRepository.findById(id).get();
	}
}
