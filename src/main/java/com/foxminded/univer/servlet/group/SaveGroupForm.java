package com.foxminded.univer.servlet.group;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.spring.dao.FacultyDaoSpring;

@WebServlet("/saveGroupForm")
public class SaveGroupForm extends HttpServlet {

	@Autowired
	private FacultyDaoSpring facultyDao;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("faculties", facultyDao.findAll());
		req.getRequestDispatcher("/group/saveGroup.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
