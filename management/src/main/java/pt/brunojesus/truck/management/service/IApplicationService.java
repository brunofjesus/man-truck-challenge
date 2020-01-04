package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.Application;

/**
 * The Interface IApplicationService.
 * 
 * Service for {@link pt.brunojesus.truck.model.domain.Application}.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IApplicationRepository
 */
public interface IApplicationService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Application> findAll();

	/**
	 * Find all by ids.
	 *
	 * @param ids the ids
	 * @return the list
	 */
	public List<Application> findAllByIds(List<Integer> ids);
}
