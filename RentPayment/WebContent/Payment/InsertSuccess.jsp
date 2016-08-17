<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
/* var response = new HttpServletResponse(); */
setTimeout(function(){
	location.href="After_Payment.jsp";}, 3000);
</script>
<title>Insert title here</title>
</head>
<body>
<img src="./loading.gif" width=100px height=100px>
<!-- 로딩중을 연출하기 위함임. 별 의미 없음. -->
<!-- After_Payment에 다른 작업을 합치면 될 듯함. -->
</body>
</html>