package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.FuelType;

/**
 * The Interface IFuelTypeRepository.
 * 
 * Repository for {@link pt.brunojesus.truck.model.domain.FuelType}.
 * 
 * @see pt.brunojesus.truck.management.service.IFuelTypeService
 */
@Repository
public interface IFuelTypeRepository extends CrudRepository<FuelType, Integer> {

}
