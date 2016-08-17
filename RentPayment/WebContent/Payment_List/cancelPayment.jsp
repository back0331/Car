<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <script type="text/javascript">
setTimeout(function(){
	document.getElementById("form").submit();
	},0);
</script> -->
<script type="text/javascript">
		var api_key = response.access_token;
		document.innerHTML = api_key;
</script>
<script type="text/javascript">
var includedPage = document.getElementById("RestAPI_key");
includedPage.style.display="block";
</script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/Payment_List/cancelPaymentPro.jsp">
<jsp:param value="response.access_token" name="api_key"/>
</jsp:include>
${api_key }
<script type="text/javascript">
var PaymentResponse = {
	  "code": 0,
	  "message": "string",
	  "response": {
	    "imp_uid": "${imp_uid}",
	    "merchant_uid": "string",
	    "pay_method": "string",
	    "pg_provider": "string",
	    "pg_tid": "string",
	    "apply_num": "string",
	    "card_name": "string",
	    "card_quota": 0,
	    "vbank_name": "string",
	    "vbank_num": "string",
	    "vbank_holder": "string",
	    "vbank_date": 0,
	    "name": "string",
	    "amount": 0,
	    "cancel_amount": 0,
	    "buyer_name": "string",
	    "buyer_email": "string",
	    "buyer_tel": "string",
	    "buyer_addr": "string",
	    "buyer_postcode": "string",
	    "custom_data": "string",
	    "user_agent": "string",
	    "status": "ready",
	    "paid_at": 0,
	    "failed_at": 0,
	    "cancelled_at": 0,
	    "fail_reason": "string",
	    "cancel_reason": "string",
	    "receipt_url": "string",
	    "cancel_receipt_urls": [
	      "string"
	    ]
	  }
	};
var PaymentResponsed = JSON.parse(PaymentResponse);
var check = PaymentResponsed.response.fail_reason;
if(check==null){
	alert("결제취소실패!");
}else{
	alert("결제취소완료!");
	location.href="/Payment_List/paymentListPro.jsp";
}
	
</script>
<!-- <form id="form" method="post" action="https://api.iamport.kr/users/getToken">
<input type="hidden" name="imp_key" value="1933694709553385">
<input type="hidden" name="imp_secret" 
value="wC9EZvX7rjv2hWPlRmSfKIO8XHH5YkkvXu9WZ64qpAtHwi3oFVDpDsyBafG9A0ExuOuuUyVk8o2FH54m"> -->
</form>
</body>
</html>