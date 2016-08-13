<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="color.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>
<%-- <%
	HttpSession session2 = request.getSession();
	String user_id = (String)session2.getAttribute("id");
	int article_no=0;
  try{ 
    if(request.getParameter("article_no")!=null){
     article_no=Integer.parseInt(request.getParameter("article_no"));
		
}
%> --%>
<script type="text/javascript">
	function getInfo(){
		location.href="/Board/review_board/WritePro.do";
	}
</script>
<body bgcolor="<%=bodyback_c%>"> 
<h1>이용후기</h1>
<hr/>
<br>
<form method="post" name="writeform" action="writePro.jsp" onsubmit="return writeSave()">
<input type="hidden" name="id" value="${user_id }"> 
<input type="hidden" name="article_no" value="${article_no }">


<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="<%=bodyback_c%>"
   align="center">
 

  <tr>
    <td  width="100"  bgcolor="<%=value_c%>" align="center" >제 목</td>
    <td  width="400">
       <input type="text" size="40" maxlength="50" name="article_subject"></td>


  <tr>
    <td  width="100"  bgcolor="<%=value_c%>" align="center" >내 용</td>
    <td  width="400" >
     <textarea name="article_content" rows="15" cols="50"></textarea> </td>
  </tr>
  <tr>
    <td  width="100"  bgcolor="<%=value_c%>" align="center" >비밀 번호</td>
    <td  width="400" >
     <input type="password" size="8" maxlength="12" name="password">
</td>
  </tr>  
<tr>     
<td colspan=2 bgcolor="<%=value_c%>" align="center">
  <input type="submit" value="등록" > 
  <input type="reset" value="다시작성">
  <input type="button" value="취소" OnClick="window.location='list.jsp'">
</td></tr></table>   
<%-- <%
  }catch(Exception e){}
%>  --%>   
</form>     
</body>
</html>     
