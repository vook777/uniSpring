package com.foxminded.univer.servlet.auditorium;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.AuditoriumDao;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.AuditoriumDaoSpring;

@WebServlet("/showAllAuditoriums")
public class GetAuditoriums extends HttpServlet {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private AuditoriumDaoSpring auditoriumDao = context.getBean(AuditoriumDaoSpring.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("auditoriums", auditoriumDao.findAll());
        req.getRequestDispatcher("/auditorium/showAllAuditoriums.jsp").forward(req, resp);
    }
}
