<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>실시간예약3</title>
</head>
<body>
<form action="book3InputPro.do" name="book3Form" onsubmit="return confirm('예약하시겠습니까?');">

<input type="hidden" name="car_id" value="${car.car_id}"/>
<input type="hidden" name="rent_date" value="${rent_date}"/>
<input type="hidden" name="return_date" value="${return_date}"/>
<input type="hidden" name="total_price" value="${total_price}"/>
<input type="hidden" name="carOwner" value="${carOwner}"/>

<table border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>지점</td>
		<td>${agency_name}</td>
	</tr>
	<tr>
		<td>대여일자</td>
		<td>${rent_date}</td>
	</tr>
	<tr>
		<td>반납일자</td>
		<td>${return_date}</td>
	</tr>
	<tr>
		<td>선택차량</td>
		<td>${car.car_name}
			${car.options}</td>
	</tr>
	<tr>
		<td>총금액</td>
		<td>${total_price}</td>
	</tr>
</table>
<input type="button" value="이전" onclick="javascipt:window.history.go(-1)"/>
<input type="submit" value="예약"/>
</form>
</body>
</html>