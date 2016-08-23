<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="./view/color.jspf"%>
<html>
<head>
<style>
	@font-face{
		font-family: 'NanumSquareR';
		src:url('../decorators/font/NanumSquareR.ttf');
	}
	@font-face{
		font-family: 'NanumSquareB';
		src:url('../decorators/font/NanumSquareB.ttf');
	}
	section {margin-top:15px;margin-bottom:30px;height:800px;}
	body {font-family:'NanumSquareR' !important;}
	div#b {width: 80%; margin :auto; border-top: 2px solid #7F7A7A;}
	div#b table th {background-color: #F9F9F9; height: 40px;border-bottom: thin solid #C9C9C9;}
	div#b table td {padding-left: 10px;border-bottom: thin solid #C9C9C9;}
	div#b textarea {margin:10px 0px;}
	div#btn {margin-top:70px;}
	div#btn a {border:1px solid #00B0F0; background-color:#00B0F0; padding: 10px 15px; border-radius:5px;color:white;text-decoration: none;}
</style>
<script>
	function writeSave(){
		if(!document.writeform.article_subject.value){
			alert("제목을 입력해주세요.");
			document.writeform.article_subject.focus();
			return false;
		}
		if(!document.writeform.article_content.value){
			alert("내용을 입력해주세요.");
			document.writeform.article_content.focus();
			return false;
		}
		if(!document.writeform.password.value){
			alert("비밀번호를 입력해주세요.");
			document.writeform.password.focus();
			return false;
		}
		
		document.writeform.submit();
	}
</script>
</head>
 
     
<body bgcolor="${bodyback_c}"> 
<div id="b">
<form method="post" name="writeform" action="/ChaChaCha/review/writePro.do" >
<input type="hidden" name="id" value="${user_id}"> 
<input type="hidden" name="article_no" value="${article_no}">
<input type="hidden" name="book_no" value="${book_no}"/>


<table width="100%" cellspacing="0" cellpadding="0" text-align="center">
 

  <tr>
    <th  width="140px;">제 목</th>
    <td  >
       <input type="text" size="65" maxlength="65" name="article_subject"></td>


  <tr>
    <th>내 용</th>
    <td>
     <textarea name="article_content" rows="30" cols="70"></textarea> </td>
  </tr>
  <tr>
    <th>비밀번호</th>
    <td  >
     <input type="password" size="40" maxlength="40" name="password">
</td>
  </tr>  
</table>   
<div id="btn" align="center">
	<a href="#" onclick="writeSave();return false;">등록하기</a>
	<a href="#" onclick="javascript:window.location.href='/ChaChaCha/review/list.do';return false;">취소</a> 
</div> 
</form> 
</div>    
</body>
</html>     
