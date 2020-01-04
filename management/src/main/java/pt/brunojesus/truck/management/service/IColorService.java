package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.Color;

public interface IColorService {

	/**
	 * Find all by ids.
	 *
	 * @param ids the ids
	 * @return the list
	 */
	List<Color> findAllByIds(List<Integer> ids);

}
