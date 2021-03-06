package com.foxminded.univer.servlet.faculty;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;

@WebServlet("/saveFaculty")
public class SaveFaculty extends HttpServlet {

	@Autowired
	private FacultyDaoSpring facultyDao;

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

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
