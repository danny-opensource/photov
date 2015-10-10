//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2004
 * 
 * XDoclet definition:
 * @struts:form name="messageForm"
 */
public class MessageForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** messageName property */
	private String messageName;

	/** messageId property */
	private int messageId;

	/** messageText property */
	private String messageText;
	
	private String messageSubject;

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		return new ActionErrors();
	}

	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		messageId = 0;
		messageName = new String();
		messageText = new String();
	}

	/** 
	 * Returns the messageName.
	 * @return String
	 */
	public String getMessageName() {
		return messageName;
	}

	/** 
	 * Set the messageName.
	 * @param messageName The messageName to set
	 */
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	/** 
	 * Returns the messageId.
	 * @return int
	 */
	public int getMessageId() {
		return messageId;
	}

	/** 
	 * Set the messageId.
	 * @param messageId The messageId to set
	 */
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	/** 
	 * Returns the messageText.
	 * @return String
	 */
	public String getMessageText() {
		return messageText;
	}

	/** 
	 * Set the messageText.
	 * @param messageText The messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
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
