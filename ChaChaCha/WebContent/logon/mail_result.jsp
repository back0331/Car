<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="mail.MailSender"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>     
<%   
MultipartRequest mul = new MultipartRequest(request,".","utf-8");
String email = mul.getParameter("email");
int auth = Integer.parseInt(request.getParameter("auth"));
MailSender m= new MailSender();
m.sendStr(email, auth);   
%>
<div>인증번호 : <input type="text" id="acc"  style="width: 60px" >
					<input type="button" value="확인" onclick="check(<%=auth%>)"></div> 
					
</body>
</html>