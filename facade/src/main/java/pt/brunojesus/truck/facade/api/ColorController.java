package pt.brunojesus.truck.facade.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.ColorsApi;
import pt.brunojesus.truck.codegen.dto.ColorDTO;
import pt.brunojesus.truck.facade.utils.functional.IGenericMappedListResponse;
import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Color;

@RestController
@AutoLogger
public class ColorController implements ColorsApi {

	private final GenericService<Color, Integer> colorService;
	private final IGenericMappedListResponse<Color, ColorDTO> genericMappedListResponse;

	@Autowired
	public ColorController(@Qualifier("colorService") GenericService<Color, Integer> colorService,
			IGenericMappedListResponse<Color, ColorDTO> genericMappedListResponse) {
		super();
		this.colorService = colorService;
		this.genericMappedListResponse = genericMappedListResponse;
	}

	@Override
	public ResponseEntity<List<ColorDTO>> listColors() {
		return genericMappedListResponse.apply(() -> colorService.findAll(), ColorDTO.class);
	}

}
