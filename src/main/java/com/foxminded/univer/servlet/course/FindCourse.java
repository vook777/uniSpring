package com.foxminded.univer.servlet.course;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.foxminded.univer.dao.impl.CourseDao;
import com.foxminded.univer.spring.config.AppConfig;
import com.foxminded.univer.spring.dao.CourseDaoSpring;

@WebServlet("/findCourse")
public class FindCourse extends HttpServlet {

	private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private CourseDaoSpring courseDao = context.getBean(CourseDaoSpring.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	Integer id = Integer.parseInt(req.getParameter("courseId"));
    	req.setAttribute("course", courseDao.findById(id));
        req.getRequestDispatcher("/course/showCourse.jsp").forward(req, resp);
    }
}
