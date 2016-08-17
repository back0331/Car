package reviewaction;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.CommandAction;
import reviewboard.ReviewBoardDBBean;
import reviewboard.ReviewBoardDataBean;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8"); 

		ReviewBoardDataBean review = new ReviewBoardDataBean();
		review.setBook_no(Integer.parseInt(request.getParameter("book_no")));
		review.setArticle_subject(request.getParameter("article_subject"));
		review.setArticle_content(request.getParameter("article_content"));
		review.setId(request.getParameter("id"));
		review.setPassword(request.getParameter("password"));
		review.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		
		ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
		int r=dbPro.insertArticle(review); 
		
		request.setAttribute("r", new Integer(r));
		
		return "/review_board/writePro.jsp";
	}
}
