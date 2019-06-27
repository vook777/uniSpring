package com.foxminded.univer.spring.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.spring.config.AppConfig;

public class AuditoriumDaoSpringTest {
	
	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private AuditoriumDaoSpring auditoriumDao = context.getBean(AuditoriumDaoSpring.class);

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
