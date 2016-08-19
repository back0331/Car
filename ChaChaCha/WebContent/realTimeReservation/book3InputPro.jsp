<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div>
	<c:if test="${result==1}">
		예약되었습니다.<br>
		<a href="/ChaChaCha/myPage/myBookView.do">예약내역 확인하기</a>
		<a href="/ChaChaCha/payment/InsertPro.do">결제하기</a>
	</c:if>
	<c:if test="${result==0}">
		예약실패
	</c:if>
</div>
</body>
</html>