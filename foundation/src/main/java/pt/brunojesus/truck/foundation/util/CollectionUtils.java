package pt.brunojesus.truck.foundation.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtils extends org.springframework.util.CollectionUtils {

	public static <T> List<T> itemsNotInList(Collection<T> list1, Collection<T> list2) {

		Collection<T> result = null;

		if (isEmpty(list1)) {
			result = Collections.emptyList();
		} else if (isEmpty(list2)) {
			result = list2;
		} else {
			result = new ArrayList<>(list1);
			result.removeAll(list2);
		}

		return Collections.unmodifiableList(new ArrayList<>(result));
	}

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
