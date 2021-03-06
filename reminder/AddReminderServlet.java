package com.mateusz.reminder;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mateusz.database.mDataBase;

@WebServlet(urlPatterns="/remind.do")
public class AddReminderServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private mDataBase db;
	private ReminderService reminderService;
	
	public void init() {
		this.reminderService=new ReminderService();
	}
	
	public void removeReminder(int todoID) {
		this.reminderService.removeReminder(todoID);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().setAttribute("todoID", request.getParameter("todo_id"));
		request.getRequestDispatcher("/WEB-INF/views/reminder.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int todoID=Integer.parseInt(request.getSession().getAttribute("todoID").toString());
		String date = request.getParameter("date");
		String hour = request.getParameter("hour");
		int userID=Integer.parseInt(request.getSession().getAttribute("UserID").toString());
		String hour1=hour.replace(':', '-');
		String fullDate=date+"-"+hour1;
		String todoName="";
		System.out.println(fullDate);
	
		Reminder reminder=new Reminder(fullDate,todoID,userID);
		if(reminder.isDateCorrect()) {
			try {
				db=new mDataBase();
				db.setRemindDate(date+" "+hour, todoID);
				ResultSet rs=db.getTodoName(todoID);
				if(rs.next())
					todoName=rs.getString(1);
			}
			catch(SQLException e) {
				System.out.println(e+"ReminderServlet");
			}
			catch(ClassNotFoundException e1) {
				System.out.println(e1);
			}
			finally {
				try {
					db.closeConnection();
				}
				catch(SQLException e) {
					System.out.println(e);
				}
			}
			reminder.setTodoName(todoName);
			Thread t=new Thread(reminder);
			t.start();
			this.reminderService.addReminder(todoID, reminder);
			request.getSession().setAttribute("reminders", reminderService);
			response.sendRedirect("/list-todo.do");
		}
		else {
			System.out.println("Wrong data given!");
			request.setAttribute("errorMessage", "Wrong data given!");
			request.getRequestDispatcher("/WEB-INF/views/reminder.jsp").forward(request, response);	
		}
	}
}
