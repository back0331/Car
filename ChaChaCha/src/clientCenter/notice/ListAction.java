package clientCenter.notice;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import bean.NoticeBoardDBBean;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
			HttpSession session2 = request.getSession();
			String user_id = (String)session2.getAttribute("userId");
			int pageSize=10;
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
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
			int currentPage=Integer.parseInt(pageNum);

			int startRow=(currentPage*pageSize)-(pageSize-1);
			int endRow=currentPage*pageSize;
			int count=0;
			int number=0;
			
			List articleList =null;
			NoticeBoardDBBean dbPro=NoticeBoardDBBean.getInstance();
			count=dbPro.getArticleCount();//전체 글 수
			
			if(search.equals("")||search==null)
				count=dbPro.getArticleCount();
			else{
				count=dbPro.getArticleCount(searchn,search);
			}
			 
			if(count>0){//글이 있다면
				if(search.equals("")||search==null)
					articleList=dbPro.getArticles(startRow,endRow);
				else
					articleList=dbPro.getArticles(startRow, endRow, searchn, search);
				
			}  
			number=count-(currentPage-1)*pageSize;
				//전체글 갯수-(현재페이지-1)*10;
			
			request.setAttribute("currentPage", new Integer(currentPage));
			request.setAttribute("startRow", new Integer(startRow));
			request.setAttribute("endRow", new Integer(endRow));
			request.setAttribute("number", new Integer(number));
			request.setAttribute("search",search);
			request.setAttribute("searchn", new Integer(searchn));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("pageSize", new Integer(pageSize));
			request.setAttribute("articleList", articleList);
			request.setAttribute("user_id",user_id);
			request.setAttribute("pageNum",new Integer(pageNum));
		return "/clientCenter/notice/list.jsp";
	}

}
