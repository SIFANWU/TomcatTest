package web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import exception.UserExistException;
import service.impl.ServiceImpl;
import utils.WebUtils;
import web.formbean.RegisterForm;

 
@WebServlet("/Servlet/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//form 封装到formbean中
		RegisterForm form = WebUtils.requesttoBean(request, RegisterForm.class);
		boolean b = form.validate();
		
		if (!b) {
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return ;
		}
		
		User user = new User();
		WebUtils.copyBean(form, user);
		user.setId(WebUtils.generateID());
		
		ServiceImpl service = new ServiceImpl();
		
		try {
			service.register(user);
			request.setAttribute("message", "Register successfully! Website will come back homepage within 3 seconds!<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/index.jsp'>");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
			
		} catch (UserExistException e) {
			//用户已经注册 所以导致失败
			form.getErrors().put("username","The user has registered!");
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return;
		}catch (Exception e) {
			//其他未知错误导致 注册失败
			e.printStackTrace();
			request.setAttribute("message", "Unknow error!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
