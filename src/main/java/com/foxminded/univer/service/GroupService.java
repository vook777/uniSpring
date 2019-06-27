package com.foxminded.univer.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.FacultyDao;
import com.foxminded.univer.dao.impl.GroupDao;
import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.models.Group;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.CourseDaoSpring;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;
import com.foxminded.univer.spring.dao.GroupDaoSpring;

public class GroupService {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private GroupDaoSpring groupDao = context.getBean(GroupDaoSpring.class);
	private FacultyDaoSpring facultyDao = context.getBean(FacultyDaoSpring.class);
	
	public Group save(Integer id, String name, Integer facultyId) throws ClassNotFoundException {
		Group groupToSave = new Group();
		Faculty faculty = facultyDao.findById(facultyId);
		groupToSave.setId(id);
		groupToSave.setName(name);
		groupToSave.setFaculty(faculty);
		Group groupToReturn = groupDao.save(groupToSave);
		groupToReturn.setFaculty(faculty);
		return groupToReturn;
	}

	public void delete(Group group) throws ClassNotFoundException {
		groupDao.delete(group);
	}
	
	public Group findById(Integer groupId) throws ClassNotFoundException {
		Group groupToReturn = groupDao.findById(groupId);
		groupToReturn.setFaculty(facultyDao.findById(groupDao.getFacultyId(groupId)));
		return groupToReturn;
	}
	
	public List<Group> findAll() throws ClassNotFoundException {
		List<Group> groups = groupDao.findAll();
		groups.stream().forEach(group -> {
			group.setFaculty(facultyDao.findById(groupDao.getFacultyId(group.getId())));
		});
		return groups;
	}
	
	public List<Group> findByFacultyId(Integer facultyId) throws ClassNotFoundException {
		return groupDao.findByFacultyId(facultyId);
	}
}
