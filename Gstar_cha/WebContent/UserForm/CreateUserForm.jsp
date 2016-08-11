<%@ page contentType="text/html; charset=utf-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" media="all" />

<style>
	
	
</style>
<script>

</script>
</head>
<body>


	<form action="/Gstar_cha/UserForm/create.do" method="post">
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
					placeholder="ID" required> <input type="button" name="id"
					value="ID중복확인" OnClick="javascript:location='/Gstar_cha/UserForm/idCheck.do'"></td>
			</tr>

			<tr>
				<td><label class="red">*</label>비밀번호</td>
				<td><input type="password" name="password" size="15"
					maxlength="12" placeholder="Password" required></td>
			</tr>

		 	<tr>
				<td><label class="red">*</label>이메일</td>
				<td><input type="text" name="email" size="15"
					placeholder="E-mail"> <label>@</label> 
					
					<select name=email1>
						<option selected>선택</option>
						<option value="@googole.com">google.com</option>
						<option value="@daum.net">daum.net</option>
				</select></td>
			</tr>

			<tr>
				<td><label class="red">*</label>전화번호</td>
				<td><select name=phone>
						<option selected>선택</option>
						<option value="010">010</option>
						<option value="016">016</option>
				</select> <label>-</label> <input type="text" name="phone2" size="3"
					maxlength="4"> <label>-</label> <input type="text"
					name="phone3" size="3" maxlength="4"></td>
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
				<td colspan="2" align="center"><input type="submit"
					name="confirm" value="등록"> <input type="button" value="취소"
					onclick="javascript:window.location='main.jsp'"></td>
			</tr>
		</table>
	</form>
</body>
</html>
