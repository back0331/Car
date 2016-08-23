package review;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;
import bean.BookDBBean;
import bean.PointDBBean;
import bean.PointDataBean;
import bean.ReviewBoardDBBean;
import bean.ReviewBoardDataBean;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8"); 

		ReviewBoardDataBean review = new ReviewBoardDataBean();
		int book_no = Integer.parseInt(request.getParameter("book_no"));
		review.setBook_no(book_no);
		review.setArticle_subject(request.getParameter("article_subject"));
		review.setArticle_content(request.getParameter("article_content"));
		review.setId(request.getParameter("id"));
		review.setPassword(request.getParameter("password"));
		
		ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
		int r=dbPro.insertArticle(review); 
		if(r==1){
			BookDBBean bookDb = BookDBBean.getInstance();
			bookDb.updateBookReview(book_no);
			
			PointDBBean pointDb = PointDBBean.getInstance();
			PointDataBean point = new PointDataBean();
			point.setId(request.getParameter("id"));
			point.setType("Àû¸³");
			point.setAmount("1000");
			pointDb.insertPointList(point);
		}
		request.setAttribute("r", new Integer(r));
		
		return "/review/writePro.jsp";
	}
}
