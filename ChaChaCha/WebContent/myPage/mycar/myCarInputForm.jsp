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
			url: "../../ajax/carTypeList.jsp",
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
		if(!document.registerForm.mycar_name.value){
			alert("차이름을 입력해주세요.");
			document.registerForm.mycar_name.focus();
			return false;
		}
		if(!document.registerForm.car_type.value){
			alert("차타입을 선택해주세요.");
			return false;
		}
		if(document.registerForm.cars.value==0){
			alert("차종을 선택해주세요.");
			return false;
		}
		if(!document.registerForm.color.value){
			alert("색상을 선택해주세요.");
			return false;
		}
		if(!document.registerForm.carNumber.value){
			alert("차번호를 입력해주세요.");
			document.registerForm.carNumber.focus();
			return false;
		}
		
		document.registerForm.submit();
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
				url: "../../ajax/checkMyCarName.jsp",
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
<style>
	@font-face{
		font-family: 'NanumSquareR';
		src:url('../decorators/font/NanumSquareR.ttf');
	}
	@font-face{
		font-family: 'NanumSquareB';
		src:url('../decorators/font/NanumSquareB.ttf');
	}
	body {font-family: 'NanumSquareR' !important;}
	h2 {padding-left:40px;padding-top:20px;}
	div#f {border-top: 2px solid #7F7A7A;width:800px; margin:auto;}
	div#f table th {background-color: #F9F9F9; border-bottom: thin solid #DFDEDE; height: 50px;}
	div#f table td {padding-left: 30px;border-bottom: thin solid #DFDEDE;}
	div#f table span#checkMyCarName {font-weight: bold; font-size: 15px; color:red;}
	
	div#sub {margin-top:50px;}
	div#sub a {border:1px solid #00B0F0; background-color:#00B0F0; padding: 10px 15px; border-radius:5px;color:white;text-decoration: none; }
</style>
</head>
<body>
<h2>차량 추가</h2>
<form name="registerForm" action="myCarInputPro.do" onsubmit="return check();">
<div id="f">
<table width="100%" cellpadding="0" cellspacing="0">
	<tr><th>차 이름</th><td width="600px"><input type="text" id="mycar_name" name="mycar_name" onblur="return checkMyCarName();"/> <span id="checkMyCarName"></span></td></tr>
	<tr>
		<th>차 타입</th>
		<td>
			<select name="car_type" id="car_type" onchange="setCarType()">
				<option value="">선택해주세요</option>
				<c:forEach var="type" items="${carTypeList}">
				<option value="${type}">${type}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>차 종</th>
		<td>
			<select name="cars" id="cars">
				<option value=0>선택해주세요</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>
			<select name="color" id="color" class="register">
				<option value="">선택해주세요</option>
				<option value="black">검정색</option>
				<option value="white">흰색</option>
				<option value="red">빨강색</option>
				<option value="navy">남색</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>차번호</th>
		<td><input type="text" name="carNumber"/></td>
	</tr>
	<tr>
		<th>옵션</th>
		<td>
			<label><input type="checkbox" name="options" class="register" value="네비게이션"/>네비게이션</label>
			<label><input type="checkbox" name="options" class="register" value="블랙박스"/>블랙박스</label>
		</td>
	</tr>
</table>
</div>
<div id="sub" align="center">
<a href="#" onclick="check();return false;">∨등록하기</a>
<a href="#" onclick="javascript:window.location='myCarView.do';return false;">취소</a>
</div>
</form>
</body>
</html>