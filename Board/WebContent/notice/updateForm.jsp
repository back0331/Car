<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.NoticeBoardDBBean" %>
<%@ page import = "board.NoticeBoardDataBean" %>
<%@ include file="color.jsp"%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>

<%
  int article_no = Integer.parseInt(request.getParameter("article_no"));
  String pageNum = request.getParameter("pageNum");
  try{
	  NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
      NoticeBoardDataBean article =  dbPro.updateGetArticle(article_no);

%>

<body bgcolor="<%=bodyback_c%>"> 
<b>공지사항</b>
<hr/>
<br>
<center>
<form method="post" name="writeform" action="updatePro.jsp?pageNum=<%=pageNum%>"
  onsubmit="return writeSave()">
<table width="500" border="1" cellspacing="0" cellpadding="0"  bgcolor="<%=bodyback_c%>"
  align="center">
  <tr>
    <td  width="150"  bgcolor="<%=value_c%>" align="center" >제 목</td>
    <td align="left" width="350">
       <input type="text" size="40" maxlength="50" name="article_subject" value="<%=article.getArticle_subject()%>"></td>
  </tr>
  <tr>
    <td  width="150"  bgcolor="<%=value_c%>" align="center" >내 용</td>
    <td align="left" width="350">
     <textarea rows="13" cols="50" name="article_content" ><%=article.getArticle_content()%></textarea></td>
  </tr>
    </table>
</td>
  </tr>
  <br/>
  <tr>     
   <td colspan=2 bgcolor="<%=value_c%>" align="center">
   <input type="hidden" name="article_no" value=<%=article_no %>>
     <input type="submit" value="글수정" > 
     <input type="reset" value="다시작성">
     <input type="button" value="취소"
       onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
   </td>
</tr>

</form>
<%
}catch(Exception e){}%>     
     
</body>
</html>     
