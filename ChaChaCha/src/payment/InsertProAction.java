package payment;
import java.util.*;

import javax.servlet.http.*;

import action.CommandAction;
import bean.*;

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
		request.setAttribute("total_price", total_price);
		request.setAttribute("email", email);
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("zipcode", zipcode);
		request.setAttribute("point", point);
		//for������ �ϱ⿣ ���� ������ ���� ��� �׳� ��������, �� ����.
		
		return "/Payment/Main_Payment.jsp";
		
		//Main_Payment.jsp�� �Ű������� get������� ����. Main_Payment�� EL�±װ� ����. �������� ���������� ������.
	}
}