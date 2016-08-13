 <%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "Rent_Login.UserList_DAO" %>

<% request.setCharacterEncoding("utf-8");%>

<%
    String id = request.getParameter("id");
String password  = request.getParameter("password");

UserList_DAO manager = UserList_DAO.getInstance();
    int check= manager.userCheck(id,password);

if(check==1){
session.setAttribute("id",id);
response.sendRedirect("loginsuccess.jsp");
}else if(check==0){%>
<script>
  alert("비밀번호가 맞지 않습니다.");
      history.go(-1);
</script>
<% }else{ %>
<script>
  alert("아이디가 맞지 않습니다..");
  history.go(-1);
</script>
<%} %> 



