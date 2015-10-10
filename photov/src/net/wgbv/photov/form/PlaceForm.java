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
 * @struts:form name="placeForm"
 */
public class PlaceForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** states property */
	private ArrayList states;

	/** placeName property */
	private String placeName;

	/** countryId property */
	private int countryId;

	/** countries property */
	private ArrayList countries;

	/** stateId property */
	private int stateId;

	/** city property */
	private String city;

	/** placeId property */
	private int placeId;

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
		states = null;
		placeName = null;
		countryId = 0;
		countries = null;
		stateId = 0;
		city = null;
		placeId = 0;

	}

	/** 
	 * Returns the states.
	 * @return ArrayList
	 */
	public ArrayList getStates() {
		return states;
	}

	/** 
	 * Set the states.
	 * @param states The states to set
	 */
	public void setStates(ArrayList states) {
		this.states = states;
	}

	/** 
	 * Returns the placeName.
	 * @return String
	 */
	public String getPlaceName() {
		return placeName;
	}

	/** 
	 * Set the placeName.
	 * @param placeName The placeName to set
	 */
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
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
	 * Returns the countries.
	 * @return ArrayList
	 */
	public ArrayList getCountries() {
		return countries;
	}

	/** 
	 * Set the countries.
	 * @param countries The countries to set
	 */
	public void setCountries(ArrayList countries) {
		this.countries = countries;
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
	 * Returns the city.
	 * @return String
	 */
	public String getCity() {
		return city;
	}

	/** 
	 * Set the city.
	 * @param city The city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/** 
	 * Returns the placeId.
	 * @return int
	 */
	public int getPlaceId() {
		return placeId;
	}

	/** 
	 * Set the placeId.
	 * @param placeId The placeId to set
	 */
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

}
