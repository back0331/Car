package qnaaction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import qnaboard.QnABoardDBBean;
import qnaboard.QnABoardDataBean;

public class WriteProAction implements CommandAction {
  
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			request.setCharacterEncoding("UTF-8");
  
			QnABoardDataBean qna= new QnABoardDataBean();
			qna.setArticle_no(Integer.parseInt(request.getParameter("article_no")));
			qna.setId(request.getParameter("id"));
			qna.setPassword(request.getParameter("password"));
			qna.setArticle_subject(request.getParameter("article_subject"));
			qna.setArticle_content(request.getParameter("article_content"));
			qna.setReg_date(new Timestamp(System.currentTimeMillis()));
			qna.setIp(request.getRemoteAddr());
			qna.setArticle_type(request.getParameter("article_type"));
			qna.setCom_check(request.getParameter("com_check"));
			
			QnABoardDBBean dbPro=QnABoardDBBean.getInstance();
			dbPro.insertArticle(qna); 

			return "/qna_board/writePro.jsp";
	}

}
