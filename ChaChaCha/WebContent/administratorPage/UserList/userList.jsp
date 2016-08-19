<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../view/color.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="../../decorators/style.css" rel="stylesheet" type="text/css">
<script>
function userListPro(){
	location.href="/ChaChaCha/administratorPage/UserList/userListPro.do";
	return false;
}
</script>
</head>
<body onload="return userListPro()">
<table border="1">
<tr>
<td>&nbsp;&nbsp;&nbsp;</td><td>아이디</td><td>비밀번호</td><td>이름</td><td>전화번호</td>
<td>주소(우편번호)</td><td>이메일</td><td>등록일</td><td>생년월일</td><td>마일리지</td><td>등급</td>
</tr>
<c:forEach var="userList" items="${userList }" varStatus="status">
<tr>

<td>${status.count }</td>
<td><a href="/ChaChaCha/administratorPage/UserList/userKick.do">${userList.id }</a></td>
<td>${userList.password }</td>
<td>${userList.name }</td>
<td>${userList.phone }</td>
<td>${userList.address}  ${userList.zipcode }</td>
<td>${userList.email }</td>
<td>${userList.reg_date }</td>
<td>${userList.birth }</td>
<td>${userList.point }</td>
<td>${userList.grade }<input type="hidden" name="${userList.id }" value="${userList.id }"></td>

</tr>
</c:forEach>
</table>
<form>
<c:if test="${userCount>0 }">
  <c:set var="pageCount" value="${userCount / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
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
</form>
<select name="searchn">
<option value="0">아이디</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>
<input type="text" name="search" size="30">
<input type="submit" value="검색">
</body>
</html>