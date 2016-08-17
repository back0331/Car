<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="${bodyback_c}"> 
<b>이용후기</b>
<hr/>
<br>
<center>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" 
  bgcolor="${bodyback_c}" align="center"> 

  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c}">글제목</td>
    <td align="center" width="375" align="center" colspan="3">
     ${review.article_subject}</td>
  </tr>
  <tr>
     <td align="center" width="125" bgcolor="${value_c}">글내용</td> 
    <td align="left" width="375" colspan="3"><pre>${review.article_content}</pre></td>
  </tr>
  </table>
  <br>
	
  <tr height="30">     
    <td colspan="4" bgcolor="${value_c}" align="center" >
  <input type="button" value="글수정"
       onclick="document.location.href='/Board/review_board/updateForm.do?article_no=${review.article_no}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="글삭제"onclick="document.location.href='/Board/review_board/deletePro.do?article_no=${review.article_no}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="글목록"onclick="document.location.href='/Board/review_board/list.do?pageNum=${pageNum}'">
    </td>
  </tr>

</form>

			</center>
			</body>
</html>