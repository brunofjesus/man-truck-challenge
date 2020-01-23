package pt.brunojesus.truck.facade.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.FueltypesApi;
import pt.brunojesus.truck.codegen.dto.FuelTypeDTO;
import pt.brunojesus.truck.facade.utils.functional.IGenericMappedListResponse;
import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.FuelType;

@RestController
@AutoLogger
public class FuelTypeController implements FueltypesApi {

	private final GenericService<FuelType, Integer> fuelTypeService;
	private final IGenericMappedListResponse<FuelType, FuelTypeDTO> genericMappedListResponse;

	@Autowired
	public FuelTypeController(@Qualifier("fuelTypeService") GenericService<FuelType, Integer> fuelTypeService,
			IGenericMappedListResponse<FuelType, FuelTypeDTO> genericMappedListResponse) {
		super();
		this.fuelTypeService = fuelTypeService;
		this.genericMappedListResponse = genericMappedListResponse;
	}

	@Override
	public ResponseEntity<List<FuelTypeDTO>> listFuelTypes() {
		return genericMappedListResponse.apply(() -> fuelTypeService.findAll(), FuelTypeDTO.class);
	}
}
