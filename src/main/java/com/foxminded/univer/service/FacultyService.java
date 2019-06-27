package com.foxminded.univer.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;

public class FacultyService {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private FacultyDaoSpring facultyDao = context.getBean(FacultyDaoSpring.class);

	public List<Faculty> findAll() throws ClassNotFoundException {
		return facultyDao.findAll();
	}
}
