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
public class AuditChange {
	private int changeId;
	private String changeName;
	
	public AuditChange(){
	}
	public AuditChange(int changeId, String changeName){
		this.changeId = changeId;
		this.changeName = changeName;
		
	}
	/**
	 * @return
	 */
	public int getChangeId() {
		return changeId;
	}

	/**
	 * @return
	 */
	public String getChangeName() {
		return changeName;
	}

	/**
	 * @param i
	 */
	public void setChangeId(int i) {
		changeId = i;
	}

	/**
	 * @param string
	 */
	public void setChangeName(String string) {
		changeName = string;
	}

}
