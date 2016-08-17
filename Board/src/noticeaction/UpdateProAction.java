package noticeaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import noticeboard.NoticeBoardDBBean;
import noticeboard.NoticeBoardDataBean;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
 
		request.setCharacterEncoding("UTF-8");

		NoticeBoardDataBean article= new NoticeBoardDataBean();
		article.setArticle_no(Integer.parseInt(request.getParameter("article_no")));
		article.setArticle_subject(request.getParameter("article_subject"));
		article.setArticle_content(request.getParameter("article_content"));
		String pageNum = request.getParameter("pageNum");
		NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
	    int r = dbPro.updateArticle(article);

	    request.setAttribute("pageNum", new Integer(pageNum));
	    request.setAttribute("r", r);
		return "/notice/updatePro.jsp";
	}

}
