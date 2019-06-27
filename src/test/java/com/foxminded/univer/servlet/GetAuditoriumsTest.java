package com.foxminded.univer.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.foxminded.univer.dao.JdbcDao;
import com.foxminded.univer.servlet.auditorium.GetAuditoriums;

public class GetAuditoriumsTest extends Mockito {

	private String path = "/auditorium/showAllAuditoriums.jsp";
	private GetAuditoriums servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher dispatcher;

	@Before
	public void setUp() {
		servlet = new GetAuditoriums();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		dispatcher = mock(RequestDispatcher.class);
	}

	@Test
	public void test() throws ServletException, IOException {

		when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

		servlet.doGet(request, response);

		verify(request, times(1)).getRequestDispatcher(path);
		verify(request, never()).getSession();
		verify(dispatcher).forward(request, response);
	}
}
