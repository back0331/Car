<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.ReviewBoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="review" scope="page" class="board.ReviewBoardDataBean">
<jsp:setProperty name="review" property="*"/>
</jsp:useBean>

<%
	review.setReg_date(new Timestamp(System.currentTimeMillis()));
	

	ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
	int r=dbPro.insertArticle(review); 

	 if(r>0){%>
	  <script>
	  alert("후기작성 완료");
	  location.href="list.jsp";
	  </script>
	 <%} %>
%>  
