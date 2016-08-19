<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../view/color.jspf"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<script>
function delete_content(no){
	if(confirm("정말로 삭제하시겠습니까??")){
		location.href="/clientCenter/qna_board/deletePro.do?article_no="+no;
	}
}


</script>
<body bgcolor="${bodyback_c}"> 
<br>
<center>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" 
  bgcolor="${bodyback_c}" align="center"> 
   <tr height="30">
 	 <td align="center" width="125" bgcolor="${value_c}">글 번호</td>
    <td align="center" width="125" align="center">${qna.article_no}</td>
  <td align="center" width="125" bgcolor="${value_c}">문의 유형</td>
  <td alingn="center" width="125" align="center">
  ${qna.article_type }</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c}">작성자</td>
    <td align="center" width="125" align="center">${qna.id }</td>
    <td align="center" width="125" bgcolor="${value_c}" >작성일</td>
    <td align="center" width="125" align="center">${qna.reg_date }</td>
  </tr>
  <tr height="30">
    <td align="center" width="125" bgcolor="${value_c}">글제목</td>
    <td align="center" width="375" align="center" colspan="3">${qna.article_subject }</td>
  </tr>
  <tr>
     <td align="center" width="125" bgcolor="${value_c}">글내용</td> 
     <td align="left" width="375" colspan="3"><pre>${qna.article_content }</pre></td>
  </tr>
 
  <br>
  <tr height="30">     
    <td colspan="4" bgcolor="${value_c}" align="center" >
  <input type="button" value="글수정" onclick="javascript:location='/clientCenter/qna_board/updateForm.do?article_no=${qna.article_no}&pageNum=${pageNum}'">&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="글삭제" onclick="delete_content('${qna.article_no}')">
   &nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" value="글목록"onclick="location.href='/clientCenter/qna_board/list.do?pageNum=${pageNum}'">
    </td>
  </tr>
</form>
<c:if test="${user_id=='admin'}">
	<form method=post action=/clientCenter/qna_board/contentPro.do >  
					<tr bgcolor="${value_c}" align=center>
						<td>코멘트 작성</td>
						<td colspan=2>
							<textarea name=comment_content rows="6" cols="40"></textarea>
							<input type=hidden name=article_no value=${qna.article_no}>
							<input type=hidden name=p_num value=${pageNum}>
							<input type=hidden name=comment_no value=${count+1}>
						</td>
										
											<td align=center>
						<input type=submit value=코멘트달기>
						</td>
						</tr>
					</form> 
				</table>
	</c:if>
	<c:if test="${count>0 }">	
					<p>
				<table width=500 border=0 cellspacing=0 cellpadding=0 bgcolor=${bodyback_c} align=center>
				
				<br/>
				<br/>
				<tr>
						<td>코멘트 수: ${comments.size()}
						<hr/>	 </td></tr>
					
			 <c:forEach var="dbc" items="${comments}">
				<tr>
					<td align=left size=250 bgcolor=${ value_c }>
							&nbsp;<b>관리자</b> (${dbc.reg_date})
							</td>
					<c:if test= "${user_id=='admin' }">
							<td align=right size=250 bgcolor=${ value_c }>
							<a href="/clientCenter/qna_board/delCommentPro.do?article_no=${dbc.article_no }
							&comment_no=${dbc.comment_no}&pageNum=${ pageNum }" >[삭제]</a>&nbsp;
							</td>  
						</c:if>
						</tr>	   
						<tr>
							<td colspan=2>${dbc.comment_content }<td>
					</c:forEach>
						</tr>
				</table>
			</c:if>  
		
		   
			</center>
			</body>
</html>

