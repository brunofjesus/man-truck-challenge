package pt.brunojesus.truck.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.brunojesus.truck.management.service.IRelTruckColorService;
import pt.brunojesus.truck.model.domain.RelTruckColor;
import pt.brunojesus.truck.persistence.repository.IRelTruckColorRepository;

@Service
public class RelTruckColorService implements IRelTruckColorService {

	private final IRelTruckColorRepository relTruckColorRepository;
	
	@Autowired
	public RelTruckColorService(IRelTruckColorRepository relTruckColorRepository) {
		this.relTruckColorRepository = relTruckColorRepository;
	}

	@Override
	public RelTruckColor save(RelTruckColor relTruckColor) {
		return relTruckColorRepository.save(relTruckColor);
	}

	@Override
	public void delete(RelTruckColor relTruckColor) {
		relTruckColorRepository.delete(relTruckColor);
	}

}
