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

@WebServlet("/deleteAuditorium")
public class DeleteAuditorium extends HttpServlet {

	@Autowired
	private AuditoriumDaoSpring auditoriumDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("auditoriumId"));
		Auditorium aduditoriumToDelete = auditoriumDao.findById(id);
		auditoriumDao.delete(aduditoriumToDelete);
		req.setAttribute("deletedAuditorium", aduditoriumToDelete);
		req.getRequestDispatcher("/auditorium/showDeleted.jsp").forward(req, resp);
	}
	
	public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
