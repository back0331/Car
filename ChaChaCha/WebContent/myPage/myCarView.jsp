<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
	function check(){
		//alert("삭제");
		//체크박스 선택안됐으면 삭제할 차량을 선택하라고 알림
		var checkbox = false;
		for(i = 0; i < document.myCarForm.mycar_name.length; i++){
			if(document.myCarForm.mycar_name[i].checked==true)
				checkbox=true;
		}

		if(!checkbox){
			alert("삭제할 차를 선택해주세요.");
			return false;
		}
	}
</script>
</head>
<body>
<form name="myCarForm" action="myCarDeletePro.do" onsubmit="return check();">
<input type="button" value="등록" onclick="javascript:window.location='myCarInputForm.do'"/>
<input type="submit" value="삭제"/>
<table>
	<tr>
		<th></th>
		<th>차이름</th>
		<th>차타입</th>
		<th>차종</th>
		<th>색상</th>
		<th>차번호</th>
		<th>옵션</th>
	</tr>
	<c:if test="${empty myCarList}">
		<tr><td colspan="7">등록 내역이 존재하지 않습니다.</td></tr>
	</c:if>
	<c:if test="${!empty myCarList}">
		<c:forEach var="myCar" items="${myCarList}">
		<tr>
			<td><input type="checkbox" name="mycar_name" value="${myCar.mycar_name}"/></td>
			<td>${myCar.mycar_name}</td>
			<td>${myCar.car_type}</td>
			<td>${myCar.car_name}</td>
			<td>${myCar.color}</td>
			<td>${myCar.carNumber}</td>
			<td>${myCar.options}</td>
		</tr>
		</c:forEach>
	</c:if>
</table>
</form>
</body>
</html>