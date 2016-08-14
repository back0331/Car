package reviewaction;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.*;
import jdbc.CommandAction;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String user_id = "aa";//(String)session.getAttribute("id");
		int article_no=0;
		ReviewBoardDataBean review = new ReviewBoardDataBean();
		review.setArticle_subject(request.getParameter("article_subject"));
		review.setArticle_content(request.getParameter("article_content"));
		review.setPassword(request.getParameter("password"));
		review.setArticle_no(article_no);
		review.setId(user_id);
		review.setReg_date(new Timestamp(System.currentTimeMillis()));
		/*review.setArticle_no(Integer.parseInt(request.getParameter("article_no")));*/
		/*review.setId(request.getParameter("id"));*/
		try{
			ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
			int r=dbPro.insertArticle(review); 
			if(r>0){
				/*if(Integer.parseInt(request.getParameter("article_no"))!=0){
					article_no=Integer.parseInt(request.getParameter("article_no"));
				}
					request.setAttribute("article_no", article_no);
					request.setAttribute("user_id", user_id);*/
				return "/review_board/writePro.jsp";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/review_board/writeFailed.jsp";
	}
}
