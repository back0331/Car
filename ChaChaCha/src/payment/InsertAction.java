package payment;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.BookDataBean;
import bean.PayListDBBean;
import bean.PayListDataBean;
import bean.UserListDataBean;
import action.CommandAction;

public class InsertAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		PayListDataBean pdb = new PayListDataBean();
		BookDataBean bdb = new BookDataBean();
		UserListDataBean uldb = new UserListDataBean();
		
		pdb.setPg(request.getParameter("pg"));
		pdb.setPay_method(request.getParameter("pay_method"));
		pdb.setReg_date(new Timestamp(System.currentTimeMillis()));
		bdb.setTotal_price(Integer.parseInt(request.getParameter("pay_total_price")));
		uldb.setEmail(request.getParameter("email"));
		uldb.setName(request.getParameter("name"));
		uldb.setPhone(request.getParameter("phone"));
		uldb.setAddress1(request.getParameter("address1"));
		uldb.setAddress2(request.getParameter("address2"));
		uldb.setZipcode(request.getParameter("zipcode"));
		uldb.setPoint(Integer.parseInt(request.getParameter("point")));
		//PayListDataBean, BookDataBean, UserListDataBean에 결제에 필요한 값들을 저장해둠.
		//아래의 insertPaymentInfo메소드 실행할 때 필요함.
		
		session.setAttribute("pg", request.getParameter("pg"));
		session.setAttribute("pay_method", request.getParameter("pay_method"));
		session.setAttribute("reg_date", new Timestamp(System.currentTimeMillis()));
		session.setAttribute("total_price", Integer.parseInt(request.getParameter("pay_total_price")));
		session.setAttribute("email", request.getParameter("email"));
		session.setAttribute("name", request.getParameter("name"));
		session.setAttribute("phone", request.getParameter("phone"));
		session.setAttribute("address1", request.getParameter("address1"));
		session.setAttribute("address2", request.getParameter("address2"));
		session.setAttribute("zipcode", request.getParameter("zipcode"));
		session.setAttribute("point", Integer.parseInt(request.getParameter("point")));
		//결제를 하는 javascript코드에 필요한 값들을 세션 영역에 파라미터 형식으로 저장해둠.
		
		PayListDBBean pdbb = PayListDBBean.getInstance();
		String insert_result = pdbb.insertPaymentInfo(pdb, uldb, bdb);
		//해당 book_no에 따른 결제정보를 DB에서 읽어와서 pay_list테이블에 저장하는 메소드. 
		return insert_result;
	}
}
