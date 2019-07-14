package com.foxminded.univer.servlet.student;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.service.StudentService;

@WebServlet("/saveStudent")
public class SaveStudent extends HttpServlet {

	@Autowired
	private StudentService studentService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = null;
		if (!req.getParameter("id").contentEquals("")) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		Integer groupId = Integer.parseInt(req.getParameter("groupId"));
		String studentCardNumber = req.getParameter("studentCardNumber");
		try {
			req.setAttribute("student", studentService.save(id, firstName, lastName, groupId, studentCardNumber));
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/student/showSaved.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
