package pt.brunojesus.truck.management.service;

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
	 * Find by id.
	 *
	 * @param id the id
	 * @return the classification
	 */
	public Classification findById(int id);

}
