package com.foxminded.univer.servlet.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.FacultyDao;
import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;

@WebServlet("/deleteFaculty")
public class DeleteFaculty extends HttpServlet {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private FacultyDaoSpring facultyDao = context.getBean(FacultyDaoSpring.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("facultyId"));
		Faculty facultyToDelete = facultyDao.findById(id);
		facultyDao.delete(facultyToDelete);
		req.setAttribute("deletedFaculty", facultyToDelete);
		req.getRequestDispatcher("/faculty/showDeleted.jsp").forward(req, resp);
	}
}