package com.foxminded.univer.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.CourseDao;
import com.foxminded.univer.models.Course;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.CourseDaoSpring;

public class CourseService {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private CourseDaoSpring courseDao = context.getBean(CourseDaoSpring.class);
	
	public Course findById(Integer courseId) throws ClassNotFoundException {
		return courseDao.findById(courseId);
	}
	
	public List<Course> findAll() throws ClassNotFoundException {
		return courseDao.findAll();
	}
}
