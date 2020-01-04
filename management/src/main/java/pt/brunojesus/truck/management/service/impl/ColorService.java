package pt.brunojesus.truck.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.foundation.util.CollectionUtils;
import pt.brunojesus.truck.management.service.IColorService;
import pt.brunojesus.truck.model.domain.Color;
import pt.brunojesus.truck.persistence.repository.IColorRepository;

@Service
@AutoLogger
public class ColorService implements IColorService {

	private final IColorRepository colorRepository;

	@Autowired
	public ColorService(IColorRepository colorRepository) {
		super();
		this.colorRepository = colorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Color> findAllByIds(List<Integer> ids) {

		if (CollectionUtils.isEmpty(ids)) {
			throw new IllegalArgumentException("Ids are mandatory");
		}

		Iterable<Color> colors = colorRepository.findAllById(ids);

		return CollectionUtils.fromIterable(colors);
	}
}
