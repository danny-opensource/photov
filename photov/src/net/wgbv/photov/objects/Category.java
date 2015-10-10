/*
 * Created on Jul 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

import java.util.ArrayList;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Category implements Photov  {
	private String categoryName;

	private String categoryDate;

	private String categoryUrl;

	private int categoryId;
	private int topicId;

	private ArrayList topics;

	/**
	 * @return
	 */
	public String getCategoryDate() {
		return categoryDate;
	}

	/**
	 * @return
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @return
	 */
	public String getCategoryUrl() {
		return categoryUrl;
	}

	/**
	 * @return
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @return
	 */
	public ArrayList getTopics() {
		return topics;
	}

	/**
	 * @param string
	 */
	public void setCategoryDate(String string) {
		categoryDate = string;
	}

	/**
	 * @param i
	 */
	public void setCategoryId(int i) {
		categoryId = i;
	}

	/**
	 * @param string
	 */
	public void setCategoryName(String string) {
		categoryName = string;
	}

	/**
	 * @param string
	 */
	public void setCategoryUrl(String string) {
		categoryUrl = string;
	}

	/**
	 * @param i
	 */
	public void setTopicId(int i) {
		topicId = i;
	}

	/**
	 * @param list
	 */
	public void setTopics(ArrayList list) {
		topics = list;
	}

}
