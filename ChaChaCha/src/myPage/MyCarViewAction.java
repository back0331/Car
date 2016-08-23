package myPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MyCarRegisterDBBean;
import action.CommandAction;

public class MyCarViewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String id = (String)request.getSession().getAttribute("userId");
//		String id = "test";
		
		MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
		List myCarList = myCarDb.getMyCars(id);
		
		request.setAttribute("myCarList", myCarList);
		
		
		return "myCarView.jsp";
	}

}
