//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-05-2004
 * 
 * XDoclet definition:
 * @struts:form name="newPhotoQueueForm"
 */
public class NewPhotoQueueForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** action property */
	private String action;

	/** photoId property */
	private int photoId;

	private String title;
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

	}

	/** 
	 * Returns the action.
	 * @return String
	 */
	public String getAction() {
		return action;
	}

	/** 
	 * Set the action.
	 * @param action The action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/** 
	 * Returns the photoId.
	 * @return int
	 */
	public int getPhotoId() {
		return photoId;
	}

	/** 
	 * Set the photoId.
	 * @param photoId The photoId to set
	 */
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

}
