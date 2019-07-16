package com.foxminded.univer.servlet.course;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.models.Course;
import com.foxminded.univer.spring.dao.CourseDaoSpring;

@WebServlet("/saveCourse")
public class SaveCourse extends HttpServlet {

	@Autowired
	private CourseDaoSpring courseDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Course courseToSave = new Course();
		if (req.getParameter("id").contentEquals("")) {
			courseToSave.setId(null);
		} else {
			courseToSave.setId(Integer.parseInt(req.getParameter("id")));
		}
		courseToSave.setName(req.getParameter("name"));
		courseToSave.setNumberOfWeeks(Integer.parseInt(req.getParameter("numberOfWeeks")));
		courseToSave.setDescription(req.getParameter("description"));
		try {
			Course savedCourse = courseDao.save(courseToSave);
			req.setAttribute("course", savedCourse);
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/course/showSaved.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
