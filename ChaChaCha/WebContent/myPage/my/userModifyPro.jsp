<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${result==1}">
	<script>
		alert("회원정보가 수정되었습니다.");
	</script>
</c:if>
<c:if test="${result==0}">
	<script>
		alert("수정 오류");
	</script>
</c:if>
<meta http-equiv="Refresh" content="0;url=/ChaChaCha/myPage/my/userModifyForm.do">