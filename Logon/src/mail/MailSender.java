package mail;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
public class MailSender {
	private String fromEmail = "yeyez3zang@gmail.com"; 
	private String password = "rotkdsus1"; 

	public void sendStr(String email,int auth){		
		System.out.println(email);
		System.out.println(auth);
		String toEmail = email;
		String toName = "관리자";
		String subject = "CHACHACHA 메일인증코드입니다.";
		String content = "CHACHACH 회원가입 인증번호는 ["+auth+"] 입니다.";	
		sendMail(toEmail, toName, subject, content);	
	}
	public void sendMail(String toEmail, String toName, String subject, String content){
		try{
		Properties props = new Properties();
		props.put("mail.smtp.starttls", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465"); 
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
        props.put("mail.smtp.socketFactory.fallback", "false"); 
        props.setProperty("mail.smtp.quitwait", "false"); 
        MyAuth auth = new MyAuth(fromEmail, password);
        Session sess = Session.getDefaultInstance(props, auth);
        MimeMessage msg = new MimeMessage(sess);
        msg.setHeader("content-type", "text/plain;charset=utf-8");
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName, "utf-8"));      
        msg.setSubject(subject);
        msg.setContent(content, "text/html;charset=utf-8");
        msg.setSentDate(new java.util.Date());
        Transport.send(msg);
    } catch (UnsupportedEncodingException e) {
       
        e.printStackTrace();
    } catch (MessagingException e) {
        e.printStackTrace();
    }
}
}

