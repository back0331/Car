<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="board.QnACommentDBBean" %>
<%@ page import="java.sql.Timestamp" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="cmt" scope="page" class="board.QnACommentDataBean">
	<jsp:setProperty name="cmt" property="*"/>
</jsp:useBean>
<%
    QnACommentDBBean comt=QnACommentDBBean.getInstance();
	cmt.setReg_date(new Timestamp(System.currentTimeMillis()));

	
	comt.insertComment(cmt);
	
	String article_no=request.getParameter("article_no");
	String p_num=request.getParameter("p_num");
	
	String url="content.jsp?article_no="+article_no+"&pageNum="+p_num;
	response.sendRedirect(url);
%>

