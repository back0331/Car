package myPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDBBean;
import action.CommandAction;

public class MyBookViewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = (String)request.getSession().getAttribute("userId");
		//String id = "test";
		
		int page = 5;
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		int startPage = 1;
		
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 6;
		
		BookDBBean bookDb = BookDBBean.getInstance();
		int totalBook = bookDb.getTotalBookCount(id);
		
		int pageCount = totalBook / pageSize + (totalBook % pageSize==0 ? 0 : 1);
		int start = totalBook - ((currentPage-1) * pageSize); //15
		int end = start - pageSize + 1; //10
		if(end<1){
			end = 1;
		}
		List bookList = bookDb.getBookList(id, start, end);
		request.setAttribute("bookList", bookList);
		
		currentPage = currentPage-1;
		if(currentPage<1) currentPage=1;
		startPage = currentPage / page * page + 1;
		int endPage = startPage + page - 1;
		if(endPage>totalBook/pageSize+1){
			endPage=totalBook/pageSize+1;
		}
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(new Date());
		
		request.setAttribute("today", today);
		
		return "myBookView.jsp";
	}

}
