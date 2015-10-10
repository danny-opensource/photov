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
 * @struts:form name="categoryForm"
 */
public class CategoryForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** categoryName property */
	private String categoryName;

	/** categoryDate property */
	private String categoryDate;

	/** categoryUrl property */
	private String categoryUrl;

	/** categoryId property */
	private int categoryId;
	private int topicId;

	/** topics property */
	private ArrayList topics;

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
		this.categoryId = 0;
		this.topicId = 0;
		this.categoryName = null;
		this.categoryUrl = null;
		this.categoryDate = null;
		this.topics = null;
	}

	/** 
	 * Returns the categoryName.
	 * @return String
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/** 
	 * Set the categoryName.
	 * @param categoryName The categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/** 
	 * Returns the categoryDate.
	 * @return String
	 */
	public String getCategoryDate() {
		return categoryDate;
	}

	/** 
	 * Set the categoryDate.
	 * @param categoryDate The categoryDate to set
	 */
	public void setCategoryDate(String categoryDate) {
		this.categoryDate = categoryDate;
	}

	/** 
	 * Returns the categoryUrl.
	 * @return String
	 */
	public String getCategoryUrl() {
		return categoryUrl;
	}

	/** 
	 * Set the categoryUrl.
	 * @param categoryUrl The categoryUrl to set
	 */
	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
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

}
