package com.foxminded.univer.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.models.Teacher;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;
import com.foxminded.univer.spring.dao.TeacherDaoSpring;

public class TeacherService {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private FacultyDaoSpring facultyDao = context.getBean(FacultyDaoSpring.class);
	private TeacherDaoSpring teacherDao = context.getBean(TeacherDaoSpring.class);
	
	public Teacher save(Integer id, String firstName, String lastName, Integer facultyId) throws ClassNotFoundException {
		Teacher teacherToSave = new Teacher();
		Faculty faculty = facultyDao.findById(facultyId);
		teacherToSave.setId(id);
		teacherToSave.setFirstName(firstName);
		teacherToSave.setLastName(lastName);
		teacherToSave.setFaculty(faculty);
		Teacher teacherToReturn = teacherDao.save(teacherToSave);
		teacherToReturn.setFaculty(faculty);
		return teacherToReturn;
	}
	
	public void delete(Teacher teacher) throws ClassNotFoundException {
		teacherDao.delete(teacher);
	}
	
	public Teacher findById(Integer teacherId) throws ClassNotFoundException {
		Teacher teacherToReturn = teacherDao.findById(teacherId);
		Faculty faculty = facultyDao.findById(teacherDao.getFacultyId(teacherId));
		teacherToReturn.setFaculty(faculty);
		return teacherToReturn;
	}

	public List<Teacher> findAll() throws ClassNotFoundException {
		List<Teacher> teachers = teacherDao.findAll();
		teachers.stream().forEach(teacher -> {
			Faculty faculty = facultyDao.findById(teacherDao.getFacultyId(teacher.getId()));
			teacher.setFaculty(faculty);
		});
		return teachers;
	}
}
