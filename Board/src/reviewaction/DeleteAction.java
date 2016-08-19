package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.*;
import jdbc.CommandAction;

public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		String pageNum = request.getParameter("currentPage");
	 	ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
	 	int r = dbPro.deleteArticle(article_no);
	 	if(r>0){
	 		return "/review_board/deletePro.jsp";
	 	}
	 	return "/review_board/passwordDenied.jsp";
	}
}
