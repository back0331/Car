<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
	function setCarType(){
		$.ajax({
			type: "POST",
			url: "../ajax/carTypeList.jsp",
			data: {car_type:$(":selected", "#car_type").val()},
			success: function(result){
				$("#cars").html("");
				$("#cars").append(result);
				console.log(result);
			},
			error: function(){
				alert("error");
			}
		})
	}
	
	function check(){
		
	}
	
	function checkMyCarName(){
		if($("#mycar_name").val()==""){
	//			alert("비었음");
			$("#checkMyCarName").html(" 차이름을 입력해주세요.");
			return false;
		} else {
			//alert("값있음");
			$.ajax({
				type: "POST",
				url: "../ajax/checkMyCarName.jsp",
				data: {mycar_name:$("#mycar_name").val()},
				success: function(result){
					$("#checkMyCarName").html("");
					$("#checkMyCarName").html(result);
					console.log(result);
				},
				error: function(){
					alert("error");
				}
			})
		}
	}
</script>
</head>
<body>
<form name="registerForm" action="myCarInputPro.do" onsubmit="return check();">
<table>
	<tr><td>차 이름</td><td><input type="text" id="mycar_name" name="mycar_name" onblur="return checkMyCarName();"/><span id="checkMyCarName"></span></td></tr>
	<tr>
		<td>차 타입</td>
		<td>
			<select name="car_type" id="car_type" onchange="setCarType()">
				<option>선택해주세요</option>
				<c:forEach var="type" items="${carTypeList}">
				<option value="${type}">${type}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>차종</td>
		<td>
			<select name="cars" id="cars">
				<option value=0>선택해주세요</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>색상</td>
		<td>
			<select name="color" id="color" class="register">
				<option>선택해주세요</option>
				<option value="black">검정색</option>
				<option value="white">흰색</option>
				<option value="red">빨강색</option>
				<option value="navy">남색</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>차번호</td>
		<td><input type="text" name="carNumber"/></td>
	</tr>
	<tr>
		<td>옵션</td>
		<td>
			<label><input type="checkbox" name="options" class="register" value="네비게이션"/>네비게이션</label>
			<label><input type="checkbox" name="options" class="register" value="블랙박스"/>블랙박스</label>
		</td>
	</tr>
</table>
<input type="submit" value="등록"/>
<input type="button" value="취소" onclick="javascript:window.location='myCarView.do'"/>
</form>
</body>
</html>