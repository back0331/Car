<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
	div.list {border-top: 2px solid #7F7A7A;width:90%; margin:auto;}
	div.list table {text-align: center;}
	div.list table tr.point {cursor: pointer;}
	div.list table tr.t th {background-color: #F9F9F9; height: 50px;}
	div.list table tr td{ height: 50px;}
	.hide {display: none;}
	div.list table tr.hide table th {border-top: thin solid black; border-bottom: thin solid black;}
	span#s {font-size: 12px;color: #8B8888;margin-left: 10px;}
	
</style>
<script src="http://code.jquery.com/jquery-2.2.4.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>

	$(function(){
		
		$(".point").click(function(){
 			$(this).next("tr").toggleClass("hide");
			if($(this).next("tr").is(":visible")){
				$(this).next("tr").css("background-color","#F9F9F9");
				
			} else {
				$(this).next("tr").css("background-color","white");
			}
		})
	})
	
	function showList(car_id){
		//alert(car_id);
		$.ajax({
			type: "POST",
			url: "../../ajax/userCarBookList.jsp",
			data: {
				car_id:car_id
			},
			success: function(result){
				//alert("success");
				$("#"+car_id+"").html("");
				$("#"+car_id+"").append(result);
				console.log(result);
			},
			error: function(){
				alert("error");
			}
		}) 
	}
	
</script>
</head>
<body>
<h2>등록 내역 <span id="s">등록 내역을 선택하면 해당 차량의 예약리스트를 확인할 수 있습니다.</span></h2>
<div id="rlist" class="list">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr class="t">	
			<th>등록날짜</th>
			<th>차이름</th>
			<th>지점</th>
			<th>등록 시작일</th>
			<th>등록 종료일</th>
		</tr>
		<c:forEach var="userCar" items="${userCarList}">
			
			<tr class="point" onclick="showList(${userCar.car_id})">
				<td>${userCar.reg_date}</td>
				<td>${userCar.myCarName}</td>
				<td>${userCar.state}</td>
				<td>${userCar.startDate}</td>
				<td>${userCar.endDate}</td>
			</tr>
			<tr class="hide" align="center"><td colspan="5" id="${userCar.car_id}">
			</td></tr>
			
		</c:forEach>
	</table>
</div>
<h2>지난 등록 내역</h2>
<div id="blist" class="list">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr class="t">
			<th>등록날짜</th>
			<th>차이름</th>
			<th>지점</th>
			<th>등록 시작일</th>
			<th>등록 종료일</th>
		</tr>
		<c:forEach var="userCar" items="${expiredUserCarList}">
			<tr>
				<td>${userCar.reg_date}</td>
				<td>${userCar.myCarName}</td>
				<td>${userCar.state}</td>
				<td>${userCar.startDate}</td>
				<td>${userCar.endDate}</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>