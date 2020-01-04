package pt.brunojesus.truck.persistence.repository;

import pt.brunojesus.truck.model.domain.RelTruckApplication;

/**
 * The Interface IRelTruckApplicationRepository.
 * 
 * Repository for {@link pt.brunojesus.truck.model.domain.RelTruckApplication}.
 * 
 * @see pt.brunojesus.truck.management.service.IRelTruckApplicationService
 */
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
