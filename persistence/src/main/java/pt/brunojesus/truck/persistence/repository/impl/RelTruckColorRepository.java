package pt.brunojesus.truck.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.model.domain.RelTruckColor;
import pt.brunojesus.truck.persistence.foundation.BaseRepository;
import pt.brunojesus.truck.persistence.repository.IRelTruckColorRepository;

@Repository
@AutoLogger
public class RelTruckColorRepository extends BaseRepository implements IRelTruckColorRepository {

	@Override
	public RelTruckColor save(RelTruckColor relTruckColor) {
		return super.save(relTruckColor);
	}

	@Override
	public void delete(RelTruckColor relTruckColor) {
		super.delete(relTruckColor);
	}

}
