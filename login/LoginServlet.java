package com.mateusz.login;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mateusz.todo.TodoService;

@WebServlet(urlPatterns="/login.do")
public class LoginServlet extends HttpServlet {
	
	private LoginService userValidationService=new LoginService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		int UserID=userValidationService.isUserValid(name, password);
		if(UserID!=-1) {
			System.out.println("Zalogowano: "+name+" "+UserID+".");
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("UserID", UserID);
			response.sendRedirect("/list-todo.do");
		}
		else {
			request.setAttribute("errorMessage", "Invalid Credentials!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}
}
