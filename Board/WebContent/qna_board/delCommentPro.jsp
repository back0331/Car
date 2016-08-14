<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>

<c:if test="${r>0}">

  <script>
  alert("삭제완료");
  location.href="/Board/qna_board/content.do?article_no="+${article_no}+"&pageNum="+${pageNum};
  </script>
</c:if>

