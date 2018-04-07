package com.mateusz.newuser;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mateusz.database.mDataBase;

@WebServlet(urlPatterns="/user.new")
public class NewUserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private mDataBase db;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/views/new-user.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email=request.getParameter("email");
		try {
			db=new mDataBase();
			db.addUser(name, password,email);
		}
		catch(SQLException e) {
			System.out.println(e);
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
		response.sendRedirect("/login.do");
	}
}
