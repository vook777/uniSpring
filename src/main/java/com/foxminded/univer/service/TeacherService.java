package com.foxminded.univer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.models.Teacher;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;
import com.foxminded.univer.spring.dao.TeacherDaoSpring;

public class TeacherService {

	@Autowired
	private FacultyDaoSpring facultyDao;
	@Autowired
	private TeacherDaoSpring teacherDao;
	
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

	public FacultyDaoSpring getFacultyDao() {
		return facultyDao;
	}

	public void setFacultyDao(FacultyDaoSpring facultyDao) {
		this.facultyDao = facultyDao;
	}

	public TeacherDaoSpring getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDaoSpring teacherDao) {
		this.teacherDao = teacherDao;
	}
}
