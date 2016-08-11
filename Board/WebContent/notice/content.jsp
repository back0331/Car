<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.NoticeBoardDBBean" %>
<%@ page import = "board.NoticeBoardDataBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<script>
function delete_content(no){
	if(confirm("정말로 삭제하시겠습니까??")){
		location.href="deletePro.jsp?article_no="+no;
	}
}

</script>
<%
HttpSession session2 = request.getSession();
String user_id = (String)session2.getAttribute("id");
   int article_no = Integer.parseInt(request.getParameter("article_no"));
   String pageNum = request.getParameter("pageNum");

   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

   try{
	   NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
       NoticeBoardDataBean article = dbPro.getArticle(article_no);
 	
%>
<body bgcolor="<%=bodyback_c%>"> 
<b>공지사항</b>
<hr/>
<br>
<center>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" 
  bgcolor="<%=bodyback_c%>" align="center"> 
   <tr height="30">
 	 <td align="center" width="125" bgcolor="<%=value_c%>">글 번호</td>
    <td align="center" width="125" align="center">
     <%=article.getArticle_no()%></td>
     <td align="center" width="125" bgcolor="<%=value_c%>" >작성일</td>
    <td align="center" width="125" align="center">
     <%= sdf.format(article.getReg_date())%></td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="<%=value_c%>">글제목</td>
    <td align="center" width="375" align="center" colspan="3">
     <%=article.getArticle_subject()%></td>
  </tr>
  <tr>
     <td align="center" width="125" bgcolor="<%=value_c%>">글내용</td> 
    <td align="left" width="375" colspan="3"><pre><%=article.getArticle_content()%></pre></td>
  </tr>
  </table>
  <br>
  <%
if(user_id=="admin"){%>
	
  <tr height="30">     
    <td colspan="4" bgcolor="<%=value_c%>" align="center" >
  <input type="button" value="글수정"
       onclick="document.location.href='updateForm.jsp?article_no=<%=article.getArticle_no()%>&pageNum=<%=pageNum%>'">&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="글삭제"onclick="delete_content('<%=article.getArticle_no()%>')">
   &nbsp;&nbsp;&nbsp;&nbsp;
   
     
       <input type="button" value="글목록"onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
    </td>
  </tr>
  <%}else{ %>
    <input type="button" value="글목록"onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
 <%} %>
</form>
					
			<%
	}catch(Exception e){}
			%>
			</center>
			</body>
</html>