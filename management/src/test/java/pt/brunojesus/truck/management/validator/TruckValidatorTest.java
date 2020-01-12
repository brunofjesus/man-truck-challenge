package pt.brunojesus.truck.management.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import pt.brunojesus.truck.management.foundation.GenericService;
import pt.brunojesus.truck.model.domain.Application;
import pt.brunojesus.truck.model.domain.Classification;
import pt.brunojesus.truck.model.domain.Color;
import pt.brunojesus.truck.model.domain.FuelType;
import pt.brunojesus.truck.model.domain.RelTruckApplication;
import pt.brunojesus.truck.model.domain.RelTruckApplicationId;
import pt.brunojesus.truck.model.domain.RelTruckColor;
import pt.brunojesus.truck.model.domain.RelTruckColorId;
import pt.brunojesus.truck.model.domain.Truck;

@RunWith(MockitoJUnitRunner.class)
public class TruckValidatorTest {

	@Mock
	private GenericService<Classification, Integer> classificationService;

	@Mock
	private GenericService<FuelType, Integer> fuelTypeService;

	@Mock
	private GenericService<Application, Integer> applicationService;

	@Mock
	private GenericService<Color, Integer> colorService;

	private TruckValidator validator;

	@Before
	public void beforEach() {
		validator = new TruckValidator(classificationService, fuelTypeService, applicationService, colorService);
	}

	@Test
	public void testAccept() {
		Truck truck = Mockito.mock(Truck.class);

		// Pass validate classification
		Classification truckClassification = Mockito.mock(Classification.class);
		Mockito.when(truck.getClassification()).thenReturn(truckClassification);
		Mockito.when(truckClassification.getId()).thenReturn(10);
		Mockito.when(classificationService.findById(Mockito.eq(10))).thenReturn(truckClassification);

		// Pass validate fuel type
		FuelType truckFuelType = Mockito.mock(FuelType.class);
		Mockito.when(truck.getFuelType()).thenReturn(truckFuelType);
		Mockito.when(truckFuelType.getId()).thenReturn(11);
		Mockito.when(fuelTypeService.findById(Mockito.eq(11))).thenReturn(truckFuelType);

		// Pass validate rel truck applications
		RelTruckApplication relTruckApplication1 = Mockito.mock(RelTruckApplication.class);
		RelTruckApplication relTruckApplication2 = Mockito.mock(RelTruckApplication.class);
		List<RelTruckApplication> relTruckApplications = Arrays.asList(relTruckApplication1, relTruckApplication2);
		Mockito.when(truck.getRelTruckApplications()).thenReturn(relTruckApplications);
		Mockito.when(relTruckApplication1.getId()).thenReturn(new RelTruckApplicationId(1, 1));
		Mockito.when(relTruckApplication2.getId()).thenReturn(new RelTruckApplicationId(1, 2));
		Application a1 = Mockito.mock(Application.class);
		Application a2 = Mockito.mock(Application.class);
		Mockito.when(applicationService.findAllByIds(Mockito.any())).thenReturn(Arrays.asList(a1, a2));

		// Pass validate rel truck colors
		RelTruckColor relTruckColor1 = Mockito.mock(RelTruckColor.class);
		RelTruckColor relTruckColor2 = Mockito.mock(RelTruckColor.class);
		List<RelTruckColor> relTruckColors = Arrays.asList(relTruckColor1, relTruckColor2);
		Mockito.when(truck.getRelTruckColors()).thenReturn(relTruckColors);
		Mockito.when(relTruckColor1.getId()).thenReturn(new RelTruckColorId(1, 1));
		Mockito.when(relTruckColor2.getId()).thenReturn(new RelTruckColorId(1, 2));
		Color c1 = Mockito.mock(Color.class);
		Color c2 = Mockito.mock(Color.class);
		Mockito.when(colorService.findAllByIds(Mockito.any())).thenReturn(Arrays.asList(c1, c2));

		validator.accept(truck);

		Mockito.verify(truck, Mockito.times(2)).getClassification();
		Mockito.verify(truckClassification, Mockito.times(1)).getId();

		Mockito.verify(truck, Mockito.times(2)).getFuelType();
		Mockito.verify(truckFuelType, Mockito.times(1)).getId();

		Mockito.verify(truck, Mockito.times(2)).getRelTruckApplications();
		Mockito.verify(relTruckApplication1, Mockito.times(1)).getId();
		Mockito.verify(relTruckApplication2, Mockito.times(1)).getId();
		Mockito.verify(applicationService, Mockito.times(1)).findAllByIds(Mockito.any());

		Mockito.verify(truck, Mockito.times(2)).getRelTruckColors();
		Mockito.verify(relTruckColor1, Mockito.times(1)).getId();
		Mockito.verify(relTruckColor2, Mockito.times(1)).getId();
		Mockito.verify(colorService, Mockito.times(1)).findAllByIds(Mockito.any());
	}

