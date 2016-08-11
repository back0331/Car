<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
    <head>
        <title><decorator:title default="chachacha(차차차)입니다." /></title>
        <decorator:head />
    </head>
    <body>
    	<div>
			<a href="" target="contents">실시간 예약</a>
			<a href="" target="contents">내 차 등록</a>
			<a href="" target="contents">이용후기</a>
			<a href="" target="contents">마이페이지</a>
			<a href="" target="contents">고객센터</a>
			<input type="button" onclick="" value="로그인">
		</div>
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
			<iframe name="contents" width="800px" height="600px" frameborder="1"></iframe>
			<decorator:body/>
		</section>
		<hr/>
    </body>
<style>
body{margin:100px;}
a{text-decoration:;color:#000000;}
header{width:1000px;height:100px;margin-top:30px;background-color:#333333;color:#FFF;text-align:center;line-height:100px;}
nav{width:200px;height:600px;float:left;background-color:#666666;}
</style>
</html>