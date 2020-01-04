package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.Application;

public interface IApplicationService {

	/**
	 * Find all by ids.
	 *
	 * @param ids the ids
	 * @return the list
	 */
	List<Application> findAllByIds(List<Integer> ids);

}
