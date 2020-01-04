package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.Application;

/**
 * The Interface IApplicationRepository.
 * 
 * Repository for {@link pt.brunojesus.truck.model.domain.Application}.
 * 
 * @see pt.brunojesus.truck.management.service.IApplicationService
 */
@Repository
public interface IApplicationRepository extends CrudRepository<Application, Integer> {

}
