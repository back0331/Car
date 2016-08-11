package paymentAction;
import javax.servlet.http.*;
public interface CommandAction{
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
//요청 파라미터로 명령어를 전달하는 방식의 슈퍼 인터페이스
//세션에 저장하려면 request.getsession(); 메소드를 실행하면 된다.
