<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result==1}">
	<script>alert("회원가입이 완료되었습니다.");
	
	</script>
	
	
</c:if>
<c:if test="${result==0}">
	<script>alert("회원가입 실패");</script>
</c:if>
<meta http-equiv="Refresh" content="0;url=/ChaChaCha/logon/loginForm.do">

