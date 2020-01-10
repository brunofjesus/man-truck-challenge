package pt.brunojesus.truck.facade.api;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.ClassificationsApi;
import pt.brunojesus.truck.codegen.dto.ClassificationDTO;
import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.service.IClassificationService;
import pt.brunojesus.truck.model.domain.Classification;

@RestController
@AutoLogger
public class ClassificationController implements ClassificationsApi {

	private final IClassificationService classificationService;
	private final BiFunction<Supplier<Collection<Classification>>, Class<ClassificationDTO>, ResponseEntity<List<ClassificationDTO>>> genericMappedListResponse;

	public ClassificationController(IClassificationService classificationService,
			BiFunction<Supplier<Collection<Classification>>, Class<ClassificationDTO>, ResponseEntity<List<ClassificationDTO>>> genericMappedListResponse) {
		super();
		this.classificationService = classificationService;
		this.genericMappedListResponse = genericMappedListResponse;
	}

	@Override
	public ResponseEntity<List<ClassificationDTO>> listClassificiations() {
		return genericMappedListResponse.apply(() -> classificationService.findAll(), ClassificationDTO.class);
	}

}