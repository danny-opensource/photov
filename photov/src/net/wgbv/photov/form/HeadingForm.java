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
 * Creation date: 07-08-2004
 * 
 * XDoclet definition:
 * @struts:form name="headingForm"
 */
public class HeadingForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** headingId property */
	private int headingId;

	/** headingDate property */
	private String headingDate;

	/** headingUrl property */
	private String headingUrl;

	/** headingName property */
	private String headingName;

	/** categories property */
	private ArrayList categories;

	/** categoryId property */
	private int categoryId;

	/** topics property */
	private ArrayList topics;
	
	private int topicId;
	
	private int groupId = 0;
	private ArrayList groups = null;
	private int allGroupId = 0;
	private ArrayList allGroups = null;


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
		this.categories = null;
		this.categoryId = 0;
		this.headingDate = null;
		this.headingId = 0;
		this.headingName = null;
		this.headingUrl = null;
		this.topics = null;
	}

	/** 
	 * Returns the headingId.
	 * @return int
	 */
	public int getHeadingId() {
		return headingId;
	}

	/** 
	 * Set the headingId.
	 * @param headingId The headingId to set
	 */
	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}

	/** 
	 * Returns the headingDate.
	 * @return String
	 */
	public String getHeadingDate() {
		return headingDate;
	}

	/** 
	 * Set the headingDate.
	 * @param headingDate The headingDate to set
	 */
	public void setHeadingDate(String headingDate) {
		this.headingDate = headingDate;
	}

	/** 
	 * Returns the headingUrl.
	 * @return String
	 */
	public String getHeadingUrl() {
		return headingUrl;
	}

	/** 
	 * Set the headingUrl.
	 * @param headingUrl The headingUrl to set
	 */
	public void setHeadingUrl(String headingUrl) {
		this.headingUrl = headingUrl;
	}

	/** 
	 * Returns the headingName.
	 * @return String
	 */
	public String getHeadingName() {
		return headingName;
	}

	/** 
	 * Set the headingName.
	 * @param headingName The headingName to set
	 */
	public void setHeadingName(String headingName) {
		this.headingName = headingName;
	}

	/** 
	 * Returns the categories.
	 * @return ArrayList
	 */
	public ArrayList getCategories() {
		return categories;
	}

	/** 
	 * Set the categories.
	 * @param categories The categories to set
	 */
	public void setCategories(ArrayList categories) {
		this.categories = categories;
	}

	/** 
	 * Returns the categoryId.
	 * @return int
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/** 
	 * Set the categoryId.
	 * @param categoryId The categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/** 
	 * Returns the topics.
	 * @return ArrayList
	 */
	public ArrayList getTopics() {
		return topics;
	}

	/** 
	 * Set the topics.
	 * @param topics The topics to set
	 */
	public void setTopics(ArrayList topics) {
		this.topics = topics;
	}

	/**
	 * @return
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @param i
	 */
	public void setTopicId(int i) {
		topicId = i;
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
	public ArrayList getAllGroups() {
		return allGroups;
	}

	/**
	 * @return
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @return
	 */
	public ArrayList getGroups() {
		return groups;
	}

	/**
	 * @param i
	 */
	public void setAllGroupId(int i) {
		allGroupId = i;
	}

	/**
	 * @param list
	 */
	public void setAllGroups(ArrayList list) {
		allGroups = list;
	}

	/**
	 * @param i
	 */
	public void setGroupId(int i) {
		groupId = i;
	}

	/**
	 * @param list
	 */
	public void setGroups(ArrayList list) {
		groups = list;
	}

}
