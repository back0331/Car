<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<b>${id}</b>
<c:if test="${check eq true}">�� �̹� �����ϴ� ID�Դϴ�. <br/></c:if>
<c:if test="${check ne true}"> ��� �����մϴ�.<br/></c:if>

<a href="#" onclick="history.go(-1)"> �ݱ�</a>


</body>
</html>