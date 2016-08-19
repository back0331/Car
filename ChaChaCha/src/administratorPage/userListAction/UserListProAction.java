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
		int userCount = 0;
		
		int pageSize=10;
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");
		int searchn=0;
		if(pageNum==null){
			pageNum="1";
		}
		if(search==null){
			search="";  
		}else{
			searchn=Integer.parseInt(request.getParameter("searchn"));
		}
		int currentPage=Integer.parseInt(pageNum);//���� ��������ȣ ����
		int startRow=(currentPage*pageSize)-(pageSize-1);
		int endRow=currentPage*pageSize;
		int count=0;
		int number=0;
		
		try{
			userList = uldbb.getUserList(); 
			userCount = uldbb.getUserCount();
			if(userList!=null){
				request.setAttribute("userList", userList);
				request.setAttribute("userCount", userCount);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/administratorPage/UserList/userList.jsp";
	}

}
