package pt.brunojesus.truck.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Color;
import pt.brunojesus.truck.persistence.repository.IColorRepository;

/**
 * The Class ColorService.
 * 
 * @see pt.brunojesus.truck.persistence.repository.IColorRepository
 * @see pt.brunojesus.truck.management.foundation.GenericService
 */
@Service("colorService")
@AutoLogger
public class ColorService extends GenericService<Color, Integer> {

	@Autowired
	public ColorService(IColorRepository colorRepository) {
		super(colorRepository);
	}
}
