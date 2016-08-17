<%@ page contentType="text/html; charset=EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
	<td>
		<input type="button" value="결제폼으로고고싱" 
		onclick="javascript:location='/RentPayment/Payment/InsertPro.do?book_no=1'"/>
		<!-- 원래는 예약내역의 버튼을 클릭하는 상황임. 지금은 book_no가 1인 상황을 가정한 것임. -->
	</td>
	<td>
		예약이 완료되었습니다.<br/>버튼을 누르시면 결제를 진행합니다.
	</td>
</tr>
</table>
</body>
</html>