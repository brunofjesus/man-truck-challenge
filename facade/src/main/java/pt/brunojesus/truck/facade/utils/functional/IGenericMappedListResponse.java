package pt.brunojesus.truck.facade.utils.functional;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface IGenericMappedListResponse<S, T> {

	ResponseEntity<List<T>> apply(Supplier<Collection<S>> collection, Class<T> targetClass);
}
