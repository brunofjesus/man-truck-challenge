package pt.brunojesus.truck.foundation.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The Class CollectionUtils.
 * 
 * This class extends the {@link org.springframework.util.CollectionUtils} and
 * adds some functionality.
 * 
 * @see org.springframework.util.CollectionUtils
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

	/**
	 * Items not in collection.
	 * 
	 * Returns the items that are in the itemsCollection but not in
	 * collectionToCompareAgainst
	 *
	 * @param <T>                        the generic type
	 * @param itemsCollection            the items collection
	 * @param collectionToCompareAgainst the collection to compare against
	 * @return the list
	 */
	public static <T> List<T> itemsNotInCollection(Collection<T> itemsCollection,
			Collection<T> collectionToCompareAgainst) {

		Collection<T> result = null;

		if (isEmpty(itemsCollection)) {
			result = Collections.emptyList();
		} else if (isEmpty(collectionToCompareAgainst)) {
			result = collectionToCompareAgainst;
		} else {
			result = new ArrayList<>(itemsCollection);
			result.removeAll(collectionToCompareAgainst);
		}

		return Collections.unmodifiableList(new ArrayList<>(result));
	}

	/**
	 * From iterable.
	 * 
	 * Creates a {@link java.util.List} with the contents of the
	 * {@link java.lang.Iterable}
	 *
	 * @param <T>      the generic type
	 * @param iterable the iterable
	 * @return the list
	 */
	public static <T> List<T> fromIterable(Iterable<T> iterable) {
		List<T> result = null;

		if (iterable == null) {
			return null;
		}

		result = new ArrayList<>();
		iterable.forEach(result::add);

		return Collections.unmodifiableList(result);
	}
}
