<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="color.jspf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" media="all" />

</head>
<script>
			
			function openConfirmid(userinput) {
		        // 아이디를 입력했는지 검사
		        if (userinput.id.value == "") {
		            alert("아이디를 입력하세요");
		            return;
		        }
		        // url과 사용자 입력 id를 조합합니다.
		        url = "confirmId.do?id=" + userinput.id.value ;
		       
		        // 새로운 윈도우를 엽니다.
		        open(url, "confirm", "toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
		    }
			
			var xhr;
			function send(){
				alert("인증번호를 보냈습니다.");
				var auth="";
				for(var i=0;i<5;i++){
					auth += parseInt(Math.random()*(9+1));
				}
				var frm = document.getElementById("mail_frm");
				var email = frm.email.value;
				var fd = new FormData(frm);
				if(xhr==null) xhr = new XMLHttpRequest();
					xhr.onreadystatechange=loadData;
					xhr.open('post','./mail_result.jsp?auth='+auth,true);
					xhr.send(fd);
			}
			function loadData(){
				if(xhr.status==200 && xhr.readyState==4){
		  			var rs = document.getElementById("mail_cont");
					 rs.innerHTML  = xhr.responseText;
				}
			}
			function check(auto){
				var frm = document.getElementById("mail_frm");
				var acc = document.getElementById("acc").value;
				if(auto==acc){
				alert("인증되었습니다.");
				frm.radio.checked=true;
				}else{
				alert("일치하지 않습니다.");
				}
			} 
			function ok(){
				var frm = document.getElementById("mail_frm");
				if(frm.radio.checked==true){
					alert("체크됨");
				}else{
					alert("체크안됨");
				}
			}
			
			
			</script>
		
<body>

<form name="mail_frm"  id="mail_frm" method="post" enctype="multipart/form-data" action="/Logon/logon/createUserPro.do">
	
		<table border="5">

	<h1 id="row">회원가입</h1>
	<h6>※<label class="red">*</label>은 필수입력 항목입니다. 회원가입을 위해 반드시 기입해 주십시오.</h6>




			<tr>
				<td width="130"><label class="red">*</label>이름</td>
				<td width="350"><input type="text" name="name" size="15"
					autofocus placeholder="Name" required></td>
			</tr>

			<tr>
				<td><label class="red">*</label>아이디</td>
				<td><input type="text" name="id" size="15" maxlength="15"
					placeholder="ID" required> <input type="button" name="confirm_id"
					value="ID중복확인" OnClick="openConfirmid(this.form)"></td>
			</tr>
			

			<tr>
				<td><label class="red">*</label>비밀번호</td>
				<td><input type="password" name="password" size="15"
					maxlength="12" placeholder="Password" required></td>
			</tr>

		 	<tr>
				<td><label class="red">*</label>이메일</td>
				<td><input type="text" name="email" size="15"
					placeholder="E-mail" required> <!-- <label>@</label> 
					
					<select name=email1>
						<option selected>선택</option>
						<option value="@naver.com">naver.com</option>
						<option value="@googole.com">google.com</option>
						<option value="@daum.net">daum.net</option>
				</select> -->
				<input id = "mail_btn" type="button" onclick="send()" value="인증번호 받기">
				<div id="mail_cont"></div>
				<input type="radio" name="radio" style="display: none;">
				</td>
			</tr>

			<tr>
				<td><label class="red">*</label>전화번호</td>
				<td><select name=phone>
						<option selected>선택</option>
						<option value="010">010</option>
						<option value="016">016</option>
				</select> <label>-</label> <input type="text" name="phone2" size="3"
					maxlength="4" required> <label>-</label> <input type="text"
					name="phone3" size="3" maxlength="4" required></td>
			</tr>

			
				<tr>
					<td rowspan="2">주소</td>
					<td><input type="text" id="postcode" name="zipcode" placeholder="우편번호">
						<input type="button" onclick="DaumPostcode()" value="우편번호 찾기">
					</td>
											
				</tr>				
				<tr>
					<td><input type="text" id="address" name="address" placeholder="주소"> 
						<input type="text" id="address2" name="address2" placeholder="상세주소"></td>
				</tr>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address').value = fullAddr;

                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('address2').focus();
            }
        }).open();
    }
</script>
				
				
				
			

			<!--<tr>
				<td>이메일수신여부</td>
				<td><input type="checkbox" value="true"> <label
					for="chek">수신동의</label></td>
			</tr> -->


			<tr>
				<td colspan="2" align="center">
				<input type="submit" name="confirm" value="등록"> 
				<input type="button" value="취소"	 onclick="javascript:window.location='/Logon/logon/loginForm.do'"></td>
			</tr>
		</table>
	</form>
</body>  
</html>