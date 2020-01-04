package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.Classification;

@Repository
public interface IClassificationRepository extends CrudRepository<Classification, Integer> {

}
