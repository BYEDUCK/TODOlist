package com.mateusz.todo;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mateusz.database.mDataBase;
import com.mateusz.todo.TodoService;

@WebServlet(urlPatterns="/add-todo.do")
public class AddTodoServlet extends HttpServlet {
	
	private mDataBase db;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/views/add-todo.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String newTodo = request.getParameter("todo");
		String category = request.getParameter("category");
		int UserID=(int)request.getSession().getAttribute("UserID");
		
		try {
			db=new mDataBase();
			System.out.println(UserID);
			db.addTodo(newTodo,category,UserID);
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
