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
 * Creation date: 07-21-2004
 * 
 * XDoclet definition:
 * @struts:form name="groupForm"
 */
public class GroupForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** groupId property */
	private int groupId;

	/** groupDesc property */
	private String groupDesc;

	/** groupName property */
	private String groupName;
	
	private int selectedUserId;
	private int allUserId;
	private ArrayList selectedUsers;
	private ArrayList allUsers;

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
		groupId = 0;
		groupDesc = null;
		groupName = null;
		selectedUserId = 0;
		allUserId = 0;
		selectedUsers = null;
		allUsers = null;
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
	 * Returns the groupDesc.
	 * @return String
	 */
	public String getGroupDesc() {
		return groupDesc;
	}

	/** 
	 * Set the groupDesc.
	 * @param groupDesc The groupDesc to set
	 */
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	/** 
	 * Returns the groupName.
	 * @return String
	 */
	public String getGroupName() {
		return groupName;
	}

	/** 
	 * Set the groupName.
	 * @param groupName The groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return
	 */
	public int getAllUserId() {
		return allUserId;
	}

	/**
	 * @return
	 */
	public ArrayList getAllUsers() {
		return allUsers;
	}

	/**
	 * @return
	 */
	public int getSelectedUserId() {
		return selectedUserId;
	}

	/**
	 * @return
	 */
	public ArrayList getSelectedUsers() {
		return selectedUsers;
	}

	/**
	 * @param i
	 */
	public void setAllUserId(int i) {
		allUserId = i;
	}

	/**
	 * @param list
	 */
	public void setAllUsers(ArrayList list) {
		allUsers = list;
	}

	/**
	 * @param i
	 */
	public void setSelectedUserId(int i) {
		selectedUserId = i;
	}

	/**
	 * @param list
	 */
	public void setSelectedUsers(ArrayList list) {
		selectedUsers = list;
	}

}
