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

import com.foxminded.univer.service.AuditoriumService;
import com.foxminded.univer.service.ServiceException;

@WebServlet("/showAllAuditoriums")
public class GetAuditoriums extends HttpServlet {

	@Autowired
	private AuditoriumService auditoriumService;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("auditoriums", auditoriumService.findAll());
		} catch (ClassNotFoundException e) {
			throw new ServiceException("Cannot get auditoriums");
		}
		req.getRequestDispatcher("/auditorium/showAllAuditoriums.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
