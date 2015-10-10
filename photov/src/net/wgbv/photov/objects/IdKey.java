/*
 * Created on Aug 18, 2004
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
public class IdKey {
	private int idKeyId;
	private String idKeyName;
	
	public IdKey(){
	}
	
	public IdKey(int idKeyId, String idKeyName){
		this.idKeyId = idKeyId;
		this.idKeyName = idKeyName;
	}
	
	/**
	 * @return
	 */
	public int getIdKeyId() {
		return idKeyId;
	}

	/**
	 * @return
	 */
	public String getIdKeyName() {
		return idKeyName;
	}

	/**
	 * @param i
	 */
	public void setIdKeyId(int i) {
		idKeyId = i;
	}

	/**
	 * @param string
	 */
	public void setIdKeyName(String string) {
		idKeyName = string;
	}

}
