package logon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.CommandAction;
import bean.UserListDBBean;
import mail.MailSender2;

public class SearchPwAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");

	    String id = request.getParameter("id");
	    String email=request.getParameter("email");
	    if(email==null)
	    	email="";
	    
	    int check=0;
	    if(!email.equals("")){
	    	UserListDBBean manager = UserListDBBean.getInstance();
	    	check= manager.searchPw(id,email);
			//System.out.println("check:::"+check);
		}
	    
	    if(check==1){
	    	
		    String text="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			String code="";
	
	    	for( int i=0; i < 7; i++ )
	    	   code += text.charAt((int)(Math.random() * text.length()));
	    	
	    	//System.out.println(code);
		    	
	    	MailSender2 m= new MailSender2();
	    	m.sendStr(email, code);   
	    	UserListDBBean userDB = UserListDBBean.getInstance();
	    	userDB.updatePw(code, id);
	    }
		
	    request.setAttribute("id", id);
	    request.setAttribute("check", check);
		return "/logon/searchPw.jsp";
	}

}
