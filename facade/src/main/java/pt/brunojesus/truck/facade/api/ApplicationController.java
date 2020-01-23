package pt.brunojesus.truck.facade.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import pt.brunojesus.truck.codegen.api.ApplicationsApi;
import pt.brunojesus.truck.codegen.dto.ApplicationDTO;
import pt.brunojesus.truck.facade.utils.functional.IGenericMappedListResponse;
import pt.brunojesus.truck.foundation.aop.AutoLogger;
import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Application;

@RestController
@AutoLogger
public class ApplicationController implements ApplicationsApi {

	private final GenericService<Application, Integer> applicationService;
	private final IGenericMappedListResponse<Application, ApplicationDTO> genericMappedListResponse;

	@Autowired
	public ApplicationController( //
			@Qualifier("applicationService") GenericService<Application, Integer> applicationService,
			IGenericMappedListResponse<Application, ApplicationDTO> genericMappedListResponse) {
		super();
		this.applicationService = applicationService;
		this.genericMappedListResponse = genericMappedListResponse;
	}

	@Override
	public ResponseEntity<List<ApplicationDTO>> listApplications() {
		return genericMappedListResponse.apply(() -> applicationService.findAll(), ApplicationDTO.class);
	}

}
