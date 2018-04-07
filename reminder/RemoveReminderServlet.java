package com.mateusz.reminder;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns="/remove_remind.do")
public class RemoveReminderServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int todoID;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		todoID=Integer.parseInt(request.getParameter("todo_id").toString());
		ReminderService rs=(ReminderService) request.getSession().getAttribute("reminders");
		rs.removeReminder(todoID);
		response.sendRedirect("/list-todo.do");
	}
	
}
