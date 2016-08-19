<%@page import="bean.CarListJoinCarDataBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="bean.MyCarRegisterDataBean"%>
<%@page import="bean.MyCarRegisterDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<%
	request.setCharacterEncoding("utf-8");

	String id = "test";
	String mycar_name = request.getParameter("mycar_name");
	//System.out.println("mycar::::" + mycar_name);
	//System.out.println("id::::" + id);
	MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
	//MyCarRegisterDataBean myCar = myCarDb.getMycarByName(id, mycar_name);
	CarListJoinCarDataBean myCar = myCarDb.getMycarByName(id, mycar_name);

%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<table>
	<tr><td>차이름</td><td><%=myCar.getMycar_name() %></td></tr>
	<tr><td>차번호</td><td><%=myCar.getCarNumber() %></td></tr>
	<tr><td>색상</td><td><%=myCar.getColor() %></td></tr>
	<tr><td>옵션</td><td><%=myCar.getOptions() %></td></tr>
</table>
</body>
</html>