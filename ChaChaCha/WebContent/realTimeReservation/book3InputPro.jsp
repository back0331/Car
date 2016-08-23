<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
	.b {font-family: 'NanumSquareR' !important; text-align: center;}
	.b h1 {font-size : 23px;}
	.b b {font-size: 20px; color: #00B0F0;}
	div#sec {width:1000px;margin:auto;margin-bottom: 230px;}
	div#a {margin-top : 60px; margin-bottom: 230px;}
	div#a a {text-decoration: none; color : gray;}
	div#a a:HOVER {text-decoration: underline;}
</style>
</head>
<body>
<div id="sec"><img src="../decorators/images/step4.png" width="1000px"/></div>
<div class="b">
	<h1>예약이 완료되었습니다.</h1>
	<b>차차차</b> 를 이용해주셔서 감사합니다.
	<div id="a">
		<a href="/ChaChaCha/myPage/book/myBookView.do">예약내역 확인하기</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="/ChaChaCha/payment/InsertPro.do">결제하기</a> 
	</div>
	
<%-- 	<c:if test="${result==1}"> --%>
<!-- 		<h1>예약이 완료되었습니다.</h1> -->
<!-- 		<b>차차차</b>를 이용해주셔서 감사합니다. -->
<!-- 		<a href="/ChaChaCha/myPage/myBookView.do">예약내역 확인하기</a> -->
<!-- 		<a href="/ChaChaCha/payment/InsertPro.do">결제하기</a> -->
<%-- 	</c:if> --%>
<%-- 	<c:if test="${result==0}"> --%>
<!-- 		예약실패 -->
<%-- 	</c:if> --%>
</div>
</body>
</html>