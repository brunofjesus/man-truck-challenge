package pt.brunojesus.truck.management.foundation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.util.CollectionUtils;

/**
 * The Class GenericReadService.
 * 
 * This class provides a simple service for simple instances wich rely on
 * CrudRepositories
 *
 * @param <T>  the model type
 * @param <ID> the model id type
 * 
 * @see org.springframework.data.repository.CrudRepository
 */
public abstract class GenericService<T, ID> {

	private final CrudRepository<T, ID> crudRepository;

	/**
	 * Instantiates a new generic read service.
	 *
	 * @param crudRepository the crud repository
	 */
	public GenericService(CrudRepository<T, ID> crudRepository) {
		super();
		this.crudRepository = crudRepository;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 */
	@Transactional(readOnly = true)
	public T findById(ID id) {
		return crudRepository.findById(id).get();
	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return CollectionUtils.fromIterable(crudRepository.findAll());
	}

	/**
	 * Find all by ids.
	 *
	 * @param ids the ids
	 * @return the list
	 */
	@Transactional(readOnly = true)
	public List<T> findAllByIds(List<ID> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new IllegalArgumentException("Ids are mandatory");
		}

		Iterable<T> items = crudRepository.findAllById(ids);

		return CollectionUtils.fromIterable(items);
	}

}
