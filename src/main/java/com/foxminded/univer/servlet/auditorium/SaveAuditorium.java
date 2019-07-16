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

import com.foxminded.univer.models.Auditorium;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;

@WebServlet("/saveAuditorium")
public class SaveAuditorium extends HttpServlet {

	@Autowired
	private AuditoriumDaoSpring auditoriumDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Auditorium auditoriumToSave = new Auditorium();
		if (req.getParameter("id").contentEquals("")) {
			auditoriumToSave.setId(null);
		} else {
			auditoriumToSave.setId(Integer.parseInt(req.getParameter("id")));
		}
		auditoriumToSave.setName(req.getParameter("name"));
		auditoriumToSave.setCapacity(Integer.parseInt(req.getParameter("capacity")));
		try {
			Auditorium savedAuditorium = auditoriumDao.save(auditoriumToSave);
			req.setAttribute("auditorium", savedAuditorium);
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/auditorium/showSaved.jsp").forward(req, resp);
	}
	
	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
