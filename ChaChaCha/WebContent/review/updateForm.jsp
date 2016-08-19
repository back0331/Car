<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="./view/color.jspf"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>

 
<body bgcolor="${bodyback_c}"> 
<br>
<center>
<form method="post" name="writeForm" action="/review/updatePro.do?pageNum=${pageNum}"
  onsubmit="return writeSave()">
<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="${bodyback_c}"
  align="center">
  <tr>
     <td  width="150"  bgcolor="${value_c}" align="center" >제 목</td>
    <td align="left" width="350">
       <input type="text" size="40" maxlength="50" name="article_subject" value="${review.article_subject}"></td>
  </tr>
  <tr>
    <td  width="150"  bgcolor="${value_c}" align="center" >내 용</td>
    <td align="left" width="350">
     <textarea rows="13" cols="50" name="article_content" >${review.article_content}</textarea></td>
  </tr>
    </table>    

  <br/>
  <tr>     
   <td colspan=2 bgcolor="${value_c}" align="center">
   <input type="hidden" name="article_no" value="${article_no}">
     <input type="submit" value="글수정" > 
     <input type="reset" value="다시작성">
     <input type="button" value="취소"
       onclick="document.location.href='/review/list.do?pageNum=${pageNum}'">
   </td>
</tr>

</form>

</body>
</html>     
