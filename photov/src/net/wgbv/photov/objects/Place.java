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
public class Place  implements Photov {
	private String placeName = new String();
	private int place_id = 0;
	private int state_id = 0;
	private String city = new String();

public Place(){
}

	public Place(int placeId, int stateId, String city, String placeName){
		this.setPlaceId(placeId);
		this.setStateId(stateId);
		this.setCity(city);
		this.setPlaceName(placeName);
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return
	 */
	public int getPlaceId() {
		return place_id;
	}

	/**
	 * @return
	 */
	public String getPlaceName() {
		return placeName;
	}

	/**
	 * @return
	 */
	public int getStateId() {
		return state_id;
	}

	/**
	 * @param string
	 */
	public void setCity(String string) {
		city = string;
	}

	/**
	 * @param i
	 */
	public void setPlaceId(int i) {
		place_id = i;
	}

	/**
	 * @param string
	 */
	public void setPlaceName(String string) {
		placeName = string;
	}

	/**
	 * @param i
	 */
	public void setStateId(int i) {
		state_id = i;
	}

}
