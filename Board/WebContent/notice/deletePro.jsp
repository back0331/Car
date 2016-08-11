<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.NoticeBoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>


<% request.setCharacterEncoding("UTF-8");%>

<%
//아이디값 받아오고, 받아온 아이디로 비교하여 맞으면 바로 수정! 
 	 int article_no = Integer.parseInt(request.getParameter("article_no"));
   
	NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
   	 int r = dbPro.deleteArticle(article_no);
  if(r>0){%>
  <script>
  alert("삭제완료");
  location.href="list.jsp";
  </script>
 <%} %>