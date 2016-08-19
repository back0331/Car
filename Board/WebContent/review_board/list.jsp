<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.ReviewBoardDBBean" %>
<%@ page import = "board.ReviewBoardDataBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.css"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c }">
<h1>이용후기</h1>
<hr/>

<c:if test="${count==0}">
<table width="800" border="1" cellpadding="0" cellspacing="0" align="center">
<tr><td align="center">게시판에 저장된 글이 없습니다.</td>
</table>
</c:if>

<c:if test="${count>0 }">
<table width="800" border="1" cellpadding="0" cellspacing="0">
<tr height="30" bgcolor="${value_c }">
	  <td align="center"  width="50"  >번 호</td>
	  <td align="center"  width="100" >이미지</td>
      <td align="center"  width="300" >제   목</td>
      <td align="center"  width="100" >아이디</td> 
      <td align="center"  width="250" >작성일</td>
</tr>
<c:forEach var="article" items="${articleList }" varStatus="status">
<tr height="50">  
	<td align="center" width="50">${status.count}<%-- <%=number-- %> --%></td><!-- 번호 -->
	<td align="center"><img src="img.jpg" width="50" height="50"></img></td><!-- 이미지 -->

	<td align="center" width="300">
	 <a href="/Board/review_board/content.do?article_no=${article.article_no }"><!-- &currentPage=${currentPage} -->
     ${article.article_subject }</a></td><!-- 제목 -->
     
     <td align="center"  width="100">${article.id }</td> <!-- 아이디 -->
     <td align="center"  width="250">${article.reg_date}</td><!-- 작성일 -->
</tr>
</c:forEach>
</table>
</c:if>

<c:if test="${count>0 }">
<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0?0:1) }"/>
<c:set var="startPage" value="${(currentPage/5)*5+1 }"/>
<c:set var="pageBlock" value="5"/>
<c:set var="endPage" value="${startPage+pageBlock-1 }"/>
<c:if test="${startPage>5 }">
	<a href="list.jsp?pageNum=${startPage-5 }">[이전]</a>
</c:if>
<c:forEach begin="${startPage }" end="${pageCount }" step="1" varStatus="status">
	<a href="list.jsp?pageNum=${status.count }">[${status.count }]</a>
</c:forEach>
<c:if test="${endPage<pageCount }">
	<a href="list.jsp?pageNum=${startPage+5 }">[다음]</a>
</c:if>
</c:if>
<br><br>
<form action="">
<select name="searchn">
<option value="0">아이디</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>
<input type="text" name="search" size="30">
<input type="submit" value="검색">
</form>
</body>
</html>