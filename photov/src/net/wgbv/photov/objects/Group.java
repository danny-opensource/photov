/*
 * Created on Jul 13, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

import net.wgbv.photov.action.Constants;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Group implements Comparable, Photov {

	int groupId = 0;
	String groupName = new String();
	String groupDesc = new String();

	public Group() {
	}
	public Group(int groupId, String groupName, String groupDesc) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDesc = groupDesc;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Group))
			return false;
		Group g = (Group) o;
		return (this.groupId == g.getGroupId())
			&& this.groupName.equals(g.getGroupName())
			&& this.groupDesc.equals(g.getGroupDesc());

	}

	public int hashCode() {
		return 31 * groupName.hashCode() + groupDesc.hashCode() + groupId;
	}

	public String toString() {
		return getGroupName();
	}

	public int compareTo(Object o) {
		Group g = (Group) o;
		int totalCmp = 0;
		boolean isLeftUserGroup = false;
		boolean isRightUserGroup = false;
		String leftSub = new String();
		if (this.groupDesc.length() > 15) {
			leftSub = this.groupDesc.substring(0,15);
		} else {
			leftSub = null;
		}
		String rightSub = new String();
		if (g.getGroupDesc().length() > 15) {
			rightSub = g.getGroupDesc().substring(0,15);
		} else {
			rightSub = null;
		}
		if ((leftSub != null)
			&& (leftSub.equalsIgnoreCase(Constants.USER_GROUP_INDICATOR))) {
			isLeftUserGroup = true;
		}
		if ((rightSub != null)
			&& (rightSub.equalsIgnoreCase(Constants.USER_GROUP_INDICATOR))) {
			isRightUserGroup = true;
		}
		// Both or Neither are user groups, sort normally on group name
		if ((isLeftUserGroup && isRightUserGroup)
			|| (!isLeftUserGroup && !isRightUserGroup)) {
			int groupNameCmp = this.groupName.toLowerCase().compareTo(g.getGroupName().toLowerCase());
			if (groupNameCmp != 0) {
				return groupNameCmp;
			} else {
				return 0;
			}
		} else { // One of them is, one isn't a User Group
			if (isLeftUserGroup)
				return 1;
			else if (isRightUserGroup)
				return -1;
		}
		return 0;
	}

	/**
	 * @return
	 */
	public String getGroupDesc() {
		return groupDesc;
	}

	/**
	 * @return
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @return
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param string
	 */
	public void setGroupDesc(String string) {
		groupDesc = string;
	}

	/**
	 * @param i
	 */
	public void setGroupId(int i) {
		groupId = i;
	}

	/**
	 * @param string
	 */
	public void setGroupName(String string) {
		groupName = string;
	}

}
