package pt.brunojesus.truck.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Sort.Order;

import pt.brunojesus.truck.model.domain.Truck;

public interface ITruckRepository {

	/**
	 * Find all.
	 *
	 * @param fetchFuelType             the fetch fuel type
	 * @param fetchClassification       the fetch classification
	 * @param fetchRelTruckApplications the fetch rel truck applications
	 * @param fetchRelTruckColors       the fetch rel truck colors
	 * @param order                     the order
	 * @return the list of trucks
	 */
	public List<Truck> findAll(boolean fetchFuelType, boolean fetchClassification, boolean fetchRelTruckApplications,
			boolean fetchRelTruckColors, Order order);

	/**
	 * Gets one truck.
	 *
	 * @param id                        the id
	 * @param fetchFuelType             the fetch fuel type
	 * @param fetchClassification       the fetch classification
	 * @param fetchRelTruckApplications the fetch rel truck applications
	 * @param fetchRelTruckColors       the fetch rel truck colors
	 * @return the truck
	 */
	public Truck getOne(long id, boolean fetchFuelType, boolean fetchClassification, boolean fetchRelTruckApplications,
			boolean fetchRelTruckColors);

	/**
	 * Update.
	 *
	 * @param updatedTruck the updated truck
	 * @return the truck
	 */
	public Truck update(Truck updatedTruck);

	/**
	 * Save.
	 *
	 * @param truck the truck
	 * @return the truck
	 */
	public Truck save(Truck truck);

	/**
	 * Delete.
	 *
	 * @param truck the truck
	 */
	public void delete(Truck truck);
}
