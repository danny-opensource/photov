/*
 * Created on Jul 9, 2004
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
public class Site implements Photov  {
	private int siteId = 0;
	private String siteName = new String();
	private String siteUrl = new String();
	
	
	/**
	 * @return
	 */
	public int getSiteId() {
		return siteId;
	}

	/**
	 * @return
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @return
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * @param i
	 */
	public void setSiteId(int i) {
		siteId = i;
	}

	/**
	 * @param string
	 */
	public void setSiteName(String string) {
		siteName = string;
	}

	/**
	 * @param string
	 */
	public void setSiteUrl(String string) {
		siteUrl = string;
	}

}
