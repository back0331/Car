<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function check(){
		if(confirm('예약하시겠습니까?')){
			document.book3Form.submit();
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
	.b {font-family: 'NanumSquareR' !important;}
	div#sec {width:1000px;margin:auto;margin-bottom: 60px;}
	div#table {width:500px; margin:auto; border-top: 2px solid #7F7A7A;}
	div#table table th {background-color: #F9F9F9; width: 200px;}
	div#table table td {padding-left: 30px;}
	div#table table th, div#table table td {height: 60px; border-bottom: thin solid #C9C9C9;}
	div#next {width:100%;margin:auto;font-family: 'NanumSquareB' !important;margin-top:80px;margin-bottom:80px; text-decoration: left;}
	div#next a {border:1px solid gray;  border-radius:5px; color:gray;
		text-decoration: none; padding:10px 24px;}
		
</style>
</head>
<body>
<div id="sec"><img src="../decorators/images/step3.png" width="1000px"/></div>
<div class="b">
<form action="book3InputPro.do" name="book3Form">

	<input type="hidden" name="car_id" value="${car.car_id}"/>
	<input type="hidden" name="rent_date" value="${rent_date}"/>
	<input type="hidden" name="return_date" value="${return_date}"/>
	<input type="hidden" name="total_price" value="${total_price}"/>
	<input type="hidden" name="carOwner" value="${carOwner}"/>
	
	<div id="table">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th>지점</th>
			<td>${agency_name}</td>
		</tr>
		<tr>
			<th>대여일자</th>
			<td>${rent_date}</td>
		</tr>
		<tr>
			<th>반납일자</th>
			<td>${return_date}</td>
		</tr>
		<tr>
			<th>선택차량</th>
			<td>${car.car_name}<br>
				${car.options}</td>
		</tr>
		<tr>
			<th>총금액</th>
			<td>${total_price}원</td>
		</tr>
	</table>
	</div>
</form>
</div>
<div id="next">
<a href="#" onclick="javascipt:window.history.go(-1);return false;" style="margin-right:837px;">이전</a>
<a href="#" onclick="check();return false;" >예약</a>
</div>
</body>
</html>