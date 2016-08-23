package logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import bean.UserListDBBean;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String id= request.getParameter("id");
		String password= request.getParameter("password");
		
		UserListDBBean manager= UserListDBBean.getInstance();
		int check= manager.userCheck(id,password);
		
			request.setAttribute("id", id);
			request.setAttribute("password", password);
			request.setAttribute("check", check);
			request.getSession().setAttribute("userId",id);
		
		return "/logon/loginPro.jsp";
	
	}
}
