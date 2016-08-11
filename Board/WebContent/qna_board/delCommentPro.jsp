<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="board.QnACommentDBBean" %>
<%@ page import="java.sql.Timestamp" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	int comment_no=Integer.parseInt(request.getParameter("comment_no"));
	int article_no=Integer.parseInt(request.getParameter("article_no"));
	String pageNum=request.getParameter("pageNum");

	
	QnACommentDBBean cmtPro=QnACommentDBBean.getInstance();

	 int r = cmtPro.deleteComment(comment_no, article_no);
  if(r>0){%>
  <script>
  alert("삭제완료");
  location.href="content.jsp";
  </script>
 <%} %>

