/*
 * Created on Jul 8, 2004
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
public class Topic  implements Photov {

	/** topicId property */
	private int topicId;

	/** topicDate property */
	private String topicDate;

	/** topicName property */
	private String topicName;

	/** topicUrl property */
	private String topicUrl;

	/**
	 * @return
	 */
	public String getTopicDate() {
		return topicDate;
	}

	/**
	 * @return
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @return
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @return
	 */
	public String getTopicUrl() {
		return topicUrl;
	}

	/**
	 * @param string
	 */
	public void setTopicDate(String string) {
		topicDate = string;
	}

	/**
	 * @param i
	 */
	public void setTopicId(int i) {
		topicId = i;
	}

	/**
	 * @param string
	 */
	public void setTopicName(String string) {
		topicName = string;
	}

	/**
	 * @param string
	 */
	public void setTopicUrl(String string) {
		topicUrl = string;
	}

}
