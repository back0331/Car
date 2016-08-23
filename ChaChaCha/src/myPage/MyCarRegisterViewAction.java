package myPage;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.UserCarListDBBean;

public class MyCarRegisterViewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = (String)request.getSession().getAttribute("userId");
		UserCarListDBBean userCarDb = UserCarListDBBean.getInstance();
		//오늘 날짜 구하기
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String today = formatter.format(new Date());
//		System.out.println("today:::"+today);
		
		//오늘날짜 기준으로 기간 만료인지 아닌지 expired_user_car_list 테이블 업데이트 먼저
		userCarDb.updateExpiredUserCarList(today);
		
		List userCarList = userCarDb.getUserCarList(id);
		List expiredUserCarList = userCarDb.getExpiredUserCarList(id);
		
		request.setAttribute("userCarList", userCarList);
		request.setAttribute("expiredUserCarList", expiredUserCarList);
		
		return "myCarRegisterView.jsp";
	}

}
