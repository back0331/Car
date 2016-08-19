<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="../decorators/script1.js" ></script>

<c:if test="${check==1}">


<script>
window.opener.location.href="/ChaChaCha/main/main.do";
self.close();
	
/* window.opener.location.reload();
window.close(); */
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
