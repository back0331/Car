<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>예약내역</title>
</head>
<body>
예약내역
<hr>
<table border="1" cellspacing="0">
	<tr>
		<th>예약날짜</th>
		<th>예약번호</th>
		<th>예약상태</th>
		<th>차량</th>
		<th>기간</th>
		<th>지점</th>
		<th>총금액</th>
	</tr>
	<c:if test="${empty bookList}">
		<tr><td colspan="7">예약내역이 존재하지 않습니다.</td></tr>
	</c:if>
	<c:if test="${!empty bookList}">
		<c:forEach var="book" items="${bookList}">
		<tr>
			<td>${book.reg_date}</td>
			<td>${book.book_no}</td>
			<td>
				<c:if test="${book.state==0}">예약대기<br><input type="button" value="결제하기"/><br><input type="button" value="취소"/></c:if>
				<c:if test="${book.state==1}">예약완료</c:if>
				<c:if test="${book.state==2}">예약취소</c:if>
			</td>
			<td>${book.car_name}</td>
			<td>${book.rent_date}-${book.return_date}</td>
			<td>${book.agency_name}</td>
			<td>${book.total_price}</td>
		</tr>
		</c:forEach>
	</c:if>
</table>
</body>
</html>