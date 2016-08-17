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
	//json형식의 객체 호출방법. 파라미터가 key, value를 가짐. pg가 키, '${pg}'가 밸류
    pg : '${pg}',
    pay_method : '${pay_method}',
    merchant_uid : 'merchant_' + new Date().getTime(),
    name : 'chachacha',
    amount : '${total_price}',
    buyer_email : '${email}',
    buyer_name : '${name}',
    buyer_tel : '${phone}',
    buyer_addr : '${address}',
    buyer_postcode : '${zipcode}',
    notice_url : 'http://localhost:8088/RentPayment/Payment/InsertSuccess.jsp'
}, function(rsp){
    if ( rsp.success ) {
        var msg = '결제가 완료되었습니다.';
        msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;
        msg += 'book_no: ' + '${book_no}';
        alert(msg);
        setTimeout(location.href="/RentPayment/Payment/InsertImp_uid.do?imp_uid="+rsp.imp_uid+"&book_no=${book_no}", 0);
        //결제성공시 결제한 정보를 alert창으로 띄운뒤 부여된 imp_uid를 db저장하기 위해 InsertImp_uidAction을 실행함.
        //저장할 imp_uid와 저장할 위치를 찾을 용도의 book_no을 갖고 이동.
    } else { 
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
        alert(msg);
    }
});
</script>
<title>Insert title here</title>
</head>
<body>
결제중
</body>

</html>