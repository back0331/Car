package administratorPage.userListAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.PayListDBBean;

public class PaymentCancelAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int book_no = Integer.parseInt(request.getParameter("book_no"));
		String imp_uid = "";
		PayListDBBean pldbb = PayListDBBean.getInstance();
		try{
			imp_uid=pldbb.getImp_uid(book_no);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/administratorPage/Payment_List/cancelPayment.jsp";
	}

}
