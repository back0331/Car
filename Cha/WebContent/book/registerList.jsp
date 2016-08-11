<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="bean.MyCarRegisterDataBean"%>
<%@page import="bean.MyCarRegisterDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");

	String id = "test";
	String mycar_name = request.getParameter("mycar_name");
	//System.out.println("mycar::::" + mycar_name);
	//System.out.println("id::::" + id);
	MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
	MyCarRegisterDataBean myCar = myCarDb.getMycarByName(id, mycar_name);

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