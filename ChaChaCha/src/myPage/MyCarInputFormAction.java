package myPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CarDBBean;
import action.CommandAction;

public class MyCarInputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// CAR�� ��ϵ� ������ Ÿ���� ������
		CarDBBean carDb = CarDBBean.getInstance();
		List carTypeList = carDb.getCarTypes();
		request.setAttribute("carTypeList", carTypeList);
		
		return "myCarInputForm.jsp";
	}

}
