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

		// MYCAR_REGISTER�� ��ϵ� �� ���� ��Ͽ��� �̸��� ������
		MyCarRegisterDBBean registerDb = MyCarRegisterDBBean.getInstance();
		List myCarNames = registerDb.getMyCarNames(id);
		request.setAttribute("myCarNames", myCarNames);
		
		// CAR�� ��ϵ� ������ Ÿ���� ������
		CarDBBean carDb = CarDBBean.getInstance();
		List carTypeList = carDb.getCarTypes();
		request.setAttribute("carTypeList", carTypeList);
		
		// ���� ����� ������
		AgencyDBBean agencyDb = AgencyDBBean.getInstance();
		List agencyList = agencyDb.getAgencyList();
		request.setAttribute("agencyList", agencyList);
		
		return "myCarRegisterInputForm.jsp";
	}

}
