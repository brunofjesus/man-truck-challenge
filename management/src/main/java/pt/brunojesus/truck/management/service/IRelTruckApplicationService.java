package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.RelTruckApplication;

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
