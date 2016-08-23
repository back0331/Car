<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/jquery.1.11.1.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.1.js"></script>
<script type="text/javascript">
var IMP = window.IMP;
IMP.init('imp32851262');
IMP.request_pay({
    pg : '${pg}', // version 1.1.0부터 지원.
        /*
            'kakao':카카오페이,
            'inicis':이니시스, 'html5_inicis':이니시스(웹표준결제),
            'nice':나이스,
            'jtnet':jtnet,
            'uplus':LG유플러스
        */
    pay_method : '${pay_method}', // 'card' : 신용카드 | 'trans' : 실시간계좌이체 | 'vbank' : 가상계좌 | 'phone' : 휴대폰소액결제
    merchant_uid : 'merchant_' + new Date().getTime(),
    name : 'chachacha',
    amount : '${total_price}',
    buyer_email : '${email}',
    buyer_name : '${name}',
    buyer_tel : '${phone}',
    buyer_addr : '${address}',
    buyer_postcode : '${zipcode}',
    m_redirect_url : 'InsertSuccess.jsp'
     //in app browser결제에서만 사용 
}, function(rsp){
    if ( rsp.success ) {
        var msg = '결제가 완료되었습니다.';
        msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;
    } else { 
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
    }
});
</script>

<!-- 결제창 띄우는 코드임.  -->
<title>Insert title here</title>
</head>
<body>
<script async>
window.onload=function(){location.href="InsertSuccess.jsp";}
</script>
</body>
</html>