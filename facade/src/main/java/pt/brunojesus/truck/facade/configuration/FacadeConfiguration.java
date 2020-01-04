package pt.brunojesus.truck.facade.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * The Class FacadeConfiguration.
 */
@Configuration
public class FacadeConfiguration {

	/**
	 * Model mapper.
	 * 
	 * Maps objects directly
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	/**
	 * Object mapper.
	 * 
	 * Serializes objects
	 *
	 * @return the object mapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();

		// Hibernate configurations, disable lazy loading and keep ids on lazys
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.disable(Feature.FORCE_LAZY_LOADING);
		hibernate5Module.enable(Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
		mapper.registerModule(hibernate5Module);

		// Date serialization to ISO
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		return mapper;
	}

	/**
	 * Cors configurer.
	 *
	 * @return the web mvc configurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/trucks/**").allowedOrigins("*");
			}
		};
	}
}
