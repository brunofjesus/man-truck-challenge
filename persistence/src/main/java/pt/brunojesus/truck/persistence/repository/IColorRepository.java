package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.Color;

@Repository
public interface IColorRepository extends CrudRepository<Color, Integer> {

}
