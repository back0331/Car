package clientCenter.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.QnACommentDBBean;
public class DelCommentProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			request.setCharacterEncoding("UTF-8");
	
			int comment_no=Integer.parseInt(request.getParameter("comment_no"));
			int article_no=Integer.parseInt(request.getParameter("article_no"));
			String pageNum=request.getParameter("pageNum");

			
			QnACommentDBBean cmtPro=QnACommentDBBean.getInstance();

			 int r = cmtPro.deleteComment(comment_no, article_no);
			 
			 request.setAttribute("comment_no",new Integer(comment_no));
			 request.setAttribute("article_no",new Integer(article_no));
			 request.setAttribute("pageNum", pageNum);
			 request.setAttribute("r", new Integer(r));
			 
		return "/clientCenter/qna_board/delCommentPro.jsp";
	}

}
