package pt.brunojesus.truck.facade.api;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.ColorsApi;
import pt.brunojesus.truck.codegen.dto.ColorDTO;
import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Color;

@RestController
@AutoLogger
public class ColorController implements ColorsApi {

	private final GenericService<Color, Integer> colorService;
	private final BiFunction<Supplier<Collection<Color>>, Class<ColorDTO>, ResponseEntity<List<ColorDTO>>> genericMappedListResponse;

	@Autowired
	public ColorController(@Qualifier("colorService") GenericService<Color, Integer> colorService,
			BiFunction<Supplier<Collection<Color>>, Class<ColorDTO>, ResponseEntity<List<ColorDTO>>> genericMappedListResponse) {
		super();
		this.colorService = colorService;
		this.genericMappedListResponse = genericMappedListResponse;
	}

	@Override
	public ResponseEntity<List<ColorDTO>> listColors() {
		return genericMappedListResponse.apply(() -> colorService.findAll(), ColorDTO.class);
	}

}
