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
		
		document.bookForm2.submit();
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
<style>
	#info{width: 100%;margin: auto;border: 1px solid #BABABA;}
	#info li {padding: 5px;}
	@font-face{
		font-family: 'NanumSquareR';
		src:url('../decorators/font/NanumSquareR.ttf');
	}
	@font-face{
		font-family: 'NanumSquareB';
		src:url('../decorators/font/NanumSquareB.ttf');
	}
	.b {font-family: 'NanumSquareR' !important;}
	div#sec {width:1000px;margin:auto;margin-bottom: 30px;}
	div#list {text-align: right;margin-top:60px;margin-bottom: 20px;}
	div#list a {text-decoration: none; color: black;}
	div#list a:HOVER {text-decoration: underline;}
	
	div#table {border-top: 2px solid #7F7A7A;}
 	div#table table {text-align: center;} 
/*  	div#table table td {height: 100px;} */
	div#table th {background-color: #F9F9F9; font-size: 14px; height:30px;}
	div#table table tr td {border-bottom: thin solid #C9C9C9;}
	div#table table tr td.r {text-align: left;}
	div#next {width:100%;margin:auto;font-family: 'NanumSquareB' !important;margin-top:80px;margin-bottom:80px; text-decoration: left;}
	div#next a {border:1px solid gray;  border-radius:5px; color:gray;
		text-decoration: none; padding:10px 24px;}
		

	
</style>
</head>
<body>
<div id="sec"><img src="../decorators/images/step2.png" width="1000px"/></div>
<div class="b">
<form method="post" name="bookForm2" action="book3InputForm.do" onsubmit="return check();">
	<input type="hidden" id="rent_date" name="rent_date" value="${rent_date}"/>
	<input type="hidden" id="return_date" name="return_date" value="${return_date}"/>
	<input type="hidden" id="agency_no" name="agency_no" value="${agency_no}"/>
	
	<div id="info">
	<ul>
		<li>지점: ${agency_name}</li>
		<li>대여일자: ${rent_date}</li>
		<li>반납일자: ${return_date}</li>
	</ul>
	</div>

	<div id="list">
	<a href="#" onclick="sortByType('all');return false;">전체</a>&nbsp;&nbsp;&nbsp;
	<a href="#" onclick="sortByType('대형');return false;">대형</a>&nbsp;&nbsp;&nbsp;
	<a href="#" onclick="sortByType('중형');return false;">중형</a>&nbsp;&nbsp;&nbsp;
	<a href="#" onclick="sortByType('소형');return false;">소형</a>&nbsp;&nbsp;&nbsp;
	<a href="#" onclick="sortByType('승합차');return false;">승합차</a>&nbsp;&nbsp;&nbsp;
	<a href="#" onclick="sortByType('suv');return false;">suv</a>
	</div>
	<div id="table"></div>
	
</form>
</div>
<div id="next">
<a href="#" onclick="javascipt:window.history.go(-1);return false;" style="margin-right:837px;">이전</a>
<a href="#" onclick="check();return false;" >다음</a>
</div>
</body>
</html>