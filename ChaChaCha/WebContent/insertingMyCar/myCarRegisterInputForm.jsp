<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<!-- <script src="http://code.jquery.com/jquery-2.2.4.js"></script> -->
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
		
		$("#list").hide();
	});
	
	function setRegister(){
		//alert("change");
		if($(":selected", "#mycar").val()!="new"){
			$("#new").hide();
			$("#list").show();
			$.ajax({
				type: "POST",
				url: "../ajax/registerList.jsp",
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
		if(document.registerForm.mycar.value=="new"){
			if(!document.registerForm.mycar_name.value){
				alert("차이름을 입력해주세요.");
				document.registerForm.mycar_name.focus();
				return false;
			}
			if(!document.registerForm.car_type.value){
				alert("차량 타입을 선택해주세요.");
				return false;
			}
			if(document.registerForm.cars.value==0){
				alert("차종을 선택해주세요.");
				return false;
			}
			if(!document.registerForm.color.value){
				alert("색상을 입력해주세요.");
				return false;
			}
			if(!document.registerForm.carNumber.value){
				alert("차번호를 입력해주세요.");
				document.registerForm.carNumber.focus();
				return false;
			}
		}
		
		if(document.registerForm.agency_no.value==0){
			alert("희망 차고지를 선택해주세요.");
			return false;
		}
		if(!document.registerForm.startDate.value){
			alert("등록 시작일을 선택해주세요.");
			return false;
		}
		if(!document.registerForm.endDate.value){
			alert("등록 종료일을 선택해주세요.");
			return false;
		}
		if(!document.registerForm.checkDate.value){
			alert("마지막 점검일을 선택해주세요.");
			return false;
		}
		if((document.registerForm.startDate.value)>=(document.registerForm.endDate.value)){
			alert("등록날짜를 다시 확인해주세요.");
			return false;
		}
		
		if(confirm("등록하시겠습니까?")){
			document.registerForm.submit();
		}
	}
	
	function selectDate(){
		// 반납일자가 대여일자보다 뒤면 에러
		if($("#startDate").val()!="" && $("#endDate").val()!=""){
			if(($("#startDate").val())>($("#endDate").val())){
				alert("등록날짜를 다시 확인해주세요.");
				return;
			} else if(($("#startDate").val())==($("#endDate").val())){
				alert("차량 등록은 1일 이상 가능합니다. 다시 선택해주세요.")
				return;
			}
		}
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
<style>
	@font-face{
		font-family: 'NanumSquareR';
		src:url('../decorators/font/NanumSquareR.ttf');
	}
	@font-face{
		font-family: 'NanumSquareB';
		src:url('../decorators/font/NanumSquareB.ttf');
	}
	#form {font-family: 'NanumSquareR' !important;}
	table {width:1000px;}
	div#selectMyCar , div.list {border-top: 2px solid #7F7A7A;}
	div#selectMyCar table th {background-color: #F9F9F9; width: 200px; height: 50px;}
	div#selectMyCar table td, div.list table td {padding-left: 30px;}
	div.list { width: 100%;margin-top: 20px;}
	div.list table th {background-color: #F9F9F9; width: 200px; height: 60px;}
	div.list span#checkMyCarName {font-weight: bold; font-size: 15px; color:red;}
	
	div#submit {width:100%;margin:auto;text-align: right; margin-top:80px;margin-bottom: 80px;text-align: center;}
	div#submit a {border:1px solid gray;  border-radius:5px; color:gray;
		text-decoration: none; padding:10px 24px;}
	table th, table td {border-bottom: thin solid #C9C9C9;}
</style>
</head>
<body>
<div id="form">
<form name="registerForm" action="myCarRegisterInputPro.do" method="post"">
	<div id="selectMyCar">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<th>등록된 내 차 선택 </th>
			<td>
				<select id="mycar" name="mycar" onchange="setRegister()" >
<!-- 					<option>선택해주세요</option> -->
					<option value="new">새로 등록하기</option>
					<c:forEach var="myCar" items="${myCarNames}">
					<option value="${myCar}">${myCar}</option>
					</c:forEach>
				</select>
			</td>		
		</tr>
	</table>
	</div>
	<div class="list" id="list"></div>
		
	<div class="list" id="new">
	<table cellpadding="0" cellspacing="0">
		<tr><th>차 이름</th><td><input type="text" id="mycar_name" name="mycar_name" size="30" onblur="return checkMyCarName();"/> <span id="checkMyCarName"></span></td></tr>
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
			<th>차종</th>
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
			<td><input type="text" size="30" id="carNumber" name="carNumber"/></td>
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
	<div class="list">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<th>희망 차고지</th>
			<td>
				<select name="agency_no">
					<option value=0>선택해주세요</option>
					<c:forEach var="agency" items="${agencyList}">
					<option value="${agency.agency_no}">${agency.agency_name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>등록 시작일</th>
			<td>
				<input type="text" size="30" class="testDatepicker" name="startDate" id="startDate" onchange="selectDate()" readonly>
			</td>
		</tr>
		<tr>
			<th>등록 종료일</th>
			<td>
				<input type="text" size="30" class="testDatepicker" name="endDate" id="endDate" onchange="selectDate()" readonly>
			</td>
		</tr>
		<tr>
			<th>마지막 점검일</th>
			<td>
				<input type="text" size="30" class="testDatepicker2" name="checkDate" id="checkDate" readonly>
			</td>
		</tr>
	</table> 
	</div>
	<div id="submit">
		<a href="#" onclick="check();return false;">등록하기</a>
	</div>


</form>
</div>
</body>
</html>