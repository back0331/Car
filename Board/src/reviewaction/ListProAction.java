package reviewaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;

import board.*;
import jdbc.CommandAction;

import java.text.*;
import java.util.*;

import javax.servlet.http.*;

public class ListProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id="java";
		String pwd="1234";
		
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);
		String user_id = (String)session.getAttribute("id");
		
		int pageSize=10;//하나의 페이지에서 보여줄 행의 수
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");
		
		int searchn=0;
		if(pageNum==null){
			pageNum="1";//string 타입으로 문자열 1
		}
		//pagenum이 parameter값으로 넘어오지 않았다면, 맨 첫번째 페이지로.
		if(search==null){
			search="";  
		}else{
			searchn=Integer.parseInt(request.getParameter("searchn"));
		}
		
		int currentPage=Integer.parseInt(pageNum);//현재 페이지번호 저장
		int startRow=(currentPage*pageSize)-(pageSize-1);
		int endRow=currentPage*pageSize;
		int count=0;//전체 글의 갯수
		int number=0;//번호 부분 출력하기 위해 만든 number
		
		List articleList =null;
		ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
		
		if(search.equals("")||search==null) 
			count=dbPro.getArticleCount();//전체 글 수
		else{
			count=dbPro.getArticleCount(searchn,search);
		}
		if(count>0){//글이 있다면
			if(search.equals("")||search==null)
				articleList=dbPro.getArticles(startRow,endRow);
			else
				articleList=dbPro.getArticles(startRow, endRow, searchn, search);
		}
		number=count-(currentPage-1)*pageSize;//전체글 갯수-(현재페이지-1)*10;
		int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		ReviewBoardDataBean review = null;
		System.out.println(articleList.size());
		System.out.println("startRow:::"+startRow+", endRow:::"+endRow);
		for(int i=0;i<articleList.size();i++){
			review=(ReviewBoardDataBean)articleList.get(i);
		}
		session.setAttribute("articleList", articleList);
		session.setAttribute("review", review);
		session.setAttribute("number", number);
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("count", count);
		session.setAttribute("pageSize", pageSize);
		session.setAttribute("pageCount", pageCount);
		return "/review_board/list.jsp";
}
}
