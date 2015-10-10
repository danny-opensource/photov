//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-07-2004
 * 
 * XDoclet definition:
 * @struts:form name="topicForm"
 */
public class TopicForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** topicId property */
	private int topicId;

	/** topicDate property */
	private String topicDate;

	/** topicName property */
	private String topicName;

	/** topicUrl property */
	private String topicUrl;

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
		this.topicId = 0;
		this.topicName = null;
		this.topicUrl = null;
		this.topicDate = null;
	}

	/** 
	 * Returns the topicId.
	 * @return int
	 */
	public int getTopicId() {
		return topicId;
	}

	/** 
	 * Set the topicId.
	 * @param topicId The topicId to set
	 */
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	/** 
	 * Returns the topicDate.
	 * @return String
	 */
	public String getTopicDate() {
		return topicDate;
	}

	/** 
	 * Set the topicDate.
	 * @param topicDate The topicDate to set
	 */
	public void setTopicDate(String topicDate) {
		this.topicDate = topicDate;
	}

	/** 
	 * Returns the topicName.
	 * @return String
	 */
	public String getTopicName() {
		return topicName;
	}

	/** 
	 * Set the topicName.
	 * @param topicName The topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/** 
	 * Returns the topicUrl.
	 * @return String
	 */
	public String getTopicUrl() {
		return topicUrl;
	}

	/** 
	 * Set the topicUrl.
	 * @param topicUrl The topicUrl to set
	 */
	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
	}

}