	@Test
	public void testAccept_allEmpty() {
		Truck truck = Mockito.mock(Truck.class);
		validator.accept(truck);

		Mockito.verify(truck, Mockito.times(1)).getClassification();

		Mockito.verify(truck, Mockito.times(1)).getFuelType();

		Mockito.verify(truck, Mockito.times(1)).getRelTruckApplications();
		Mockito.verify(applicationService, Mockito.never()).findAllByIds(Mockito.any());

		Mockito.verify(truck, Mockito.times(1)).getRelTruckColors();
		Mockito.verify(colorService, Mockito.never()).findAllByIds(Mockito.any());
	}

	@Test
	public void testAccept_nullTruck_mustFail() {
		try {
			validator.accept(null);
			Assert.fail("Exception should been thrown");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Truck cannot be null", e.getMessage());
		}
	}

	@Test
	public void testAccept_invalidClassification_mustFail() {
		Truck truck = Mockito.mock(Truck.class);

		Classification truckClassification = Mockito.mock(Classification.class);
		Mockito.when(truck.getClassification()).thenReturn(truckClassification);
		Mockito.when(truckClassification.getId()).thenReturn(10);
		Mockito.when(classificationService.findById(Mockito.eq(10))).thenThrow(new NoSuchElementException());

		try {
			validator.accept(truck);
			Assert.fail("Exception should been thrown");
		} catch (ValidationException e) {
			Assert.assertEquals("Invalid classification id: 10", e.getMessage());
		}

		Mockito.verify(truck, Mockito.times(3)).getClassification();
		Mockito.verify(truckClassification, Mockito.times(2)).getId();
	}

	@Test
	public void testAccept_invalidFuelType_mustFail() {
		Truck truck = Mockito.mock(Truck.class);

		// Pass validate classification
		Classification truckClassification = Mockito.mock(Classification.class);
		Mockito.when(truck.getClassification()).thenReturn(truckClassification);
		Mockito.when(truckClassification.getId()).thenReturn(10);
		Mockito.when(classificationService.findById(Mockito.eq(10))).thenReturn(truckClassification);

		// Fail fuel type
		FuelType truckFuelType = Mockito.mock(FuelType.class);
		Mockito.when(truck.getFuelType()).thenReturn(truckFuelType);
		Mockito.when(truckFuelType.getId()).thenReturn(11);
		Mockito.when(fuelTypeService.findById(Mockito.eq(11))).thenThrow(new NoSuchElementException());

		try {
			validator.accept(truck);
			Assert.fail("Exception should been thrown");
		} catch (ValidationException e) {
			Assert.assertEquals("Invalid fuel type id: 11", e.getMessage());
		}

		Mockito.verify(truck, Mockito.times(3)).getFuelType();
		Mockito.verify(truckFuelType, Mockito.times(2)).getId();
	}

	@Test
	public void testAccept_invalidRelTruckApplications_all_mustFail() {
		Truck truck = Mockito.mock(Truck.class);

		// Pass validate classification
		Classification truckClassification = Mockito.mock(Classification.class);
		Mockito.when(truck.getClassification()).thenReturn(truckClassification);
		Mockito.when(truckClassification.getId()).thenReturn(10);
		Mockito.when(classificationService.findById(Mockito.eq(10))).thenReturn(truckClassification);

		// Pass validate fuel type
		FuelType truckFuelType = Mockito.mock(FuelType.class);
		Mockito.when(truck.getFuelType()).thenReturn(truckFuelType);
		Mockito.when(truckFuelType.getId()).thenReturn(11);
		Mockito.when(fuelTypeService.findById(Mockito.eq(11))).thenReturn(truckFuelType);

		// Fail validate rel truck applications
		RelTruckApplication relTruckApplication1 = Mockito.mock(RelTruckApplication.class);
		RelTruckApplication relTruckApplication2 = Mockito.mock(RelTruckApplication.class);
		List<RelTruckApplication> relTruckApplications = Arrays.asList(relTruckApplication1, relTruckApplication2);
		Mockito.when(truck.getRelTruckApplications()).thenReturn(relTruckApplications);
		Mockito.when(relTruckApplication1.getId()).thenReturn(new RelTruckApplicationId(1, 1));
		Mockito.when(relTruckApplication2.getId()).thenReturn(new RelTruckApplicationId(1, 2));
		Mockito.when(applicationService.findAllByIds(Mockito.any())).thenReturn(new ArrayList<Application>());

		try {
			validator.accept(truck);
			Assert.fail("Exception should been thrown");
		} catch (ValidationException e) {
			Assert.assertEquals("Invalid applications: All", e.getMessage());
		}

		Mockito.verify(truck, Mockito.times(2)).getRelTruckApplications();
		Mockito.verify(relTruckApplication1, Mockito.times(1)).getId();
		Mockito.verify(relTruckApplication2, Mockito.times(1)).getId();
		Mockito.verify(applicationService, Mockito.times(1)).findAllByIds(Mockito.any());
	}

