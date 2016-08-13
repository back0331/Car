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
		
		int pageSize=10;//�ϳ��� ���������� ������ ���� ��
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");
		
		int searchn=0;
		if(pageNum==null){
			pageNum="1";//string Ÿ������ ���ڿ� 1
		}
		//pagenum�� parameter������ �Ѿ���� �ʾҴٸ�, �� ù��° ��������.
		if(search==null){
			search="";  
		}else{
			searchn=Integer.parseInt(request.getParameter("searchn"));
		}
		
		int currentPage=Integer.parseInt(pageNum);//���� ��������ȣ ����
		int startRow=(currentPage*pageSize)-(pageSize-1);
		int endRow=currentPage*pageSize;
		int count=0;//��ü ���� ����
		int number=0;//��ȣ �κ� ����ϱ� ���� ���� number
		
		List articleList =null;
		ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
		
		if(search.equals("")||search==null) 
			count=dbPro.getArticleCount();//��ü �� ��
		else{
			count=dbPro.getArticleCount(searchn,search);
		}
		if(count>0){//���� �ִٸ�
			if(search.equals("")||search==null)
				articleList=dbPro.getArticles(startRow,endRow);
			else
				articleList=dbPro.getArticles(startRow, endRow, searchn, search);
		}
		number=count-(currentPage-1)*pageSize;//��ü�� ����-(����������-1)*10;
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
