package mail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyCode  extends Authenticator{
	private String fromEmail;
	private String password;
	
	public MyCode(String fromEmail, String password){
		this.fromEmail = fromEmail;
		this.password = password;
	}
	// 여기에 데이터를 담아서 구글 쪽으로 가야 한다.
	@Override
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(fromEmail,password);
	}
	
}
