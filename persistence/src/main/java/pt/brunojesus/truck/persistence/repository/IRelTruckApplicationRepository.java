package pt.brunojesus.truck.persistence.repository;

import pt.brunojesus.truck.model.domain.RelTruckApplication;

public interface IRelTruckApplicationRepository {

	/**
	 * Save.
	 *
	 * @param relTruckApplication the rel truck application
	 * @return the rel truck application
	 */
	public RelTruckApplication save(RelTruckApplication relTruckApplication);
	
	/**
	 * Delete.
	 *
	 * @param relTruckApplication the rel truck application
	 */
	public void delete(RelTruckApplication relTruckApplication);
}
