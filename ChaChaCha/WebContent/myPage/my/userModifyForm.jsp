<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.js"></script>
<script>
function checkIt()
{
   inputForm=eval("document.modify_frm");
   if(!inputForm.password.value){
	 alert("비밀번호를 입력하세요.");
     inputForm.password.focus();
	return false;
   }
   
   inputForm=eval("document.modify_frm");
   if(!inputForm.address.value){
	 alert("주소를 입력하세요.");
	return false;
   }
   
   inputForm=eval("document.modify_frm");
   if(!inputForm.phone1.value || !inputForm.phone2.value || !inputForm.phone3.value){
	 alert("전화번호를 입력하세요.");
	return false;
   }
 
   document.modify_frm.submit();
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
            document.getElementById("address2").value="";
            document.getElementById('address2').focus();
        }
    }).open();
}

	function modify(){
		
	}
	
	function out(){
		if(!document.reason_frm.password.value){
			alert("비밀번호를 입력하세요.");
			document.reason_frm.password.focus();
			return false;
		}
		
		if(!document.reason_frm.reason.value){
			alert("탈퇴사유를 선택해주세요.");
			return false;
		}
		
		if(document.reason_frm.reason.value=="기타" && !document.reason_frm.etc.value){
			alert("탈퇴사유를 입력해주세요.");
			return false;
		}
		
		if(confirm("정말 탈퇴하시겠습니까?")){
			document.reason_frm.submit();
		}
	}
	
	function reasonChange(){
		if($(":selected","#reason").text()=="기타"){
			$("#etc").removeAttr("disabled");
		} else {
			$("#etc").val("");
			$("#etc").attr("disabled", "disabled");
		}
	}
</script>
<style>
	@font-face{
		font-family: 'NanumSquareR';
		src:url('../decorators/font/NanumSquareR.ttf');
	}
	@font-face{
		font-family: 'NanumSquareB';
		src:url('../decorators/font/NanumSquareB.ttf');
	}
	section {margin-top:15px;margin-bottom:30px;height:1250px;}
	body {font-family: 'NanumSquareR' !important;}
	h2 {padding-left:40px;padding-top:20px;}
	h2#out {padding-top: 60px;}
	div#modify table th ,div#out table th {background-color: #F9F9F9; height:50px;border-bottom: thin solid #DFDEDE;}
	div#modify table th#addr {height:100px;}
	div#out table th#e {height:150px;}
	div#modify, div#out {width:90%; margin:auto;margin-top: 20px;border-top: 2px solid #7F7A7A;}
	div#modify table td, div#out table td {padding-left:30px; border-bottom: thin solid #DFDEDE;}
	div#info {width:723px;margin:auto;text-align: center; border:1px solid #dfdede; background-color: #F9F9F9; padding:20px; font-size: 15px;}
	div.btn {margin-top:50px;}
	div.btn a {border:1px solid gray;  border-radius:5px; color:gray;
		text-decoration: none; padding:10px 24px;}
</style>
</head>
<body>
<h2>기본정보</h2>
<div id="modify">
<form method="post" name="modify_frm" id="modify_frm" action="userModifyPro.do">
	
	<input type="hidden" value="${member.id}" name="id"/>
	<table width="100%"  cellspacing="0" cellpadding="0">
			
			<tr>
				<th width="180px;">아이디</th>
				<td>${member.id}</td>
			</tr>
			
			<tr>
				<th width="130">이름</th>
				<td width="350">${member.name}</td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" size="30"
					maxlength="30" placeholder="Password" required></td>
			</tr>

		 	<tr>
				<th>이메일</th>
				<td>${member.email}</td>
			</tr>

			<tr>
				<th>전화번호</th>
				<td><select name="phone1">
						<option value="010" selected>010</option>
				</select> <label>-</label> <input type="text" name="phone2" size="3"
					maxlength="4" value="${phone2}"> <label>-</label> <input type="text"
					name="phone3" size="3" maxlength="4" value="${phone3}" required></td>
			</tr>

			
				<tr>
					<th id="addr">주소</th>
					<td ><input type="text" id="postcode" name="zipcode" placeholder="우편번호" value="${member.zipcode}" readonly>
						<input class="btn" type="button" onclick="DaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" id="address" size="55" name="address1" placeholder="주소" value="${member.address1}" ><br>
						<input type="text" id="address2" size="55" name="address2" placeholder="상세주소" value="${member.address2}">
					</td>
											
				</tr>				
				
				<tr>
					<th>생년월일</th>
					<td>${member.birth}</td>
											
				</tr>
				
		</table>
		<div class="btn" align="center">
		<a href="#" onclick="checkIt();return false;">수정하기</a>
		</div>
	</form>
</div>
<h2 id="out">회원탈퇴</h2>
<div id="info" align="center">
	현재 차량을 렌트중이거나 예약중이면 탈퇴가 불가능합니다.<br>
	또한, 현재 등록되어있는 내 차가 있으면 탈퇴하실 수 없습니다.
</div>
<div id="out">
	<form method="post" id="reason_frm" name="reason_frm" action="userDeletePro.do" onsubmit="return check();">
	<table width="100%" cellpadding="0" cellspacing="0" > 
		<tr>
			<th width="180px;">비밀번호</th>
			<td><input type="password" name="password" id="password"/></td>
		</tr>
		<tr>
			<th>탈퇴사유</th>
			<td>
				<select name="reason" id="reason" onchange="reasonChange()">
					<option value="">선택해주세요.</option>
					<option value="서비스불만">서비스 불만</option>
					<option value="기타">기타</option>
				</select>
			</td>
		</tr>
		<tr>
			<th id="e">기타</th>
			<td>
				<textarea name="etc" id="etc" rows="5" cols="70" disabled="disabled"></textarea>
			</td>
		</tr>
	</table>
	</form>
</div>
<div class="btn" align="center"><a href="#" onclick="out();return false;">탈퇴하기</a></div>
</body>
</html>