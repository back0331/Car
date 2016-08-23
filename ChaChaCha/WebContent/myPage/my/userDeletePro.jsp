<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<c:choose>
		<c:when test="${msg==0}">
			<script>
			alert("비밀번호가 맞지 않습니다.\n다시 확인해주세요.");
			window.history.go(-1);
			</script>
		</c:when>
		<c:when test="${msg==1}">
			<script>
			alert("내 차 등록이 되어 있으므로 탈퇴가 불가능합니다.");
			window.history.go(-1);
			</script>
		</c:when>
		<c:when test="${msg==2}">
			<script>
			alert("현재 차량을 예약중이므로 탈퇴가 불가능합니다.");
			window.history.go(-1);
			</script>
		</c:when>
		<c:when test="${msg==3}">
			<script>
			alert("탈퇴실패");
			</script>
			<meta http-equiv="Refresh" content="0;url=/ChaChaCha/main/main.do">
		</c:when>
		<c:when test="${msg==4}">
			<script>
			alert("탈퇴되었습니다. 차차차를 이용해주셔서 감사합니다.");
			</script>
			<meta http-equiv="Refresh" content="0;url=/ChaChaCha/main/main.do">
		</c:when>
	</c:choose>
</body>
</html>
