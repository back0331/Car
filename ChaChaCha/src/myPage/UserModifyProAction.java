package myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.UserListDBBean;
import bean.UserListDataBean;

public class UserModifyProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone1")+request.getParameter("phone2")+request.getParameter("phone3");
		String zipcode = request.getParameter("zipcode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
//		System.out.println(id);
//		System.out.println(password);
//		System.out.println(phone);
//		System.out.println(zipcode);
//		System.out.println(address);
		UserListDataBean member = new UserListDataBean();
		member.setId(id);
		member.setPassword(password);
		member.setPhone(phone);
		member.setZipcode(zipcode);
		member.setAddress1(address1);
		member.setAddress2(address2);
		
		UserListDBBean userDb = UserListDBBean.getInstance();
		int result = userDb.updateMember(member);
		
		request.setAttribute("result", result);
		return "userModifyPro.jsp";
	}

}
