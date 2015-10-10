/*
 * Created on Jul 3, 2004
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

public class PlaceDetails extends Place  implements Photov  {

	private String stateName = new String();
	private String stateAbbr = new String();
	private int countryId = 0;
	private String countryName = new String();
	private String countryAbbr = new String();

	public PlaceDetails() {
		super();
	}

	public PlaceDetails(
		int placeId,
		int stateId,
		String city,
		String placeName) {
		this.setPlaceId(placeId);
		this.setStateId(stateId);
		this.setCity(city);
		this.setPlaceName(placeName);
	}

	public PlaceDetails(Place place) {
		this.setPlaceId(place.getPlaceId());
		this.setStateId(place.getStateId());
		this.setCity(place.getCity());
		this.setPlaceName(place.getPlaceName());
	}

	/**
	 * @return
	 */
	public String getPlaceFullName() {
		return this.getPlaceFullName(true, false);
	}

	/**
	 * @return
	 */
	public String getPlaceFullName(boolean isNone, boolean isUSA) {
		StringBuffer sb = new StringBuffer();
		if (!this.getPlaceName().equalsIgnoreCase("")) {
			sb.append(this.getPlaceName() + " ");
		} else if (isNone) {
			sb.append("None ");
		}
		if (!this.getCity().equalsIgnoreCase("")) {
			sb.append(this.getCity() + ", ");
		} else {
			sb.append(" ");
		}
		if (!this.getStateAbbr().equalsIgnoreCase("")) {
			sb.append(this.getStateAbbr());
		}
		sb.append(" ");
		if (!this.getCountryAbbr().equalsIgnoreCase("")) {
			if (this.getCountryAbbr().equalsIgnoreCase("USA")) {
				if (isUSA) {
					sb.append(this.getCountryAbbr());
				}
			} else {
				sb.append(this.getCountryAbbr());
			}
		}

		return sb.toString().replaceAll("  ", " ");
	}

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
	 * @return
	 */
	public String getStateAbbr() {
		return stateAbbr;
	}

	/**
	 * @return
	 */
	public String getStateName() {
		return stateName;
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

	/**
	 * @param string
	 */
	public void setStateAbbr(String string) {
		stateAbbr = string;
	}

	/**
	 * @param string
	 */
	public void setStateName(String string) {
		stateName = string;
	}

}
