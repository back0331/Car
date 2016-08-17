package logon;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;

public class CreateUserFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String id= request.getParameter("id");
		String password= request.getParameter("password");
		String name= request.getParameter("name");
		String email= request.getParameter("email");
		String email1=request.getParameter("email1");
		String phone=request.getParameter("phone");
		String phone2=request.getParameter("phone2");
		String phone3=request.getParameter("phone3");
		String zipcode=request.getParameter("zipcode");
		String address=request.getParameter("address");
		String address2=request.getParameter("address2");
		
		UserListDBBean uld= UserListDBBean.getInstance();
		
		
		
		return "/logon/createUserForm.jsp";
	}
		
}
