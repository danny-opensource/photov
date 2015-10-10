//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.wgbv.photov.objects.Person;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-21-2004
 * 
 * XDoclet definition:
 * @struts:form name="userForm"
 */
public class UserForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** active property */
	private boolean active;

	/** password property */
	private String password;
	private String password2;

	/** groupId property */
	private int groupId;

	/** update property */
	private boolean update;

	/** userId property */
	private int userId;

	/** person property */
	private Person person;

	/** username property */
	private String username;

	/** personId property */
	private int personId;

	private ArrayList people;
	private ArrayList groups;
	
	private ArrayList selectedGroups;
	private ArrayList allGroups;
	private int selectedGroupId;
	private int allGroupId;
	
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

		if ((password != null) && (password2 != null)) {
			if (!password.equalsIgnoreCase(password2)) {
				ActionError error =
					new ActionError("error.login.password.mismatch");
				ae.add(ActionErrors.GLOBAL_MESSAGE, error);
			}
		}
		return ae;
	}

	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		active = false;
		password = null;
		update = false;
		person = null;
		username = null;
		groupId = 0;
		userId = 0;
		personId = 0;
	}

	/** 
	 * Returns the active.
	 * @return boolean
	 */
	public boolean getActive() {
		return active;
	}

	/** 
	 * Set the active.
	 * @param active The active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * Returns the groupId.
	 * @return int
	 */
	public int getGroupId() {
		return groupId;
	}

	/** 
	 * Set the groupId.
	 * @param groupId The groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/** 
	 * Returns the update.
	 * @return boolean
	 */
	public boolean getUpdate() {
		return update;
	}

	/** 
	 * Set the update.
	 * @param update The update to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/** 
	 * Returns the userId.
	 * @return int
	 */
	public int getUserId() {
		return userId;
	}

	/** 
	 * Set the userId.
	 * @param userId The userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/** 
	 * Returns the person.
	 * @return Person
	 */
	public Person getPerson() {
		return person;
	}

	/** 
	 * Set the person.
	 * @param person The person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
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
	 * @return
	 */
	public ArrayList getPeople() {
		return people;
	}

	/**
	 * @param list
	 */
	public void setPeople(ArrayList list) {
		people = list;
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
	public ArrayList getAllGroups() {
		return allGroups;
	}

	/**
	 * @return
	 */
	public ArrayList getSelectedGroups() {
		return selectedGroups;
	}

	/**
	 * @param list
	 */
	public void setAllGroups(ArrayList list) {
		allGroups = list;
	}

	/**
	 * @param list
	 */
	public void setSelectedGroups(ArrayList list) {
		selectedGroups = list;
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

	/**
	 * @return
	 */
	public int getAllGroupId() {
		return allGroupId;
	}

	/**
	 * @return
	 */
	public int getSelectedGroupId() {
		return selectedGroupId;
	}

	/**
	 * @param i
	 */
	public void setAllGroupId(int i) {
		allGroupId = i;
	}

	/**
	 * @param i
	 */
	public void setSelectedGroupId(int i) {
		selectedGroupId = i;
	}

	/**
	 * @return
	 */
	public ArrayList getGroups() {
		return groups;
	}

	/**
	 * @param list
	 */
	public void setGroups(ArrayList list) {
		groups = list;
	}

}
