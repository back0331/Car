package qnaaction;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qnaaction.CommandAction;
import qnaboard.QnABoardDBBean;
import qnaboard.QnACommentDBBean;

public class ListAction implements CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
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
			  
			
			int currentPage=Integer.parseInt(pageNum);//현재 페이지번호 저장
			int startRow=(currentPage*pageSize)-(pageSize-1);
			int endRow=currentPage*pageSize;
			int count=0;
			int number=0;
			
			
			List articleList =null;
			QnABoardDBBean dbPro=QnABoardDBBean.getInstance();
			count=dbPro.getArticleCount();
			
			if(search.equals("")||search==null)
				count=dbPro.getArticleCount();
			else{
				
				count=dbPro.getArticleCount(searchn,search);
			}
				
			 QnACommentDBBean cdb=QnACommentDBBean.getInstance();
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
			request.setAttribute("searchn", new Integer(searchn));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("articleList", articleList);
			request.setAttribute("user_id", user_id);
		
			return "/qna_board/list.jsp";
		
	}

	}
