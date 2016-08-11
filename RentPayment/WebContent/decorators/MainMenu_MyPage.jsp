<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
    <head>
    <script type="text/javascript">
    	function selectMenu(){
    		document.getElementById(x).style.background = "red";
    	}
    	function blurMenu(){
    		document.getElementById(x).style.background = "white";
    	}
    </script>
        <title><decorator:title default="chachacha(차차차)입니다." /></title>
        <decorator:head />
    </head>
    <body>
    	
    	<div>
			<a href="" target="contents" onfocus="selectMenu()" onblur="blurMenu()">실시간 예약</a>
			<a href="" target="contents" onfocus="selectMenu()" onblur="blurMenu()">내 차 등록</a>
			<a href="" target="contents" onfocus="selectMenu()" onblur="blurMenu()">이용후기</a>
			<a href="" target="contents" onfocus="selectMenu()" onblur="blurMenu()">마이페이지</a>
			<a href="" target="contents" onfocus="selectMenu()" onblur="blurMenu()">고객센터</a>
			<input type="button" onclick="" value="로그인">
		</div>
		
		<hr/>
		<nav>
			<h3>마이페이지</h3><hr/>
			<a href="" target="contents">내 마일리지</a><hr/>
			<a href="" target="contents">예약내역</a><hr/>
			<a href="" target="contents">취소내역</a><hr/>
			<a href="" target="contents">내 차 정보</a><hr/>
			<a href="" target="contents">내 차 등록내역</a><hr/>
			<div class="dropdown">
			<button class="dropbtn">불편사항신고</button>
				<div class="dropdown-content">
				<a href="" target="contents">신고하기</a><hr/>
				<a href="" target="contents">신고내역</a><hr/>
				</div>
			</div>
			<a href="" target="contents">회원정보수정</a><hr/>
			<a href="" target="contents">회원탈퇴</a><hr/>
		</nav>
<!-- 		<div class="dropdown">
  			<ul><button class="dropbtn">Dropdown</button></ul>
  			<div class="dropdown-content">
    			<li><a href="#" align="right">Link 1</a></li>
   				<li><a href="#" align="right">Link 2</a></li>
   				<li><a href="#" align="right">Link 3</a></li>
 			</div>
		</div> -->
		<hr/>
		<section>
			<iframe name="contents" width="1000px" height="750px" frameborder="1" scrolling="yes"><decorator:body/></iframe>
			<hr/>
		</section>
		<hr/>
    </body>
<style>
div.headmenu{text-align:center;}
body{margin:100px;}
a{text-decoration:;color:#000000;margin:30px;text-align:center;}
header{width:1000px;height:100px;margin-top:30px;text-align:center;line-height:100px;}
nav{width:220px;height:600px;float:left;border-style:solid;margin-top:50px;padding:0px;}
iframe{border-style:hidden;margin:5px;border:0px;padding:0px;padding:0px;}
.dropdown {position: relative;display: inline-block;}
.dropdown-content {display: none;position: absolute;min-width: 160px;box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);}
.dropdown:hover .dropdown-content {display: block;}
.dropdown:hover .dropbtn {}
</style>
</html>