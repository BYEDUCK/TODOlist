package com.mateusz.todo;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mateusz.database.mDataBase;

@WebServlet(urlPatterns="/done-todo.do")
public class DoneTodoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private mDataBase db;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int todoID=Integer.parseInt(request.getParameter("todo_id"));
			db=new mDataBase();
			db.setDone(todoID);
		}
		catch(SQLException e) {
			System.out.println(e+"AddTodoServlet");
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
		response.sendRedirect("/list-todo.do");
	}
}
