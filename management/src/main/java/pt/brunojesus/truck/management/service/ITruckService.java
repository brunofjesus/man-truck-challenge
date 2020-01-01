package pt.brunojesus.truck.management.service;

import java.util.List;

import pt.brunojesus.truck.model.domain.Truck;

public interface ITruckService {

	public List<Truck> findAll();
}
