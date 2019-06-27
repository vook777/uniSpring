package com.foxminded.univer.servlet.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.CourseDao;
import com.foxminded.univer.models.Course;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.CourseDaoSpring;

@WebServlet("/saveCourse")
public class SaveCourse extends HttpServlet {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private CourseDaoSpring courseDao = context.getBean(CourseDaoSpring.class);

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
}
