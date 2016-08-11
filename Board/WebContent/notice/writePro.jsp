<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.NoticeBoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="article" scope="page" class="board.NoticeBoardDataBean">
<jsp:setProperty name="article" property="*"/>
</jsp:useBean>

<%
	article.setReg_date(new Timestamp(System.currentTimeMillis()));
	

	NoticeBoardDBBean dbPro=NoticeBoardDBBean.getInstance();
	dbPro.insertArticle(article);

	response.sendRedirect("list.jsp");
%>      
