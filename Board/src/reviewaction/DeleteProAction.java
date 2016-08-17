package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import reviewboard.ReviewBoardDBBean;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		   
		ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
	   	 int r = dbPro.deleteArticle(article_no);
	   	 
	   	 request.setAttribute("article_no", new Integer(article_no));
	   	 request.setAttribute("r", new Integer(r));
		
	   	 return "/review_board/deletePro.jsp";
}
}
