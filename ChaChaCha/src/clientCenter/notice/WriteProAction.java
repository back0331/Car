package clientCenter.notice;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.NoticeBoardDBBean;
import bean.NoticeBoardDataBean;

public class WriteProAction implements CommandAction{
  
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		 request.setCharacterEncoding("UTF-8");

		NoticeBoardDataBean article = new NoticeBoardDataBean();
		article.setArticle_type(request.getParameter("article_type"));
		article.setArticle_subject(request.getParameter("article_subject"));
		article.setArticle_content(request.getParameter("article_content"));
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		NoticeBoardDBBean dbPro=NoticeBoardDBBean.getInstance();
		dbPro.insertArticle(article);

		      
		return "/clientCenter/notice/writePro.jsp";
	}

}