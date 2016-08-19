<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.net.*"%>
<%@page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<!-- Allow-Control-Allow-Origin 확장프로그램 크롬에 설치 필요. -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.1.js"></script>
<script type="text/javascript">
access_token = "";
function check() {
	$.post('https://api.iamport.kr/users/getToken', 
		{
			imp_key:"1933694709553385",
			imp_secret:"wC9EZvX7rjv2hWPlRmSfKIO8XHH5YkkvXu9WZ64qpAtHwi3oFVDpDsyBafG9A0ExuOuuUyVk8o2FH54m"
		},
	function(data){
		var access_token = data.response.access_token;
		var code = data.code;
		var message = data.message;
		alert(code);
		alert(access_token);
		alert(message);
		if(access_token!=""){
			$.post('https://api.iamport.kr/payments/cancel?_token='+access_token, 
					{
						imp_uid : "${imp_uid}"
					},
			    function(data){
					var code = data.code;
					alert(code);
			        if(code==0){
			        	location.href="/ChaChaCha/administrator/Payment_List/ListPro.do";
			        }else{
			        	alert("결제취소 실패");
			        	location.href="/ChaChaCha/administrator/Payment_List/ListPro.do";
			        }
			    }
			);
		}
	});
    return false;
}
</script>
<title>Insert title here</title>
</head>
<body onload="return check()">
</body>
</html>