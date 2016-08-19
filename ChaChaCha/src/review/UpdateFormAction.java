package review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import action.CommandAction;
import bean.ReviewBoardDBBean;
import bean.ReviewBoardDataBean;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		String pageNum = request.getParameter("pageNum");
		ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
		ReviewBoardDataBean review =  dbPro.checkpwd(article_no);
		
		request.setAttribute("article_no", new Integer(article_no));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("review", review);
	 
	return "/review/updateForm.jsp";
	}
}
