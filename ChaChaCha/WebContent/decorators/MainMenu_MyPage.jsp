<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = (String)request.getSession().getAttribute("userId");
	request.setAttribute("id", id);
	String path=request.getRequestURI();
	int index = path.lastIndexOf("/");
	String type = path.substring(18,index);
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
	    		<li class="main_menu current"><a href="/ChaChaCha/myPage/point/myPointView.do" target="_self" onclick="checkLogin();return false;">마이페이지</a></li>
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
	    		<li class="main_menu"><a href="/ChaChaCha/insertingMyCar/myCarRegisterInputForm.do" target="_self">차량등록</a></li>
	    		<li class="main_menu"><a href="/ChaChaCha/review/list.do" target="_self">이용후기</a></li>
	    		<li class="main_menu current"><a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">마이페이지</a></li>
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
			<div class="submenu">마이페이지</div>
			<div class="nav_menu">
			<nav id="menuItem">
	 			<!-- 1. point -->
				<c:if test="${type=='point'}">
				<div id="point">
						<a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">
						<img src="../../decorators/images/point_y.png"/></a>
				</div>
				<div id="book">
					<a href="/ChaChaCha/myPage/book/myBookView.do" target="_self">
					<img src="../../decorators/images/book_n.png"/></a>
				</div>
				<div id="mycar">
						<a href="/ChaChaCha/myPage/mycar/myCarView.do" target="_self">
						<img src="../../decorators/images/mycar_n.png"/></a>
				</div>
				<div id="register">
					<a href="/ChaChaCha/myPage/register/myCarRegisterView.do" target="_self">
					<img src="../../decorators/images/register_n.png"/></a>
				</div>
				<div id="complain">
					<a href="#" target="_self">
					<img src="../../decorators/images/complain_n.png"/></a>
				</div>
				<div id="my">
					<a href="/ChaChaCha/myPage/my/userModifyForm.do" target="_self">
					<img src="../../decorators/images/my_n.png"/></a>
				</div>
				</c:if>
				
				<!-- 2.book -->
				<c:if test="${type=='book'}">
				<div id="point">
						<a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">
						<img src="../../decorators/images/point_n.png"/></a>
				</div>
				<div id="book">
					<a href="/ChaChaCha/myPage/book/myBookView.do" target="_self">
					<img src="../../decorators/images/book_y.png"/></a>
				</div>
				<div id="mycar">
						<a href="/ChaChaCha/myPage/mycar/myCarView.do" target="_self">
						<img src="../../decorators/images/mycar_n.png"/></a>
				</div>
				<div id="register">
					<a href="/ChaChaCha/myPage/register/myCarRegisterView.do" target="_self">
					<img src="../../decorators/images/register_n.png"/></a>
				</div>
				<div id="complain">
					<a href="#" target="_self">
					<img src="../../decorators/images/complain_n.png"/></a>
				</div>
				<div id="my">
					<a href="/ChaChaCha/myPage/my/userModifyForm.do" target="_self">
					<img src="../../decorators/images/my_n.png"/></a>
				</div>
				</c:if>
				
				<!-- 3. mycar -->
				<c:if test="${type=='mycar'}">
				<div id="point">
						<a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">
						<img src="../../decorators/images/point_n.png"/></a>
				</div>
				<div id="book">
					<a href="/ChaChaCha/myPage/book/myBookView.do" target="_self">
					<img src="../../decorators/images/book_n.png"/></a>
				</div>
				<div id="mycar">
						<a href="/ChaChaCha/myPage/mycar/myCarView.do" target="_self">
						<img src="../../decorators/images/mycar_y.png"/></a>
				</div>
				<div id="register">
					<a href="/ChaChaCha/myPage/register/myCarRegisterView.do" target="_self">
					<img src="../../decorators/images/register_n.png"/></a>
				</div>
				<div id="complain">
					<a href="#" target="_self">
					<img src="../../decorators/images/complain_n.png"/></a>
				</div>
				<div id="my">
					<a href="/ChaChaCha/myPage/my/userModifyForm.do" target="_self">
					<img src="../../decorators/images/my_n.png"/></a>
				</div>
				</c:if>
				
				<!-- 4. register -->
				<c:if test="${type=='register'}">
				<div id="point">
						<a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">
						<img src="../../decorators/images/point_n.png"/></a>
				</div>
				<div id="book">
					<a href="/ChaChaCha/myPage/book/myBookView.do" target="_self">
					<img src="../../decorators/images/book_n.png"/></a>
				</div>
				<div id="mycar">
						<a href="/ChaChaCha/myPage/mycar/myCarView.do" target="_self">
						<img src="../../decorators/images/mycar_n.png"/></a>
				</div>
				<div id="register">
					<a href="/ChaChaCha/myPage/register/myCarRegisterView.do" target="_self">
					<img src="../../decorators/images/register_y.png"/></a>
				</div>
				<div id="complain">
					<a href="#" target="_self">
					<img src="../../decorators/images/complain_n.png"/></a>
				</div>
				<div id="my">
					<a href="/ChaChaCha/myPage/my/userModifyForm.do" target="_self">
					<img src="../../decorators/images/my_n.png"/></a>
				</div>
				</c:if>
				
				<!-- 5. complain -->
				<c:if test="${type=='complain'}">
				<div id="point">
						<a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">
						<img src="../../decorators/images/point_n.png"/></a>
				</div>
				<div id="book">
					<a href="/ChaChaCha/myPage/book/myBookView.do" target="_self">
					<img src="../../decorators/images/book_n.png"/></a>
				</div>
				<div id="mycar">
						<a href="/ChaChaCha/myPage/mycar/myCarView.do" target="_self">
						<img src="../../decorators/images/mycar_n.png"/></a>
				</div>
				<div id="register">
					<a href="/ChaChaCha/myPage/register/myCarRegisterView.do" target="_self">
					<img src="../../decorators/images/register_n.png"/></a>
				</div>
				<div id="complain">
					<a href="#" target="_self">
					<img src="../../decorators/images/complain_y.png"/></a>
				</div>
				<div id="my">
					<a href="/ChaChaCha/myPage/my/userModifyForm.do" target="_self">
					<img src="../../decorators/images/my_n.png"/></a>
				</div>
				</c:if>
				
				<!-- 6. my -->
				<c:if test="${type=='my'}">
				<div id="point">
						<a href="/ChaChaCha/myPage/point/myPointView.do" target="_self">
						<img src="../../decorators/images/point_n.png"/></a>
				</div>
				<div id="book">
					<a href="/ChaChaCha/myPage/book/myBookView.do" target="_self">
					<img src="../../decorators/images/book_n.png"/></a>
				</div>
				<div id="mycar">
						<a href="/ChaChaCha/myPage/mycar/myCarView.do" target="_self">
						<img src="../../decorators/images/mycar_n.png"/></a>
				</div>
				<div id="register">
					<a href="/ChaChaCha/myPage/register/myCarRegisterView.do" target="_self">
					<img src="../../decorators/images/register_n.png"/></a>
				</div>
				<div id="complain">
					<a href="#" target="_self">
					<img src="../../decorators/images/complain_n.png"/></a>
				</div>
				<div id="my">
					<a href="/ChaChaCha/myPage/my/userModifyForm.do" target="_self">
					<img src="../../decorators/images/my_y.png"/></a>
				</div>
				</c:if>
				
				
			</nav>
			<div class="content2" scrolling="yes"><decorator:body/></div>
			</div>
			</div>
		</section>
				
		<footer>
		</footer>
    </body>
</html>