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
			<h3>고객센터</h3><hr/>
			<a href="" target="contents">공지사항</a><hr/>
			<a href="" target="contents">Q&A</a><hr/>
		</nav>
		<hr/>
		<section>
			<iframe name="contents" width="1000px" height="750px" scrolling="yes" frameborder="1"><decorator:body/></iframe>
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
</style>
</html>