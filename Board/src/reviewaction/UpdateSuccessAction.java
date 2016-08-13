package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.*;
import jdbc.CommandAction;

public class UpdateSuccessAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
		ReviewBoardDataBean review = new ReviewBoardDataBean();
		review.setArticle_subject(request.getParameter("article_subject"));
		review.setArticle_content(request.getParameter("article_content"));
		review.setArticle_no(Integer.parseInt(request.getParameter("article_no")));
		int r = dbPro.updateArticle(review);
		if(r>0){
			return "/review_board/updatePro.jsp";
		}
	return "/review_board/listPro.do";
	}
}
