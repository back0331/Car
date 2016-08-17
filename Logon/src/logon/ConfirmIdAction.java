package logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;

public class ConfirmIdAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 request.setCharacterEncoding("UTF-8");

		    String id = request.getParameter("id");
			UserListDBBean manager = UserListDBBean.getInstance();
		    int check= manager.confirmId(id);
		    
		    request.setAttribute("id", id);
		    request.setAttribute("check", check);
		 
	
		return "/logon/confirmId.jsp";
	}

}
