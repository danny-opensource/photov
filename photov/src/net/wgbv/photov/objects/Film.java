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
public class Film  implements Photov {
	private int filmId = 0;
	private String filmName = new String();
	/**
	 * @return
	 */
	public int getFilmId() {
		return filmId;
	}

	/**
	 * @return
	 */
	public String getFilmName() {
		return filmName;
	}

	/**
	 * @param i
	 */
	public void setFilmId(int i) {
		filmId = i;
	}

	/**
	 * @param string
	 */
	public void setFilmName(String string) {
		filmName = string;
	}

}
