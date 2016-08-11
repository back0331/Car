package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PointDBBean;

public class MyPointViewAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
//		String id = request.getSession().getAttribute("userId");
		String id = "test";
		
		PointDBBean pointDb = PointDBBean.getInstance();
		int currentPoint = pointDb.getMyPoint(id); //���� �� ���ϸ���
		List pointList = pointDb.getMyPointList(id); //�� ���ϸ��� ����
		
		request.setAttribute("currentPoint", currentPoint);
		request.setAttribute("pointList", pointList);
		
		return "myPoint.jsp";
	}

}
