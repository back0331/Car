package myPage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PointDBBean;
import action.CommandAction;

public class MyPointViewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = (String)request.getSession().getAttribute("userId");
//		String id = "test";
		
		PointDBBean pointDb = PointDBBean.getInstance();
		int currentPoint = pointDb.getMyPoint(id); //현재 내 마일리지
		List pointList = pointDb.getMyPointList(id); //내 마일리지 내역
		
		request.setAttribute("currentPoint", currentPoint);
		request.setAttribute("pointList", pointList);
		
		return "myPointView.jsp";
	}

}
