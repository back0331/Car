package reviewaction;

import javax.websocket.*;

import jdbc.CommandAction;
import reviewboard.ReviewBoardDBBean;
import reviewboard.ReviewBoardDataBean;

import java.text.*;
import java.util.*;

import javax.servlet.http.*;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id="aa";  
		String pwd="1234";
		HttpSession session2 = request.getSession();
		session2.setAttribute("id", id);
		session2.setAttribute("pwd", pwd);
		String user_id = (String)session2.getAttribute("id");
		int pageSize=10;
		//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");
		

		int searchn=0;
		
		if(pageNum==null){
			pageNum="1";
		}
		if(search==null){
			search="";  
		}else{
			searchn=Integer.parseInt(request.getParameter("searchn"));
		}
		  
		
		int currentPage=Integer.parseInt(pageNum);//���� ��������ȣ ����
		int startRow=(currentPage*pageSize)-(pageSize-1);
		int endRow=currentPage*pageSize;
		int count=0;
		int number=0;
		
		
		List articleList =null;
		ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
		count=dbPro.getArticleCount();
		if(search.equals("")||search==null){
			count=dbPro.getArticleCount();}
		else{
			
			count=dbPro.getArticleCount(searchn,search);
		}
		
		if(count>0){
			if(search.equals("")||search==null)
				articleList=dbPro.getArticles(startRow,endRow);
			else
				articleList=dbPro.getArticles(startRow, endRow, searchn, search);
			
			}
		number=count-(currentPage-1)*pageSize;
		
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("search",search);
		request.setAttribute("pageNum",new Integer(pageNum));
		request.setAttribute("searchn", new Integer(searchn));
	    session2.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("articleList", articleList);
		request.setAttribute("user_id", user_id);
		
		return "/review_board/list.jsp";
}
}
