<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.QnABoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="qna" scope="page" class="board.QnABoardDataBean">
<jsp:setProperty name="qna" property="*"/>
</jsp:useBean>

<%
	qna.setReg_date(new Timestamp(System.currentTimeMillis()));
	qna.setIp(request.getRemoteAddr());

	QnABoardDBBean dbPro=QnABoardDBBean.getInstance();
	dbPro.insertArticle(qna); 

	response.sendRedirect("list.jsp");
%>
