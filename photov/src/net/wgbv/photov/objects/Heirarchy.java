/*
 * Created on Jul 19, 2004
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
public class Heirarchy implements Photov  {
 private int topicId = 0;
 private String topicName = new String();
 private boolean topicNew = false;
 private boolean topicComplete = true;
 private boolean topicPriv = false;
 private int categoryId = 0;
 private String categoryName = new String();
 private boolean categoryNew = false;
 private boolean categoryComplete = true;
 private boolean categoryPriv = false;
 private int headingId = 0;
 private String headingName = new String();
 private boolean headingNew = false;
 private boolean headingComplete = true;
 private boolean headingPriv = false;

/**
 * @return
 */
public boolean isCategoryComplete() {
	return categoryComplete;
}

/**
 * @return
 */
public int getCategoryId() {
	return categoryId;
}

/**
 * @return
 */
public String getCategoryName() {
	return categoryName;
}

/**
 * @return
 */
public boolean isCategoryNew() {
	return categoryNew;
}

/**
 * @return
 */
public boolean isCategoryPriv() {
	return categoryPriv;
}

/**
 * @return
 */
public boolean isHeadingComplete() {
	return headingComplete;
}

/**
 * @return
 */
public int getHeadingId() {
	return headingId;
}

/**
 * @return
 */
public String getHeadingName() {
	return headingName;
}

/**
 * @return
 */
public boolean isHeadingNew() {
	return headingNew;
}

/**
 * @return
 */
public boolean isHeadingPriv() {
	return headingPriv;
}

/**
 * @return
 */
public boolean isTopicComplete() {
	return topicComplete;
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
public boolean isTopicNew() {
	return topicNew;
}

/**
 * @return
 */
public boolean isTopicPriv() {
	return topicPriv;
}

/**
 * @param b
 */
public void setCategoryComplete(boolean b) {
	categoryComplete = b;
}

/**
 * @param i
 */
public void setCategoryId(int i) {
	categoryId = i;
}

/**
 * @param string
 */
public void setCategoryName(String string) {
	categoryName = string;
}

/**
 * @param b
 */
public void setCategoryNew(boolean b) {
	categoryNew = b;
}

/**
 * @param b
 */
public void setCategoryPriv(boolean b) {
	categoryPriv = b;
}

/**
 * @param b
 */
public void setHeadingComplete(boolean b) {
	headingComplete = b;
}

/**
 * @param i
 */
public void setHeadingId(int i) {
	headingId = i;
}

/**
 * @param string
 */
public void setHeadingName(String string) {
	headingName = string;
}

/**
 * @param b
 */
public void setHeadingNew(boolean b) {
	headingNew = b;
}

/**
 * @param b
 */
public void setHeadingPriv(boolean b) {
	headingPriv = b;
}

/**
 * @param b
 */
public void setTopicComplete(boolean b) {
	topicComplete = b;
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
 * @param b
 */
public void setTopicNew(boolean b) {
	topicNew = b;
}

/**
 * @param b
 */
public void setTopicPriv(boolean b) {
	topicPriv = b;
}

}
