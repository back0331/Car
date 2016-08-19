<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>로그인</title>
<link  rel="stylesheet" type="text/css">

<script>
function checkIt()
{
   inputForm=eval("document.inform");
   if(!inputForm.id.value){
     alert("아이디를 입력하세요..");
inputForm.id.focus();
return false;
   }
   if(!inputForm.passwd.value){
     alert("패스워드를 입력하세요..");
inputForm.passwd.focus();
return false;
   }
}

function logon(){
	//window.close();
	location.replace("/ChaChaCha/logon/createUserForm.jsp");
}

</script>

</head>
<BODY>
<form name="myform" action="/ChaChaCha/logon/loginPro.do" method="post" onSubmit="return checkIt()">
<TABLE cellSpacing=1 cellPadding=1 width="260" border=1 align="center" >
 
  <TR height="30">
    <TD colspan="2" align="middle"><STRONG>회원로그인</STRONG></TD></TR>
 
  <TR height="30">
    <TD width="110"  align=center>아이디</TD>
    <TD width="150"  align=center>
       <INPUT type="text" name="id" size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD width="110"  align=center>비밀번호</TD>
    <TD width="150"  align=center>
      <INPUT type=password name="password"  size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD colspan="2" align="middle"  >
      <INPUT type=submit value="로그인">
      <INPUT type="button" value="id/pw찾기" onclick="javascript:location='/ChaChaCha/logon/searchPw.do'">
      <input type="button" value="회원가입" onclick="logon()"></TD></TR>
</TABLE>
</form>

</BODY>
</HTML>