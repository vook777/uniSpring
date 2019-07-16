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

import com.foxminded.univer.service.GroupService;
import com.foxminded.univer.service.ServiceException;
import com.foxminded.univer.spring.dao.GroupDaoSpring;

@WebServlet("/showAllGroups")
public class GetGroups extends HttpServlet {

	@Autowired
	private GroupService groupService;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("groups", groupService.findAll());
		} catch (ClassNotFoundException e) {
			throw new ServiceException("Cannot find all groups");
		}
		req.getRequestDispatcher("/group/showAllGroups.jsp").forward(req, resp);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
