<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://d3tik7g7snxrwv.cloudfront.net/js/jquery.1.11.1.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.1.js"></script>
<script type="text/javascript">
function After_Payment(rsp) {
	var msg = null;
	  if ( rsp.success === true ) {//성공이면 true, 실패면 false
		console.log( rsp.imp_uid );//아임포트 거래 고유번호
		console.log( rsp.pay_method );//결제수단. card:신용카드, trans:실시간계좌이체, vbank:가상계좌, phone:휴대폰소액결제
		console.log( rsp.merchant_uid );//상점에서 전달한 고유번호. 전달되지 않으면 임의로 생성됨
		console.log( rsp.name );//주문명
		console.log( rsp.paid_amount );//결제된 금액
		console.log( rsp.pg_provider );//PG사 이름. html5_inicis
		console.log( rsp.pg_tid );//PG사 승인번호
		console.log( rsp.apply_num );//카드사 승인번호(신용카드 결제일 때만 있음)
		console.log( rsp.vbank_num );//가상계좌 입금계좌번호(가상계좌 결제일 때만)
		console.log( rsp.vbank_date );//가상계좌 은행명(가상계좌 결제일 때만)
		console.log( rsp.vbank_holder );//가상계좌 예금주(가상계좌 결제일 때만)
		console.log( rsp.vbank_name );//가상계좌 입금기한(가상계좌 결제일 때만)
		console.log( rsp.buyer_name );//구매자명
		console.log( rsp.buyer_email );//구매자 Email
		console.log( rsp.buyer_tel );//구매자 전화번호
		console.log( rsp.buyer_addr );//구매자 배송주소
		console.log( rsp.buyer_postcode );//구매자 배송주소 우편번호
		console.log( rsp.custom_data );//결제요청시 전달한 custom_data.javascript object형태로 반환
		console.log( rsp.status );//결제 상태. paid:결제완료 ready:미결제 cancelled:결제취소 failed:결제실패
		console.log( rsp.paid_at );//결제성공 시점의 UNIX timestamp
		console.log( rsp.receipt_url );//결제건의 매출전표 URL
	  }else{
		console.log( error_code );//결제 실패 구분 문자열
		console.log( error_msg );//결제 실패에 대한 상세내용
	  }
}
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>