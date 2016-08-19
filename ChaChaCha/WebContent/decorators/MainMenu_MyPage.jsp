<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = (String)request.getSession().getAttribute("userId");
	request.setAttribute("id", id);
%>
<link type="text/css" rel="stylesheet" href="../decorators/style.css"/>
<html>
    <head>
    <script src="../decorators/script1.js" ></script>
        <title><decorator:title default="chachacha(차차차)입니다."/></title>
	    <decorator:head/>
    </head>
    <body>
    	<div class="headmenu">
			<a href="/ChaChaCha/realTimeReservation/book1InputForm.do" target="_self" onfocus="selectMenu()" onblur="blurMenu()">실시간 예약</a>
			<a href="/ChaChaCha/insertingMyCar/myCarRegisterInputForm.do" target="_self" onfocus="selectMenu()" onblur="blurMenu()">내 차 등록</a>
			<a href="/ChaChaCha/review/list.do" target="_self" onfocus="selectMenu()" onblur="blurMenu()">이용후기</a>
			<a href="/ChaChaCha/myPage/myPointView.do" target="_self" onfocus="selectMenu()" onblur="blurMenu()">마이페이지</a>
			<a href="/ChaChaCha/clientCenter/notice/list.do" target="_self" onfocus="selectMenu()" onblur="blurMenu()">고객센터</a>
			<c:if test="${empty id}"><input type="button" onclick="login()" value="로그인"></c:if>
			<c:if test="${!empty id}">${id}님<input type="button" onclick="logout()" value="로그아웃"></c:if>
		</div>
		<hr>
		<section>
			<div class="submenu">마이페이지</div>
			<nav>
				<a href="/ChaChaCha/myPage/myPointView.do" target="_self">내 마일리지</a><hr/>
				<a href="/ChaChaCha/myPage/myBookView.do" target="_self">예약내역</a><hr/>
				<a href="/ChaChaCha/myPage/myPointView.do" target="_self">취소내역</a><hr/>
				<a href="/ChaChaCha/myPage/myCarView.do" target="_self">내 차 정보</a><hr/>
				<a href="/ChaChaCha/myPage/myCarRegisterView.do" target="_self">내 차 등록내역</a><hr/>
				<div class="dropdown">
					<button class="dropbtn">불편사항신고</button>
					<div class="dropdown-content">
					<a href="" target="contents">신고하기</a><hr/>
					<a href="" target="contents">신고내역</a><hr/>
					</div>
				</div>
				<a href="/ChaChaCha/logon/modifyForm.do" target="_self">회원정보수정</a><hr/>
				<a href="/ChaChaCha/myPage/myPointView.do" target="_self">회원탈퇴</a><hr/>
			</nav>
			<div class="content" scrolling="yes"><decorator:body/></div>
		</section>
    </body>
</html>