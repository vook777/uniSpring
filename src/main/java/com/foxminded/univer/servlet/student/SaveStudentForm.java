package com.foxminded.univer.servlet.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.GroupDaoSpring;

@WebServlet("/saveStudentForm")
public class SaveStudentForm extends HttpServlet {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private GroupDaoSpring groupDao = context.getBean(GroupDaoSpring.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("groups", groupDao.findAll());
		req.getRequestDispatcher("/student/saveStudent.jsp").forward(req, resp);
	}
}
