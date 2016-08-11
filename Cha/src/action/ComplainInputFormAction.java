package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComplainInputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
//		String id = request.getSession().getAttribute("userId");
		String id = "test";
		
		return "complainInputForm.jsp";
	}

}
