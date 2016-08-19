package administratorPage.userListAction;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.*;

public class ListProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		PayListDataBean pldb = new PayListDataBean();
		BookDataBean bdb = new BookDataBean();
		PayListDBBean pldbb = PayListDBBean.getInstance();
		
		List payList = new ArrayList();
		List idList = new ArrayList();
		try{
			payList = pldbb.getPaymentList(pldb);
			idList = pldbb.getIdList(bdb);
			System.out.println("payList is null??:::"+payList.isEmpty()+", numList is null??:::"+idList.isEmpty());
			request.setAttribute("payList", payList);
			request.setAttribute("idList", idList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/administratorPage/Payment_List/paymentList.jsp";
	}

}
