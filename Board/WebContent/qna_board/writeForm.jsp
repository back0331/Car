<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>

  
<body bgcolor="${bodyback_c}"> 
<h1>Q & A</h1>
<hr/>
<br>  
<center>
<form method="post" name="writeform" action="/Board/qna_board/writePro.do">
<input type="hidden" name="id" value="${user_id}">
<input type="hidden" name="article_no" value=${article_no}>


<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="${bodyback_c}"
   align="center">
 
  <tr>
<td  width="100"  bgcolor="${value_c}" align="center" >유 형</td>
<td>
<select name="article_type">
<option value="예약">예약</option>
<option value="결제">결제</option>
<option value="기타">기타</option>
</select>
</td>

  <tr>
    <td  width="100"  bgcolor="${value_c}" align="center" >제 목</td>
    <td  width="400">
        
       <input type="text" size="40" maxlength="50" name="article_subject"></td>

  </tr>

  <tr>
    <td  width="100"  bgcolor="${value_c}" align="center" >내 용</td>
    <td  width="400" >
     <textarea name="article_content" rows="15" cols="50"></textarea> </td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="${value_c}" align="center" >비밀 번호</td>
    <td  width="400" >
     <input type="password" size="8" maxlength="12" name="password">
</td>
  </tr>  
<tr>     
<td colspan=2 bgcolor="${value_c}" align="center">
  <input type="submit" value="등록" > 
  <input type="reset" value="다시작성">
  <input type="button" value="취소" OnClick="window.location='/Board/qna_board/list.do'">
</td></tr></table>   

</form>     
</body>
</html>     
