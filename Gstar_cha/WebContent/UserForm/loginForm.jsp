<%@ page  contentType="text/html; charset=utf-8"%>

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

</script>

</head>
<BODY onload="begin()"">
<form name="myform" action="loginPro.jsp" method="post" onSubmit="return checkIt()">
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
      <INPUT type=reset value="다시입력">
      <input type="button" value="회원가입" onclick="javascript:location='/Gstar_cha/UserForm/CreateUserForm.do'"></TD></TR>
</TABLE>
</form>

</BODY>
</HTML>