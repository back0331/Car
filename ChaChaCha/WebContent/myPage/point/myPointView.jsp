<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>
	@font-face{
		font-family: 'NanumSquareR';
		src:url('../decorators/font/NanumSquareR.ttf');
	}
	@font-face{
		font-family: 'NanumSquareB';
		src:url('../decorators/font/NanumSquareB.ttf');
	}
	div#b {width:850px; height:auto; font-family: 'NanumSquareR' !important;}	
	div#p {width: 100%; text-align: center; height: 120px;margin-top: 100px; font-size: 25px;}
	div#p b {font-size: 30px;}
	div#b h2{padding-left:40px;}
	div#list {width:90%; border-top: 2px solid #7F7A7A;margin: auto;}
	div#list table {text-align: center;} 
	div#list table th {background-color: #F9F9F9; height:30px;}
	div#list table td {height:30px;border-bottom: thin solid #DFDEDE;}
</style>
</head>
<body>
<div id="b">
<div id="p">고객님의 현재 마일리지는  <b>${currentPoint}</b> 입니다.</div> 
<h2>마일리지 내역</h2>
<div id="list">

<table cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<th width="400">날짜</th>
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
</div>
</div>
</body>
</html>