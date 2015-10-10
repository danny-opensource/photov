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
public class Heading implements Photov  {
	private String headingName;

	private String headingDate;

	private String headingUrl;

	private int categoryId;
	private int headingId;

	private ArrayList categories;
	/**
	 * @return
	 */
	public ArrayList getCategories() {
		return categories;
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
	public String getHeadingDate() {
		return headingDate;
	}

	/**
	 * @return
	 */
	public int getHeadingId() {
		return headingId;
	}

	/**
	 * @return
	 */
	public String getHeadingName() {
		return headingName;
	}

	/**
	 * @return
	 */
	public String getHeadingUrl() {
		return headingUrl;
	}

	/**
	 * @param list
	 */
	public void setCategories(ArrayList list) {
		categories = list;
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
	public void setHeadingDate(String string) {
		headingDate = string;
	}

	/**
	 * @param i
	 */
	public void setHeadingId(int i) {
		headingId = i;
	}

	/**
	 * @param string
	 */
	public void setHeadingName(String string) {
		headingName = string;
	}

	/**
	 * @param string
	 */
	public void setHeadingUrl(String string) {
		headingUrl = string;
	}

}
