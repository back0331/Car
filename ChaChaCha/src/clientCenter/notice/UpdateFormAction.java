package clientCenter.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.NoticeBoardDBBean;
import bean.NoticeBoardDataBean;


public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		  int article_no = Integer.parseInt(request.getParameter("article_no"));
     	  String pageNum=request.getParameter("pageNum");
		  
		  NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
		  NoticeBoardDataBean article =  dbPro.updateGetArticle(article_no);
		
		  request.setAttribute("article_no", article_no);
		  request.setAttribute("pageNum", pageNum);
		  request.setAttribute("article", article);
		  
		  return "/clientCenter/notice/updateForm.jsp";
	}

}
