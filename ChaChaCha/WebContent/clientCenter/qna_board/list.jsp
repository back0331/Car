<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>

<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c}">
<center>
<table width="800">  

	<td align="right" bgcolor="${value_c}">
	<a href="/clientCenter/qna_board/writeForm.do"> 글쓰기</a></td>
</table>

<c:if test="${count==0}">

<table width="800" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td align="center">
	게시판에 저장된 글이 없습니다.
	</td>  
</table>  
</c:if>

<c:if test="${count>0}">

<table border="1" width="800" cellpadding="0" cellspacing="0" align="center">
<tr height="30" bgcolor="${value_c }">
	  <td align="center"  width="50"  >번 호</td>
	  <td align="center"  width="100" >문의유형</td>
      <td align="center"  width="300" >제   목</td>
      <td align="center"  width="100" >아이디</td>
      <td align="center"  width="250" >작성일</td>
      <td align="center"  width="100" >IP</td>   
    </tr>
 <c:forEach var="article" items="${articleList}">
	
		<!--int com_count=cdb.getCommentCount(qna.getArticle_no()); -->
	<tr height="30">
	<td align="center" width="50">
	 <c:out value="${number}"/>
  	 <c:set var="number" value="${number - 1}"/>
	
	<td align="center"  width="100">${article.article_type}</a></td>   
    </td>
     <td width="300"> 

<c:if test="${user_id=='admin'}">
 <a href="/clientCenter/qna_board/content.do?article_no=${article.article_no}&pageNum=${currentPage}">
     ${article.article_subject}</a>
<c:if test="${com_check=='y'}"><img src="images/hot.gif" border="0"  height="16"></c:if>
   </c:if>
<c:if test="${user_id!='admin' }">
    <a href="/clientCenter/qna_board/lock.do?article_no=${article.article_no}&pageNum=${currentPage}">
     ${article.article_subject}</a>  
 </c:if>
    <td align="center"  width="100">${article.id}</a></td>
    <td align="center"  width="250">${article.reg_date}</td>

    <td align="center" width="100" >${article.ip}</td>
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
        <a href="/clientCenter/qna_board/list.do?pageNum=${startPage - 5 }">[이전]</a>
   </c:if>

   <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <a href="/clientCenter/qna_board/list.do?pageNum=${i}">[${i}]</a>
   </c:forEach>

   <c:if test="${endPage < pageCount}">
        <a href="/clientCenter/qna_board/list.do?pageNum=${startPage + 5}">[다음]</a>
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