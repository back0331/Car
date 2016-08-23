package insertingMyCar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AgencyDBBean;
import bean.CarDBBean;
import bean.MyCarRegisterDBBean;
import action.CommandAction;

public class MyCarRegisterInputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = (String)request.getSession().getAttribute("userId");
		//String id = "test";

		// MYCAR_REGISTER에 등록된 내 차량 목록에서 이름만 가져옴
		MyCarRegisterDBBean registerDb = MyCarRegisterDBBean.getInstance();
		List myCarNames = registerDb.getMyCarNames(id);
		request.setAttribute("myCarNames", myCarNames);
		
		// CAR에 등록된 차량의 타입을 가져옴
		CarDBBean carDb = CarDBBean.getInstance();
		List carTypeList = carDb.getCarTypes();
		request.setAttribute("carTypeList", carTypeList);
		
		// 지점 목록을 가져옴
		AgencyDBBean agencyDb = AgencyDBBean.getInstance();
		List agencyList = agencyDb.getAgencyList();
		request.setAttribute("agencyList", agencyList);
		
		return "myCarRegisterInputForm.jsp";
	}

}
