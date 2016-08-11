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
    	<div class="headmenu">
			<a href="" target="contents" id="x" onfocus="selectMenu()" onblur="blurMenu()">실시간 예약</a>
			<a href="" target="contents" id="x" onfocus="selectMenu()" onblur="blurMenu()">내 차 등록</a>
			<a href="" target="contents" id="x" onfocus="selectMenu()" onblur="blurMenu()">이용후기</a>
			<a href="" target="contents" id="x" onfocus="selectMenu()" onblur="blurMenu()">마이페이지</a>
			<a href="" target="contents" id="x" onfocus="selectMenu()" onblur="blurMenu()">고객센터</a>
			<input type="button" onclick="" value="로그인">
		</div>
		
		<hr/>
		<nav>
			<h3>관리자페이지</h3><hr/>
			차량관리<hr/>
			<ul>
			<li><a href="" target="contents">차량상세조회</a></li>
			<li><a href="" target="contents">지점별차량정보</a></li>
			</ul>
			회원관리<hr/>
			<ul>
			<li><a href="" target="contents">회원목록</a></li>
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
		<hr/>
		<section>
			<iframe name="contents" width="900px" height="750px" scrolling="yes" frameborder="1"><decorator:body/></iframe>
		</section>
		<hr/>
    </body>
<style>
div.headmenu{text-align:center;}
body{margin:100px;padding:0;}
a{text-decoration:;color:#000000;margin:30px;text-align:center;}
header{width:1200px;height:100px;margin-top:30px;text-align:center;line-height:100px;}
nav{width:220px;height:500px;float:left;border-style:solid;margin-top:50px;}
iframe{border-style:hidden;margin:5px;border:0px;padding:0px;padding:0px;}
</style>
</html>