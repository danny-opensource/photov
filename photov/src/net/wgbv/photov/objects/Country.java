/*
 * Created on Jul 20, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Country  implements Photov {
	private int countryId = 0;
	private String countryAbbr = new String();
	private String countryName = new String();
	/**
	 * @return
	 */
	public String getCountryAbbr() {
		return countryAbbr;
	}

	/**
	 * @return
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @return
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param string
	 */
	public void setCountryAbbr(String string) {
		countryAbbr = string;
	}

	/**
	 * @param i
	 */
	public void setCountryId(int i) {
		countryId = i;
	}

	/**
	 * @param string
	 */
	public void setCountryName(String string) {
		countryName = string;
	}

}
