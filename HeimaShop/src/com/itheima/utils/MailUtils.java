package com.itheima.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1. Create a program and mail server session object

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.host", "smtp.126.com");
		props.setProperty("mail.smtp.auth", "true");// Specify validation to be true

		// Create a validator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("hshorse168", "hshorse168");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2. Create a Message, which is equivalent to the message content
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("hshorse168@126.com")); // Set Sender
		// Set the sending method and receiver
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); 

		message.setSubject("用户激活");
		
		//message.setText("This is an activation email, please<a href='#'>click</a>");

		message.setContent(emailMsg, "text/html;charset=utf-8");

		// 3. Create Transport to send mail

		Transport.send(message);
	}
}
