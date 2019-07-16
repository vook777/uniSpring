package com.foxminded.univer.servlet.group;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.foxminded.univer.models.Faculty;
import com.foxminded.univer.models.Group;
import com.foxminded.univer.service.GroupService;
import com.foxminded.univer.spring.dao.FacultyDaoSpring;
import com.foxminded.univer.spring.dao.GroupDaoSpring;

@WebServlet("/saveGroup")
public class SaveGroup extends HttpServlet {

	@Autowired
	private GroupService groupService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = null;
		if (!req.getParameter("id").contentEquals("")) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		String name = req.getParameter("name");
		Integer facultyId = Integer.parseInt(req.getParameter("facultyId"));
		try {
			Group savedGroup = groupService.save(id, name, facultyId);
			req.setAttribute("group", savedGroup);
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		req.getRequestDispatcher("/group/showSaved.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
