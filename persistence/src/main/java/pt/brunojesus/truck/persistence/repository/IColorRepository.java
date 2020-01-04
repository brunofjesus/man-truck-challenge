package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.Color;

/**
 * The Interface IColorRepository.
 * 
 * Repository for {@link pt.brunojesus.truck.model.domain.Color}.
 * 
 * @see pt.brunojesus.truck.management.service.IColorService
 */
@Repository
public interface IColorRepository extends CrudRepository<Color, Integer> {

}
