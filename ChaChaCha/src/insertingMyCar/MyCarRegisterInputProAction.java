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
		
		String mycar = request.getParameter("mycar"); //new or ��ϵ� ���̸�(=mycar_name)
		
		String id = (String)request.getSession().getAttribute("userId");
		//String id = "test";
		
		/* ---------------mycar�� new�� ���� �ʴ� ��---------------- */
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
		
		//���ε���ϴ°Ŷ��
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
			//mycar_register�� ���ε���ϴ°� �ƴ϶��,
			//���೻���� user_car_list�� �����ϱ� ���� �̹� �����ϴ��� üũ
			int check = userCarDb.checkCarList(id, mycar);
			//check=0:����X check=1:����
			if(check==1){//�̹� �����ϹǷ�
				result = -1;
			}
		}

		//���೻�� user_car_list�� ����
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
