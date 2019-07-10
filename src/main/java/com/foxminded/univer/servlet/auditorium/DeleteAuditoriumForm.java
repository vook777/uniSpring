package com.foxminded.univer.servlet.auditorium;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.AuditoriumDao;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;

@WebServlet("/deleteAuditoriumForm")
public class DeleteAuditoriumForm extends HttpServlet {

	@Autowired
	private AuditoriumDaoSpring auditoriumDao;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("auditoriums", auditoriumDao.findAll());
		req.getRequestDispatcher("/auditorium/deleteAuditorium.jsp").forward(req, resp);
	}
}
