package pt.brunojesus.truck.management.service;

import pt.brunojesus.truck.model.domain.RelTruckColor;

public interface IRelTruckColorService {

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
