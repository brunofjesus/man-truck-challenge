package pt.brunojesus.truck.management.service;

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
	 * Find by id.
	 *
	 * @param id the id
	 * @return the fuel type
	 */
	public FuelType findById(int id);

}
