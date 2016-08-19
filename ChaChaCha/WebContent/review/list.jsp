<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="./view/color.jspf"%>

<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c}">

<center>
<c:if test="${count==0 }">

<table width="800" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td align="center">
	게시판에 저장된 글이 없습니다.
	</td>
</table>
</c:if>
<c:if test="${count>0 }">
<table width="800" border="1" cellpadding="0" cellspacing="0">
<tr height="30" bgcolor="${value_c}">
	  <td align="center"  width="50"  >번 호</td>
	  <td align="center"  width="100" >이미지</td>
      <td align="center"  width="300" >제   목</td>
      <td align="center"  width="100" >아이디</td> 
      <td align="center"  width="250" >작성일</td>
</tr>
<c:forEach var="article" items="${articleList}">
<tr height="50">  
	<td align="center" width="50">
 	<c:out value="${number}"/>
  	<c:set var="number" value="${number - 1}"/>
	<td align="center"><img src="img.jpg" width="50" height="50"></img></td>


	<td align="center" width="300">
	 <a href="/review/lock.do?article_no=${article.article_no}&pageNum=${currentPage}">
     ${article.article_subject}</a></td> 
     <td align="center"  width="100">${article.id}</td> 
     <td align="center"  width="250">${article.reg_date}</td>
</tr>
     </c:forEach>
</table>
</c:if>


<c:if test="${count > 0}">
  <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
   <c:set var="pageBlock" value="${5}"/>
   <fmt:parseNumber var="result" value="${currentPage / 5}" integerOnly="true" />
   <c:set var="startPage" value="${result * 5+ 1}" />
   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
   <c:if test="${endPage > pageCount}">
        <c:set var="endPage" value="${pageCount}"/>
   </c:if>
         
   <c:if test="${startPage > 5}">
        <a href="/review/list.do?pageNum=${startPage - 5 }">[이전]</a>
   </c:if>

   <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <a href="/review/list.do?pageNum=${i}">[${i}]</a>
   </c:forEach>

   <c:if test="${endPage < pageCount}">
        <a href="/review/list.do?pageNum=${startPage + 5}">[다음]</a>
   </c:if>
</c:if>

<br></br>
<form>

<select name="searchn">
<option value="0">아이디</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>

<input type="text" name="search" size="30">
<input type="submit" value="검색">

</form>
</center>
</body>
</html>