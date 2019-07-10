package com.foxminded.univer.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

import com.foxminded.univer.models.Auditorium;
import com.foxminded.univer.spring.config.AppConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class AuditoriumDaoSpringTest {
	
	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private AuditoriumDaoSpring auditoriumDao = context.getBean(AuditoriumDaoSpring.class);
	
	@Test
	public void testSaveForNewAuditorium() throws ClassNotFoundException {
		Auditorium expected = new Auditorium();
		expected.setName("new");
		expected.setCapacity(20);
		auditoriumDao.save(expected);
		expected.setId(17);
		
		Auditorium actual = auditoriumDao.findById(17);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void testDelete() {
		auditoriumDao.delete(auditoriumDao.findById(17));
		Assert.assertNull(auditoriumDao.findById(17));
	}
	
	@Test
	public void testFindById() throws ClassNotFoundException {
		// Given
		Auditorium expectedAuditorium = new Auditorium();
		expectedAuditorium.setId(2);
		expectedAuditorium.setName("A2");
		expectedAuditorium.setCapacity(50);

		// When
		Auditorium actualAuditorium = auditoriumDao.findById(2);

		// Then
		Assert.assertEquals(expectedAuditorium, actualAuditorium);
	}
	
	@Test
	public void testFindAll() {
		Auditorium auditorium1 = new Auditorium();
		Auditorium auditorium2 = new Auditorium();
		Auditorium auditorium3 = new Auditorium();
		List<Auditorium> expected = new ArrayList<>();
		auditorium1.setId(2);
		auditorium1.setName("A2");
		auditorium1.setCapacity(50);
		expected.add(auditorium1);
		auditorium2.setId(3);
		auditorium2.setName("A3");
		auditorium2.setCapacity(100);
		expected.add(auditorium2);
		auditorium3.setId(4);
		auditorium3.setName("A4");
		auditorium3.setCapacity(30);
		expected.add(auditorium3);
		
		List<Auditorium> actual = auditoriumDao.findAll();
		
		Assert.assertEquals(expected, actual);
	}
}
