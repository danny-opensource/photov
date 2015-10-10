//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.wgbv.photov.dam.PersonFactory;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-21-2004
 * 
 * XDoclet definition:
 * @struts:form name="registrationForm"
 */
public class RegistrationForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** password property */
	private String password;
	private String password2;

	/** username property */
	private String username;

	/** personId property */
	private int personId;

	/** people property */
	private int groupId;

	private ArrayList people;

	private int userId;
	
	private String email;

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
		ActionErrors ae = new ActionErrors();

		if (people == null) {
			people = (ArrayList) PersonFactory.getRegistrationPeople();
		}

		return ae;
	}
	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		password = null;
		password2 = null;
		username = null;
		personId = 0;
		people = null;
		groupId = 0;
		userId = 0;
		email = null;
		people = (ArrayList) PersonFactory.getRegistrationPeople();

	}

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Returns the username.
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/** 
	 * Set the username.
	 * @param username The username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 
	 * Returns the personId.
	 * @return int
	 */
	public int getPersonId() {
		return personId;
	}

	/** 
	 * Set the personId.
	 * @param personId The personId to set
	 */
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	/** 
	 * Returns the people.
	 * @return ArrayList
	 */
	public ArrayList getPeople() {
		return people;
	}

	/** 
	 * Set the people.
	 * @param people The people to set
	 */
	public void setPeople(ArrayList people) {
		this.people = people;
	}

	/**
	 * @return
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param string
	 */
	public void setPassword2(String string) {
		password2 = string;
	}

	/**
	 * @return
	 */
	public int getUserId() {
		return userId;
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
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param i
	 */
	public void setGroupId(int i) {
		groupId = i;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

}
