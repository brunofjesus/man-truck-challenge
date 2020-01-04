package pt.brunojesus.truck.persistence.repository;

import pt.brunojesus.truck.model.domain.RelTruckColor;

/**
 * The Interface IRelTruckColorRepository.
 * 
 * Repository for {@link pt.brunojesus.truck.model.domain.RelTruckColor}.
 * 
 * @see pt.brunojesus.truck.management.service.IRelTruckColorService
 */
public interface IRelTruckColorRepository {

	/**
	 * Save.
	 *
	 * @param relTruckColor the rel truck color
	 * @return the rel truck color
	 */
	public RelTruckColor save(RelTruckColor relTruckColor);

	/**
	 * Delete.
	 *
	 * @param relTruckColor the rel truck color
	 */
	public void delete(RelTruckColor relTruckColor);
}
