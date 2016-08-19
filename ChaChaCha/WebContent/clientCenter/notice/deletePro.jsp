<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>

<c:if test="${r>0}">
  <script>
  alert("삭제완료");
  location.href="/clientCenter/notice/list.do";
  </script>
</c:if>
