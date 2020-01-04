package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.Color;

/**
 * The Interface IColorService.
 * 
 * Service for {@link pt.brunojesus.truck.model.domain.Color}.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IColorRepository
 */
public interface IColorService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Color> findAll();

	/**
	 * Find all by ids.
	 *
	 * @param ids the ids
	 * @return the list
	 */
	public List<Color> findAllByIds(List<Integer> ids);

}
