<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.NoticeBoardDBBean" %>
<%@ page import = "board.NoticeBoardDataBean" %>
<%@ page import = "board.QnACommentDBBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jsp"%>
<% 
//선언부
    String id="admin";
	String pwd="1234";
	HttpSession session2 = request.getSession();
	session2.setAttribute("id", id);
	session2.setAttribute("pwd", pwd);
	String user_id = (String)session2.getAttribute("id");
	int pageSize=10;//하나의 페이지에서 보여줄 행의 수
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<%
//pageNum:현재 요청한 페이지 수
	String pageNum=request.getParameter("pageNum");
	String search=request.getParameter("search");
	
	int searchn=0;
	
	if(pageNum==null){
		pageNum="1";//string 타입으로 문자열 1
	}

	//pagenum이 parameter값으로 넘어오지 않았다면, 맨 첫번째 페이지로.
	if(search==null){
		search="";
	}else{
		searchn=Integer.parseInt(request.getParameter("searchn"));
	}
	int currentPage=Integer.parseInt(pageNum);//현재 페이지번호 저장

	int startRow=(currentPage*pageSize)-(pageSize-1);
	int endRow=currentPage*pageSize;
	int count=0;//전체 글의 갯수
	int number=0;//번호 부분 출력하기 위해 만든 number
	
	List articleList =null;
	NoticeBoardDBBean dbPro=NoticeBoardDBBean.getInstance();
	count=dbPro.getArticleCount();//전체 글 수
	
	if(search.equals("")||search==null)
		count=dbPro.getArticleCount();
	else{
		count=dbPro.getArticleCount(searchn,search);
	}
	 QnACommentDBBean cdb=QnACommentDBBean.getInstance();
	if(count>0){//글이 있다면
		if(search.equals("")||search==null)
			articleList=dbPro.getArticles(startRow,endRow);
		else
			articleList=dbPro.getArticles(startRow, endRow, searchn, search);
		
	}
	number=count-(currentPage-1)*pageSize;
		//전체글 갯수-(현재페이지-1)*10;
%>
<html>
<head>
<title>공지사항</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<h1>공지사항</h1>
<hr/>
<h5>차차차 공지사항을 알려드립니다</h5>

<center>
<br/>

<table width="800">
<%
if(user_id=="admin"){%>
	

	<td align="right" bgcolor="<%=value_c %>">
	<a href="writeForm.jsp"> 글쓰기</a></td>
</table>
<% }%>
</table>
<%
if(count==0){//가지고 온 글이 없다면
%>
<table width="800" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td align="center">
	게시판에 저장된 글이 없습니다.
	</td>
</table>
<%}else{ //가지고 온 글 이 있다면 %>
<table border="1" width="800" cellpadding="0" cellspacing="0" align="center">
<tr height="30" bgcolor="<%=value_c %>">
	  <td align="center"  width="50"  >번 호</td>
      <td align="center"  width="250" >제   목</td>
      <td align="center"  width="150" >작성일</td>
      
    
    </tr>
    <% 
    System.out.println("articleList:::::::"+articleList);
    for(int i=0;i<articleList.size();i++){
		NoticeBoardDataBean article=(NoticeBoardDataBean)articleList.get(i);
		int comment_no=cdb.getCommentCount(article.getArticle_no());
		%>
	<tr height="30">
	<td align="center" width="50"><%=number-- %></td>
	<td width="250">
     <a href="content.jsp?article_no=<%=article.getArticle_no()%>&pageNum=<%=currentPage%>">
           <%=article.getArticle_subject()%></a>
          
       
    <td align="center"  width="150"><%= sdf.format(article.getReg_date())%></td>
    
  </tr>
     <%}%>
</table>
<%}%>
<br>
<%
    if (count > 0) {//글이 있다면=>paging
    //전체 페이지의 수를 연산
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
											//나머지가 없다면 0을 더하고, 있다면 1을 더함
        int startPage = (int)(currentPage/5)*5+1;
		int pageBlock=5;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) endPage = pageCount;
       
        if (startPage > 5) {    %>
        <a href="list.jsp?pageNum=<%= startPage - 5 %>">[이전]</a>
<%      }
        for (int i = startPage ; i <= endPage ; i++) {  %>
        <a href="list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
        }
        if (endPage < pageCount) {  %>
        <a href="list.jsp?pageNum=<%= startPage + 5 %>">[다음]</a>
<%
        }
    }
%>
<br></br>

<form>
<select name="searchn">

<option value="0">제목</option>
<option value="1">내용</option>
</select>

<input type="text" name="search" size="30">
<input type="submit" value="검색">

</form>
</center>
</body>
</html>