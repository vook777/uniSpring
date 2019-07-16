package com.foxminded.univer.servlet.teacher;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.service.TeacherService;
import com.foxminded.univer.models.Teacher;

@WebServlet("/saveTeacher")
public class SaveTeacher extends HttpServlet {

	@Autowired
	private TeacherService teacherService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = null;
		if (!req.getParameter("id").contentEquals("")) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		Integer facultyId = Integer.parseInt(req.getParameter("facultyId"));
		try {
			Teacher savedTeacher = teacherService.save(id, firstName, lastName, facultyId);
			req.setAttribute("teacher", savedTeacher);
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/teacher/showSaved.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
