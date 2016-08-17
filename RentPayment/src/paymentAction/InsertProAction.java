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
		//������ �ʿ��� ���� �������� ���� ArrayList��ü �ϳ� ����
		
		UserListDataBean uldb = new UserListDataBean();
		BookDataBean bdb = new BookDataBean();
		//User���̺�, Book���̺� �����ϱ� ���� �ΰ��� ��ü ����.
		
		PayListDBBean pdbb = PayListDBBean.getInstance();
		/*int check = pdbb.insertPaymentInfoPro(state);*/
		paymentinfolist = (ArrayList) pdbb.GetPaymentInfo(uldb, bdb);
		System.out.println("GetPaymentInfo�޼ҵ� ���� ����");
		int book_no = 0;
		//���� ���� ������ �޾ƿ��� GetPaymentInfo�޼ҵ� ����.
		
		book_no = Integer.parseInt(request.getParameter("book_no"));
		System.out.println("book_no:::"+book_no); // 1
		//imp_uid�������� �ʿ�
		
		int total_price = (int) paymentinfolist.get(0);
		String email = (String) paymentinfolist.get(1);
		String name = (String) paymentinfolist.get(2);
		String phone = (String) paymentinfolist.get(3);
		String address = (String) paymentinfolist.get(4);
		String zipcode = (String) paymentinfolist.get(5);
		int point = (int) paymentinfolist.get(6);
		//for������ �ϱ⿣ ���� ������ ���� ��� �׳� ��������, �� ����.
		
		return "/Payment/Main_Payment.jsp?total_price="+total_price+"&email="+email+"&name="+
				name+"&phone="+phone+"&address="+address+"&zipcode="+zipcode+"&point="+point+
				"&book_no="+book_no;
		
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