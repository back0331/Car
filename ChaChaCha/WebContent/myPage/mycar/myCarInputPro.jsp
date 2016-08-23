<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
<c:if test="${result==1}">등록성공</c:if>
<c:if test="${result==0}">등록실패</c:if>
<br>
<a href="/ChaChaCha/myPage/mycar/myCarView.do">등록내역확인</a>
</body>
</html>