package mailsending;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class MailFormation {

	public static void main(String[] args) {

	      
	      String to = "PALANIYAPPAN_ARUNACHALAM1@homedepot.com";

	      
	      String from = "PALANIYAPPAN_ARUNACHALAM1@homedepot.com";

	      final String username = "PALANIYAPPAN_ARUNACHALAM1@homedepot.com";
	      final String password = "#palani123";
 
	     
	      String host = "mail4.homedepot.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	         
	         Message message = new MimeMessage(session);

	         
	         message.setFrom(new InternetAddress(from));

	         
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         
	         message.setSubject("QRCode");

	         
	         BodyPart messageBodyPart = new MimeBodyPart();

	         
	         messageBodyPart.setText("Please find the QRCode of the selected product in the attachment");

	        
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "C://AZ-POC//QR_code//MyQRCode8.png";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }

	}

}
