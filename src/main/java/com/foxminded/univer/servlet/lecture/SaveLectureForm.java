package com.foxminded.univer.servlet.lecture;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.service.GroupService;
import com.foxminded.univer.service.TeacherService;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;
import com.foxminded.univer.spring.dao.CourseDaoSpring;

@WebServlet("/saveLectureForm")
public class SaveLectureForm extends HttpServlet {

	@Autowired
	private AuditoriumDaoSpring auditoriumDao;
	@Autowired
	private CourseDaoSpring courseDao;
	@Autowired
	private GroupService groupService;
	@Autowired
	private TeacherService teacherService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("auditoriums", auditoriumDao.findAll());
			req.setAttribute("courses", courseDao.findAll());
			req.setAttribute("groups", groupService.findAll());
			req.setAttribute("teachers", teacherService.findAll());
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/lecture/saveLecture.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
