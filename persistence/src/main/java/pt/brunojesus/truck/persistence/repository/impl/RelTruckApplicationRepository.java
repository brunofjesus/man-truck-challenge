package pt.brunojesus.truck.persistence.repository.impl;

import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.model.domain.RelTruckApplication;
import pt.brunojesus.truck.persistence.foundation.BaseRepository;
import pt.brunojesus.truck.persistence.repository.IRelTruckApplicationRepository;

@Repository
public class RelTruckApplicationRepository extends BaseRepository implements IRelTruckApplicationRepository {

	@Override
	public RelTruckApplication save(RelTruckApplication relTruckApplication) {
		return super.save(relTruckApplication);
	}

	@Override
	public void delete(RelTruckApplication relTruckApplication) {
		super.delete(relTruckApplication);
	}
}
