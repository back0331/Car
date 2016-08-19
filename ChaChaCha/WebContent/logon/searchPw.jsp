<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>PW찾기</title>
<link  rel="stylesheet" type="text/css">
</head>
<c:if test= "${check==1}">
<script>
alert("임시비밀번호가 발송 되었습니다.");
location.href="/ChaChaCha/logon/loginForm.jsp";
</script>
</c:if>
<c:if test="${check==-1}">
<script>
alert("입력하신 정보를 확인 해 주세요!!");
</script>
</c:if>

 <script>

function check(){
	if(!document.search_frm.id.value){
		alert("아이디를 입력해주세요.");
		document.search_frm.id.focus();
		return false;
	} 
	
	if(!document.search_frm.email.value){
		alert("이메일을 입력해주세요.");
		document.search_frm.email.focus();
		return false;
	} 
}
</script> 
<BODY>
<form name="search_frm" id="search_frm" enctype="multipart/form-data" onSubmit="return check()" >
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center" >
 
  <TR height="30">
    <TD colspan="2" align="middle"><STRONG>PW찾기</STRONG></TD></TR>
 
  <TR height="30">
    <TD width="110"  align=center>아이디</TD>
    <TD width="150"  align=center>
       <INPUT type="text" name="id" size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD width="110"  align=center>E-mail</TD>
    <TD width="150"  align=center>
      <INPUT type="text" name="email"  size="20" maxlength="20"></TD></TR>
      <tr height="30">
      <td colspan="2" align="middle">
      <input type="submit"  value="확인">
      <input type="reset" value="다시입력"></TD></TR>
     
 </TABLE>
</form>

</BODY>
</HTML>