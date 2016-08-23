package myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.BookDBBean;

public class MyBookDeleteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int book_no = Integer.parseInt(request.getParameter("book_no"));
		BookDBBean bookDb = BookDBBean.getInstance();
		int result = bookDb.deleteBook(book_no);
		
		request.setAttribute("result", result);
 		return "myBookDelete.jsp";
	}

}
