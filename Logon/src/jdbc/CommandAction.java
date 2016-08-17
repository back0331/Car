package jdbc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//요청 파라미터로 명령어를 전달하는 방식의 슈퍼 인터페이스
public interface CommandAction{
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable;
	
	//만약, 세션에 저장하고싶다=> 매개변수는 request- request.getSession으로 세션을 받아올 수 있음 
	
	}
