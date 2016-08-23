package clientCenter.qna;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import bean.QnABoardDBBean;
import bean.QnABoardDataBean;
import bean.QnACommentDBBean;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session2 = request.getSession();
		String user_id = (String)session2.getAttribute("userId");
	   	String pageNum = request.getParameter("pageNum");
	   	int article_no = Integer.parseInt(request.getParameter("article_no"));
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   
		   
		   QnABoardDBBean dbPro = QnABoardDBBean.getInstance();
		   QnABoardDataBean qna =  dbPro.getArticle(article_no); 
	 	
	      QnACommentDBBean cdb=QnACommentDBBean.getInstance();
		  ArrayList comments=cdb.getComments(article_no);
		  int count=cdb.getCommentCount(qna.getArticle_no());
			
	
		
		request.setAttribute("pageNum", new Integer(pageNum));
		session2.setAttribute("user_id", user_id);
		request.setAttribute("qna", qna);
		request.setAttribute("comments",comments);
		request.setAttribute("count", new Integer(count));
	 
	   return "/clientCenter/qna_board/content.jsp"; 
	
	}
}
