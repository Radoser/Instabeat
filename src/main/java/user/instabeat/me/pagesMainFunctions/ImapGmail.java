package user.instabeat.me.pagesMainFunctions;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class ImapGmail {

	private static String link;
	protected static String gmailUser = PropertiesXml.loadProperty("gmailUser");
	protected static String gmailUserPassword = PropertiesXml.loadProperty("gmailUserPassword");

	public static String GetConfirmationLink() throws Exception {

		Folder inbox;

		/* Set the mail properties */
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		/* Create the session and get the store for read the mail. */
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", gmailUser, gmailUserPassword);
		
		/* Mention the folder name which you want to read. */
		inbox = store.getFolder("Inbox");
//		System.out.println("No of Unread Messages : "
//				+ inbox.getUnreadMessageCount());
		Utils.Log.info("|Number of Unread Messages : " + inbox.getUnreadMessageCount());
		
		/* Open the inbox using store. */
		inbox.open(Folder.READ_ONLY);

		Message msg = inbox.getMessage(inbox.getMessageCount());


			String from = InternetAddress.toString(msg.getFrom());
			if (from != null) {
//				System.out.println("From: " + from);
				Utils.Log.info("|From: " + from);
			}

			String replyTo = InternetAddress.toString(msg.getReplyTo());
			if (replyTo != null) {
//				System.out.println("Reply-to: " + replyTo);
				Utils.Log.info("|Reply-to: " + replyTo);
			}
			String to = InternetAddress.toString(msg
					.getRecipients(Message.RecipientType.TO));
			if (to != null) {
//				System.out.println("To: " + to);
				Utils.Log.info("|To: " + to);
			}

			String subject = msg.getSubject();
			if (subject != null) {
//				System.out.println("Subject: " + subject);
				Utils.Log.info("|Subject: " + subject);
			}
			Date sent = msg.getSentDate();
			if (sent != null) {
//				System.out.println("Sent: " + sent);
				Utils.Log.info("|Sent: " + sent);
			}

		/*	System.out.println();
			System.out.print("LINK : ");*/
			
			
			
			Multipart multipart = (Multipart) msg.getContent();
			String rawText = "";
			for (int x = 0; x < multipart.getCount(); x++) {
				BodyPart bodyPart = multipart.getBodyPart(x);

				rawText += getText(bodyPart);
			
			}
//			 System.out.println(rawText);
			
			/* System.out.println("staging-web-"
					+ rawText.split("staging-web-")[1].split("\"")[0]);*/
			// System.out.println("http://user."+rawText.split("http://user.")[1].split("\"")[0]);

			/*link = "http://user."
					+ rawText.split("http://user.")[1].split("\"")[0];*/
			link = "staging-web-"
					+ rawText.split("staging-web-")[1].split("\"")[0];
//			System.out.println(link);
			Utils.Log.info("|Link is: "+link);
			
					/*
			 * link = "http://ibeat.pub.globallogic.com" +
			 * rawText.split("http://user.instabeat.me")[1].split("\"")[0];
			 * System.out.println(link);
			 */
		
		inbox.close(true);
		store.close();
		
		
		return link;

	}

	private static boolean textIsHtml = true;

	/**
	 * Return the primary text content of the message.
	 */
	private static String getText(Part p) throws MessagingException,
			IOException {
		if (p.isMimeType("text/*")) {
			String s = (String) p.getContent();
			textIsHtml = p.isMimeType("text/html");
			return s;
		}

		if (p.isMimeType("multipart/alternative")) {
			// prefer html text over plain text
			Multipart mp = (Multipart) p.getContent();
			String text = null;
			for (int i = 0; i < mp.getCount(); i++) {
				Part bp = mp.getBodyPart(i);
				if (bp.isMimeType("text/plain")) {
					if (text == null)
						text = getText(bp);
					continue;
				} else if (bp.isMimeType("text/html")) {
					String s = getText(bp);
					if (s != null)
						return s;
				} else {
					return getText(bp);
				}
			}
			return text;
		} else if (p.isMimeType("multipart/*")) {
			Multipart mp = (Multipart) p.getContent();
			for (int i = 0; i < mp.getCount(); i++) {
				String s = getText(mp.getBodyPart(i));
				if (s != null)
					return s;
			}
		}

		return null;
	}
	
}
