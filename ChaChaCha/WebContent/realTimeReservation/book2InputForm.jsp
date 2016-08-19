<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
	$(function(){
		$.ajax({
			type: "POST",
			url: "../ajax/availableCarList.jsp",
			data: {
				type:"all",
				rent_date:$("#rent_date").val(),
				return_date:$("#return_date").val(),
				agency_no:$("#agency_no").val()
			},
			success: function(result){
				//alert("success");
				$("#table").html("");
				$("#table").append(result);
				console.log(result);
			},
			error: function(){
				alert("error");
			}
		}) 
	})
	
	function check(){
		if(!document.bookForm2.car_id.value){
			alert("원하는 차를 선택해주세요.");
			return false;
		}
	}
	
	function sortByType(type){
		//alert("ok");
		$.ajax({
			type: "POST",
			url: "../ajax/availableCarList.jsp",
			data: {
				type:type,
				rent_date:$("#rent_date").val(),
				return_date:$("#return_date").val(),
				agency_no:$("#agency_no").val()
			},
			success: function(result){
				//alert("success");
				$("#table").html("");
				$("#table").append(result);
				console.log(result);
			},
			error: function(){
				alert("error");
			}
		}) 
	}
</script>
</head>
<style>
	#a{
		width: 70%;
		height: 100px;
		border: 1px;
		background-color: white;
		margin: auto;
	}
</style>
<body>
<form name="bookForm2" action="book3InputForm.do" onsubmit="return check();">
	<input type="hidden" id="rent_date" name="rent_date" value="${rent_date}"/>
	<input type="hidden" id="return_date" name="return_date" value="${return_date}"/>
	<input type="hidden" id="agency_no" name="agency_no" value="${agency_no}"/>
	
	<div id="a">
	<ul>
		<li>지점: ${agency_name}</li>
		<li>대여일자: ${rent_date}</li>
		<li>반납일자: ${return_date}</li>
	</ul>
	</div>

	<div>
	가능차목록<br>
	<a href="#" onclick="sortByType('all');return false;">전체</a>
	<a href="#" onclick="sortByType('대형');return false;">대형</a>
	<a href="#" onclick="sortByType('중형');return false;">중형</a>
	<a href="#" onclick="sortByType('소형');return false;">소형</a>
	<a href="#" onclick="sortByType('승합차');return false;">승합차</a>
	<a href="#" onclick="sortByType('suv');return false;">suv</a>
	</div>
	<br><br>
	<div>
	<div id="table"></div>
	</div>
	
	<div style="text-align:center;">
	<input type="button" value="이전" onclick="javascipt:window.history.go(-1)"/>
	<input type="submit" value="다음"/>
	</div>
</form>
</body>
</html>