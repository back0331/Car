<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=1d1c47a477b151b83387afa331dea443"></script>
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
	        minDate: "-0D",
	        font: "20px"
		});
		
		//alert("map");
		var mapContainer = document.getElementById("map"), // 지도를 표시할 div  
		mapOption = {
			center: new daum.maps.LatLng(37.522187, 126.982673), // 지도의 중심좌표
        	level: 8 // 지도의 확대 레벨
        };
		map = new daum.maps.Map(mapContainer, mapOption);
		
		var mapTypeControl = new daum.maps.MapTypeControl();
		
		// 마커를 생성합니다
		marker = new daum.maps.Marker({});

		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new daum.maps.ZoomControl();
		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
		
	});
	
	function mapChange(){

		if($(":selected","#agency").text()=="선택해주세요")
			return false;
		
		var loc1 = $(":selected","#agency").attr("title");
		var loc2 = $(":selected","#agency").attr("id");
		
		marker.setPosition(new daum.maps.LatLng(loc1,loc2));

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		map.setLevel(4);
		
		var moveLatLon = new daum.maps.LatLng(loc1,loc2);
	    map.panTo(moveLatLon);
	}
	

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
		
		document.bookForm1.submit();
	}
	
	function selectDate(){
		// 반납일자가 대여일자보다 뒤면 에러
		if($("#rent_date").val()!="" && $("#return_date").val()!=""){
			if(($("#rent_date").val())>($("#return_date").val())){
				alert("반납일자를 다시 확인해주세요.");
				return;
			} else if(($("#rent_date").val())==($("#return_date").val())){
				alert("대여는 1일 이상 신청가능합니다. 다시 선택해주세요.")
				return;
			}
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
	
	form {font-family: 'NanumSquareR' !important;}
	section {margin-top:15px;margin-bottom:30px;height:900px;}
	div#sec {width:1000px;margin:auto;margin-bottom: 30px;}
	div#map{width:1000px;height:400px;}
	div#table {margin-top:30px; border-top: 2px solid #7F7A7A;}
	table input {font-size: 18px;}
	table th {height: 80px; background-color: #F9F9F9;}
	table th, table td {border-bottom: thin solid #C9C9C9;}
	table tr td.input {padding-left: 30px;}
/* 	table tr td div {text-align: center; background-color: #F9F9F9; padding:25px 20px;} */
	div#next {width:100%;margin:auto;font-family: 'NanumSquareB' !important;text-align: right; margin-top:80px;margin-bottom: 80px;}
	div#next a {border:1px solid gray;  border-radius:5px; color:gray;
		text-decoration: none; padding:10px 24px;}
</style>
</head>
<body>
<div id="sec"><img src="../decorators/images/step1.png" width="1000px"/></div>
<div id="map"></div>
<div id="table">
<form name="bookForm1" action="book2InputForm.do" method="post">
	<table width="1000px" height="200" cellspacing="0" callpadding="0">
		<tr>
			<th width="200">지점선택</th>
			<td class="input">
				<select id="agency" name="agency" onchange="mapChange()">
					<option value="">선택해주세요</option>
					<c:forEach var="agency" items="${agencyList}">
						<option value="${agency.agency_no}" id="${agency.loc2}" title="${agency.loc1}">${agency.agency_name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>대여일자</th>
			<td class="input">
				<input size="30px" type="text" class="testDatepicker" name="rent_date" id="rent_date" onchange="selectDate()" readonly>
			</td>
		</tr>
		<tr>
			<th>반납일자</th>
			<td class="input">
				<input size="30px" type="text" class="testDatepicker" name="return_date" id="return_date" onchange="selectDate()" readonly>
			</td>
		</tr>
	</table>
</form>
</div>
<div id="next">
<a href="#" onclick="check();return false;">다음</a>
</div>

</body>
</html> 