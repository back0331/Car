package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import reviewboard.ReviewBoardDBBean;
import reviewboard.ReviewBoardDataBean;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
		ReviewBoardDataBean review = new ReviewBoardDataBean();
		review.setArticle_subject(request.getParameter("article_subject"));
		review.setArticle_content(request.getParameter("article_content"));
		review.setArticle_no(Integer.parseInt(request.getParameter("article_no")));
		int r = dbPro.updateArticle(review);
		
		request.setAttribute("r", r);
		
	return "/review_board/updatePro.jsp";
	
	}
}
