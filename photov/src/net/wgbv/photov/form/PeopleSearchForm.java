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
 * Creation date: 07-15-2004
 * 
 * XDoclet definition:
 * @struts:form name="peopleSearchForm"
 */
public class PeopleSearchForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	private String searchString = new String("");
	private String action = null;
	private ArrayList letters = null;
	private ArrayList allLetters = null;
	private ArrayList peopleRanking = null;
	private int personIdTwo = 0;
	private int personIdOne = 0;

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
		searchString = new String("");
		action = null;
		letters = null;
		allLetters = null;
		peopleRanking = null;
		personIdTwo = 0;
		personIdOne = 0;
	}

	/** 
	 * Returns the searchString.
	 * @return String
	 */
	public String getSearchString() {
		return searchString;
	}

	/** 
	 * Set the searchString.
	 * @param searchString The searchString to set
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
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
	 * Returns the letters.
	 * @return ArrayList
	 */
	public ArrayList getLetters() {
		return letters;
	}

	/** 
	 * Set the letters.
	 * @param letters The letters to set
	 */
	public void setLetters(ArrayList letters) {
		this.letters = letters;
	}

	/** 
	 * Returns the allLetters.
	 * @return ArrayList
	 */
	public ArrayList getAllLetters() {
		return allLetters;
	}

	/** 
	 * Set the allLetters.
	 * @param allLetters The allLetters to set
	 */
	public void setAllLetters(ArrayList allLetters) {
		this.allLetters = allLetters;
	}

	/**
	 * @return
	 */
	public ArrayList getPeopleRanking() {
		return peopleRanking;
	}

	/**
	 * @param list
	 */
	public void setPeopleRanking(ArrayList list) {
		peopleRanking = list;
	}

	/**
	 * @return
	 */
	public int getPersonIdOne() {
		return personIdOne;
	}

	/**
	 * @return
	 */
	public int getPersonIdTwo() {
		return personIdTwo;
	}

	/**
	 * @param i
	 */
	public void setPersonIdOne(int i) {
		personIdOne = i;
	}

	/**
	 * @param i
	 */
	public void setPersonIdTwo(int i) {
		personIdTwo = i;
	}

}
