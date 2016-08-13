package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.ReviewBoardDBBean;
import board.ReviewBoardDataBean;
import jdbc.CommandAction;

public class ReviewPasswordCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		ReviewBoardDataBean review = null;
		
		String insertedPassword = request.getParameter("insertedPassword");
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println("insertedPassword:::"+insertedPassword+"article_no:::"+article_no+"currentPage:::"+currentPage);
		ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
		review = dbPro.checkpwd(article_no, insertedPassword);
		
		if(review==null){
			return "/review_board/passwordDenied.jsp";
		}
		request.setAttribute("article_subject", review.getArticle_subject());
		request.setAttribute("article_content", review.getArticle_content());
		request.setAttribute("article_no", article_no);
		System.out.println("article_subject:::"+review.getArticle_subject()+"artice_content:::"+review.getArticle_content());
		
		if(request.getParameter("confirming")=="updateConfirmed"){
			return "/review_board/updateForm.jsp?article_no=";
		}else{
			return "/Board/review_board/delete.do";
		}
	}

}
