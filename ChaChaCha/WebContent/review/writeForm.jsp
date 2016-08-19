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
<form method="post" name="writeform" action="/review/writePro.do" onsubmit="return writeSave()">
<input type="hidden" name="id" value="${user_id}"> 
<input type="hidden" name="article_no" value="${article_no}">


<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="${bodyback_c}"
   align="center">
 

  <tr>
    <td  width="100"  bgcolor="${value_c }" align="center" >제 목</td>
    <td  width="400">
       <input type="text" size="40" maxlength="50" name="article_subject"></td>


  <tr>
    <td  width="100"  bgcolor="${value_c }" align="center" >내 용</td>
    <td  width="400" >
     <textarea name="article_content" rows="15" cols="50"></textarea> </td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="${value_c }" align="center" >비밀 번호</td>
    <td  width="400" >
     <input type="password" size="8" maxlength="12" name="password">
</td>
  </tr>  
<tr>     
<td colspan=2 bgcolor="${value_c }" align="center">
  <input type="submit" value="등록" > 
  <input type="reset" value="다시작성">
  <input type="button" value="취소" OnClick="window.location='/review/list.do'">
</td></tr></table>   
   
</form>     
</body>
</html>     
