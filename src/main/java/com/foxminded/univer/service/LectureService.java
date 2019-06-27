package com.foxminded.univer.service;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.DaoException;
import com.foxminded.univer.dao.impl.AuditoriumDao;
import com.foxminded.univer.dao.impl.CourseDao;
import com.foxminded.univer.dao.impl.GroupDao;
import com.foxminded.univer.dao.impl.LectureDao;
import com.foxminded.univer.dao.impl.TeacherDao;
import com.foxminded.univer.models.Auditorium;
import com.foxminded.univer.models.Course;
import com.foxminded.univer.models.Group;
import com.foxminded.univer.models.Lecture;
import com.foxminded.univer.models.Teacher;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;
import com.foxminded.univer.spring.dao.CourseDaoSpring;
import com.foxminded.univer.spring.dao.GroupDaoSpring;
import com.foxminded.univer.spring.dao.LectureDaoSpring;
import com.foxminded.univer.spring.dao.TeacherDaoSpring;

public class LectureService {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private AuditoriumDaoSpring auditoriumDao = context.getBean(AuditoriumDaoSpring.class);
	private CourseDaoSpring courseDao = context.getBean(CourseDaoSpring.class);
	private GroupDaoSpring groupDao = context.getBean(GroupDaoSpring.class);
	private LectureDaoSpring lectureDao = context.getBean(LectureDaoSpring.class);
	private TeacherDaoSpring teacherDao = context.getBean(TeacherDaoSpring.class);

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

	public Lecture findById(Integer lectureId) throws DaoException, ClassNotFoundException {
		Lecture lectureToReturn = lectureDao.findById(lectureId);
		lectureToReturn.setAuditorium(auditoriumDao.findById(lectureDao.getAuditoriumId(lectureId)));
		lectureToReturn.setCourse(courseDao.findById(lectureDao.getCourseId(lectureId)));
		lectureToReturn.setGroup(groupDao.findById(lectureDao.getGroupId(lectureId)));
		lectureToReturn.setTeacher(teacherDao.findById(lectureDao.getTeacherId(lectureId)));
		return lectureToReturn;
	}

	public List<Lecture> findAll() throws DaoException, ClassNotFoundException {
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
}
