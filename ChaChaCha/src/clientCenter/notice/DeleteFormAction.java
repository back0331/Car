package clientCenter.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		  int article_no = Integer.parseInt(request.getParameter("article_no"));
		  String pageNum = request.getParameter("pageNum");
		  
		  request.setAttribute("pageNum", pageNum);
		  
		return "/clientCenter/notice/deleteForm.jsp";
	}

}
