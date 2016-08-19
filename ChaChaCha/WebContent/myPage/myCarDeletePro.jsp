<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${result==-1}">
		삭제불가능. user_car_list에 등록되어있는 차량
	</c:when>
	<c:when test="${result==0}">
		삭제실패
	</c:when>
	<c:when test="${result==1}">
		삭제완료
	</c:when>
</c:choose>
<a href="/ChaChaCha/myPage/myCarView.do">내 차 보기</a>
</body>
</html>