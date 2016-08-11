package Rent_Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InsertUserAction implements CommandAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		UserListDataBean uldb = new UserListDataBean();
		
		uldb.setId(request.getParameter("id"));
		uldb.setName(request.getParameter("name"));
		uldb.setPassword(request.getParameter("password"));
		uldb.setEmail(request.getParameter("email")+request.getParameter("email1"));
		uldb.setPhone(request.getParameter("phone")+request.getParameter("phone2")+request.getParameter("phone3"));
		uldb.setZipcode(request.getParameter("zipcode"));
		uldb.setAddress(request.getParameter("address")+request.getParameter("address2"));
		
		
		UserList_DAO uld = UserList_DAO.getInstance();
		
		/*uld.insertMember(uldb);*/
		
		String uldaction = uld.insertMember(uldb);
		
		return uldaction;
	}

}
