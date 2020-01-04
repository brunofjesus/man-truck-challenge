package pt.brunojesus.truck.management.service;

import pt.brunojesus.truck.model.domain.FuelType;

public interface IFuelTypeService {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the fuel type
	 */
	public FuelType findById(int id);

}
