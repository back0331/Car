package realTimeReservation;

import action.CommandAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AgencyDBBean;

public class Book2InputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String rent_date = request.getParameter("rent_date");
		String return_date = request.getParameter("return_date");
		int agency_no = Integer.parseInt(request.getParameter("agency"));
		
		//선택한 예약정보를 전달한다.
		request.setAttribute("agency_no", agency_no);
		request.setAttribute("rent_date", rent_date);
		request.setAttribute("return_date", return_date);
				
		AgencyDBBean agencyDb = AgencyDBBean.getInstance();
		String agency_name = agencyDb.getAgencyName(agency_no);
		request.setAttribute("agency_name", agency_name);
		
		return "book2InputForm.jsp";
	}

}
