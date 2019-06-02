package com.foxminded.univer.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foxminded.univer.service.StudentService;

@WebServlet("/deleteStudentForm")
public class DeleteStudentForm extends HttpServlet {

	private StudentService studentService = new StudentService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("students", studentService.findAll());
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/student/deleteStudent.jsp").forward(req, resp);
	}
}
