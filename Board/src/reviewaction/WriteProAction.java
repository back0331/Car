package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.CommandAction;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String user_id = "aa";//(String)session.getAttribute("id");
		int article_no=0;
		try{ 
			if(Integer.parseInt(request.getParameter("article_no"))!=0){
				article_no=Integer.parseInt(request.getParameter("article_no"));
			}
			request.setAttribute("article_no", article_no);
			request.setAttribute("user_id", user_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
