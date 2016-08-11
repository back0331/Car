<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>등록완료</title>
</head>
<body>
<c:if test="${result==1}">
등록완료
</c:if>
<c:if test="${result==0}">
등록실패
</c:if>
<c:if test="${result==-1}">
user_car_list에 이미 등록되어있습니다.
</c:if>
</body>
</html>