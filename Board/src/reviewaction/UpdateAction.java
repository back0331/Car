package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.*;
import jdbc.CommandAction;

public class UpdateAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String insertedPassword = request.getParameter("password");
		int article_no = (int) session.getAttribute("article_no");
		String currentPage = request.getParameter("currentPage");
		ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
		ReviewBoardDataBean review =  dbPro.checkpwd(article_no, insertedPassword);
		
		String password = review.getPassword();
		if(password!=insertedPassword){
			return "/review_board/update_passwordDenied.jsp";
		}
		return "/review_board/updateForm.jsp?article_no="+article_no+"&currentPage"+currentPage;
	}

}
