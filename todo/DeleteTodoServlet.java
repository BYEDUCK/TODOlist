package com.mateusz.todo;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mateusz.database.mDataBase;

@WebServlet(urlPatterns="/delete-todo.do")
public class DeleteTodoServlet extends HttpServlet {
	
	private mDataBase db;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int todoID=Integer.parseInt(request.getParameter("todo_id"));
		try {
			db=new mDataBase();
			db.deleteTodo(todoID);
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
