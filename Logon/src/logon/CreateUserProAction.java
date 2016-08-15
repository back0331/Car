package logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;


public class CreateUserProAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		UserListDataBean uldb = new UserListDataBean();
		UserListDBBean uld = UserListDBBean.getInstance();
		
		uldb.setId(request.getParameter("id"));
		uldb.setName(request.getParameter("name"));
		uldb.setPassword(request.getParameter("password"));
		uldb.setEmail(request.getParameter("email")+request.getParameter("email1"));
		uldb.setPhone(request.getParameter("phone")+request.getParameter("phone2")+request.getParameter("phone3"));
		uldb.setZipcode(request.getParameter("zipcode"));
		uldb.setAddress(request.getParameter("address")+request.getParameter("address2"));
		
		uld.insertMember(uldb);
		
			
		
		return "/logon/createUserPro.jsp";
	}
}

	
	