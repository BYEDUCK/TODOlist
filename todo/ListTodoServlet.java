package com.mateusz.todo;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import com.mateusz.database.mDataBase;
import com.mateusz.todo.TodoService;

@WebServlet(urlPatterns="/list-todo.do")
public class ListTodoServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TodoService todoService;
	private int UserID;
	private mDataBase db;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserID=(int) request.getSession().getAttribute("UserID");
		try {
			db=new mDataBase();
			ResultSet rs=db.getTodos(UserID);
			todoService=new TodoService();
			while(rs.next()) {
				if(!rs.getBoolean("done"))
					todoService.addUndoneTodo(new Todo(rs.getString("description"),rs.getString("category"),rs.getInt("_id"),rs.getBoolean("done"),rs.getString("remind_date")));
				else
					todoService.addDoneTodo(new Todo(rs.getString("description"),rs.getString("category"),rs.getInt("_id"),rs.getBoolean("done"),rs.getString("remind_date")));	
		}
		}
		catch(SQLException e) {
			System.out.println(e+"ListTodoServlet");
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
		request.setAttribute("todos_undone", todoService.retrieveUndoneTodos());
		request.setAttribute("todos_done", todoService.retrieveDoneTodos());
		request.getRequestDispatcher("/WEB-INF/views/list-todos.jsp").forward(request, response);
	}
	
}
