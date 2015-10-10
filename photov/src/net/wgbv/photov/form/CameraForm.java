//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-21-2004
 * 
 * XDoclet definition:
 * @struts:form name="cameraForm"
 */
public class CameraForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** cameraName property */
	private String cameraName;

	/** cameraId property */
	private int cameraId;

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

return new ActionErrors();	}

	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		cameraName = null;
		cameraId = 0;
	}

	/** 
	 * Returns the cameraName.
	 * @return String
	 */
	public String getCameraName() {
		return cameraName;
	}

	/** 
	 * Set the cameraName.
	 * @param cameraName The cameraName to set
	 */
	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}

	/** 
	 * Returns the cameraId.
	 * @return int
	 */
	public int getCameraId() {
		return cameraId;
	}

	/** 
	 * Set the cameraId.
	 * @param cameraId The cameraId to set
	 */
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}

}
