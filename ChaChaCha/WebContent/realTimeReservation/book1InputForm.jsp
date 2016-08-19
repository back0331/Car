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
</head>
<body>
<div id="map" style="width:700px;height:400px;"></div>
<form name="bookForm1" action="book2InputForm.do" onsubmit="return check();">
	<div>
	<table width="700" height="200" border="1" cellspacing="0">
		<tr>
			<td>지점선택</td>
			<td>
				<select id="agency" name="agency" onchange="mapChange()">
					<option value="">선택해주세요</option>
					<c:forEach var="agency" items="${agencyList}">
						<option value="${agency.agency_no}" id="${agency.loc2}" title="${agency.loc1}">${agency.agency_name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>대여일자</td>
			<td>
				<input size="30px" type="text" class="testDatepicker" name="rent_date" id="rent_date" onchange="selectDate()" readonly>
			</td>
		</tr>
		<tr>
			<td>반납일자</td>
			<td>
				<input size="30px" type="text" class="testDatepicker" name="return_date" id="return_date" onchange="selectDate()" readonly>
			</td>
		</tr>
	</table>
	</div>
	<div style="text-align:center;">
	<input type="submit" value="다음"/>
	</div>
</form>
</body>
</html> 