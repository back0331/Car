<%@page import="bean.CarDataBean"%>
<%@page import="java.util.List"%>
<%@page import="bean.CarDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String car_type = request.getParameter("car_type");
	//System.out.println("car_type::::" + car_type);
	CarDBBean carDb = CarDBBean.getInstance();
	List cars = carDb.getCars(car_type);
	//System.out.println("cars:::::"+cars.size());
		out.print("<option value=0>선택해주세요</option>");
	for(int i=0;i<cars.size();i++){
		CarDataBean car = (CarDataBean)cars.get(i);
		int car_no = car.getCar_no();
		String name = car.getCar_name();
		//System.out.println("name::::" + name);
		out.print("<option value="+car_no+">"+name+"</option> ");
	}
%>


