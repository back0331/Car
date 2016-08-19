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
	
	/*location.href="/ChaChaCha/logon/loginForm.jsp";*/
	
	open(
			'/ChaChaCha/logon/login.do',
			'login',
			'toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200');
}

function logout(){
	location.href="/ChaChaCha/logon/logout.do";
}


