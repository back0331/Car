package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AgencyDBBean;
import bean.CarListDBBean;
import bean.CarListJoinCarDataBean;
import bean.UserCarListDBBean;

public class Book3InputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String rent_date = request.getParameter("rent_date");
		String return_date = request.getParameter("return_date");
		String car_id_f = request.getParameter("car_id");
		int index = car_id_f.indexOf("/");
		String car_id = car_id_f.substring(0, index);
		String carOwner = car_id_f.substring(index+1); //고객차인지 회사차인지 구분을 위한 변수
		
		CarListDBBean carListDb = CarListDBBean.getInstance();
		UserCarListDBBean userCarListDb = UserCarListDBBean.getInstance();
		//회사차
		CarListJoinCarDataBean car = carListDb.getCarData(car_id);
		if(car.getCarNumber()==null)
			car = userCarListDb.getCarData(car_id);//고객차
		
		AgencyDBBean agencyDb = AgencyDBBean.getInstance();
		String agency_name = agencyDb.getAgencyName(car.getAgency_no());
		
		int days = Integer.parseInt(return_date)-Integer.parseInt(rent_date);
		int price = car.getPrice();
		int total_price = price * days;
		
		request.setAttribute("agency_name", agency_name);
		request.setAttribute("rent_date", rent_date);
		request.setAttribute("return_date", return_date);
		request.setAttribute("car", car);
		request.setAttribute("total_price", total_price);
		request.setAttribute("carOwner", carOwner);
		
		return "book3InputForm.jsp";
	}

}
