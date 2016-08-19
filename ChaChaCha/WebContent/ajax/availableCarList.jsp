<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="bean.UserCarListDBBean"%>
<%@page import="bean.CarListDBBean"%>
<%@page import="bean.CarCheckDBBean"%>
<%@page import="java.util.List"%>
<%@page import="bean.BookDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<%
	request.setCharacterEncoding("utf-8");

	String car_type = request.getParameter("type");
	String rent_date = request.getParameter("rent_date");
	String return_date = request.getParameter("return_date");
	int agency_no = Integer.parseInt(request.getParameter("agency_no"));
//  System.out.print(type);
//  System.out.print(rent_date);
//  System.out.print(return_date);
//  System.out.print(agency_no);
	
	//리스트 불러오기
	//book테이블에서 지정한 날짜에 사용중인 차량의 목록을 가져온다. car_id리스트
	BookDBBean bookDb = BookDBBean.getInstance();
	List bookUnavailableCarList = bookDb.getUnavailableCarList(rent_date, return_date);
	//check테이블에서 지정한 날짜에 수리중인 차량의 목록을 가져온다. car_id리스트
	CarCheckDBBean checkingDb = CarCheckDBBean.getInstance();
	List checkUnavailableCarList = checkingDb.getUnavailableCarList(rent_date, return_date);

	//회사차
	CarListDBBean carListDb = CarListDBBean.getInstance();
	UserCarListDBBean userCarListDb = UserCarListDBBean.getInstance();
	List availableCarList = new ArrayList();
	List availableUserCarList = new ArrayList();
	if(car_type.equals("all")){
		availableCarList = carListDb.getAvailableCarList(agency_no, bookUnavailableCarList, checkUnavailableCarList);
		availableUserCarList = userCarListDb.getAvailableUserCarList(agency_no, rent_date, return_date, bookUnavailableCarList);
	}else{
		availableCarList = carListDb.getAvailableCarList(car_type, agency_no, bookUnavailableCarList, checkUnavailableCarList);
		availableUserCarList = userCarListDb.getAvailableUserCarList(car_type, agency_no, rent_date, return_date, bookUnavailableCarList);
	}

	//System.out.println("size:::" + availableCarList.size());
	request.setAttribute("availableCarList", availableCarList);
	request.setAttribute("availableUserCarList", availableUserCarList);
		
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<table id="table">
<tr>
		<th>차ID</td>
		<th>지점</td>
		<th>차타입</th>
		<th>차명</td>
		<th>옵션</td>
		<th>색상</td>
		<th>가격</td>
		<th>선택</th>
</tr>
<c:if test="${empty availableCarList && empty availableUserCarList}">
	<tr><td colspan="8">가능한 차가 없습니다.</td></tr>
</c:if>
<c:if test="${!empty availableCarList}">
<c:forEach var="car" items="${availableCarList}">
	<tr>
		<td>${car.car_id}</td>
		<td>${car.agency_no}</td>
		<td>${car.car_type}</td>
		<td>${car.car_name}</td>
		<td>${car.options}</td>
		<td>${car.color}</td>
		<td>${car.price}</td>
		<td><input type="radio" name="car_id" value="${car.car_id}/c"/></td>
	</tr>
</c:forEach>
</c:if>
<c:if test="${!empty availableUserCarList}">
	<tr><td colspan="8">UserCarList</td></tr>
	<c:forEach var="userCar" items="${availableUserCarList}">
		<tr>
			<td>${userCar.car_id}</td>
			<td>${userCar.agency_no}</td>
			<td>${userCar.car_type}</td>
			<td>${userCar.car_name}</td>
			<td>${userCar.options}</td>
			<td>${userCar.color}</td>
			<td>${userCar.price}</td>
			<td><input type="radio" name="car_id" value="${userCar.car_id}/u"/></td>
		</tr>
	</c:forEach>
</c:if>
</table>
</body>
</html>