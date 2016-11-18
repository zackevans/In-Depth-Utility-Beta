package program.util.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Class: EmailUtil
 * @author ZackEvans
 *
 * This class holds useful tools for Emails
 */

public class EmailUtil 
{
	/**
	 * Function: sendNoteEMail(String[] to, String subject, String body) 
	 * @author ZackEvans
	 * @param to
	 * @param subject
	 * @param body
	 * 
	 * This method sends emails to multiple email addresses
	 */
	
	public static void sendNoteEMail(String[] to, String subject, String body) 
	{
		final String from = "idunoreply@gmail.com"; // 
		final String pass = "swordfish15";
		
		Properties props = System.getProperties();
	    String host = "smtp.gmail.com";
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");

	    Session session = Session.getDefaultInstance(props); // create new email session 
	    MimeMessage message = new MimeMessage(session);

	    try 
	    {
	        message.setFrom(new InternetAddress(from));
	        InternetAddress[] toAddress = new InternetAddress[to.length];

	        // To get the array of addresses
	        for( int i = 0; i < to.length; i++ ) 
	        {
	            toAddress[i] = new InternetAddress(to[i]);
	        }

	        // add recipients to message
	        for( int i = 0; i < toAddress.length; i++) 
	        {
	            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
	        }

	        message.setSubject(subject);
	        message.setText(body);
	        Transport transport = session.getTransport("smtp");
	        transport.connect(host, from, pass);
	        
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    
	    catch (AddressException ae) 
	    {
	        ae.printStackTrace();
	    }
	    catch (MessagingException me) 
	    {
	        me.printStackTrace();
	    }
	}
	
	/**
	 * Function: sendNoteEMail(String to, String subject, String body) 
	 * @author ZackEvans
	 * @param to
	 * @param subject
	 * @param body
	 * 
	 * This function sends an email to an address
	 */
	
	public static void sendNoteEMail(String to, String subject, String body) 
	{
		final String from = "idunoreply@gmail.com"; // 
		final String pass = "swordfish15";
		
		Properties props = System.getProperties();
	    String host = "smtp.gmail.com";
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");

	    Session session = Session.getDefaultInstance(props); // create new email session 
	    MimeMessage message = new MimeMessage(session);

	    try 
	    {
	    	message.setFrom(new InternetAddress(from));
	        InternetAddress toAddress = new InternetAddress(to);
	       
	        message.addRecipient(Message.RecipientType.TO, toAddress);
	        message.setSubject(subject);
	        message.setText(body);
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect(host, from, pass);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    
	    catch (AddressException ae) 
	    {
	        ae.printStackTrace();
	    }
	    catch (MessagingException me) 
	    {
	        me.printStackTrace();
	    }
	}
	
	/**
	 * Function: validateEmailAddress(String email) 
	 * @author ZackEvans
	 * @param email
	 * @return If the email passed into the method it a real email
	 * 
	 * This function returns if a email exists or not
	 */
	
	public static boolean validateEmailAddress(String email) 
	{
		boolean result = true;
		
		try 
		{
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate(); // try to validate email
		} 
		 
		catch (AddressException ex) 
		{
			result = false; // if email fails then set result to false
		}
		 
		return result;
	}	
}