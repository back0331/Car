<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${result==1}">
<script>
	alert("예약이 취소되었습니다.");
</script>
</c:if>
<c:if test="${result==0}">
<script>
	alert("취소 실패");
</script>
</c:if>
<meta http-equiv="Refresh" content="0;url=/ChaChaCha/myPage/book/myBookView.do">
