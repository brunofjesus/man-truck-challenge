package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.FuelType;

@Repository
public interface IFuelTypeRepository extends CrudRepository<FuelType, Integer> {

}
