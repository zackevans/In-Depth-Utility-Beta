package program.util.email;

/**
 * Class: PushEmail
 * @author ZackEvans
 *
 * This class is a runnable thread that will send multiple emails in the background of the program.
 */

public class PushEmail implements Runnable 
{
	String[] to;
	String subject;
	String body;
	
	/**
	 * Constructor: PushEmail(String[] to, String subject, String body) 
	 * @author ZackEvans
	 * @param to
	 * @param subject
	 * @param body
	 * 
	 * This constructor takes in an array of emails, subject, and a body of an email.
	 */
	
	public PushEmail(String[] to, String subject, String body) 
	{
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	/**
	 * Function: run()
	 * @author ZackEvans
	 * 
	 * This function overrides the defult runnable function to send an email.
	 */
	
	@Override
	public void run() 
	{
		SendEmail.sendNoteEMail(to, subject, body); // send email.
	}
}
