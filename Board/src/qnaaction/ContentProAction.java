package qnaaction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import qnaboard.QnACommentDBBean;
import qnaboard.QnACommentDataBean;

public class ContentProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		QnACommentDataBean cmt = new QnACommentDataBean();
		cmt.setArticle_no(Integer.parseInt(request.getParameter("article_no")));
		cmt.setComment_no(Integer.parseInt(request.getParameter("comment_no")));
		cmt.setComment_content(request.getParameter("comment_content"));
		cmt.setReg_date(new Timestamp(System.currentTimeMillis()));
	    QnACommentDBBean comt=QnACommentDBBean.getInstance();  
		
		comt.insertComment(cmt);
		
		String article_no=request.getParameter("article_no");
		String pageNum=request.getParameter("p_num");
		
		request.setAttribute("article_no", new Integer(article_no));
		request.setAttribute("pageNum",new Integer(pageNum));
		
		return "/qna_board/contentPro.jsp";
	}

}
