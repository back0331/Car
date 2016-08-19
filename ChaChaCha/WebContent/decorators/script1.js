function selectMenu(){
	document.getElementById(x).style.background = "red";
}

function blurMenu(){
	document.getElementById(x).style.background = "white";
}

function login(){
	//alert("login");
//	url = "/ChaChaCha/logon/loginForm.jsp"; 
//	window.open(url,"post","toolbar=no ,width=500 ,height=300,directories=no,status=yes,scrollbars=yes,menubar=no");
	
	location.href="/ChaChaCha/logon/loginForm.jsp";
}
function logout(){
	location.href="/ChaChaCha/logon/logout.do";
}
function userListPro(){
	location.href="/ChaChaCha/administratorPage/UserList/userListPro.do";
}
