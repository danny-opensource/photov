/*
 * Created on Jun 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

import java.util.ArrayList;

import net.wgbv.photov.dam.PersonFactory;
import net.wgbv.photov.dam.UserFactory;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class User extends Person  implements Photov {

	private String username = new String();
	private String password = new String();
	private int userId = 0;
	private int personId = 0;
	private int userGroupId = 0;
	private int userLevel = 0;
	private String groupName = new String();
	private boolean active = false;
	private boolean canUpdate = false;
	private ArrayList groupIds = new ArrayList(10);
	private ArrayList groups = new ArrayList(10);
	/**
	 * 
	 */
	public User() {
	}
	public User(
		int intUserId,
		int intPersonId,
		int intUserGrpId,
		String strUsername,
		String strPassword,
		boolean active,
		boolean canUpdate) {

		this.setUserId(intUserId);
		this.setPersonId(intPersonId);
		this.setUserGroupId(intUserGrpId);
		this.setUsername(strUsername);
		this.setPassword(strPassword);
		this.setActive(active);
		this.setCanUpdate(canUpdate);
		Person person = PersonFactory.getPersonByPersonId(this.personId);
		this.setPersonId(person.getPersonId());
		this.setFName(person.getFName());
		this.setMName(person.getMName());
		this.setLName(person.getLName());
		this.setGroupIds(UserFactory.getGroupsByUserId(intUserId));

	}

	public void setUserId(int temp) {
		userId = temp;
	}

	public int getUserId() {
		return userId;
	}

	public void setPersonId(int temp) {
		personId = temp;
	}

	public int getPersonId() {
		return personId;
	}

	public void setUsername(String temp) {
		username = temp;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String temp) {
		password = temp;
	}

	public String getPassword() {
		return password;
	}

	public void setActive(boolean bl) {
		active = bl;
	}

	public boolean isActive() {
		return active;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(this.getPersonId() + " | ");
		sb.append(this.getPersonName() + " | ");

		return sb.toString();
	}

	/**
	 * @return
	 */
	public int getUserLevel() {
		return userLevel;
	}

	/**
	 * @param i
	 */
	public void setUserLevel(int i) {
		userLevel = i;
	}

	/**
	 * @return
	 */
	public boolean getCanUpdate() {
		return canUpdate;
	}

	/**
	 * @param string
	 */
	public void setCanUpdate(boolean bl) {
		this.canUpdate = bl;
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
	public void setGroupName(String string) {
		groupName = string;
	}

	/**
	 * @return
	 */
	public ArrayList getGroupIds() {
		return groupIds;
	}

	/**
	 * @return
	 */
	public ArrayList getGroups() {
		return groups;
	}

	/**
	 * @return
	 */
	public int getUserGroupId() {
		return userGroupId;
	}

	/**
	 * @param list
	 */
	public void setGroupIds(ArrayList list) {
		groupIds = list;
	}

	/**
	 * @param list
	 */
	public void setGroups(ArrayList list) {
		groups = list;
	}

	/**
	 * @param i
	 */
	public void setUserGroupId(int i) {
		userGroupId = i;
	}

}
