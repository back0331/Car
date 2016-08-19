<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function formSubmit(){
	document.getElementById("paymentList").submit();
}
</script>
<title>Insert title here</title>
</head>
<body>
<c:set var="idList" value="${idList }"/>
<form id="paymentList" action="/ChaChaCha/administratorPage/Payment_List/Cancel.do">
	<table border="1" cellpadding="0" cellspacing="2">
	<tr>
		<td>번호</td><td>아이디</td><td>입금자</td><td>결제금액(원)</td><td>결제수단</td><td>PG사</td>
		<td>이메일</td><td>결제일</td><td>예약번호</td><td>상태</td>
	</tr>
	<tr>
	<c:forEach var="payList" items="${payList}" varStatus="status">
	<%-- <c:forEach var="payList" items="${payList }" varStatus="status"> --%>
		<td>${payList.no}</td>
		<td>${idList[status.index].id}</td>
		<td>${payList.name}</td>
		<td>${payList.pay_total_price}</td>
		<td>${payList.pay_method}</td>
		<td>${payList.pg}</td>
		<td>${payList.email}</td>
		<td>${payList.reg_date }</td>
		<td><a href=# onclick="formSubmit()">${payList.book_no }</a><input type="hidden" name="book_no" value="${payList.book_no }"></td>
		<td>
		</td>
	</tr>
	</c:forEach>
	</table>
	
</form>
</body>
</html>