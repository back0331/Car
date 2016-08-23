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
		if(reason.equals("기타")){
			reason = request.getParameter("etc");
		}
		UserListDBBean userDb = UserListDBBean.getInstance();
		int msg = 0;
		int check = userDb.userCheck(id, password);
		request.setAttribute("check", check);
		
		//비밀번호 맞음
		if(check==1){
			UserCarListDBBean userCarDb = UserCarListDBBean.getInstance();
			//user_car_list에 해당 아이디로 차량이 등록되어있는지 체크
			int result = userCarDb.checkCarList(id);
			//등록되어있지 않음
			if(result==0){
				//현재 예약내역이 있는지 체크
				BookDBBean bookDb = BookDBBean.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				String today = formatter.format(new Date());
				int result2 = bookDb.checkBookList(id, today);
				//예약내역이 없음
				if(result2==0){
					//해당 유저 user_list에서 삭제한 후, out_user에 삽입
					UserListDataBean user = userDb.getMember(id);
					int result3 = userDb.deleteMember(user, reason);
					//멤버 삭제 성공
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
