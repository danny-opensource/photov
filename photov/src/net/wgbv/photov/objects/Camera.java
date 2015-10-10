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
public class Camera implements Photov  {
	private int cameraId = 0;
	private String cameraName = new String();
	/**
	 * @return
	 */
	public int getCameraId() {
		return cameraId;
	}

	/**
	 * @return
	 */
	public String getCameraName() {
		return cameraName;
	}

	/**
	 * @param i
	 */
	public void setCameraId(int i) {
		cameraId = i;
	}

	/**
	 * @param string
	 */
	public void setCameraName(String string) {
		cameraName = string;
	}

}
