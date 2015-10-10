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
 * @struts:form name="filmForm"
 */
public class FilmForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** filmName property */
	private String filmName;

	/** filmId property */
	private int filmId;

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
		filmName = null;
		filmId = 0;
	}

	/** 
	 * Returns the filmName.
	 * @return String
	 */
	public String getFilmName() {
		return filmName;
	}

	/** 
	 * Set the filmName.
	 * @param filmName The filmName to set
	 */
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	/** 
	 * Returns the filmId.
	 * @return int
	 */
	public int getFilmId() {
		return filmId;
	}

	/** 
	 * Set the filmId.
	 * @param filmId The filmId to set
	 */
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

}
