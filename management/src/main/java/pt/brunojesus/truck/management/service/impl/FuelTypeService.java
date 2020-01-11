package pt.brunojesus.truck.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.FuelType;
import pt.brunojesus.truck.persistence.repository.IFuelTypeRepository;

/**
 * The Class FuelTypeService.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IFuelTypeRepository
 * @see pt.brunojesus.truck.management.foundation.GenericService
 */
@Service("fuelTypeService")
@AutoLogger
public class FuelTypeService extends GenericService<FuelType, Integer> {

	@Autowired
	public FuelTypeService(IFuelTypeRepository fuelTypeRepository) {
		super(fuelTypeRepository);
	}
}
