package pt.brunojesus.truck.facade.utils.functional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import pt.brunojesus.truck.facade.mapper.IGenericMapper;
import pt.brunojesus.truck.foundation.util.CollectionUtils;

/**
 * The Class GenericMappedListResponse.
 *
 * Generates a response entity for a generic object that can be mapped directly
 * to the target class.
 * 
 * Intended to be used in controllers that return lists when the DTO can be
 * directly mapped from the model.
 *
 * @param <S> the source type (i.e: Model)
 * @param <T> the target type (i.e: DTO)
 */
@Component
public class GenericMappedListResponse<S, T>
		implements BiFunction<Supplier<Collection<S>>, Class<T>, ResponseEntity<List<T>>> {

	private final IGenericMapper genericMapper;

	@Autowired
	public GenericMappedListResponse(IGenericMapper genericMapper) {
		super();
		this.genericMapper = genericMapper;
	}

	@Override
	public ResponseEntity<List<T>> apply(Supplier<Collection<S>> collectionSupplier, Class<T> targetClazz) {
		List<T> resultContents = Collections.emptyList();

		Collection<S> unmappedObjects = collectionSupplier.get();

		if (CollectionUtils.isEmpty(unmappedObjects) == false) {
			resultContents = unmappedObjects.stream().map(unmapped -> genericMapper.map(unmapped, targetClazz))
					.collect(Collectors.toList());
		}

		return ResponseEntity.ok(resultContents);
	}

}
