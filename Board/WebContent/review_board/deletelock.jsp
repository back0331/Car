<%@page import="board.ReviewBoardDataBean"%>
<%@page import="board.ReviewBoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ include file="color.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<BODY bgcolor="${bodyback_c }">
<form id ="myform" name="myform" action="/Board/review_board/reviewpasswordcheck.do">
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center" >
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="${title_c }"><STRONG>��� �� �Դϴ� !!</STRONG></TD></TR>
  <TR height="30">
    <TD width="110" bgcolor="${title_c }" align=center>��й�ȣ</TD>
    <TD width="150" bgcolor="${value_c }" align=center>
      <INPUT type=password name="insertedPassword" size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="${title_c }" >
      <INPUT type="submit" value="Ȯ��">
      <INPUT type=reset value="�ٽ��Է�">
      <input type="hidden" name="article_no" value="${article_no }">
      <input type="hidden" name="currentPage" value="${currentPage }">
      <input type="hidden" name="confirming" value="deleteConfirmed">
     <input type="button" value="���" OnClick="window.location='content.jsp'">  
</TABLE>
</form>
</BODY>
</HTML>