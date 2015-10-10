//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-19-2004
 * 
 * XDoclet definition:
 * @struts:form name="placeListForm"
 */
public class PlaceListForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** placeList property */
	private ArrayList placeList;

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

		placeList = null;
	}

	/** 
	 * Returns the placeList.
	 * @return ArrayList
	 */
	public ArrayList getPlaceList() {
		return placeList;
	}

	/** 
	 * Set the placeList.
	 * @param placeList The placeList to set
	 */
	public void setPlaceList(ArrayList placeList) {
		this.placeList = placeList;
	}

}
