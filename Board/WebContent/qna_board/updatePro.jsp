<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${r>0}">
<script>          
        alert("수정 완료");
        location.href="/Board/qna_board/list.do";
      
</script>
</c:if>