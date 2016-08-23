<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${r>0}">

	  <script>
	  alert("후기 작성이 완료되었습니다.");
	  location.href="/ChaChaCha/review/list.do";
	  </script>
	  
</c:if> 
