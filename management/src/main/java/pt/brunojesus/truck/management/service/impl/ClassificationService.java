package pt.brunojesus.truck.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Classification;
import pt.brunojesus.truck.persistence.repository.IClassificationRepository;

/**
 * The Class ClassificationService.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IClassificationRepository
 * @see pt.brunojesus.truck.management.foundation.GenericService
 */
@Service("classificationService")
@AutoLogger
public class ClassificationService extends GenericService<Classification, Integer> {

	@Autowired
	public ClassificationService(IClassificationRepository classificationRepository) {
		super(classificationRepository);
	}
}
