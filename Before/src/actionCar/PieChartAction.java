package actionCar;
  
import java.sql.Connection;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CarListDBBean;
import bean.BookDBBean;
import bean.CarCheckDBBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PieChartAction  implements CommandAction {
	
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws Throwable{
				
/*				String pageNum = request.getParameter("pageNum");
							System.out.println(pageNum); */   //처음엔 null
/*				if(pageNum == null){
					pageNum="1";
				}*/
				//운행중 : Operating 9, 예약대기 : Ready 3, 예약완료 : Paid 4 , 정비중: Reparing 16, 연체중 :  Delay 13
				int Operating=11;
				int Ready=15;
				int Paid =16;
				int Reparing = 24;
				int Delay=18;
				
			       Calendar yesterday = Calendar.getInstance();
			   
			       yesterday.add(Calendar.DATE, -1);
			       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			       String yesterdays= dateFormat.format(yesterday.getTime());
			       
			       Calendar today = Calendar.getInstance();
			       SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
			       String todays=dateFormat.format(today.getTime());
			       System.out.println("today:::" + todays);
			       
			       
			       
			       
			       
			List availableCarList=null	;//데이터 뭉치들을 담을 그릇을 만들고.
			List BookedCarList=null;
			List CheckedCarList=null;
			
			List bookedList=null;
			List checkedList=null;
			
			
			
				CarListDBBean dbPro=CarListDBBean.getInstance();
				BookDBBean dbPro1=BookDBBean.getInstance();
				CarCheckDBBean dbPro2=CarCheckDBBean.getInstance();
				
				System.out.println("여긴 실행되나?");
				
			bookedList=dbPro1.getUnavailableCarList(yesterdays,todays );  //state=1인 car_id 가져옴.
					///얘가 비었다는 것인데....;
			
			checkedList=dbPro2.getUnavailableCarList(yesterdays,todays);  //state=2인 애들 가져옴.

			availableCarList=dbPro.getAvailableCarList(1,bookedList,checkedList);	//지점 1의 사용가능한 차량들 보기. 그래서 그것을 LIST 객체에 넣기.
/*			BookedCarList	=dbPro.getBookedCarList(1,bookedList,checkedList);	
			CheckedCarList  =dbPro.getCheckedCarList(1,bookedList,checkedList);*/
			
			int availableNum	=availableCarList.size();
			int BookedNum		=BookedCarList.size();
			int CheckNum		=CheckedCarList.size();
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
				request.setAttribute("BookedCarList", BookedCarList);
				request.setAttribute("CheckedCarList", CheckedCarList);
				
				request.setAttribute("availableNum", availableNum);
				request.setAttribute("BookedNum", BookedNum);
				request.setAttribute("CheckNum", CheckNum);
				
				
				
				System.out.println(availableNum);
				System.out.println(BookedNum);
				System.out.println(CheckNum);
				
				
				return "/MyPage/pieChart.jsp";
				
	
			
				
				
			}

}







 
 
