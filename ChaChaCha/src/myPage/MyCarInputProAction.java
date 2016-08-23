package myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.CommandAction;

import bean.MyCarRegisterDBBean;
import bean.MyCarRegisterDataBean;

public class MyCarInputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			
		request.setCharacterEncoding("utf-8");
		
		String id = (String)request.getSession().getAttribute("userId");
//		String id = "test";
		
		String mycar_name = request.getParameter("mycar_name");
		int car_no = Integer.parseInt(request.getParameter("cars"));
		String carnumber = request.getParameter("carNumber");
		String color = request.getParameter("color");
		String[] optionsArray = request.getParameterValues("options");
		
		String options="";
		if(optionsArray==null){}
		else if(optionsArray.length>0){
			for(int i = 0; i < optionsArray.length; i++){
				if(i==(optionsArray.length-1))
					options += optionsArray[i];
				else
					options += optionsArray[i]+",";
			}
		}
		
//		System.out.println(mycar_name);
//		System.out.println(car_no);
//		System.out.println(carnumber);
//		System.out.println(color);
//		System.out.println(options);
		
		int result = 0;
		MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
		MyCarRegisterDataBean myCar = new MyCarRegisterDataBean();
		myCar.setId(id);
		myCar.setMycar_name(mycar_name);
		myCar.setCar_no(car_no);
		myCar.setCarNumber(carnumber);
		myCar.setColor(color);
		myCar.setOptions(options);
		result = myCarDb.insertMyCarRegister(myCar);
		
		request.setAttribute("result", result);
		
		return "myCarInputPro.jsp";
	}

}
