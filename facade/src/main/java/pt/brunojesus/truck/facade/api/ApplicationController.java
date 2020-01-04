package pt.brunojesus.truck.facade.api;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.ApplicationsApi;
import pt.brunojesus.truck.codegen.dto.ApplicationDTO;
import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.service.IApplicationService;
import pt.brunojesus.truck.model.domain.Application;

@RestController
@AutoLogger
public class ApplicationController implements ApplicationsApi {

	private final IApplicationService applicationService;
	private final BiFunction<Supplier<Collection<Application>>, Class<ApplicationDTO>, ResponseEntity<List<ApplicationDTO>>> genericMappedListResponse;

	@Autowired
	public ApplicationController(IApplicationService applicationService,
			BiFunction<Supplier<Collection<Application>>, Class<ApplicationDTO>, ResponseEntity<List<ApplicationDTO>>> genericMappedListResponse) {
		super();
		this.applicationService = applicationService;
		this.genericMappedListResponse = genericMappedListResponse;
	}

	@Override
	public ResponseEntity<List<ApplicationDTO>> listApplications() {
		return genericMappedListResponse.apply(() -> applicationService.findAll(), ApplicationDTO.class);
	}

}
