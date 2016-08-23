package mail;
import java.io.UnsupportedEncodingException;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
public class MailSender2 {
	private String fromEmail = "withchachacha@gmail.com"; 
	private String password = "cha1234cha"; 

	public void sendStr(String email,String code){		
		System.out.println(email);
		System.out.println(code);
		String toEmail = email;
		String toName = "관리자";
		String subject = "CHACHACHA 임시비밀번호입니다.";
		String content = "고객님의 임시비밀번호는 ["+code+"] 입니다.";	
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
        MyCode code=new MyCode(fromEmail,password);
        Session sess=Session.getDefaultInstance(props,code);
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

