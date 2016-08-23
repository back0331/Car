package logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.CommandAction;
import bean.UserListDBBean;


public class SearchIdAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");

		String id = "";
	    String name = request.getParameter("name");
	    String email=request.getParameter("email");
	    if(email==null)
	    	email="";
	    
	    int check=0;
	    if(!email.equals("")){
	    	UserListDBBean manager = UserListDBBean.getInstance();
	    	check= manager.searchId1(name,email);
			System.out.println("check:::"+check);
			
			if(check==1) {
				id=manager.getUserId(name, email);
				
			}
		}
	    
	   
		request.setAttribute("id", id);
	    request.setAttribute("name", name);
	    request.setAttribute("check", check);
		return "/logon/searchId.jsp";
	}

}
