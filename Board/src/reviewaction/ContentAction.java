package reviewaction;

import java.text.SimpleDateFormat;

import javax.servlet.http.*;
import board.*;
import jdbc.CommandAction;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		/*String id = (String)session.getAttribute("id");*/
		int article_no = Integer.parseInt(request.getParameter("article_no"));
		/*int pageNum = (int) session.getAttribute("currentPage");*/

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try{
			ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
			ReviewBoardDataBean article = dbPro.getArticle(article_no);
			session.setAttribute("article_no", article.getArticle_no());//앞으로의 과정에서 중요한 번호이므로 세션에 저장.
			request.setAttribute("book_no", article.getBook_no());
			request.setAttribute("article_subject", article.getArticle_subject());
			request.setAttribute("article_content", article.getArticle_content());
			session.setAttribute("password", article.getPassword());//수정시 필요함. 세션에 저장 필요
			request.setAttribute("reg_date", sdf.format(article.getReg_date()));
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return "/review_board/content.jsp";
	
	}
}
