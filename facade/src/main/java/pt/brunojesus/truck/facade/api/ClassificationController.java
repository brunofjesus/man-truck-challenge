package pt.brunojesus.truck.facade.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.ClassificationsApi;
import pt.brunojesus.truck.codegen.dto.ClassificationDTO;
import pt.brunojesus.truck.facade.utils.functional.IGenericMappedListResponse;
import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Classification;

@RestController
@AutoLogger
public class ClassificationController implements ClassificationsApi {

	private final GenericService<Classification, Integer> classificationService;
	private final IGenericMappedListResponse<Classification, ClassificationDTO> genericMappedListResponse;

	public ClassificationController(
			@Qualifier("classificationService") GenericService<Classification, Integer> classificationService,
			IGenericMappedListResponse<Classification, ClassificationDTO> genericMappedListResponse) {
		super();
		this.classificationService = classificationService;
		this.genericMappedListResponse = genericMappedListResponse;
	}

	@Override
	public ResponseEntity<List<ClassificationDTO>> listClassifications() {
		return genericMappedListResponse.apply(() -> classificationService.findAll(), ClassificationDTO.class);
	}

}
