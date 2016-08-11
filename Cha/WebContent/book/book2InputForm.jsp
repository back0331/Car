<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>실시간예약2</title>
</head>
<script>
	function check(){
		if(!document.bookForm2.car_id.value){
			alert("원하는 차를 선택해주세요.");
			return false;
		}
	}
	
	function sortByType(type){
		alert("ok");
		$.ajax({
			type: "POST",
			url: "./availableCarList.jsp",
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
<body onload="sortByType('all')">
<form name="bookForm2" action="book3InputForm.do" onsubmit="return check();">
	<input type="hidden" id="rent_date" name="rent_date" value="${rent_date}"/>
	<input type="hidden" id="return_date" name="return_date" value="${return_date}"/>
	<input type="hidden" id="agency_no" name="agency_no" value="${agency_no}"/>

	<ul>
		<li>지점: ${agency_name}</li>
		<li>대여일자: ${rent_date}</li>
		<li>반납일자: ${return_date}</li>
	</ul>
<hr>
가능차목록<br>
<a href="#" onclick="sortByType('all');return false;">전체</a>
<a href="#" onclick="sortByType('대형');return false;">대형</a>
<a href="#" onclick="sortByType('중형');return false;">중형</a>
<a href="#" onclick="sortByType('소형');return false;">소형</a>
<a href="#" onclick="sortByType('승합차');return false;">승합차</a>
<a href="#" onclick="sortByType('suv');return false;">suv</a>
<br><br>
<hr>
<div id="table"></div>
<input type="button" value="이전" onclick="javascipt:window.history.go(-1)"/>
<input type="submit" value="다음"/>
</form>
</body>
</html>