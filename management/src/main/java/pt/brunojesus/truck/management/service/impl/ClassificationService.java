package pt.brunojesus.truck.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.service.IClassificationService;
import pt.brunojesus.truck.model.domain.Classification;
import pt.brunojesus.truck.persistence.repository.IClassificationRepository;

@Service
@AutoLogger
public class ClassificationService implements IClassificationService {

	private final IClassificationRepository classificationRepository;

	@Autowired
	public ClassificationService(IClassificationRepository classificationRepository) {
		super();
		this.classificationRepository = classificationRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Classification findById(int id) {
		return classificationRepository.findById(id).get();
	}
}
