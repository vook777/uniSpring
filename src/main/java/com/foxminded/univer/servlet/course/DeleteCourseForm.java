package com.foxminded.univer.servlet.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.CourseDao;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.CourseDaoSpring;

@WebServlet("/deleteCourseForm")
public class DeleteCourseForm extends HttpServlet {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private CourseDaoSpring courseDao = context.getBean(CourseDaoSpring.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("courses", courseDao.findAll());
		req.getRequestDispatcher("/course/deleteCourse.jsp").forward(req, resp);
	}
}
