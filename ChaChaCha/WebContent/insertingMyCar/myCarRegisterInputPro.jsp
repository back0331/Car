<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	.b {font-family: 'NanumSquareR' !important; text-align: center; margin-top: 270px;}
	.b h1 {font-size : 23px;}
	div#a {margin-top : 60px; margin-bottom: 300px;}
	div#a a {text-decoration: none; color : gray;}
	div#a a:HOVER {text-decoration: underline;}
</style>
</head>
<body>
<c:if test="${result==1}">
	<div class="b">
		<h1>등록이 완료되었습니다.</h1>
		<b>차차차</b> 를 이용해주셔서 감사합니다.
		<div id="a">
			<a href="/ChaChaCha/myPage/register/myCarRegisterView.do">등록내역 확인하기</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		
	</div>
</c:if>

<%-- <c:if test="${result==0}"> --%>
<!-- 등록실패 -->
<%-- </c:if> --%>

<c:if test="${result==-1}">
	<div class="b">
		<h1>해당 차량은 이미 등록되어있습니다.</h1>
		 다시 확인해 주시기 바랍니다.
		<div id="a">
			<a href="/ChaChaCha/myPage/register/myCarRegisterView.do">등록내역 확인하기</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	
	</div>
</c:if>
</body>
</html>