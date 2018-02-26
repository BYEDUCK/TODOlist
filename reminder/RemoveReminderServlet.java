package com.mateusz.reminder;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mateusz.database.mDataBase;
import com.mateusz.todo.TodoService;

@WebServlet(urlPatterns="/remove_remind.do")
public class RemoveReminderServlet extends HttpServlet {
	
	private mDataBase db;
	private int todoID;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		todoID=Integer.parseInt(request.getParameter("todo_id").toString());
		ReminderService rs=(ReminderService) request.getSession().getAttribute("reminders");
		rs.removeReminder(todoID);
		response.sendRedirect("/list-todo.do");
	}
	
}
