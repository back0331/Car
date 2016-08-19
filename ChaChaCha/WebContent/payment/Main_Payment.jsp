<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<!-- 결제 폼 시작 -->
<form name="Request_Insert" action="/payment/Insert.do" method="post">
<!-- pay_list테이블에 값을 저장하는 Insert.do -->
<table border="1" cellpadding="0" cellspacing="1">
	<tr height="40">
		<td><label for="pg">&nbsp;&nbsp;지원 PG사</label></td>
		<td align="center">
			<select name="pg" id="pg">
				<option value="kakao">카카오페이</option>
				<option value="html5_inicis" selected>KG이니시스(웹표준결제)</option>
				<option value="inicis">KG이니시스(기존모듈)</option>
				<option value="uplus">LG유플러스</option>
				<option value="nice">나이스정보통신</option>
				<option value="jtnet">JTNet</option>
				<option value="danal">다날-휴대폰소액결제전용</option>
				<option value="paypal">페이팔-ExpressCheckout</option>
            </select>
		</td>
	</tr>
	<tr height="40">
		<td width="200"><label for="pay_method">&nbsp;&nbsp;결제수단</label></td>
		<td align="center"><select name="pay_method" id="pay_method" class="col-md-8 col-xs-8">
		<option value="card">신용카드</option></select>
		</td>
	</tr>
	<tr height="40">
		<td><label for="total_price" class="col-md-4 col-xs-4">&nbsp;&nbsp;금액</label></td>
		<td><input type="tel" name="total_price" id="total_price" value="1000" class="col-md-8 col-xs-8" readonly/>${total_price }</td>
	</tr>
	<tr height="40">
		<td><label for="email" class="col-md-4 col-xs-4">&nbsp;&nbsp;이메일주소</label></td>
		<td><input type="text" name="email" id="email" value="jagjayo@gmail.com" class="col-md-8 col-xs-8"/>${email }</td>
	</tr>
	<tr height="40">
		<td><label for="name" class="col-md-4 col-xs-4">&nbsp;&nbsp;성함</label></td>
		<td><input type="text" name="name" id="name" value="백상휘" class="col-md-8 col-xs-8"/>${name }</td>
	</tr>
	<tr height="40">
		<td><label for="phone" class="col-md-4 col-xs-4">&nbsp;&nbsp;전화번호</label></td>
		<td><input type="tel" name="phone" id="phone" value="010-1111-1111" class="col-md-8 col-xs-8"/>${phone }</td>
	</tr>
	<tr height="40">
		<td><label for="address" class="col-md-4 col-xs-4">&nbsp;&nbsp;주소</label></td>
		<td><input type="text" name="address" id="address" value="서울특별시 강남구 타워팰리스" class="col-md-8 col-xs-8"/>${address }</td>
	</tr>
	<tr height="40">
		<td><label for="zipcode" class="col-md-4 col-xs-4">&nbsp;&nbsp;우편번호</label></td>
		<td><input type="text" name="zipcode" id="zipcode" value="111-111" class="col-md-8 col-xs-8"/>${zipcode }</td>
	</tr>
	<tr height="40">
		<td><label for="point"><ul><li>총 마일리지</li></ul></label></td>
		<td><input type="text" name="point" id="point" value="50" class="col-md-8 col-xs-8" readonly/>${point }</td>
	</tr>
	<tr height="40">
		<td><ul><li>사용할 마일리지</li></ul></td>
		<td><input type="text" name="point_using" id="point_using" value="0" class="col-md-8 col-xs-8"/></td>
	</tr>
	<tr height="40">
		<td><ul><li>총 결제금액</li></ul></td>
		<td><input type="text" name="pay_total_price" id="pay_total_price" value="1504" class="col-md-8 col-xs-8" readonly/>${page.point-page.point_using}</td>
	</tr>
	<tr height="40">
		<td colspan="2" align="center"><input type="submit" value="결제하기"></td>
	</tr>
</table>
</form>
<!-- 결제폼 끝 -->
<br>
</body>
</html>