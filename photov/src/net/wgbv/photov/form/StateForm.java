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
 * Creation date: 07-20-2004
 * 
 * XDoclet definition:
 * @struts:form name="stateForm"
 */
public class StateForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** stateAbbr property */
	private String stateAbbr;

	/** stateName property */
	private String stateName;

	/** countryId property */
	private int countryId;

	/** stateId property */
	private int stateId;
	
	private ArrayList countries;

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
		countries = null ;
		stateAbbr = null ;
		stateName = null ;
		countryId = 0 ;
		stateId = 0 ;
	}

	/** 
	 * Returns the stateAbbr.
	 * @return String
	 */
	public String getStateAbbr() {
		return stateAbbr;
	}

	/** 
	 * Set the stateAbbr.
	 * @param stateAbbr The stateAbbr to set
	 */
	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	/** 
	 * Returns the stateName.
	 * @return String
	 */
	public String getStateName() {
		return stateName;
	}

	/** 
	 * Set the stateName.
	 * @param stateName The stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
	 * Returns the stateId.
	 * @return int
	 */
	public int getStateId() {
		return stateId;
	}

	/** 
	 * Set the stateId.
	 * @param stateId The stateId to set
	 */
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return
	 */
	public ArrayList getCountries() {
		return countries;
	}

	/**
	 * @param list
	 */
	public void setCountries(ArrayList list) {
		countries = list;
	}

}
