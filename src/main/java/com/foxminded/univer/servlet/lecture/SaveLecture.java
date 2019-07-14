package com.foxminded.univer.servlet.lecture;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.service.GroupService;
import com.foxminded.univer.service.LectureService;
import com.foxminded.univer.service.TeacherService;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;
import com.foxminded.univer.spring.dao.CourseDaoSpring;
import com.foxminded.univer.models.Lecture;

@WebServlet("/saveLecture")
public class SaveLecture extends HttpServlet {

	@Autowired
	private LectureService lectureService;
	@Autowired
	private AuditoriumDaoSpring auditoriumDao;
	@Autowired
	private CourseDaoSpring courseDao;
	@Autowired
	private GroupService groupService;
	@Autowired
	private TeacherService teacherService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Lecture lectureToSave = new Lecture();
		if (req.getParameter("id").contentEquals("")) {
			lectureToSave.setId(null);
		} else {
			lectureToSave.setId(Integer.parseInt(req.getParameter("id")));
		}
		try {
			lectureToSave.setAuditorium(auditoriumDao.findById(Integer.parseInt(req.getParameter("auditoriumId"))));
			lectureToSave.setCourse(courseDao.findById(Integer.parseInt(req.getParameter("courseId"))));
			lectureToSave.setGroup(groupService.findById(Integer.parseInt(req.getParameter("groupId"))));
			lectureToSave.setTeacher(teacherService.findById(Integer.parseInt(req.getParameter("teacherId"))));
			lectureToSave.setTime(LocalTime.parse(req.getParameter("time")));
			Lecture savedLecture = lectureService.save(lectureToSave);
			req.setAttribute("lecture", savedLecture);
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/lecture/showSaved.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
