package pt.brunojesus.truck.management.service;

import pt.brunojesus.truck.model.domain.RelTruckApplication;

/**
 * The Interface IRelTruckApplicationService.
 * 
 * Service for {@link pt.brunojesus.truck.model.domain.RelTruckApplication}.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IRelTruckApplicationRepository
 */
public interface IRelTruckApplicationService {

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
