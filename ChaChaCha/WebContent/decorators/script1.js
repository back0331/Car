function selectMenu(){
	document.getElementById(x).style.background = "red";
}

function blurMenu(){
	document.getElementById(x).style.background = "white";
}

function login(){
	//alert("login");
	url = "/ChaChaCha/logon/loginForm.jsp"; 
	//새창의 크기
	cw=440;
	ch=250;
	//스크린의 크기
	sw=screen.availWidth;
	sh=screen.availHeight;
	//열 창의 포지션
	px=(sw-cw)/2;
	py=300;

	window.open(url,"post","width=" + cw + ",height=" + ch + ",toolbar=no,location=no,resizable=no,status=no,left=" + px + ",top=" + py);	
}
function logout(){
	location.href="/ChaChaCha/logon/logout.do";
}
function checkLogin(){
	alert("로그인이 필요합니다.");
	login();
	//window.open("/ChaChaCha/logon/loginForm.jsp",'post','toolbar=no ,width=500 ,height=170,directories=no,status=yes,menubar=no');
}
function logon(){
	document.location.href="/ChaChaCha/logon/createUserForm.jsp";
}