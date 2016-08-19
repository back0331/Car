package administratorPage.userListAction;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.*;

public class UserListProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		UserListDBBean uldbb = UserListDBBean.getInstance();
		UserListDataBean uldb = new UserListDataBean();
		List userList = new ArrayList();
		try{
			userList = uldbb.getUserList(uldb);
			if(userList!=null){
				request.setAttribute("userList", userList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/administratorPage/UserList/userList.jsp";
	}

}
