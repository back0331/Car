package paymentListAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paymentAction.CommandAction;
import paymentListDTO.PayListDBBean;

public class ListCancelProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int book_no = 0;
		book_no = Integer.parseInt(request.getParameter("book_no"));
		PayListDBBean pldbb = PayListDBBean.getInstance();
		
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/Payment_List/cancelPayment.jsp";
	}

}
