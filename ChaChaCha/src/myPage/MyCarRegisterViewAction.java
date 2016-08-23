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
		//���� ��¥ ���ϱ�
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String today = formatter.format(new Date());
//		System.out.println("today:::"+today);
		
		//���ó�¥ �������� �Ⱓ �������� �ƴ��� expired_user_car_list ���̺� ������Ʈ ����
		userCarDb.updateExpiredUserCarList(today);
		
		List userCarList = userCarDb.getUserCarList(id);
		List expiredUserCarList = userCarDb.getExpiredUserCarList(id);
		
		request.setAttribute("userCarList", userCarList);
		request.setAttribute("expiredUserCarList", expiredUserCarList);
		
		return "myCarRegisterView.jsp";
	}

}
