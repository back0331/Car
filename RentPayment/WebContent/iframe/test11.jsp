<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<h1>iframe 홈페이지</h1>
<title>Insert title here</title>

</head>
<body>
<nav>
<ul>
	<li><a href="index.html">HOME</a></li>
	<li><a href="html.html" target="myframe">HTML</a></li>
	<li><a href="css.html" target="myframe">CSS</a></li>
	<li><a href="guest.html" target="myframe">GUEST</a></li>
</ul>
</nav>

<section>
<iframe name="myframe" width="1000px" height="800px" frameborder="1"></iframe>
</section>

<footer>
<h4>copyright</h4>
</footer>
</body>
<style>
body{
background-color:#999999;
margin:30px;
}
a{
text-decoration:;
color:#000000;
}
header{
width:1000px;
height:100px;
margin-top:30px;
background-color:#333333;
color:#FFF;
text-align:center;
line-height:100px;
}
nav{
width:200px;
height:600px;
float:left;
background-color:#666666;
}
footer{
center:both;
width:1000px;
height:50px;
line-height:50px;
background-color:#333333;
}
footer h4{
margin-left:100px;
color:#FFF;
}
</style>
</html>