<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.ReviewBoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8");%>

<jsp:useBean id="review" scope="page" class="board.ReviewBoardDataBean">
   <jsp:setProperty name="review" property="*"/>
</jsp:useBean>
<%
    String pageNum = request.getParameter("pageNum");
ReviewBoardDBBean dbPro = ReviewBoardDBBean.getInstance();
    int r = dbPro.updateArticle(review);

if(r>0){%> 
<script>          
        alert("수정 완료");
       location.href="list.jsp";
      
     </script>
     <%}%>
