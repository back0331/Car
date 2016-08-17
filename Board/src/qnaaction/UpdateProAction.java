package qnaaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
import qnaboard.QnABoardDBBean;
import qnaboard.QnABoardDataBean;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
     request.setCharacterEncoding("UTF-8"); 

    QnABoardDataBean qna= new QnABoardDataBean();
    
    qna.setArticle_no(Integer.parseInt(request.getParameter("article_no")));
    qna.setArticle_content(request.getParameter("article_content"));
    qna.setArticle_subject(request.getParameter("article_subject"));
    
    String pageNum = request.getParameter("pageNum");
	QnABoardDBBean dbPro = QnABoardDBBean.getInstance();
    int r = dbPro.updateArticle(qna);
    request.setAttribute("pageNum",new Integer(pageNum));
	request.setAttribute("r", new Integer(r));
	
		return "/qna_board/updatePro.jsp";
	}

}
