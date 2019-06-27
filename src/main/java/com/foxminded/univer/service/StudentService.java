package com.foxminded.univer.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.FacultyDao;
import com.foxminded.univer.dao.impl.GroupDao;
import com.foxminded.univer.dao.impl.StudentDao;
import com.foxminded.univer.models.Group;
import com.foxminded.univer.models.Student;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;
import com.foxminded.univer.spring.dao.GroupDaoSpring;
import com.foxminded.univer.spring.dao.StudentDaoSpring;

public class StudentService {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private FacultyDaoSpring facultyDao = context.getBean(FacultyDaoSpring.class);
	private GroupDaoSpring groupDao = context.getBean(GroupDaoSpring.class);
	private StudentDaoSpring studentDao = context.getBean(StudentDaoSpring.class);

	public Student save(Integer id, String firstName, String lastName, Integer groupId, String studentCardNumber) throws ClassNotFoundException {
		Student studentToSave = new Student();
		Group group = groupDao.findById(groupId);
		group.setFaculty(facultyDao.findById(groupDao.getFacultyId(group.getId())));
		studentToSave.setId(id);
		studentToSave.setFirstName(firstName);
		studentToSave.setLastName(lastName);
		studentToSave.setGroup(group);
		studentToSave.setStudentCardNumber(studentCardNumber);
		Student studentToReturn = studentDao.save(studentToSave);
		studentToReturn.setGroup(group);
		return studentToReturn;
	}
	
	public void delete(Student student) throws ClassNotFoundException {
		studentDao.delete(student);
	}
	
	public Student findById(Integer studentId) throws ClassNotFoundException {
		Student studentToReturn = studentDao.findById(studentId);
		Group group = groupDao.findById(studentDao.getGroupId(studentId));
		group.setFaculty(facultyDao.findById(groupDao.getFacultyId(group.getId())));
		studentToReturn.setGroup(group);
		return studentToReturn;
	}

	public List<Student> findAll() throws ClassNotFoundException {
		List<Student> students = studentDao.findAll();
		students.stream().forEach(student -> {
			Group group = groupDao.findById(studentDao.getGroupId(student.getId()));
			group.setFaculty(facultyDao.findById(groupDao.getFacultyId(group.getId())));
			student.setGroup(group);
		});
		return students;
	}
}
