package pt.brunojesus.truck.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.management.service.IApplicationService;
import pt.brunojesus.truck.model.domain.Application;
import pt.brunojesus.truck.persistence.repository.IApplicationRepository;

/**
 * The Class ApplicationService.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IApplicationRepository
 */
@Service
@AutoLogger
public class ApplicationService implements IApplicationService {

	private final IApplicationRepository applicationRepository;

	@Autowired
	public ApplicationService(IApplicationRepository applicationRepository) {
		super();
		this.applicationRepository = applicationRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Application> findAll() {
		return CollectionUtils.fromIterable(applicationRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Application> findAllByIds(List<Integer> ids) {

		if (CollectionUtils.isEmpty(ids)) {
			throw new IllegalArgumentException("Ids are mandatory");
		}

		Iterable<Application> applications = applicationRepository.findAllById(ids);

		return CollectionUtils.fromIterable(applications);
	}
}
