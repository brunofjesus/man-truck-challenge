package pt.brunojesus.truck.management.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.ValidationException;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import pt.brunojesus.truck.foundation.exception.ResourceNotFoundException;
import pt.brunojesus.truck.model.domain.Truck;
import pt.brunojesus.truck.persistence.repository.ITruckRepository;

@RunWith(MockitoJUnitRunner.class)
public class TruckServiceTest implements WithAssertions {

	@Mock
	private ITruckRepository truckRepository;

	@Mock
	private Consumer<Truck> truckValidator;

	@InjectMocks
	private TruckService truckService;

	@Test
	public void testFindAll() throws Exception {
		List<Truck> expectedResult = Arrays.asList(Mockito.mock(Truck.class), Mockito.mock(Truck.class));

		Mockito.when(truckRepository.findAll(Mockito.eq(true), Mockito.eq(true), Mockito.eq(true), Mockito.eq(true),
				Mockito.any())).thenReturn(expectedResult);

		List<Truck> result = truckService.findAll();

		Mockito.verify(truckRepository, Mockito.times(1)).findAll(Mockito.eq(true), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true), Mockito.any());

		assertThat(result).isNotNull().hasSameSizeAs(expectedResult).isEqualTo(expectedResult);
	}

	@Test
	public void testGetOne() throws Exception {
		Truck expected = Mockito.mock(Truck.class);

		Mockito.when(truckRepository.getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true))).thenReturn(expected);

		Truck result = truckService.getOne(1L);

		Mockito.verify(truckRepository, Mockito.times(1)).getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true), Mockito.eq(true));

		assertThat(result).isNotNull().isEqualTo(expected);
	}

	@Test
	public void testUpdate() throws Exception {
		Truck expected = Mockito.mock(Truck.class);
		Mockito.when(expected.getId()).thenReturn(1L);

		Mockito.when(truckRepository.getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true))).thenReturn(expected);

		Mockito.when(truckRepository.update(Mockito.eq(expected))).thenReturn(expected);

		Truck result = truckService.update(expected);

		Mockito.verify(expected, Mockito.times(1)).getId();

		Mockito.verify(truckRepository, Mockito.times(1)).getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true), Mockito.eq(true));

		Mockito.verify(truckRepository, Mockito.times(1)).update(Mockito.eq(expected));

		assertThat(result).isNotNull().isEqualTo(expected);
	}

	@Test
	public void testUpdate_validatorFails_mustFail() {

		Truck mockedTruck = Mockito.mock(Truck.class);

		Mockito.doThrow(new ValidationException("Mock exception")).when(truckValidator)
				.accept(Mockito.any(Truck.class));

		assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> truckService.update(mockedTruck));

		Mockito.verify(mockedTruck, Mockito.never()).getId();

		Mockito.verify(truckRepository, Mockito.never()).getOne(Mockito.anyLong(), Mockito.anyBoolean(),
				Mockito.anyBoolean(), Mockito.anyBoolean(), Mockito.anyBoolean());

		Mockito.verify(truckRepository, Mockito.never()).update(Mockito.any(Truck.class));
	}

	@Test
	public void testUpdate_notInDatabase_mustFail() {

		Truck mockedTruck = Mockito.mock(Truck.class);
		Mockito.when(mockedTruck.getId()).thenReturn(1L);

		Mockito.when(truckRepository.getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true))).thenReturn(null);

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> truckService.update(mockedTruck));

		Mockito.verify(mockedTruck, Mockito.times(1)).getId();

		Mockito.verify(truckRepository, Mockito.times(1)).getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true), Mockito.eq(true));

		Mockito.verify(truckRepository, Mockito.never()).update(Mockito.any(Truck.class));
	}

	@Test
	public void testSave() throws Exception {
		Truck expected = Mockito.mock(Truck.class);
		Mockito.when(expected.getId()).thenReturn(-1L);

		Mockito.when(truckRepository.save(Mockito.eq(expected))).thenReturn(expected);

		Truck result = truckService.save(expected);

		Mockito.verify(expected, Mockito.times(1)).getId();

		assertThat(result).isNotNull().isEqualTo(expected);
	}

	@Test
	public void testSave_validatorFails_mustFail() {

		Truck mockedTruck = Mockito.mock(Truck.class);

		Mockito.doThrow(new ValidationException("Mock exception")).when(truckValidator)
				.accept(Mockito.any(Truck.class));

		assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> truckService.save(mockedTruck));

		Mockito.verify(mockedTruck, Mockito.never()).getId();

		Mockito.verify(truckRepository, Mockito.never()).save(Mockito.any(Truck.class));
	}

	@Test
	public void testSave_specifyingId_mustFail() throws Exception {
		Truck expected = Mockito.mock(Truck.class);
		Mockito.when(expected.getId()).thenReturn(1L);

		assertThatExceptionOfType(ValidationException.class).isThrownBy(() -> truckService.save(expected));

		Mockito.verify(expected, Mockito.times(1)).getId();
		Mockito.verify(truckRepository, Mockito.never()).save(Mockito.any(Truck.class));
	}

	@Test
	public void testDeleteById() throws Exception {
		Truck mockedTruck = Mockito.mock(Truck.class);

		Mockito.when(truckRepository.getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true))).thenReturn(mockedTruck);

		truckService.deleteById(1L);

		Mockito.verify(truckRepository, Mockito.times(1)).delete(mockedTruck);
	}

	@Test
	public void testDeleteById_notInDatabase_mustFail() throws Exception {
		Mockito.when(truckRepository.getOne(Mockito.eq(1L), Mockito.eq(true), Mockito.eq(true), Mockito.eq(true),
				Mockito.eq(true))).thenReturn(null);

		assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> truckService.deleteById(1L));

		Mockito.verify(truckRepository, Mockito.never()).delete(Mockito.any());
	}

}
