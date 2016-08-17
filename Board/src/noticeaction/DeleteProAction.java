package noticeaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import noticeboard.NoticeBoardDBBean;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	 
		request.setCharacterEncoding("UTF-8");


 	    int article_no = Integer.parseInt(request.getParameter("article_no"));
   
	    NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
   	    int r = dbPro.deleteArticle(article_no);
		
   	    request.setAttribute("r", r);
   	    return "/notice/deletePro.jsp";
	}

}
