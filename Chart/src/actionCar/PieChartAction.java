package actionCar;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean1.CarListDBBean;


public class PieChartAction  implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
				
/*				String pageNum = request.getParameter("pageNum");
							System.out.println(pageNum); */   //ó���� null
/*				if(pageNum == null){
					pageNum="1";
				}*/
				//������ : Operating 9, ������ : Ready 3, ����Ϸ� : Paid 4 , ������: Reparing 16, ��ü�� :  Delay 13
				int Operating=11;
				int Ready=15;
				int Paid =16;
				int Reparing = 24;
				int Delay=18;
				
			
				
			List availableCarList=null	;										//������ ��ġ���� ���� �׸��� �����.
				CarListDBBean dbPro=CarListDBBean.getInstance();
				System.out.println("���� ����ǳ�?");
				
			availableCarList=dbPro.getAvailableCarList(1);	//���� 1�� ��밡���� ������ ����. �׷��� �װ��� LIST ��ü�� �ֱ�.
			
	/*			int pageSize=10;
				int currentPage=Integer.parseInt(pageNum);
				int startRow = (currentPage-1)*pageSize +1;
				int endRow=currentPage*pageSize;
				int count = 0;
				int number =0;*/
				
			/*	List articleList=null;*/
/*				BoardDBBean dbPro=BoardDBBean.getInstance();
				count = dbPro.getArticleCount();
				
				if(count >0){
					
					articleList=dbPro.getArticles(startRow, endRow);
					
				}else{
					articleList=Collections.EMPTY_LIST;
				}
				*/
				
/*				number=count-(currentPage-1)*pageSize;*/
				
/*				request.setAttribute("currentPage", new Integer(currentPage));
				request.setAttribute("startRow", new Integer(startRow));
				request.setAttribute("endRow", new Integer(endRow));
				request.setAttribute("count", new Integer(count));
				request.setAttribute("pageSize", new Integer(pageSize));
				request.setAttribute("number", new Integer(number));
				request.setAttribute("articleList", articleList);*/
				
				
			
				
				
				request.setAttribute("Operating",Operating);
				request.setAttribute("Ready",Ready);
				request.setAttribute("Paid",Paid);
				request.setAttribute("Reparing",Reparing);
				request.setAttribute("Delay",Delay);
				request.setAttribute("availableCarList", availableCarList);
			
				return "/MyPage/pieChart.jsp";
				
	
				
				
				
			}

}
