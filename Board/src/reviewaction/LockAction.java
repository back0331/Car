package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import reviewboard.ReviewBoardDBBean;
import reviewboard.ReviewBoardDataBean;

public class LockAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		  String pageNum = request.getParameter("pageNum");
		  ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
		  ReviewBoardDataBean review =  dbPro.checkpwd(article_no);
		  
		  request.setAttribute("article_no", new Integer(article_no));
		  request.setAttribute("pageNum", new Integer(pageNum));
		  request.setAttribute("review", review);

		return "/review_board/lock.jsp";
	}

}
