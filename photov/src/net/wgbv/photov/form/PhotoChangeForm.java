//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import javax.servlet.http.HttpServletRequest;

import net.wgbv.photov.objects.Person;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-06-2004
 * 
 * XDoclet definition:
 * @struts:form name="photoHideForm"
 */
public class PhotoChangeForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/* CREATE TABLE audit_queue (
	 *   audit_id int(32) NOT NULL auto_increment,
	 *   user_id int(32) NOT NULL default '0',
	 *   photo_id int(32) NOT NULL default '0',
	 *   completed enum('No','Yes') default 'No',
	 *   reviewed enum('No','Yes') default 'No',
	 *   approved enum('No','Yes') default 'No',
	 *   reason varchar(255) ,
	 *   PRIMARY KEY  (audit_id)
	 * ) TYPE=MyISAM;
	 */

	/** queue ID property - Primary Key */
	private int queueId = 0;
	/** Who asked for this change.  Can be 0 for a non-logged in user */
	private int userId = 0;
	/** Has this change been completed? property */
	private boolean completed = false;
	/** Has this change been approved?  property */
	private boolean approved = false;
	/** Has this change been reviewed?  property */
	private boolean reviewed = false;
	/** reason property */
	private String reason;
	/** Photo ID property */
	private int photoId = 0;
	
	private Person person;
	

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
		queueId = 0;
		userId = 0;
		completed = false;
		approved = false;
		reviewed = false;
		reason = null;
		photoId = 0;
		person = null;
	}

	/**
	 * @return
	 */
	public boolean isApproved() {
		return approved;
	}

	/**
	 * @return
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @return
	 */
	public int getPhotoId() {
		return photoId;
	}

	/**
	 * @return
	 */
	public int getQueueId() {
		return queueId;
	}

	/**
	 * @return
	 */
	public int getAuditId() {
		return queueId;
	}

	/**
	 * @return
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @return
	 */
	public boolean isReviewed() {
		return reviewed;
	}

	/**
	 * @return
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param b
	 */
	public void setApproved(boolean b) {
		approved = b;
	}

	/**
	 * @param b
	 */
	public void setCompleted(boolean b) {
		completed = b;
	}

	/**
	 * @param i
	 */
	public void setPhotoId(int i) {
		photoId = i;
	}

	/**
	 * @param i
	 */
	public void setQueueId(int i) {
		queueId = i;
	}
	/**
	 * @param i
	 */
	public void setAuditId(int i) {
		queueId = i;
	}
	/**
	 * @param string
	 */
	public void setReason(String string) {
		reason = string;
	}

	/**
	 * @param b
	 */
	public void setReviewed(boolean b) {
		reviewed = b;
	}

	/**
	 * @param i
	 */
	public void setUserId(int i) {
		userId = i;
	}

	/**
	 * @return
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

}
