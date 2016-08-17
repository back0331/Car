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
		paymentinfolist = (ArrayList) pdbb.GetPaymentInfo(uldb, bdb);
		System.out.println("GetPaymentInfo�޼ҵ� ���� ����");
		int book_no = 0;
		//���� ���� ������ �޾ƿ��� GetPaymentInfo�޼ҵ� ����.
		
		book_no = Integer.parseInt(request.getParameter("book_no"));
		//imp_uid�������� �ʿ�
		request.setAttribute("book_no", book_no);
		
		int total_price = (int) paymentinfolist.get(0);
		String email = (String) paymentinfolist.get(1);
		String name = (String) paymentinfolist.get(2);
		String phone = (String) paymentinfolist.get(3);
		String address = (String) paymentinfolist.get(4);
		String zipcode = (String) paymentinfolist.get(5);
		int point = (int) paymentinfolist.get(6);
		//for������ �ϱ⿣ ���� ������ ���� ��� �׳� ��������, �� ����.
		
		return "/Payment/Main_Payment.jsp?total_price="+total_price+"&email="+email+"&name="+
				name+"&phone="+phone+"&address="+address+"&zipcode="+zipcode+"&point="+point;
		
		//Main_Payment.jsp�� �Ű������� get������� ����. Main_Payment�� EL�±װ� ����. �������� ���������� ������.
	}
}