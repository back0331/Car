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
    <script src="../../decorators/script1.js" ></script>
    <script src="../../decorators/style.css" ></script>
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
			<div class="submenu">관리자페이지</div>
			<nav>
				차량관리<hr/>
				<ul>
				<li><a href="" target="contents">차량상세조회</a></li>
				<li><a href="" target="contents">지점별차량정보</a></li>
				</ul>
				회원관리<hr/>
				<ul>
				<li><a href="/ChaChaCha/administratorPage/UserList/userListPro.do" target="contents">회원목록</a></li>
				<li><a href="" target="contents">탈퇴회원목록</a></li>
				</ul>
				게시판관리<hr/>
				<ul>
				<li><a href="" target="contents">공지사항</a></li>
				<li><a href="" target="contents">Q&A</a></li>
				</ul>
				<a href="" target="contents">결제관리</a><hr/>
				<a href="" target="contents">예약관리</a><hr/>
				<a href="" target="contents">불편사항관리</a><hr/>
			</nav>
			<div class="content" scrolling="yes"><decorator:body/></div>
		</section>
    </body>
</html>