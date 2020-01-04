package pt.brunojesus.truck.management.service;

import pt.brunojesus.truck.model.domain.Classification;

public interface IClassificationService {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the classification
	 */
	public Classification findById(int id);

}
