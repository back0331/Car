package logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.UserListDBBean;
import bean.UserListDataBean;


public class CreateUserProAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		UserListDataBean uldb = new UserListDataBean();
		UserListDBBean uld = UserListDBBean.getInstance();
		
		uldb.setId(request.getParameter("id"));
		uldb.setName(request.getParameter("name"));
		uldb.setPassword(request.getParameter("password"));
		uldb.setEmail(request.getParameter("email"));
		uldb.setPhone(request.getParameter("phone")+request.getParameter("phone2")+request.getParameter("phone3"));
		uldb.setZipcode(request.getParameter("zipcode"));
		uldb.setAddress(request.getParameter("address")+request.getParameter("address2"));
		int monthInt = Integer.parseInt(request.getParameter("month"));
		String month = String.format("%02d",monthInt);
		int dayInt = Integer.parseInt(request.getParameter("day"));
		String day = String.format("%02d", dayInt);
		uldb.setBirth(request.getParameter("year")+month+day);
		
		int result = uld.insertMember(uldb);
		
		request.setAttribute("result", result);
		
		return "/logon/createUserPro.jsp";
	}
}

	
	