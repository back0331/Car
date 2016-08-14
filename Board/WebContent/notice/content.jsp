<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<script>
function delete_content(no){
	if(confirm("정말로 삭제하시겠습니까??")){
		location.href="/Board/notice/deletePro.do?article_no="+no;
	}
}

</script>

<body bgcolor="${bodyback_c}"> 
<b>공지사항</b>
<hr/>
<br>
<center>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" 
  bgcolor="${bodyback_c}" align="center"> 
   <tr height="30">
 	 <td align="center" width="125" bgcolor="${value_c }">글 번호</td>
    <td align="center" width="125" align="center">
   ${article.article_no}</td>
     <td align="center" width="125" bgcolor="${value_c }" >작성일</td>
    <td align="center" width="125" align="center">
     ${article.reg_date}</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c }">글제목</td>
    <td align="center" width="375" align="center" colspan="3">
     ${article.article_subject }</td>
  </tr>
  <tr>
     <td align="center" width="125" bgcolor="${value_c }">글내용</td> 
    <td align="left" width="375" colspan="3"><pre>${article.article_content}</pre></td>
  </tr>
  </table>
  <br>
<c:if test="${user_id=='admin'}">

  <tr height="30">     
    <td colspan="4" bgcolor="${value_c }" align="center" >
  <input type="button" value="글수정" onclick="document.location.href='/Board/notice/updateForm.do?article_no=${article.article_no}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="글삭제" onclick="delete_content('${article.article_no}')">
   &nbsp;&nbsp;&nbsp;&nbsp;
   <input type="button" value="글목록"onclick="document.location.href='/Board/notice/list.do?pageNum=${pageNum}'">
    </td>
  </tr>
</c:if>  
<c:if test="${user_id !='admin'}">
    <input type="button" value="글목록"onclick="document.location.href='/Board/notice/list.do?pageNum=${pageNum}'">
</c:if>
</form>

			</center>
			</body>
</html>