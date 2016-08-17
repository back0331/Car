package paymentAction;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paymentDTO.*;

public class InsertAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		PayListDataBean pldb = new PayListDataBean();    
		BookDataBean bdb = new BookDataBean();
		UserListDataBean uldb = new UserListDataBean();
		int book_no = 0;
		
		pldb.setPg(request.getParameter("pg"));
		pldb.setPay_method(request.getParameter("pay_method"));
		pldb.setReg_date(new Timestamp(System.currentTimeMillis()));
		pldb.setPay_Total_price(Integer.parseInt(request.getParameter("pay_total_price")));
		uldb.setEmail(request.getParameter("email"));
		uldb.setName(request.getParameter("name"));
		uldb.setPhone(request.getParameter("phone"));
		uldb.setAddress(request.getParameter("address"));
		uldb.setZipcode(request.getParameter("zipcode"));
		uldb.setPoint(Integer.parseInt(request.getParameter("point")));
		//PayListDataBean, UserListDataBean�� ������ �ʿ��� ������ �����ص�.
		//�Ʒ��� insertPaymentInfo�޼ҵ� ������ �� �ʿ���.	
		
		request.setAttribute("pg", request.getParameter("pg"));
		request.setAttribute("pay_method", request.getParameter("pay_method"));
		request.setAttribute("reg_date", new Timestamp(System.currentTimeMillis()));
		request.setAttribute("total_price", Integer.parseInt(request.getParameter("pay_total_price")));
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("phone", request.getParameter("phone"));
		request.setAttribute("address", request.getParameter("address"));
		request.setAttribute("zipcode", request.getParameter("zipcode"));
		request.setAttribute("point", Integer.parseInt(request.getParameter("point")));
		request.setAttribute("book_no", request.getParameter("book_no"));
		//������ �ϴ� json�ڵ尡 �ִ� IamportTest.jsp�� �ʿ��� ���� �Ķ���ͷ� setting.
		book_no = Integer.parseInt(request.getParameter("book_no"));
		
		PayListDBBean pdbb = PayListDBBean.getInstance();
		String insert_result = pdbb.insertPaymentInfo(pldb, uldb, bdb, book_no);
		//�ش� book_no�� ���� ���������� DB���� �о�ͼ� pay_list���̺� �����ϴ� �޼ҵ�.
		//insertPaymentInfo�޼ҵ�� imp_uid�� �������� ����. �켱 0�� ������.
		return insert_result;
	}
}
