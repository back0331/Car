<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<!-- <script src="http://code.jquery.com/jquery-2.2.4.js"></script> -->
<title>내 차 등록하기</title>
</head>
<script>
	$(function() {
		$( ".testDatepicker" ).datepicker({
      		showOn: "both",
       		buttonImage: "../image/calIcon.png",
        	buttonImageOnly: true,
        	buttonText: "달력열기",
        	nextText: '다음 달',
        	prevText: '이전 달',
        	dateFormat: "yymmdd",
        	minDate: "-0D"
		});
		
		$( ".testDatepicker2" ).datepicker({
      		showOn: "both",
       		buttonImage: "../image/calIcon.png",
        	buttonImageOnly: true,
        	buttonText: "달력열기",
        	nextText: '다음 달',
        	prevText: '이전 달',
        	dateFormat: "yymmdd",
        	maxDate: "+0D"
		});
		
	});
	
	function setRegister(){
		//alert("change");
		if($(":selected", "#mycar").val()!="new"){
			$("#new").hide();
			$.ajax({
				type: "POST",
				url: "./registerList.jsp",
				data: {mycar_name:$(":selected", "#mycar").val()},
				success: function(result){
					//alert(result);
					$("#list").html(result);
					console.log(result);
				},
				error: function(){
					alert("error");
				}
			})
		} else {
			$("#list").html("");
			$("#new").show();
		}
	}
	
	function setCarType(){
		$.ajax({
			type: "POST",
			url: "./carTypeList.jsp",
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
// 			alert("비었음");
			$("#checkMyCarName").html(" 차이름을 입력해주세요.");
			return false;
		} else {
			//alert("값있음");
			$.ajax({
				type: "POST",
				url: "./checkMyCarName.jsp",
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
<body>
내 차 등록

<hr>
<form name="registerForm" action="myCarRegisterInputPro.do" onsubmit="return check();">
	<table>
		<tr><td>등록된 내 차 선택 </td></tr>
		<tr>
			<td>
				<select id="mycar" name="mycar" onchange="setRegister()">
					<!-- <option>선택해주세요</option> -->
					<option value="new">새로등록</option>
					<c:forEach var="myCar" items="${myCarNames}">
					<option value="${myCar}">${myCar}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<div id="list"></div>
		
	<div id="new">
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
	</div>
	<table>
		<tr>
			<td>희망차고지</td>
			<td>
				<select name="agency_no">
					<option>선택해주세요</option>
					<c:forEach var="agency" items="${agencyList}">
					<option value="${agency.agency_no}">${agency.agency_name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>등록시작일</td>
			<td>
				<input type="text" class="testDatepicker" name="startDate" id="startDate" readonly>
			</td>
		</tr>
		<tr>
			<td>등록종료일</td>
			<td>
				<input type="text" class="testDatepicker" name="endDate" id="endDate" readonly>
			</td>
		</tr>
		<tr>
			<td>마지막점검일</td>
			<td>
				<input type="text" class="testDatepicker2" name="checkDate" id="checkDate" readonly>
			</td>
		</tr>
	</table> 
<input type="submit" value="등록"/>
<input type="button" value="취소" onclick="javascript:window.location=''"/>
</form>
</body>
</html>