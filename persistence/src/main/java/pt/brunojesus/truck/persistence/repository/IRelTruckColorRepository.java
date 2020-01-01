package pt.brunojesus.truck.persistence.repository;

import pt.brunojesus.truck.model.domain.RelTruckColor;

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
