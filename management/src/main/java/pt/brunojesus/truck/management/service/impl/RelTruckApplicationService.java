package pt.brunojesus.truck.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.service.IRelTruckApplicationService;
import pt.brunojesus.truck.model.domain.RelTruckApplication;
import pt.brunojesus.truck.persistence.repository.IRelTruckApplicationRepository;

@Service
@AutoLogger
public class RelTruckApplicationService implements IRelTruckApplicationService {

	private final IRelTruckApplicationRepository relTruckApplicationRepository;

	@Autowired
	public RelTruckApplicationService(IRelTruckApplicationRepository relTruckApplicationRepository) {
		this.relTruckApplicationRepository = relTruckApplicationRepository;
	}

	@Override
	@Transactional
	public RelTruckApplication save(RelTruckApplication relTruckApplication) {
		return relTruckApplicationRepository.save(relTruckApplication);
	}

	@Override
	@Transactional
	public void delete(RelTruckApplication relTruckApplication) {
		relTruckApplicationRepository.delete(relTruckApplication);
	}
}
