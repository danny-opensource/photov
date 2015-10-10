/*
 * Created on Jul 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

import java.util.Hashtable;
import java.util.Map;

import net.wgbv.photov.action.Constants;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PageNumber implements Photov {
	private int pageNumber = 0;
	private int currentPageNumber = 0;
	private int keyId = 0;
	private int keyId2 = 0;
	private String keyType = new String();
	private Hashtable ht = new Hashtable();

	public PageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public PageNumber(
		int pageNumber,
		int currentPageNumber,
		int keyId,
		int keyId2,
		String keyType) {
		this.pageNumber = pageNumber;
		this.currentPageNumber = currentPageNumber;
		this.keyId = keyId;
		this.keyId2 = keyId2;
		this.keyType = keyType;
		if (this.keyType.equalsIgnoreCase(Constants.PERSON_ID_KEY)) {
			ht.put(Constants.PERSON_ID_KEY, new Integer(this.keyId));
		} else if (this.keyType.equalsIgnoreCase(Constants.PLACE_ID_KEY)) {
			ht.put(Constants.PLACE_ID_KEY, new Integer(this.keyId));
		} else if (this.keyType.equalsIgnoreCase(Constants.HEADING_ID_KEY)) {
			ht.put(Constants.HEADING_ID_KEY, new Integer(this.keyId));
		} else if (
			(this.keyType.equalsIgnoreCase(Constants.PERSON_ID_ONE_KEY))
				|| (this.keyType.equalsIgnoreCase(Constants.PERSON_ID_TWO_KEY))) {
			ht.put(Constants.PERSON_ID_ONE_KEY, new Integer(this.keyId));
			ht.put(Constants.PERSON_ID_TWO_KEY, new Integer(this.keyId2));
		}
		ht.put(Constants.PAGE_KEY, new Integer(this.pageNumber));

	}
	/**
	 * @return
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageDisplayNumber() {
		return pageNumber + 1;
	}
	/**
	 * @param i
	 */
	public void setPageNumber(int i) {
		pageNumber = i;
	}

	/**
	 * @return
	 */
	public int getKeyId() {
		return keyId;
	}

	/**
	 * @return
	 */
	public String getKeyType() {
		return keyType;
	}

	/**
	 * @param i
	 */
	public void setKeyId(int i) {
		keyId = i;
	}

	/**
	 * @param string
	 */
	public void setKeyType(String string) {
		keyType = string;
	}

	public Map getParamMap() {
		return ht;
	}

	/**
	 * @return
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * @param i
	 */
	public void setCurrentPageNumber(int i) {
		currentPageNumber = i;
	}

}
