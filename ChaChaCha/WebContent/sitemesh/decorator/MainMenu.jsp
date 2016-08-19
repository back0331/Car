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
    	<div class="headmenu">
			<a href="" target="contents"></a>
			<a href="" target="contents"></a>
			<a href="" target="contents"></a>
			<a href="" target="contents"></a>
			<input type="button" onclick="" value="로그인">
		</div>
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
div.headmenu {overflow: auto;white-space: nowrap;}
div.scrollmenu a {display: inline-block;text-align: center;text-decoration: none;}
div.scrollmenu a:hover {}
</style>
</html>