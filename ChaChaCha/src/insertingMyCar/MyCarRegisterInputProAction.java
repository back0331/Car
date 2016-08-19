package insertingMyCar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MyCarRegisterDBBean;
import bean.MyCarRegisterDataBean;
import bean.UserCarListDBBean;
import bean.UserCarListDataBean;
import action.CommandAction;

public class MyCarRegisterInputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String mycar = request.getParameter("mycar"); //new or 등록된 차이름(=mycar_name)
		
		String id = (String)request.getSession().getAttribute("userId");
		//String id = "test";
		
		/* ---------------mycar가 new면 받지 않는 값---------------- */
		String mycar_name = request.getParameter("mycar_name");
		String cars = request.getParameter("cars");
		int car_no = Integer.parseInt(request.getParameter("cars"));
		String carnumber = request.getParameter("carNumber");
		String color = request.getParameter("color");
		String[] optionsArray = request.getParameterValues("options");
		/*---------------------------------------------*/
		int agency_no = Integer.parseInt(request.getParameter("agency_no"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String checkDate = request.getParameter("checkDate");
		
		MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
		UserCarListDBBean userCarDb = UserCarListDBBean.getInstance();
		int result = 0;
		
		//새로등록하는거라면
		if(mycar.equals("new")){
			String options="";
			if(optionsArray==null){
			}else if(optionsArray.length>0){
				for(int i = 0; i < optionsArray.length; i++){
					if(i==(optionsArray.length-1))
						options += optionsArray[i];
					else
						options += optionsArray[i]+", ";
				}
			}
			MyCarRegisterDataBean myCar = new MyCarRegisterDataBean();
			myCar.setId(id);
			myCar.setMycar_name(mycar_name);
			myCar.setCar_no(car_no);
			myCar.setCarNumber(carnumber);
			myCar.setColor(color);
			myCar.setOptions(options);
			
			myCarDb.insertMyCarRegister(myCar);
			
			mycar = mycar_name;
		} else{
			//mycar_register에 새로등록하는게 아니라면,
			//예약내용을 user_car_list에 삽입하기 전에 이미 존재하는지 체크
			int check = userCarDb.checkCarList(id, mycar);
			//check=0:존재X check=1:존재
			if(check==1){//이미 존재하므로
				result = -1;
			}
		}

		//예약내용 user_car_list에 삽입
		if(result!=-1){
			UserCarListDataBean userCar = new UserCarListDataBean();
			userCar.setId(id);
			userCar.setMyCarName(mycar);
			userCar.setAgency_no(agency_no);
			userCar.setStartDate(startDate);
			userCar.setEndDate(endDate);
			userCar.setCheckDate(checkDate);
			result = userCarDb.insertUserCarList(userCar);
		}
		request.setAttribute("result", result);
		
		return "myCarRegisterInputPro.jsp";
	}
}
