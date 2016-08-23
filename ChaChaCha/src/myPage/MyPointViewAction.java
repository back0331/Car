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
		int currentPoint = pointDb.getMyPoint(id); //���� �� ���ϸ���
		List pointList = pointDb.getMyPointList(id); //�� ���ϸ��� ����
		
		request.setAttribute("currentPoint", currentPoint);
		request.setAttribute("pointList", pointList);
		
		return "myPointView.jsp";
	}

}
