<%@page import="javafx.scene.control.Alert"%>
<%@page import="bean.CarListJoinCarDataBean"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="bean.MyCarRegisterDataBean"%>
<%@page import="bean.MyCarRegisterDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");

	String id = (String) request.getSession().getAttribute("userId");
	String mycar_name = request.getParameter("mycar_name");
	//System.out.println("mycar::::" + mycar_name);
	//System.out.println("id::::" + id);
	MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
	//MyCarRegisterDataBean myCar = myCarDb.getMycarByName(id, mycar_name);
	CarListJoinCarDataBean myCar = myCarDb.getMycarByName(id, mycar_name);
	
%>
<html>
<head>
</head>
<body>
	<table cellpadding="0" cellspacing="0">
	<tr><th>차이름</th><td><%=myCar.getMycar_name() %></td></tr>
	<tr><th>차종</th><td><%=myCar.getCar_name() %></td></tr>
	<tr><th>색상</th><td><%=myCar.getColor() %></td></tr>
	<tr><th>차번호</th><td><%=myCar.getCarNumber() %></td></tr>
	<tr><th>옵션</th><td><%=myCar.getOptions() %></td></tr>
	</table>
</body>
</html>