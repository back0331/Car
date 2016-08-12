package Rent_Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Rent_Login.CommandAction;
import Rent_Login.UserList_DAO;

public class ConfirmIdAction implements CommandAction{
	


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		  
		String id = request.getParameter("id");
		UserList_DAO manager = UserList_DAO.getInstance();
		int check= manager.confirmId(id);
		
		request.setAttribute("id", id);
		request.setAttribute("check", new Integer(check));
		
		return "/UserForm/confirmId.jsp";
	}
	}

