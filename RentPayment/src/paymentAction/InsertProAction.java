package paymentAction;
import java.util.*;

import javax.servlet.http.*;
import paymentDTO.*;

public class InsertProAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ArrayList paymentinfolist = new ArrayList();
		//결제에 필요한 값을 가져오기 위해 ArrayList객체 하나 만듬
		
		UserListDataBean uldb = new UserListDataBean();
		BookDataBean bdb = new BookDataBean();
		//User테이블, Book테이블에 접근하기 위해 두개의 객체 생성.
		
		PayListDBBean pdbb = PayListDBBean.getInstance();
		/*int check = pdbb.insertPaymentInfoPro(state);*/
		paymentinfolist = (ArrayList) pdbb.GetPaymentInfo(uldb, bdb);
		System.out.println("GetPaymentInfo메소드 실행 성공");
		int book_no = 0;
		//결제 관련 정보를 받아오는 GetPaymentInfo메소드 실행.
		
		book_no = Integer.parseInt(request.getParameter("book_no"));
		System.out.println("book_no:::"+book_no); // 1
		//imp_uid저장위해 필요
		
		int total_price = (int) paymentinfolist.get(0);
		String email = (String) paymentinfolist.get(1);
		String name = (String) paymentinfolist.get(2);
		String phone = (String) paymentinfolist.get(3);
		String address = (String) paymentinfolist.get(4);
		String zipcode = (String) paymentinfolist.get(5);
		int point = (int) paymentinfolist.get(6);
		//for문으로 하기엔 변수 갯수가 별로 없어서 그냥 변수선언, 값 저장.
		
		return "/Payment/Main_Payment.jsp?total_price="+total_price+"&email="+email+"&name="+
				name+"&phone="+phone+"&address="+address+"&zipcode="+zipcode+"&point="+point+
				"&book_no="+book_no;
		
		//Main_Payment.jsp에 매개변수를 get방식으로 보냄. Main_Payment에 EL태그가 있음. 결제폼의 고정값들을 전송함.
	}
}
/*
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String passwd = request.getParameter("passwd");
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		int check = dbPro.deleteArticle(num, passwd);
		
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("check", new Integer(check));
		
		return "/MVC/deletePro.jsp";
	}
}
*/