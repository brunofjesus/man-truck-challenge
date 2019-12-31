package pt.brunojesus.truck.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.Truck;

@Repository
public interface ITruckRepository extends JpaRepository<Truck, Long> {

}
