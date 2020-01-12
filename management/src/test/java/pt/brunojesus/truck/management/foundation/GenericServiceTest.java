package pt.brunojesus.truck.management.foundation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.repository.CrudRepository;

@RunWith(MockitoJUnitRunner.class)
public class GenericServiceTest {

	class Target {
		public Integer id;
	}

	class Service extends GenericService<Target, Integer> {

		public Service(CrudRepository<Target, Integer> crudRepository) {
			super(crudRepository);
		}

	}

	@Mock
	CrudRepository<Target, Integer> repository;

	Service service;

	@Before
	public void beforeEach() {
		this.service = Mockito.spy(new Service(repository));
	}

	@Test
	public void testFindById() {
		Target expected = Mockito.mock(Target.class);

		Mockito.when(repository.findById(Mockito.eq(1))).thenReturn(Optional.of(expected));

		Target result = service.findById(1);

		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.eq(1));

		Assert.assertNotNull(result);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testFindById_notFound_mustFail() {

		Mockito.when(repository.findById(Mockito.eq(1))).thenReturn(Optional.empty());

		try {
			service.findById(1);
			Assert.fail("Exception should have been thrown");
		} catch (NoSuchElementException e) {

		}

		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.eq(1));
	}

	@Test
	public void findAll() {
		List<Target> mockedTargets = Arrays.asList(Mockito.mock(Target.class), Mockito.mock(Target.class));

		Mockito.when(repository.findAll()).thenReturn(mockedTargets);

		List<Target> result = service.findAll();

		Mockito.verify(repository, Mockito.times(1)).findAll();

		Assert.assertNotNull(result);
		Assert.assertEquals(mockedTargets.size(), result.size());
		Assert.assertTrue(result.containsAll(mockedTargets));
	}

	@Test
	public void testFindAllByIds() {
		List<Integer> ids = Arrays.asList(1, 2);
		List<Target> mockedTargets = Arrays.asList(Mockito.mock(Target.class), Mockito.mock(Target.class));

		Mockito.when(repository.findAllById(Mockito.eq(ids))).thenReturn(mockedTargets);

		List<Target> result = service.findAllByIds(ids);

		Mockito.verify(repository, Mockito.times(1)).findAllById(ids);

		Assert.assertNotNull(result);
		Assert.assertEquals(mockedTargets.size(), result.size());
		Assert.assertTrue(result.containsAll(mockedTargets));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindAllByIds_nullList_mustFail() {
		service.findAllByIds(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindAllByIds_emptyList_mustFail() {
		service.findAllByIds(new ArrayList<Integer>());
	}
}
