package administratorPage.userListAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.*;

public class UserKickAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String msg = "";
		int check = 0;
		String id = "";
		
		id = request.getParameter("id");
		UserListDBBean uldbb = UserListDBBean.getInstance();
		UserListDataBean uldb = new UserListDataBean();
		
		try{
			check = uldbb.kickUser(id);
			if(check>0){
				msg="ȸ��Ż��Ϸ�";
				return "/ChaChaCha/administrator/UserList/userListPro.jsp";
			}else{
				msg="ȸ��Ż�����";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "/administratorPage/UserList/userList.jsp";
	}

}
