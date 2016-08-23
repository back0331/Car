<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function cancel(book_no){
		if(confirm("예약번호 : "+book_no+"\n해당 예약을 취소하시겠습니까?")){
			window.location.href="/ChaChaCha/myPage/book/myBookDelete.do?book_no="+book_no;
		}else{
			return false;
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
	body {font-family: 'NanumSquareR' !important;}
	h2 {padding-left:40px;padding-top:20px;}
	div#bList {width:800px; margin:auto;margin-top: 20px;border-top: 2px solid #7F7A7A;}
	div#bList table th{background-color: #F9F9F9; height:30px;}
	div#bList table td {height: 100px; border-bottom: thin solid #DFDEDE;}
	div#pageNum {text-align: center; margin-top: 50px; font-size: 20px;}
	div#pageNum a {text-decoration: none; color:black;padding-left: 5px;}
</style>
</head>
<body>
<h2>예약내역</h2>
<div id="bList">
<table width="100%" cellspacing="0" style="text-align: center;">
	<tr>
		<th width="100px">예약날짜</th>
		<th width="80px">예약번호</th>
		<th colspan="2" width="340px">차량</th>
		<th width="110px;">기간</th>
		<th width="70px">총금액</th>
		<th width="100px">예약상태</th>
	</tr>
	<c:if test="${empty bookList}">
		<tr><td colspan="7">예약내역이 존재하지 않습니다.</td></tr>
	</c:if>
	<c:if test="${!empty bookList}">
		<c:forEach var="book" items="${bookList}">
		<tr>
			<td>${book.reg_date}</td>
			<td>${book.book_no}</td>
			<td><img src="../../realTimeReservation/car_images/${book.car_no}.jpg" width="80px"/></td>
			<td><b>${book.car_name}</b><br>지점 : ${book.agency_name}</td> 
			<td>${book.rent_date}<br>-<br>${book.return_date}</td>
			<td>${book.total_price}</td>
			<td>
				<c:if test="${book.state==0}">예약대기<br>
					<input type="button" value="결제하기"/><br>
					<input type="button" value="취소" onclick="return cancel('${book.book_no}')"/></c:if>
				<c:if test="${book.state==1}">예약완료<br>
					[${book.state_date}]<br>
					<c:if test="${book.return_date<today && book.review=='no'}">
						<input type="button" value="후기작성" onclick="window.location='/ChaChaCha/review/writeForm.do?book_no=${book.book_no}'"/>
					</c:if>
				</c:if>
				<c:if test="${book.state==2}">예약취소<br>
					[${book.state_date}]</c:if>
			</td>
		</tr>
		</c:forEach>
	</c:if>
</table>
</div>
<div id="pageNum">
	<c:if test="${endPage>5}"><a href="">[다음]</a></c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<a href="/ChaChaCha/myPage/book/myBookView.do?pageNum=${i}">${i} </a>
	</c:forEach>
	<c:if test="${startPage>5}"><a href="">[이전]</a></c:if>
</div>
</body>
</html>