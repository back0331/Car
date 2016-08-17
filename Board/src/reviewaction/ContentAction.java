package reviewaction;

import java.text.SimpleDateFormat;

import javax.servlet.http.*;

import jdbc.CommandAction;
import reviewboard.ReviewBoardDBBean;
import reviewboard.ReviewBoardDataBean;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		HttpSession session2 = request.getSession();
		String user_id = (String)session2.getAttribute("id");
	    int article_no = Integer.parseInt(request.getParameter("article_no"));
	    String pageNum = request.getParameter("pageNum");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	
		   ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
		   ReviewBoardDataBean review = dbPro.getArticle(article_no);
		   
		   request.setAttribute("pageNum", new Integer(pageNum));
		   session2.setAttribute("user_id", user_id);
		   request.setAttribute("review", review);
		   
		   System.out.println("review:::"+review);
	
		return "/review_board/content.jsp";
	
	}
}