<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">		
<title>Insert title here</title>
</head>
<body>
<% response.sendRedirect("/Chart/MyPage/Reservation.doo"); %>
<!-- 		현재 실행중인 JSP페이지의 실행을 중단하고 다른 웹 자원이 대신 호출되도록 만드는 기능
		파라미터로 지정한 URL을 직접 호출하는 것이 아니라.
		웹 브라우저에 메세지를 보내,★ 웹 브라우저가 URL을 가지고 웹서버에 웹자원을 다시 요청!
		response.sendRedirect("호출할 웹 자원의 URL") -->
		
		
		
</body>
</html>