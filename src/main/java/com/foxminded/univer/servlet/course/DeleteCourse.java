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

@WebServlet("/deleteCourse")
public class DeleteCourse extends HttpServlet {

	@Autowired
	private CourseDaoSpring courseDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("courseId"));
		Course courseToDelete = courseDao.findById(id);
		courseDao.delete(courseToDelete);
		req.setAttribute("deletedCourse", courseToDelete);
		req.getRequestDispatcher("/course/showDeleted.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
