<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.QnABoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("UTF-8");%>

<jsp:useBean id="qna" scope="page" class="board.QnABoardDataBean">
   <jsp:setProperty name="qna" property="*"/>
</jsp:useBean>
<%
    String pageNum = request.getParameter("pageNum");
	QnABoardDBBean dbPro = QnABoardDBBean.getInstance();
    int r = dbPro.updateArticle(qna);

if(r>0){%> 
<script>          
        alert("수정 완료");
       location.href="list.jsp";
      
     </script>
     <%}%>
