<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "board.ReviewBoardDBBean" %>
<%@ page import = "board.ReviewBoardDataBean" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file="color.jsp"%>
<%
//선언부
	String id="bb";
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
	ReviewBoardDBBean dbPro=ReviewBoardDBBean.getInstance();
	count=dbPro.getArticleCount();//전체 글 수
	
	if(search.equals("")||search==null) 
		count=dbPro.getArticleCount();
	else{
		 
		count=dbPro.getArticleCount(searchn,search);
	}		
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
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
<h1>이용후기</h1>
<hr/>

<center>
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
<table width="800" border="1" cellpadding="0" cellspacing="0">
<tr height="30" bgcolor="<%=value_c %>">
	  <td align="center"  width="50"  >번 호</td>
	  <td align="center"  width="100" >이미지</td>
      <td align="center"  width="300" >제   목</td>
      <td align="center"  width="100" >아이디</td> 
      <td align="center"  width="250" >작성일</td>
</tr>
<%
	for(int i=0;i<articleList.size();i++){
		ReviewBoardDataBean review=(ReviewBoardDataBean)articleList.get(i);
		
%>
<tr height="50">  
	<td align="center" width="50"><%=number-- %></td>
	<td align="center"><img src="img.jpg" width="50" height="50"></img></td>


	<td align="center" width="300">
	 <a href="content.jsp?article_no=<%=review.getArticle_no()%>&pageNum=<%=currentPage%>">
     <%=review.getArticle_subject()%></a></td>
     <td align="center"  width="100"><%=review.getId()%></td> 
     <td align="center"  width="250"><%= sdf.format(review.getReg_date())%>
</td>
</tr>
      <%}%>
</table>
<%}%>

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
<option value="0">아이디</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>

<input type="text" name="search" size="30">
<input type="submit" value="검색">

</form>
</center>
</body>
</html>