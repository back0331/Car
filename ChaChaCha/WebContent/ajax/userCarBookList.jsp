<%@page import="java.util.List"%>
<%@page import="bean.BookDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");

	String car_id = request.getParameter("car_id");
	BookDBBean bookDb = BookDBBean.getInstance();
	List userCarBookList = bookDb.getUserCarBookList(car_id);
	
	request.setAttribute("userCarBookList", userCarBookList);
	
	
%>
<html>
<head>
<style>
	table#ul th {border-bottom: 1px solid black;border-top:1px solid black;}
	table#ul td {border-bottom: thin solid black; height: 30px;}
</style>
</head>
<body>
	<h4 style="text-align: left; padding-left:40px;padding-top:20px;">해당 차량의 예약리스트</h4>
	<table id="ul" width="90%" cellpadding="0" cellspacing="0" style="margin-bottom: 20px;">
		<tr>
			<th>예약일자</th>
			<th>예약번호</th>
			<th>예약자 ID</th>
			<th>대여일자</th>
			<th>반납일자</th>
			<th>예약상태</th>
		</tr>
		<c:if test="${empty userCarBookList}">
			<tr><td colspan="6">해당 차량의 예약 정보가 존재하지 않습니다.</td></tr>
		</c:if>
		<c:if test="${!empty userCarBookList}">
			<c:forEach var="book" items="${userCarBookList}">
				<tr>
					<td>${book.reg_date}</td>
					<td>${book.book_no}</td>
					<td>${book.id}</td>
					<td>${book.rent_date}</td>
					<td>${book.return_date}</td>
					<td>
						<c:if test="${book.state==0}">예약대기</c:if>
						<c:if test="${book.state==1}">예약완료</c:if>
						<c:if test="${book.state==2}">예약취소</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>