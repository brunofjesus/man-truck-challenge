package pt.brunojesus.truck.persistence.foundation;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class BaseRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	/**
	 * Creates the query.
	 *
	 * @param <T>   the generic type
	 * @param query the query
	 * @param clazz the clazz
	 * @return the query
	 */
	protected <T> Query<T> createQuery(String query, Class<T> clazz) {
		Query<T> result = null;
		if (query != null && clazz != null) {
			result = getSession().createQuery(query, clazz);
		}
		return result;
	}

	/**
	 * Creates the query.
	 *
	 * @param <T>   the generic type
	 * @param query the query
	 * @param clazz the clazz
	 * @return the query
	 */
	protected <T> Query<T> createQuery(StringBuilder query, Class<T> clazz) {
		Query<T> result = null;
		if (query != null && clazz != null) {
			result = createQuery(query.toString(), clazz);
		}
		return result;
	}

	/**
	 * Find all.
	 *
	 * @param <T>   the generic type
	 * @param clazz the clazz
	 * @return the list
	 */
	protected <T> List<T> findAll(Class<T> clazz) {
		List<T> list = null;

		if (clazz != null) {
			list = createQuery("from " + clazz.getName(), clazz).list();
		}

		return list;
	}

	/**
	 * Gets the one.
	 *
	 * @param <T>   the generic type
	 * @param id    the id
	 * @param clazz the clazz
	 * @return the one
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getOne(Serializable id, Class<T> clazz) {
		if (id == null || clazz == null) {
			return null;
		}

		Object object = getSession().byId(clazz).load(id);
		return (T) object;
	}

	/**
	 * Save.
	 *
	 * @param <T>    the generic type
	 * @param object the object
	 * @return the t
	 */
	protected <T> T save(T object) {
		if (object != null) {
			getSession().save(object);
		}
		return object;
	}

	/**
	 * Update.
	 *
	 * @param <T>    the generic type
	 * @param object the object
	 * @return the t
	 */
	protected <T> T update(T object) {
		if (object != null) {
			getSession().merge(object);
		}
		return object;
	}

	/**
	 * Delete.
	 *
	 * @param <T>    the generic type
	 * @param object the object
	 * @return true, if successful
	 */
	protected <T> boolean delete(T object) {
		if (object != null) {
			getSession().delete(object);
			return true;
		}
		return false;
	}

	/**
	 * Delete by id.
	 *
	 * @param <T>   the generic type
	 * @param id    the id
	 * @param clazz the clazz
	 * @return true, if successful
	 */
	protected <T> boolean deleteById(Serializable id, Class<T> clazz) {
		if (id == null || clazz == null) {
			return false;
		}

		T object = getOne(id, clazz);
		return object != null ? delete(object) : false;
	}

	/**
	 * Detach.
	 *
	 * @param object the object
	 */
	protected void detach(Object object) {
		if (object != null) {
			entityManager.detach(object);
		}
	}
}
