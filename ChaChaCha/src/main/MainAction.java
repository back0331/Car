package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class MainAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// 로그인 여부 확인 
		String id = (String)request.getSession().getAttribute("userId");
		request.setAttribute("id", id);
		
		return "/main/main.jsp";
	}

}
