package myPage;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import bean.BookDBBean;
import bean.UserCarListDBBean;
import bean.UserListDBBean;
import bean.UserListDataBean;

public class UserDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = (String)request.getSession().getAttribute("userId");
		String password = request.getParameter("password");
		String reason = request.getParameter("reason");
		if(reason.equals("��Ÿ")){
			reason = request.getParameter("etc");
		}
		UserListDBBean userDb = UserListDBBean.getInstance();
		int msg = 0;
		int check = userDb.userCheck(id, password);
		request.setAttribute("check", check);
		
		//��й�ȣ ����
		if(check==1){
			UserCarListDBBean userCarDb = UserCarListDBBean.getInstance();
			//user_car_list�� �ش� ���̵�� ������ ��ϵǾ��ִ��� üũ
			int result = userCarDb.checkCarList(id);
			//��ϵǾ����� ����
			if(result==0){
				//���� ���೻���� �ִ��� üũ
				BookDBBean bookDb = BookDBBean.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				String today = formatter.format(new Date());
				int result2 = bookDb.checkBookList(id, today);
				//���೻���� ����
				if(result2==0){
					//�ش� ���� user_list���� ������ ��, out_user�� ����
					UserListDataBean user = userDb.getMember(id);
					int result3 = userDb.deleteMember(user, reason);
					//��� ���� ����
					if(result3==1){
						msg = 4;
						request.getSession().invalidate();
					}else {
						msg = 3;
					}
				}else {
					msg = 2;
				}
			}else {
				msg=1;
			}
		}else {
		}
		
		request.setAttribute("msg", msg);
		return "userDeletePro.jsp";
	}

}
