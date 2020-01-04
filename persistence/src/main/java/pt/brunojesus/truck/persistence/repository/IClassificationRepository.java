package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.Classification;

/**
 * The Interface IClassificationRepository.
 * 
 * Repository for {@link pt.brunojesus.truck.model.domain.Classification}.
 * 
 * @see pt.brunojesus.truck.management.service.IClassificationService
 */
@Repository
public interface IClassificationRepository extends CrudRepository<Classification, Integer> {

}
