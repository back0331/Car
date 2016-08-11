<%@page import="board.ReviewBoardDataBean"%>
<%@page import="board.ReviewBoardDBBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ include file="color.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<script type="text/javascript">
function check_pwd(pwd,no,pageNum){
	var frm = document.getElementById("myform");
	var pwdv = frm.passwd.value;
	if(pwd==pwdv){
		location.href("updateForm.jsp?article_no="+no+"&pageNum="+pageNum);
	}else{
		alert("비밀번호가 맞지않습니다.");
	}
}
</script>
<BODY onload="begin()" bgcolor="<%=bodyback_c%>">

<form id = "myform" name="myform" action="content.jsp" method="post" onSubmit="return checkIt()">
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center" >
 		 <%
  int article_no = Integer.parseInt(request.getParameter("article_no"));
  String pageNum = request.getParameter("pageNum");
  ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
  ReviewBoardDataBean review =  dbPro.checkpwd(article_no);

%> 
	
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="<%=title_c%>"><STRONG>비밀 글 입니다 !!</STRONG></TD></TR>
 
  <TR height="30">
    <TD width="110" bgcolor="<%=title_c%>" align=center>비밀번호</TD>
    <TD width="150" bgcolor="<%=value_c%>" align=center>
      <INPUT type=password name="passwd"  size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD colspan="2" align="middle" bgcolor="<%=title_c%>" >
      <INPUT type="button" value="확인" onclick="check_pwd('<%=review.getPassword()%>','<%=article_no%>','<%=pageNum%>')">
      <INPUT type=reset value="다시입력">
     <input type="button" value="취소" OnClick="window.location='content.jsp'">
     
</TABLE>
</form>

</BODY>
</HTML>