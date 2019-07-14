package com.foxminded.univer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.foxminded.univer.models.Auditorium;
import com.foxminded.univer.models.Course;
import com.foxminded.univer.models.Group;
import com.foxminded.univer.models.Lecture;
import com.foxminded.univer.models.Teacher;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;
import com.foxminded.univer.spring.dao.CourseDaoSpring;
import com.foxminded.univer.spring.dao.GroupDaoSpring;
import com.foxminded.univer.spring.dao.LectureDaoSpring;
import com.foxminded.univer.spring.dao.TeacherDaoSpring;

public class LectureService {

	@Autowired
	private AuditoriumDaoSpring auditoriumDao;
	@Autowired
	private CourseDaoSpring courseDao;
	@Autowired
	private GroupDaoSpring groupDao;
	@Autowired
	private LectureDaoSpring lectureDao;
	@Autowired
	private TeacherDaoSpring teacherDao;

	public Lecture save(Lecture lecture) throws ClassNotFoundException {
		Auditorium auditorium = lecture.getAuditorium();
		Course course = lecture.getCourse();
		Group group = lecture.getGroup();
		Teacher teacher = lecture.getTeacher();
		Lecture lectureToReturn = lectureDao.save(lecture);
		lectureToReturn.setAuditorium(auditorium);
		lectureToReturn.setCourse(course);
		lectureToReturn.setGroup(group);
		lectureToReturn.setTeacher(teacher);
		return lectureToReturn;
	}

	public void delete(Lecture lecture) throws ClassNotFoundException {
		lectureDao.delete(lecture);
	}

	public Lecture findById(Integer lectureId) throws ClassNotFoundException {
		Lecture lectureToReturn = lectureDao.findById(lectureId);
		lectureToReturn.setAuditorium(auditoriumDao.findById(lectureDao.getAuditoriumId(lectureId)));
		lectureToReturn.setCourse(courseDao.findById(lectureDao.getCourseId(lectureId)));
		lectureToReturn.setGroup(groupDao.findById(lectureDao.getGroupId(lectureId)));
		lectureToReturn.setTeacher(teacherDao.findById(lectureDao.getTeacherId(lectureId)));
		return lectureToReturn;
	}

	public List<Lecture> findAll() throws ClassNotFoundException {
		List<Lecture> lectures = lectureDao.findAll();
		for (Lecture lecture : lectures) {
			Integer lectureId = lecture.getId();
			lecture.setAuditorium(auditoriumDao.findById(lectureDao.getAuditoriumId(lectureId)));
			lecture.setCourse(courseDao.findById(lectureDao.getCourseId(lectureId)));
			lecture.setGroup(groupDao.findById(lectureDao.getGroupId(lectureId)));
			lecture.setTeacher(teacherDao.findById(lectureDao.getTeacherId(lectureId)));
		}
		return lectures;
	}

	public AuditoriumDaoSpring getAuditoriumDao() {
		return auditoriumDao;
	}

	public void setAuditoriumDao(AuditoriumDaoSpring auditoriumDao) {
		this.auditoriumDao = auditoriumDao;
	}

	public CourseDaoSpring getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(CourseDaoSpring courseDao) {
		this.courseDao = courseDao;
	}

	public GroupDaoSpring getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDaoSpring groupDao) {
		this.groupDao = groupDao;
	}

	public LectureDaoSpring getLectureDao() {
		return lectureDao;
	}

	public void setLectureDao(LectureDaoSpring lectureDao) {
		this.lectureDao = lectureDao;
	}

	public TeacherDaoSpring getTeacherDao() {
		return teacherDao;
	}

	public void setTeacherDao(TeacherDaoSpring teacherDao) {
		this.teacherDao = teacherDao;
	}
}
