<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>

<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c}">
<h5>차차차 공지사항을 알려드립니다</h5>

<br/>
<center>
<table width="800" >
<c:if test="${user_id=='admin'}">  
	<td align="right" bgcolor="${value_c}">
	<a href="/clientCenter/notice/writeForm.do"> 글쓰기</a></td>
</c:if>
</table>


<c:if test="${count==0 }">

<table width="800" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td align="center">
	게시판에 저장된 글이 없습니다.
	</td>
</table>
</c:if>

<c:if test="${count>0 }">
<table border="1" width="800" cellpadding="0" cellspacing="0" align="center">
<tr height="30" bgcolor="${value_c}">
	  <td align="center"  width="50"  >번 호</td>
      <td align="center"  width="250" >제   목</td>
      <td align="center"  width="150" >작성일</td>
    </tr>
 <c:forEach var="article" items="${articleList}">
   
		<!-- int comment_no=cdb.getCommentCount(article.getArticle_no()); -->
		
	<tr height="30">
	<td align="center" width="50">
	 <c:out value="${number}"/>
  	 <c:set var="number" value="${number - 1}"/>
	<td width="250">
     <a href="/clientCenter/notice/content.do?article_no=${article.article_no }&pageNum=${currentPage}">
           ${article.article_subject }</a>
          
       
    <td align="center"  width="150">${article.reg_date }</td>
    
  </tr>
 </c:forEach>
</table>
</c:if>
<br>

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
        <a href="/clientCenter/notice/list.do?pageNum=${startPage - 5 }">[이전]</a>
   </c:if>

   <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <a href="/clientCenter/notice/list.do?pageNum=${i}">[${i}]</a>
   </c:forEach>

   <c:if test="${endPage < pageCount}">
        <a href="/clientCenter/notice/list.do?pageNum=${startPage + 5}">[다음]</a>
   </c:if>
</c:if>

<br></br>
<form>

<select name="searchn">
<option value="0">제목</option>
<option value="1">내용</option>
</select>

<input type="text" name="search" size="30">
<input type="submit" value="검색">

</form>
</center>
</body>
</html>