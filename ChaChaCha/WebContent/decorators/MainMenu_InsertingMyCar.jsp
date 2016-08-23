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
	    <style>section {margin-top:15px;margin-bottom:30px;height:auto;}</style>
    </head>
    <body>
    	<header>
    	<div class="headmenu" >
		<a href="/ChaChaCha/main/main.do"><img src="../decorators/cha.png" width="160px"/></a>
		<nav id="main-nav" class="header_menu">
	    	<c:if test="${empty id}">
	    	<ul>
	    		<li class="main_menu"><a href="/ChaChaCha/realTimeReservation/book1InputForm.do" target="_self" onclick="checkLogin();return false;">실시간예약</a></li>
	    		<li class="main_menu current"><a href="/ChaChaCha/insertingMyCar/myCarRegisterInputForm.do" target="_self" onclick="checkLogin();return false;">차량등록</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/review/list.do" target="_self">이용후기</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/myPage/point/myPointView.do" target="_self" onclick="checkLogin();return false;">마이페이지</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/clientCenter/notice/list.do" target="_self">고객센터</a></li>
	    	</ul>
	    	<div class="logBtn">
	    		<a href="#" onclick="login();return false;" id="login">로그인</a>
	    		<a href="#" onclick="logon();return false;">회원가입</a>
	    	</div>
	    	</c:if>
	    	<c:if test="${!empty id}">
	    	<ul>
	    		<li class="main_menu"><a href="/ChaChaCha/realTimeReservation/book1InputForm.do" target="_self">실시간예약</a></li>
	    		<li class="main_menu current"><a href="/ChaChaCha/insertingMyCar/myCarRegisterInputForm.do" target="_self">차량등록</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/review/list.do" target="_self">이용후기</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">마이페이지</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/clientCenter/notice/list.do" target="_self">고객센터</a></li>
	    	</ul>
	    	<div class="logBtn1">
	    		<span><b>${id}</b> 님</span>
	    		<a href="#" onclick="logout();return false;">Logout</a>
	    	</div>
	    	</c:if>
	    </nav>
		</div>
		</header>
		<section>
			<div id="con">
			<div class="submenu">내 차 등록</div>
			<div class="content"><decorator:body/></div>
			</div>
		</section>
		<footer>
		</footer>
    </body>
</html>