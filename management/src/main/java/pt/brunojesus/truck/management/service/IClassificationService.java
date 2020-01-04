package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.Classification;

/**
 * The Interface IClassificationService.
 * 
 * Service for {@link pt.brunojesus.truck.model.domain.Classification}.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IClassificationRepository
 */
public interface IClassificationService {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<Classification> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the classification
	 */
	public Classification findById(int id);

}
