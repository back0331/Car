<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../view/color.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<table border="1">
<tr>
<td>&nbsp;&nbsp;&nbsp;</td><td>아이디</td><td>비밀번호</td><td>이름</td><td>전화번호</td>
<td>주소(우편번호)</td><td>이메일</td><td>등록일</td><td>생년월일</td><td>마일리지</td><td>등급</td>
</tr>
<c:forEach var="userList" items="${userList }" varStatus="status">
<a href="/UserList/UserList/userKick.do">
<td>${userList.status }</td>
<td>${userList.id }</td>
<td>${userList.password }</td>
<td>${userList.name }</td>
<td>${userList.phone }</td>
<td>${userList.address }+${userList.zipcode }</td>
<td>${userList.email }</td>
<td>${userList.reg_date }</td>
<td>${userList.birth }</td>
<td>${userList.point }</td>
<td>${userList.grade }</td>
<input type="hidden" name="${userList.id }" value="${userList.id }">
</a>
</c:forEach>
</table>
</body>
</html>