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
 * @struts:form name="siteForm"
 */
public class SiteForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** siteUrl property */
	private String siteUrl;

	/** siteId property */
	private int siteId;

	/** siteName property */
	private String siteName;

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
		siteUrl = null;
		siteId = 0;
		siteName = null;
	}

	/** 
	 * Returns the siteUrl.
	 * @return String
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/** 
	 * Set the siteUrl.
	 * @param siteUrl The siteUrl to set
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/** 
	 * Returns the siteId.
	 * @return int
	 */
	public int getSiteId() {
		return siteId;
	}

	/** 
	 * Set the siteId.
	 * @param siteId The siteId to set
	 */
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	/** 
	 * Returns the siteName.
	 * @return String
	 */
	public String getSiteName() {
		return siteName;
	}

	/** 
	 * Set the siteName.
	 * @param siteName The siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
