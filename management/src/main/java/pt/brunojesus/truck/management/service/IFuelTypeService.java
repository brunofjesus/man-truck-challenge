package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.FuelType;

/**
 * The Interface IFuelTypeService.
 *
 * Service for {@link pt.brunojesus.truck.model.domain.FuelType}.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IFuelTypeRepository
 */
public interface IFuelTypeService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<FuelType> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the fuel type
	 */
	public FuelType findById(int id);
}
