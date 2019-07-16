package com.foxminded.univer.servlet.lecture;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.service.LectureService;
import com.foxminded.univer.models.Lecture;

@WebServlet("/deleteLecture")
public class DeleteLecture extends HttpServlet {

	@Autowired
	private LectureService lectureService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.parseInt(req.getParameter("lectureId"));
		try {
			Lecture lectureToDelete = lectureService.findById(id);
			lectureService.delete(lectureToDelete);
			req.setAttribute("deletedLecture", lectureToDelete);
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/lecture/showDeleted.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}