	@Test
	public void testAccept_invalidRelTruckApplications_one_mustFail() {
		Truck truck = Mockito.mock(Truck.class);

		// Pass validate classification
		Classification truckClassification = Mockito.mock(Classification.class);
		Mockito.when(truck.getClassification()).thenReturn(truckClassification);
		Mockito.when(truckClassification.getId()).thenReturn(10);
		Mockito.when(classificationService.findById(Mockito.eq(10))).thenReturn(truckClassification);

		// Pass validate fuel type
		FuelType truckFuelType = Mockito.mock(FuelType.class);
		Mockito.when(truck.getFuelType()).thenReturn(truckFuelType);
		Mockito.when(truckFuelType.getId()).thenReturn(11);
		Mockito.when(fuelTypeService.findById(Mockito.eq(11))).thenReturn(truckFuelType);

		// Fail validate rel truck applications
		RelTruckApplication relTruckApplication1 = Mockito.mock(RelTruckApplication.class);
		RelTruckApplication relTruckApplication2 = Mockito.mock(RelTruckApplication.class);
		List<RelTruckApplication> relTruckApplications = Arrays.asList(relTruckApplication1, relTruckApplication2);
		Mockito.when(truck.getRelTruckApplications()).thenReturn(relTruckApplications);
		Mockito.when(relTruckApplication1.getId()).thenReturn(new RelTruckApplicationId(1, 1));
		Mockito.when(relTruckApplication2.getId()).thenReturn(new RelTruckApplicationId(1, 2));
		Application a1 = Mockito.mock(Application.class);
		Mockito.when(applicationService.findAllByIds(Mockito.any())).thenReturn(Arrays.asList(a1));
		Mockito.when(a1.getId()).thenReturn(1);

		try {
			validator.accept(truck);
			Assert.fail("Exception should been thrown");
		} catch (ValidationException e) {
			Assert.assertEquals("Invalid application ids: 2", e.getMessage());
		}

		Mockito.verify(truck, Mockito.times(2)).getRelTruckApplications();
		Mockito.verify(relTruckApplication1, Mockito.times(1)).getId();
		Mockito.verify(relTruckApplication2, Mockito.times(1)).getId();
		Mockito.verify(applicationService, Mockito.times(1)).findAllByIds(Mockito.any());
	}

	@Test
	public void testAccept_invalidRelTruckColors_all_mustFail() {
		Truck truck = Mockito.mock(Truck.class);

		// Pass validate classification
		Classification truckClassification = Mockito.mock(Classification.class);
		Mockito.when(truck.getClassification()).thenReturn(truckClassification);
		Mockito.when(truckClassification.getId()).thenReturn(10);
		Mockito.when(classificationService.findById(Mockito.eq(10))).thenReturn(truckClassification);

		// Pass validate fuel type
		FuelType truckFuelType = Mockito.mock(FuelType.class);
		Mockito.when(truck.getFuelType()).thenReturn(truckFuelType);
		Mockito.when(truckFuelType.getId()).thenReturn(11);
		Mockito.when(fuelTypeService.findById(Mockito.eq(11))).thenReturn(truckFuelType);

		// Pass validate rel truck applications
		RelTruckApplication relTruckApplication1 = Mockito.mock(RelTruckApplication.class);
		RelTruckApplication relTruckApplication2 = Mockito.mock(RelTruckApplication.class);
		List<RelTruckApplication> relTruckApplications = Arrays.asList(relTruckApplication1, relTruckApplication2);
		Mockito.when(truck.getRelTruckApplications()).thenReturn(relTruckApplications);
		Mockito.when(relTruckApplication1.getId()).thenReturn(new RelTruckApplicationId(1, 1));
		Mockito.when(relTruckApplication2.getId()).thenReturn(new RelTruckApplicationId(1, 2));
		Application a1 = Mockito.mock(Application.class);
		Application a2 = Mockito.mock(Application.class);
		Mockito.when(applicationService.findAllByIds(Mockito.any())).thenReturn(Arrays.asList(a1, a2));

		// Fail validate rel truck colors
		RelTruckColor relTruckcolor1 = Mockito.mock(RelTruckColor.class);
		RelTruckColor relTruckcolor2 = Mockito.mock(RelTruckColor.class);
		List<RelTruckColor> relTruckcolors = Arrays.asList(relTruckcolor1, relTruckcolor2);
		Mockito.when(truck.getRelTruckColors()).thenReturn(relTruckcolors);
		Mockito.when(relTruckcolor1.getId()).thenReturn(new RelTruckColorId(1, 1));
		Mockito.when(relTruckcolor2.getId()).thenReturn(new RelTruckColorId(1, 2));
		Mockito.when(colorService.findAllByIds(Mockito.any())).thenReturn(new ArrayList<Color>());

		try {
			validator.accept(truck);
			Assert.fail("Exception should been thrown");
		} catch (ValidationException e) {
			Assert.assertEquals("Invalid colors: All", e.getMessage());
		}

		Mockito.verify(truck, Mockito.times(2)).getRelTruckColors();
		Mockito.verify(relTruckcolor1, Mockito.times(1)).getId();
		Mockito.verify(relTruckcolor2, Mockito.times(1)).getId();
		Mockito.verify(colorService, Mockito.times(1)).findAllByIds(Mockito.any());
	}

