package clientCenter.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.QnABoardDBBean;
import bean.QnABoardDataBean;

public class LockAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		  String pageNum = request.getParameter("pageNum");
		  QnABoardDBBean dbPro = QnABoardDBBean.getInstance();
		  QnABoardDataBean qna =  dbPro.checkpwd(article_no);
		  
		  request.setAttribute("article_no", new Integer(article_no));
		  request.setAttribute("pageNum", new Integer(pageNum));
		  request.setAttribute("qna", qna);

		return "/clientCenter/qna_board/lock.jsp";
	}

}
