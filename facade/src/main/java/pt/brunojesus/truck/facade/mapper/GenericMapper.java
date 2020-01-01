package pt.brunojesus.truck.facade.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper implements IGenericMapper {

	private final ModelMapper modelMapper;
	
	@Autowired
	public GenericMapper(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	
	public <T> T map(Object object, Class<T> targetClass) {
		
		if (object == null) {
			return null;
		}
		
		return modelMapper.map(object, targetClass);
	}

}
