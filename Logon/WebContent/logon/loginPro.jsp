<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf"%>

<c:if test="${check==1}">
<meta http-equiv="Refresh" content="0;url=/Logon/logon/loginForm.do">
<script>
  alert("로그인 완료!!");
</script>
</c:if>
<c:if test="${check==0}">
<script>
  alert("비밀번호가 맞지 않습니다.");
      history.go(-1);
</script>
</c:if>
<c:if test="${check!=0&&check!=1}">
<script>
  alert("아이디가 맞지 않습니다..");
  history.go(-1);
</script>
</c:if>
