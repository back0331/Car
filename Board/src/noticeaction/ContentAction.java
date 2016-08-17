package noticeaction;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.CommandAction;
import noticeboard.NoticeBoardDBBean;
import noticeboard.NoticeBoardDataBean;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

			HttpSession session2 = request.getSession();
			String user_id = (String)session2.getAttribute("id");
		    int article_no = Integer.parseInt(request.getParameter("article_no"));
		    String pageNum = request.getParameter("pageNum");

		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		   NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
		   NoticeBoardDataBean article = dbPro.getArticle(article_no);
		   
		   request.setAttribute("user_id", user_id);
		   request.setAttribute("pageNum", new Integer(pageNum));
		   request.setAttribute("article", article);
		   
		       
		return "/notice/content.jsp";
	}

}
