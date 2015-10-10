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
public class State  implements Photov  {
	private int stateId = 0;
	private int countryId = 0;
	private String stateName = new String();
	private String stateAbbr = new String();
	/**
	 * @return
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @return
	 */
	public String getStateAbbr() {
		return stateAbbr;
	}

	/**
	 * @return
	 */
	public int getStateId() {
		return stateId;
	}

	/**
	 * @return
	 */
	public String getStateName() {
		return stateName;
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
	public void setStateAbbr(String string) {
		stateAbbr = string;
	}

	/**
	 * @param i
	 */
	public void setStateId(int i) {
		stateId = i;
	}

	/**
	 * @param string
	 */
	public void setStateName(String string) {
		stateName = string;
	}

}
