package assignment;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificationMgr {
		
	/**
	 * Sends an email to the student who is first in the waitlist after another student drops the course
	 * @param recipient Student who has just been allocated to a course
	 * @param courseCode Course code of the course the student is registered to
	 * @exception Messaging Exception
	 * @exception UnsupportedEncodingException When program asks for a particular character converter that is unavailable
	 */
	public static void sendEmail(Student recipient, String courseCode) {

		final String sender_email = "cz2002.ss14@gmail.com"; // gmail username 
		final String sender_password = "!cz2002@ss14!"; // gmail password 
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender_email, sender_password);
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender_email, "MySTARS"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient.getEmail())); // can add multiple email addresses
			message.setSubject("Waitlist Notification");
			message.setText("Dear student, \n\nYou have successfully been registered to course "+ courseCode);

			Transport.send(message);

		} catch (MessagingException | UnsupportedEncodingException e) {
			System.out.println("\nSend failed, exception: " + e);		}
	}
}