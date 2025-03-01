package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.foundation.exception.ResourceNotFoundException;
import pt.brunojesus.truck.model.domain.Truck;

/**
 * The Interface ITruckService.
 * 
 * Service for {@link pt.brunojesus.truck.model.domain.Truck}.
 * 
 * @see pt.brunojesus.truck.persistence.repository.ITruckRepository
 */
public interface ITruckService {

	/**
	 * Find all trucks.
	 *
	 * @return the list
	 */
	public List<Truck> findAll();

	/**
	 * Gets one truck.
	 *
	 * @param id the id
	 * @return the one
	 */
	public Truck getOne(long id);

	/**
	 * Update.
	 *
	 * @param updatedTruck the updated truck
	 * @return the truck
	 * @throws ResourceNotFoundException
	 */
	public Truck update(Truck updatedTruck) throws ResourceNotFoundException;

	/**
	 * Save.
	 *
	 * @param truck the truck
	 * @return the truck
	 */
	public Truck save(Truck truck);

	/**
	 * Delete truck by id.
	 *
	 * @param truckId the truck id
	 * @throws ResourceNotFoundException
	 */
	public void deleteById(Long truckId) throws ResourceNotFoundException;
}
