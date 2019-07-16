package com.foxminded.univer.servlet.auditorium;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;

@WebServlet("/showAllAuditoriums")
public class GetAuditoriums extends HttpServlet {

	@Autowired
	private AuditoriumDaoSpring auditoriumDao;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("auditoriums", auditoriumDao.findAll());
		req.getRequestDispatcher("/auditorium/showAllAuditoriums.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
