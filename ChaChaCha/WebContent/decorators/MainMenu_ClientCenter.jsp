<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = (String)request.getSession().getAttribute("userId");
	request.setAttribute("id", id);
	
	String path=request.getRequestURI();
	int index = path.lastIndexOf("/");
	String type = path.substring(24,index);
	request.setAttribute("type", type);
%>
<link type="text/css" rel="stylesheet" href="../../decorators/style.css"/>
<html>
    <head>
    <script src="../../decorators/script1.js" ></script>
        <title><decorator:title default="chachacha(차차차)입니다."/></title>
	    <decorator:head/>
    </head>
    <body>
    	<header>
    	<div class="headmenu" >
		<a href="/ChaChaCha/main/main.do"><img src="../../decorators/cha.png" width="160px"/></a>
		<nav id="main-nav" class="header_menu">
	    	<c:if test="${empty id}">
	    	<ul>
	    		<li class="main_menu"><a href="/ChaChaCha/realTimeReservation/book1InputForm.do" target="_self" onclick="checkLogin();return false;">실시간예약</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/insertingMyCar/myCarRegisterInputForm.do" target="_self" onclick="checkLogin();return false;">차량등록</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/review/list.do" target="_self">이용후기</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/myPage/point/myPointView.do" target="_self" onclick="checkLogin();return false;">마이페이지</a></li>
	    		<li class="main_menu current"><a href="/ChaChaCha/clientCenter/notice/list.do" target="_self">고객센터</a></li>
	    	</ul>
	    	<div class="logBtn">
	    		<a href="#" onclick="login();return false;" id="login">로그인</a>
	    		<a href="#" onclick="logon();return false;">회원가입</a>
	    	</div>
	    	</c:if>
	    	<c:if test="${!empty id}">
	    	<ul>
	    		<li class="main_menu"><a href="/ChaChaCha/realTimeReservation/book1InputForm.do" target="_self">실시간예약</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/insertingMyCar/myCarRegisterInputForm.do" target="_self">차량등록</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/review/list.do" target="_self">이용후기</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">마이페이지</a></li>
	    		<li class="main_menu current"><a href="/ChaChaCha/clientCenter/notice/list.do" target="_self">고객센터</a></li>
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
			<div class="submenu">고객센터<span>차차차의 대한 궁금증 해결을 도와드립니다.</span></div>
			<div class="nav_menu">
			<nav id="menuItem1">
				<c:if test="${type=='notice'}">
				<div id="notice">
						<a href="/ChaChaCha/clientCenter/notice/list.do" target="_self">
						<img src="../../decorators/images/notice_n.png" width="100px" /></a>
				</div>
				<div id="qna">
					<a href="/ChaChaCha/clientCenter/qna_board/list.do" target="_self">
					<img src="../../decorators/images/qna_y.png" width="100px" /></a>
				</div>
				
				</c:if>
				<c:if test="${type=='qna_board'}">
				<div id="notice">
						<a href="/ChaChaCha/clientCenter/notice/list.do" target="_self">
						<img src="../../decorators/images/notice_y.png" width="100px" /></a>
				</div>
				<div id="qna">
					<a href="/ChaChaCha/clientCenter/qna_board/list.do" target="_self">
					<img src="../../decorators/images/qna_n.png" width="100px" /></a>
				</div>
				</c:if>
			</nav>
			<div class="content1" scrolling="yes"><decorator:body/></div>
			</div>
			</div>
		</section>
		<footer></footer>
		
    </body>
</html>