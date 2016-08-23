<%@page import="bean.MyCarRegisterDataBean"%>
<%@page import="bean.MyCarRegisterDBBean"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = (String)request.getSession().getAttribute("userId"); 
	//String id = "test";
	String mycar_name = request.getParameter("mycar_name");
	String result = "";
	
	MyCarRegisterDBBean myCarDb = MyCarRegisterDBBean.getInstance();
	int check = myCarDb.checkMycarByName(id, mycar_name);
	if(check==0){
		result="사용가능한 이름입니다.";
	}
	if(check==1){
		result="이미 존재하는 이름입니다. 다시 입력해주세요.";
	}
	
	out.print(result);
%>
