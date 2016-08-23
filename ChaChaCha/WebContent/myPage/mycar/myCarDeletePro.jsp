<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${result==-1}">
		<script>alert("등록되어있는 차량 정보이므로 삭제가 불가능합니다.");</script>
<!-- 		삭제불가능. user_car_list에 등록되어있는 차량 -->
	</c:when>
	<c:when test="${result==0}">
		<script>alert("삭제오류");</script>
<!-- 		삭제실패 -->
	</c:when>
	<c:when test="${result==1}">
		<script>alert("해당 차량이 삭제되었습니다.");</script>
<!-- 		삭제완료 -->
	</c:when>
</c:choose>
<meta http-equiv="Refresh" content="0;url=/ChaChaCha/myPage/mycar/myCarView.do">
