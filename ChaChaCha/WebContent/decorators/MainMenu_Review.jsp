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
			<div class="submenu">이용후기</div>
			<div class="content"><decorator:body/></div>
		</section>
    </body>
</html>