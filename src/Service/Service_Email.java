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
		
		// SMTP ���� ���� ����
		String host = "smtp.gmail.com";			// SMTP ���� �ּ� - �ܺ�(gmail) �̸��� ������ ���
		String username = "atree3139";			// �� ����
		String password = "zoalfzrjwypyzyss";	// �� ��й�ȣ
		int port = 587;							// SMTP ���� ��Ʈ ��ȣ - gmail ���� 587
		
		// request ������ �ҷ�����
		String snederMail = dto.getSenderMail();	// ������ ����� ���� �ּ�
		String senderName = dto.getSenderName();	// ������ ����� �̸�
		String recipient = dto.getReceiveMail();	// �޴� ����� ���� �ּ�
		String subject = dto.getSubject();			// ���� ����
		String body = dto.getMessage();				// ���� ����
		
		// ������ ��� ���� Properties ��ü ���
		Properties props = System.getProperties();
		
		// SMTP ���� ���� ����
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		// Session ����
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		
		// ����� Ȱ��ȭ
		session.setDebug(true);
		
		try {
			// session�� �ְ� MimeMessage ��ü ����
			Message mimeMessage = new MimeMessage(session);
			//mimeMessage.setFrom(new InternetAddress(username));
			
			// �̸��� �߽��� ���� ( ���߿��� ������ ������ �ʹٸ�  )
			mimeMessage.addFrom(new InternetAddress[] { new InternetAddress(snederMail, senderName) });
			
			// �̸��� ������ ���� ( ���� .TO | ���� .CC | ���� ���� .BCC )
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			// ���� ����
			mimeMessage.setSubject(subject);
			
			// ���� ����
			mimeMessage.setText(body);
			
			// ���� ����
			Transport.send(mimeMessage);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}