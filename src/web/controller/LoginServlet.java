package web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.impl.ServiceImpl;


@WebServlet("/Servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ServiceImpl service = new ServiceImpl();
		User user = service.login(username, password);
		
		if (user!= null) {
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("name", user.getUsername());
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}
		
		request.setAttribute("message", "The username or password is not right!");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
