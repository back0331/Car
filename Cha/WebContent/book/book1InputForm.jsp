<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=caf8112cb3a3da7c79d4323b8bf920b6"></script>
<title>실시간예약1</title>
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
		
		//지점 선택시 지도에 표시
		$("#agency").change(function(){
			$("#loc").attr("value",$(":selected").attr("title"));
			//alert($("#loc").attr("value"));
			map();
		});
		
		// 반납일자가 대여일자보다 뒤면 에러
		$("#return_date").change(function(){
			//alert($("#return_date").val());
			
			if(($("#rent_date").val())>($("#return_date").val())){
				alert("반납일자를 다시 확인해주세요.");
				return;
			} else if(($("#rent_date").val())==($("#return_date").val())){
				alert("대여는 1일 이상 신청가능합니다. 다시 선택해주세요.")
				return;
			}
			
		});
	});

	function check(){
		if(!document.bookForm1.agency.value){
			//alert("ok");
			alert("지점을 선택해주세요.");
			return false;
		}
		
		if(!document.bookForm1.rent_date.value){
			//alert("ok");
			alert("대여일자를 선택해주세요.");
			return false;
		}
		
		if(!document.bookForm1.return_date.value){
			//alert("ok");
			alert("반납일자를 선택해주세요.");
			return false;
		}
		
		if((document.bookForm1.rent_date.value)>=(document.bookForm1.return_date.value)){
			alert("반납일자를 다시 확인해주세요.");
			return false;
		}
	}
	
	function map(){
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
			mapOption = {
				center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        	level: 5 // 지도의 확대 레벨
	        };
		var map = new daum.maps.Map(mapContainer, mapOption);
		
		var loc = $("#loc").attr("value");
		//alert(loc);
		var markerPosition  = new daum.maps.LatLng(loc); 

		// 마커를 생성합니다
		var marker = new daum.maps.Marker({
		    position: markerPosition	
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
	} 
</script>
<body onload="map()">
실시간예약
<hr>
<div id="map" style="width:700px;height:400px;"></div>
<form name="bookForm1" action="book2InputForm.do" onsubmit="return check();">
<table>
	<tr>
		<td>지점선택</td>
		<td>
			<select id="agency" name="agency">
				<option value="">선택해주세요</option>
				<c:forEach var="agency" items="${agencyList}">
					<option value="${agency.agency_no}" title="${agency.loc}">${agency.agency_name}</option>
				</c:forEach>
			</select>
		</td>
		<td><input type="text" name=loc id="loc"/></td>
	</tr>
	<tr>
		<td>대여일자</td>
		<td>
			<input type="text" class="testDatepicker" name="rent_date" id="rent_date" readonly>
		</td>
	</tr>
	<tr>
		<td>반납일자</td>
		<td>
			<input type="text" class="testDatepicker" name="return_date" id="return_date" readonly>
		</td>
	</tr>
</table>
<input type="submit" value="다음"/>
</form>
</body>
</html> 