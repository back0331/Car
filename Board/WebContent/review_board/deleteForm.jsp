<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>

<%-- <%
  int article_no = Integer.parseInt(request.getParameter("article_no"));
  String pageNum = request.getParameter("pageNum");

%> --%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<!-- <script>     
function deleteSave(){
if(document.delForm.passwd.value==''){
alert("비밀번호를 입력하십시요.");
document.delForm.passwd.focus();
return false;
}
}   
</script> -->
</head>
<body bgcolor="<%=bodyback_c%>">
<b>이용후기</b>
<hr/>
<br>
<center>
<form method="POST" name="deleteForm"  action="deletePro.jsp?currentPage=${currentPage }"
   onsubmit="return deleteSave()">
<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
  <tr height="30">
     <td align=center  bgcolor="<%=value_c%>">
       <b>비밀번호를 입력해 주세요.</b></td>
  </tr>
  <tr height="30">
     <td align=center >비밀번호 :  
       <input type="password" name="password" size="8" maxlength="12">
   <input type="hidden" name="article_no" value="${article_no }">

   </td>
</tr>
<tr height="30">
    <td align=center bgcolor="<%=value_c%>">
      <input type="submit" value="글삭제" >
      <input type="button" value="글목록"
       onclick="document.location.href='list.jsp?currentPage=${currentPage}'">    
   </td>
</tr> 
</table>
</form>
</body>
</html>