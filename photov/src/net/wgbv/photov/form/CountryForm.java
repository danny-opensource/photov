//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-20-2004
 * 
 * XDoclet definition:
 * @struts:form name="countryForm"
 */
public class CountryForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** countryName property */
	private String countryName;

	/** countryId property */
	private int countryId;

	/** countryAbbr property */
	private String countryAbbr;

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
		countryName = null;
		countryAbbr = null;
		countryId = 0;
		
	}

	/** 
	 * Returns the countryName.
	 * @return String
	 */
	public String getCountryName() {
		return countryName;
	}

	/** 
	 * Set the countryName.
	 * @param countryName The countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/** 
	 * Returns the countryId.
	 * @return int
	 */
	public int getCountryId() {
		return countryId;
	}

	/** 
	 * Set the countryId.
	 * @param countryId The countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/** 
	 * Returns the countryAbbr.
	 * @return String
	 */
	public String getCountryAbbr() {
		return countryAbbr;
	}

	/** 
	 * Set the countryAbbr.
	 * @param countryAbbr The countryAbbr to set
	 */
	public void setCountryAbbr(String countryAbbr) {
		this.countryAbbr = countryAbbr;
	}

}