	@Test
	public void testAccept_invalidRelTruckColors_one_mustFail() {
		Truck truck = Mockito.mock(Truck.class);

		// Pass validate classification
		Classification truckClassification = Mockito.mock(Classification.class);
		Mockito.when(truck.getClassification()).thenReturn(truckClassification);
		Mockito.when(truckClassification.getId()).thenReturn(10);
		Mockito.when(classificationService.findById(Mockito.eq(10))).thenReturn(truckClassification);

		// Pass validate fuel type
		FuelType truckFuelType = Mockito.mock(FuelType.class);
		Mockito.when(truck.getFuelType()).thenReturn(truckFuelType);
		Mockito.when(truckFuelType.getId()).thenReturn(11);
		Mockito.when(fuelTypeService.findById(Mockito.eq(11))).thenReturn(truckFuelType);

		// Pass validate rel truck applications
		RelTruckApplication relTruckApplication1 = Mockito.mock(RelTruckApplication.class);
		RelTruckApplication relTruckApplication2 = Mockito.mock(RelTruckApplication.class);
		List<RelTruckApplication> relTruckApplications = Arrays.asList(relTruckApplication1, relTruckApplication2);
		Mockito.when(truck.getRelTruckApplications()).thenReturn(relTruckApplications);
		Mockito.when(relTruckApplication1.getId()).thenReturn(new RelTruckApplicationId(1, 1));
		Mockito.when(relTruckApplication2.getId()).thenReturn(new RelTruckApplicationId(1, 2));
		Application a1 = Mockito.mock(Application.class);
		Application a2 = Mockito.mock(Application.class);
		Mockito.when(applicationService.findAllByIds(Mockito.any())).thenReturn(Arrays.asList(a1, a2));

		// Fail validate rel truck colors
		RelTruckColor relTruckcolor1 = Mockito.mock(RelTruckColor.class);
		RelTruckColor relTruckcolor2 = Mockito.mock(RelTruckColor.class);
		List<RelTruckColor> relTruckcolors = Arrays.asList(relTruckcolor1, relTruckcolor2);
		Mockito.when(truck.getRelTruckColors()).thenReturn(relTruckcolors);
		Mockito.when(relTruckcolor1.getId()).thenReturn(new RelTruckColorId(1, 1));
		Mockito.when(relTruckcolor2.getId()).thenReturn(new RelTruckColorId(1, 2));
		Color c1 = Mockito.mock(Color.class);
		Mockito.when(colorService.findAllByIds(Mockito.any())).thenReturn(Arrays.asList(c1));
		Mockito.when(c1.getId()).thenReturn(1);

		try {
			validator.accept(truck);
			Assert.fail("Exception should been thrown");
		} catch (ValidationException e) {
			Assert.assertEquals("Invalid color ids: 2", e.getMessage());
		}

		Mockito.verify(truck, Mockito.times(2)).getRelTruckColors();
		Mockito.verify(relTruckcolor1, Mockito.times(1)).getId();
		Mockito.verify(relTruckcolor2, Mockito.times(1)).getId();
		Mockito.verify(colorService, Mockito.times(1)).findAllByIds(Mockito.any());
	}
}
