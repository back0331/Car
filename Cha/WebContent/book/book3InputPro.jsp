<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>예약완료</title>
</head>
<body>
<c:if test="${result==1}">
	예약되었습니다.<br>
	<a href="myBookView.do">예약내역 확인하기</a>
	<a href="#">결제하기</a>
</c:if>
<c:if test="${result==0}">
	예약실패
</c:if>


</body>
</html>