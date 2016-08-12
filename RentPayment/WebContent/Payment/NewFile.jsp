<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
   function mvPg(){                        
// mvPg()는 Function의 이름, 아무거나 지정하면 됨
// href=' ' 는 연결할 파일의 경로와 파일명
     window.location.href='/RentPayment/Payment/main.jsp'; 
     /* self.close(); */
   }
</script>
<title>Insert title here</title>
</head>
<body>
<input type="button" value="이동하기" onClick="javascript:mvPg()"></body>
</html>