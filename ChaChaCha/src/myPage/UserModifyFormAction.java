package myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.UserListDBBean;
import bean.UserListDataBean;

public class UserModifyFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = (String)request.getSession().getAttribute("userId");
		
		UserListDBBean userDb = UserListDBBean.getInstance();
		UserListDataBean member = userDb.getMember(id);
		request.setAttribute("member", member);
		String phone = member.getPhone();
		String phone1 = phone.substring(0, 3);
		String phone2 = phone.substring(3, 7);
		String phone3 = phone.substring(7);
		request.setAttribute("phone1", phone1);
		request.setAttribute("phone2", phone2);
		request.setAttribute("phone3", phone3);
		
		return "userModifyForm.jsp";
	}

}
