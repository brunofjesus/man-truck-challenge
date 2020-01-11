package pt.brunojesus.truck.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Application;
import pt.brunojesus.truck.persistence.repository.IApplicationRepository;

/**
 * The Class ApplicationService.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IApplicationRepository
 * @see pt.brunojesus.truck.management.foundation.GenericService
 */
@Service("applicationService")
@AutoLogger
public class ApplicationService extends GenericService<Application, Integer> {

	@Autowired
	public ApplicationService(IApplicationRepository applicationRepository) {
		super(applicationRepository);
	}
}
