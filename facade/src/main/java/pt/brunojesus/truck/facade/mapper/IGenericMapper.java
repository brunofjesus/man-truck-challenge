package pt.brunojesus.truck.facade.mapper;

@FunctionalInterface
public interface IGenericMapper {
	
	/**
	 * Map.
	 *
	 * @param <T> the target generic type
	 * @param object the object
	 * @param targetClass the target class
	 * @return the mapped object
	 */
	public <T> T map(Object object, Class<T> targetClass);
}