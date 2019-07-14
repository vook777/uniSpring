package com.foxminded.univer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.models.Group;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;
import com.foxminded.univer.spring.dao.GroupDaoSpring;

public class GroupService {

	@Autowired
	private GroupDaoSpring groupDao;
	@Autowired
	private FacultyDaoSpring facultyDao;
	
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

	public GroupDaoSpring getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDaoSpring groupDao) {
		this.groupDao = groupDao;
	}

	public FacultyDaoSpring getFacultyDao() {
		return facultyDao;
	}

	public void setFacultyDao(FacultyDaoSpring facultyDao) {
		this.facultyDao = facultyDao;
	}
}
