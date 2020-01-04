package pt.brunojesus.truck.persistence.repository.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.model.domain.RelTruckApplication;
import pt.brunojesus.truck.model.domain.RelTruckColor;
import pt.brunojesus.truck.model.domain.Truck;
import pt.brunojesus.truck.persistence.foundation.BaseRepository;
import pt.brunojesus.truck.persistence.repository.ITruckRepository;

@Repository
@AutoLogger
public class TruckRepository extends BaseRepository implements ITruckRepository {

	@Override
	public List<Truck> findAll(boolean fetchFuelType, boolean fetchClassification, boolean fetchRelTruckApplications,
			boolean fetchRelTruckColors, Order order) {

		Query<Truck> query = generateTruckFindQuery(null, fetchFuelType, fetchClassification, fetchRelTruckApplications,
				fetchRelTruckColors, order);

		return query.list();
	}

	@Override
	public Truck getOne(long id, boolean fetchFuelType, boolean fetchClassification, boolean fetchRelTruckApplications,
			boolean fetchRelTruckColors) {

		Query<Truck> query = generateTruckFindQuery(id, fetchFuelType, fetchClassification, fetchRelTruckApplications,
				fetchRelTruckColors, null);

		return query.uniqueResult();
	}

	@Override
	public Truck update(Truck updatedTruck) {
		return super.update(updatedTruck);
	}

	@Override
	public Truck save(Truck truck) {
		return super.save(truck);
	}

	@Override
	public void delete(Truck truck) {
		super.delete(truck);
	}

	/*********************
	 * PRIVATE METHODS
	 *********************/

	/**
	 * Generate truck find query.
	 * 
	 * Generates a query for finding trucks and does the proper fetches of OneToMany
	 * relations in order to avoid cartesian joins
	 *
	 * @param id                        the id
	 * @param fetchFuelType             the fetch fuel type
	 * @param fetchClassification       the fetch classification
	 * @param fetchRelTruckApplications the fetch rel truck applications
	 * @param fetchRelTruckColors       the fetch rel truck colors
	 * @param order                     the order
	 * @return the query
	 */
	private Query<Truck> generateTruckFindQuery(Long id, boolean fetchFuelType, boolean fetchClassification,
			boolean fetchRelTruckApplications, boolean fetchRelTruckColors, Order order) {

		StringBuilder hql = new StringBuilder();

		hql.append(" FROM Truck as t ");

		if (fetchFuelType) {
			hql.append(" LEFT JOIN FETCH t." + Truck.PROPERTY_FUEL_TYPE + " ");
		}

		if (fetchClassification) {
			hql.append(" LEFT JOIN FETCH t." + Truck.PROPERTY_CLASSIFICATION + " ");
		}

		hql.append(" WHERE 1=1 ");

		if (id != null) {
			hql.append(" AND t." + Truck.PROPERTY_ID + " = :id ");
		}

		if (order != null) {
			hql.append(" ORDER BY " + order.getProperty() + " " + order.getDirection().toString());
		}

		Query<Truck> query = createQuery(hql, Truck.class);

		if (id != null) {
			query.setParameter("id", id);
		}

		// Execute in separate query to avoid cartesian joins
		if (fetchRelTruckApplications) {
			fetchRelTruckApplications(true);
		}

		if (fetchRelTruckColors) {
			fetchRelTruckColors(true);
		}

		return query;
	}

	/**
	 * Fetch rel truck applications.
	 * 
	 * This method only fetches the RelTruckApplications from Truck. It is used to
	 * avoid cartesian joins
	 *
	 * @param fetchApplications the fetch applications
	 */
	private void fetchRelTruckApplications(boolean fetchApplications) {
		StringBuilder hql = new StringBuilder();

		hql.append(" FROM Truck as t ");

		hql.append(" LEFT JOIN FETCH t." + Truck.PROPERTY_TRUCK_REL_TRUCK_APPLICATIONS + " as rta");

		if (fetchApplications) {
			hql.append(" INNER JOIN FETCH rta." + RelTruckApplication.PROPERTY_APPLICATION + " ");
		}

		Query<Truck> query = createQuery(hql, Truck.class);

		query.list();
	}

	/**
	 * Fetch rel truck colors.
	 * 
	 * This method only fetches the RelTruckColors from Truck. It is used to avoid
	 * cartesian joins
	 *
	 * 
	 * @param fetchColors the fetch colors
	 */
	private void fetchRelTruckColors(boolean fetchColors) {
		StringBuilder hql = new StringBuilder();

		hql.append(" FROM Truck as t ");

		hql.append(" LEFT JOIN FETCH t." + Truck.PROPERTY_TRUCK_REL_TRUCK_COLORS + " as rtc");

		if (fetchColors) {
			hql.append(" INNER JOIN FETCH rtc." + RelTruckColor.PROPERTY_COLOR + " ");
		}

		Query<Truck> query = createQuery(hql, Truck.class);

		query.list();
	}
}
