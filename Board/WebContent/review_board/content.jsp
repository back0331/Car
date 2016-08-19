<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.ReviewBoardDBBean" %>
<%@ page import = "board.ReviewBoardDataBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c }"> 
<b>이용후기</b>
<hr/>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" bgcolor="${bodyback_c }" align="center"> 

  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c }">글제목</td>
    <td align="center" width="375" align="center" colspan="3">
     ${article_subject}</td>
  </tr>
  <tr>
     <td align="center" width="125" bgcolor="${value_c }">글내용</td> 
    <td align="left" width="375" colspan="3"><pre>${article_content}</pre></td>
  </tr>
  </table>
  <br>
	
  <tr height="30">     
    <td colspan="4" bgcolor="${value_c }" align="center" >
  <input type="button" value="글수정"
       onclick="document.location.href='updatelock.jsp?article_no=${article_no}'">&nbsp;&nbsp;&nbsp;&nbsp;<!-- &pageNum=${currentPage } -->
  <input type="button" value="글삭제"onclick="document.location.href='deletelock.jsp?article_no=${article_no}'">&nbsp;&nbsp;&nbsp;&nbsp;<!-- &pageNum=${currentPage } -->
      <input type="button" value="글목록"onclick="document.location.href='list.jsp?pageNum=${currentPage}'">
    </td>
  </tr>

</form>
</body>
</html>