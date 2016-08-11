package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDBBean;

public class MyBookViewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
//		String id = (String)request.getSession().getAttribute("userId");
		String id = "test";
		
		BookDBBean bookDb = BookDBBean.getInstance();
		List bookDataList = bookDb.getBookList(id);
		System.out.println("bookDataList size:::" + bookDataList.size());
		List bookList = bookDb.getBookListDetails(bookDataList);
		System.out.println("bookList size:::" + bookList.size());
		request.setAttribute("bookList", bookList);
		return "myBookView.jsp";
	}

}
