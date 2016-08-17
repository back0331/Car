package qnaaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.CommandAction;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session2 = request.getSession();
		String user_id = (String)session2.getAttribute("id");
		int article_no=0;
		  
		    if(request.getParameter("article_no")!=null){
		    	article_no=Integer.parseInt(request.getParameter("article_no"));
			    }
		    request.setAttribute("article_no", new Integer(article_no));
		    request.setAttribute("user_id", user_id);
		    
		return "/qna_board/writeForm.jsp";
	}

}