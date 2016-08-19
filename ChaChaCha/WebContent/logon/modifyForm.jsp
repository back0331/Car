<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import ="bean.*" %>


<html>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script>


				
			function checkIt()
			{
				
				inputForm=eval("document.mail_frm");
				   if(!inputForm.name.value){
					 alert("이름을 입력하세요.");
				     inputForm.name.focus();
					return false;
				   }
				
			   inputForm=eval("document.mail_frm");
			   if(!inputForm.id.value){
				 alert("아이디를 입력하세요.");
			     inputForm.id.focus();
				return false;
			   }
			   
			   inputForm=eval("document.mail_frm");
			   if(!inputForm.password.value){
				 alert("비밀번호를 입력하세요.");
			     inputForm.password.focus();
				return false;
			   }
			   

			   inputForm=eval("document.mail_frm");
			   if(!inputForm.phone.value || !inputForm.phone2.value || !inputForm.phone3.value){
				 alert("전화번호를 입력하세요.");
				return false;
			   }
			   
			   inputForm=eval("document.mail_frm");
			   if(!inputForm.email.value){
				 alert("이메일을 입력하세요.");
				 inputForm.email.focus();
				return false;
			   }
			   
			   inputForm=eval("document.mail_frm");
			   if(!inputForm.address.value){
				 alert("주소를 입력하세요.");
				return false;
			   }
			   
			   inputForm=eval("document.mail_frm");
			   if(!inputForm.year.value || !inputForm.month.value || !inputForm.day.value){
				 alert("생년월일을 입력하세요.");
				return false;
			   }
			   
			}
			
			var xhr;
			function send(){
				inputForm=eval("document.mail_frm");
				if(!inputForm.email.value){
					inputForm.email.focus();
					alert("email을 입력해주세요.");
					return false;
				}
				
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
				//alert("load");
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
				if(frm.radio.checked==false){
					alert("가입 인증을 확인해주세요.");
					return false;
				}
			}

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
			
			<% 
			
	
			String id=(String)session.getAttribute("id"); 
			UserListDBBean manager = UserListDBBean.getInstance();
			UserListDataBean c = manager.getMember(id);
			
			%>
			
			
			</head>


			
<body>
<form method="post" name="mail_frm"  id="mail_frm"  onSubmit="return ok()" action="">
	
		<table border="5">

	<h1 id="row">회원정보수정</h1>
	
			<tr>
				<td width="130"><label class="red">*</label>이름</td>
				<td width="350"><input type="text" name="name" size="15"
					value="<%=c.getName()%>"></td>
			</tr>

			<%-- <tr>
				<td><label class="red">*</label>아이디</td>
				<td><input type="text" name="id" size="15" maxlength="15"
					> </td>
			</tr>
			

			<tr>
				<td><label class="red">*</label>비밀번호</td>
				<td><input type="password" name="password" size="15"
					maxlength="12" placeholder="Password" required></td>
			</tr>

		 	<tr>
				<td><label class="red">*</label>이메일</td>
				<td><input type="text" name="email" size="15"
					placeholder="E-mail" required> 
					
				<input id = "mail_btn" type="button" onClick="return send()" value="인증번호 받기"/>
				<div id="mail_cont"></div>
				<input type="radio" name="radio" style="display: none;">
				</td>
			</tr>

			<tr>
				<td><label class="red">*</label>전화번호</td>
				<td><select name=phone>
						<option selected value="">선택</option>
						<option value="010">010</option>
						<option value="016">016</option>
				</select> <label>-</label> <input type="text" name="phone2" size="3"
					maxlength="4"> <label>-</label> <input type="text"
					name="phone3" size="3" maxlength="4" required></td>
			</tr>

			
				<tr>
					<td rowspan="2"><label class="red">*</label>주소</td>
					<td><input type="text" id="postcode" name="zipcode" placeholder="우편번호" readonly>
						<input type="button" onclick="DaumPostcode()" value="우편번호 찾기">
					</td>
											
				</tr>				
				<tr>
					<td><input type="text" id="address" name="address" placeholder="주소" readonly> 
						<input type="text" id="address2" name="address2" placeholder="상세주소"></td>
				</tr>
				
				<tr>
					<td><label class="red">*</label>생년월일</td>
					<td>
						<select id="year" name="year">
							<option value="">선택</option>
							<c:forEach var="i" begin="1910" end="2016" step="1">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>년
						<select id="month" name="month"> 
							<option value="">선택</option>
							<c:forEach var="i" begin="1" end="12" step="1">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>월
						<select id="day" name="day">
							<option value="">선택</option>
							<c:forEach var="i" begin="1" end="31" step="1">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>일
					</td>
											
				</tr>
				
			<tr>
				<td colspan="2" align="center"> --%>
				
				<input type="submit" value="등록" onclick=""/>
				<input type="button" value="취소"	 onclick="javascript:window.location='/ChaChaCha/main/main.do'"></td>
			</tr>
		</table>
	</form>
</body>  
</html>
