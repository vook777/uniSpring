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

@WebServlet("/saveFaculty")
public class SaveFaculty extends HttpServlet {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private FacultyDaoSpring facultyDao = context.getBean(FacultyDaoSpring.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Faculty facultyToSave = new Faculty();
		if (req.getParameter("id").contentEquals("")) {
			facultyToSave.setId(null);
		} else {
			facultyToSave.setId(Integer.parseInt(req.getParameter("id")));
		}
		facultyToSave.setName(req.getParameter("name"));
		try {
			Faculty savedFaculty = facultyDao.save(facultyToSave);
			req.setAttribute("faculty", savedFaculty);
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/faculty/showSaved.jsp").forward(req, resp);
	}
}
