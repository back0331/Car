<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

<label><h1>고객님의 현재 마일리지는 <b>${currentPoint}</b>입니다.</h1></label>
<br><br>
<h2>마일리지 내역</h2>
<hr>
<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<th>날짜</th>
		<th>상태</th>
		<th>금액</th>
	</tr>
	<c:if test="${empty pointList}">
		<tr><td colspan="3">내역이 존재하지 않습니다.</td></tr>
	</c:if>
	<c:if test="${!empty pointList}">
		<c:forEach var="point" items="${pointList}">
			<tr>
				<td>${point.reg_date}</td>
				<td>${point.type}</td>
				<td>${point.amount}</td>
			</tr>
		</c:forEach>
	</c:if>
</table>
</body>
</html>