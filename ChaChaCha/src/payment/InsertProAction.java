package payment;
import java.util.*;

import javax.servlet.http.*;
import action.CommandAction;
import bean.BookDBBean;
import bean.BookDataBean;
import bean.BookListDataBean;
import bean.PayListDBBean;
import bean.UserListDataBean;

public class InsertProAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userId");
		//String id = "test";
		ArrayList paymentinfolist = new ArrayList();
		//������ �ʿ��� ���� �������� ���� ArrayList��ü �ϳ� ����
		
		BookDBBean bookDb = BookDBBean.getInstance();
		BookDataBean bdb = bookDb.getBook(id);
		//User���̺�, Book���̺� �����ϱ� ���� �ΰ��� ��ü ����.
		
		PayListDBBean pdbb = PayListDBBean.getInstance();
		/*int check = pdbb.insertPaymentInfoPro(state);*/
		paymentinfolist = (ArrayList) pdbb.GetPaymentInfo(bdb);
		System.out.println("GetPaymentInfo�޼ҵ� ���� ����");
		//���� ���� ������ �޾ƿ��� GetPaymentInfo�޼ҵ� ����.
		
		int total_price = (int) paymentinfolist.get(0);
		String email = (String) paymentinfolist.get(1);
		String name = (String) paymentinfolist.get(2);
		String phone = (String) paymentinfolist.get(3);
		String address = (String) paymentinfolist.get(4);
		String zipcode = (String) paymentinfolist.get(5);
		int point = (int) paymentinfolist.get(6);
		//for������ �ϱ⿣ ���� ������ ���� ��� �׳� ��������, �� ����.
		
		return "/payment/Main_Payment.jsp?total_price="+total_price+"&email="+email+"&name="+
				name+"&phone="+phone+"&address="+address+"&zipcode="+zipcode+"&point="+point;
		//Main_Payment.jsp�� �Ű������� get������� ����. Main_Payment�� EL�±װ� ����. �������� ���������� ������.
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