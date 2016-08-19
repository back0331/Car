package myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MyCarRegisterDBBean;
import bean.UserCarListDBBean;
import action.CommandAction;

public class MyCarDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			
		request.setCharacterEncoding("utf-8");
		
		String id = (String)request.getSession().getAttribute("userId");
//		String id = "test";
		String mycar_name = request.getParameter("mycar_name");
		
		UserCarListDBBean userCarDb = UserCarListDBBean.getInstance();
		int result = userCarDb.checkCarList(id, mycar_name);
		if(result>0){
			result = -1;
		} else if(result==0){
			MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
			result = myCarDb.deleteMyCar(id, mycar_name);
		}
		request.setAttribute("result", result);
		//-1 삭제불가능 0 삭제실패 1 삭제완료
		return "myCarDeletePro.jsp";
	}

}
