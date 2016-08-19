package realTimeReservation;

import java.util.List;
import action.CommandAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AgencyDBBean;
import bean.AgencyDataBean;

public class Book1InputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		AgencyDBBean agencyDb = AgencyDBBean.getInstance();
		List<AgencyDataBean> agencyList = agencyDb.getAgencyList();
		
		request.setAttribute("agencyList", agencyList);
		
		return "book1InputForm.jsp";
	}

}