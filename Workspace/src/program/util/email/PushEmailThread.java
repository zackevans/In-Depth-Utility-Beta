package program.util.email;

/**
 * Class: PushEmailThread
 * @author ZackEvans
 *
 * This class adds function to a thread.
 */

public class PushEmailThread implements Runnable
{
	String[] to;
	String subject;
	String body;
	
	/**
	 * Constructor: PushEmail(String[] to, String subject, String body)
	 * @param to
	 * @param subject
	 * @param body
	 * 
	 * This constructor takes in an array of emails, subject, and a body of an email.
	 */
	
	public PushEmailThread(String[] to, String subject, String body) 
	{
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	/**
	 * Function: run()
	 * 
	 * This function overrides the defult runnable function to send an email.
	 */
	
	@Override
	public void run() 
	{
		EmailUtil.sendNoteEMail(to, subject, body); // send email.
	}
}
