package Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DTO.DTO_Email;

public class Service_Email {

	public void mailSender(DTO_Email dto) throws Exception {
		
		// SMTP 계정 정보 설정
		String host = "smtp.gmail.com";			// SMTP 서버 주소 - 외부(gmail) 이메일 서버를 사용
		String username = "atree3139";			// 내 계정
		String password = "zoalfzrjwypyzyss";	// 내 비밀번호
		int port = 587;							// SMTP 서버 포트 번호 - gmail 기준 587
		
		// request 데이터 불러오기
		String snederMail = dto.getSenderMail();	// 보내는 사람의 메일 주소
		String senderName = dto.getSenderName();	// 보내는 사람의 이름
		String recipient = dto.getReceiveMail();	// 받는 사람의 메일 주소
		String subject = dto.getSubject();			// 메일 제목
		String body = dto.getMessage();				// 메일 내용
		
		// 정보를 담기 위해 Properties 객체 사용
		Properties props = System.getProperties();
		
		// SMTP 서버 정보 설정
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		// Session 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		
		// 디버깅 활성화
		session.setDebug(true);
		
		try {
			// session을 넣고 MimeMessage 객체 생성
			Message mimeMessage = new MimeMessage(session);
			//mimeMessage.setFrom(new InternetAddress(username));
			
			// 이메일 발신자 정보 ( 다중에게 메일을 보내고 싶다면  )
			mimeMessage.addFrom(new InternetAddress[] { new InternetAddress(snederMail, senderName) });
			
			// 이메일 수신자 정보 ( 수신 .TO | 참조 .CC | 숨은 참조 .BCC )
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			// 메일 제목
			mimeMessage.setSubject(subject);
			
			// 메일 내용
			mimeMessage.setText(body);
			
			// 메일 전송
			Transport.send(mimeMessage);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}