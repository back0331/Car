<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.NoticeBoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8");%>

<jsp:useBean id="article" scope="page" class="board.NoticeBoardDataBean">
   <jsp:setProperty name="article" property="*"/>
</jsp:useBean>
<%
    String pageNum = request.getParameter("pageNum");
NoticeBoardDBBean dbPro = NoticeBoardDBBean.getInstance();
    int r = dbPro.updateArticle(article);

if(r>0){%> 
<script>          
        alert("수정 완료");
       location.href="list.jsp";
      
     </script>
     <%}%>