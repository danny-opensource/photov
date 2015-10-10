/*
 * Created on Aug 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Message {
	private int messageId = 0;
	private String messageName = new String();
	private String messageText = new String();
	private String messageSubject = new String();
	public Message(){
	}
	
	public Message(int i, String name, String subject, String text){
		this.messageId = i;
		this.messageName = name;
		this.messageSubject = subject;
		this.messageText = text;
		
	}
	/**
	 * @return
	 */
	public int getMessageId() {
		return messageId;
	}

	/**
	 * @return
	 */
	public String getMessageName() {
		return messageName;
	}

	/**
	 * @return
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param i
	 */
	public void setMessageId(int i) {
		messageId = i;
	}

	/**
	 * @param string
	 */
	public void setMessageName(String string) {
		messageName = string;
	}

	/**
	 * @param string
	 */
	public void setMessageText(String string) {
		messageText = string;
	}

	/**
	 * @return
	 */
	public String getMessageSubject() {
		return messageSubject;
	}

	/**
	 * @param string
	 */
	public void setMessageSubject(String string) {
		messageSubject = string;
	}

}
