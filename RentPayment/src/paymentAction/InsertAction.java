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
		//PayListDataBean, BookDataBean, UserListDataBean�� ������ �ʿ��� ������ �����ص�.
		//�Ʒ��� insertPaymentInfo�޼ҵ� ������ �� �ʿ���.	
/*		session.setAttribute("pg", request.getParameter("pg"));
		session.setAttribute("pay_method", request.getParameter("pay_method"));
		session.setAttribute("reg_date", new Timestamp(System.currentTimeMillis()));
		session.setAttribute("total_price", Integer.parseInt(request.getParameter("pay_total_price")));
		session.setAttribute("email", request.getParameter("email"));
		session.setAttribute("name", request.getParameter("name"));
		session.setAttribute("phone", request.getParameter("phone"));
		session.setAttribute("address", request.getParameter("address"));
		session.setAttribute("zipcode", request.getParameter("zipcode"));
		session.setAttribute("point", Integer.parseInt(request.getParameter("point")));*/
		//������ �ϴ� javascript�ڵ忡 �ʿ��� ������ ���� ������ �Ķ���� �������� �����ص�.
		System.out.println(request.getParameter("book_no"));
		book_no = Integer.parseInt(request.getParameter("book_no"));
		
		PayListDBBean pdbb = PayListDBBean.getInstance();
		String insert_result = pdbb.insertPaymentInfo(pldb, uldb, bdb, book_no);
		//�ش� book_no�� ���� ���������� DB���� �о�ͼ� pay_list���̺� �����ϴ� �޼ҵ�. 
		return insert_result;
	}
}
