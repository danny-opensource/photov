/*
 * Created on Jul 15, 2004
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
public class PersonRanking extends Person implements Comparable, Photov {

	private int pageOne = 0;
	private int pageTwo = 0;
	private int rank = 0;
	private int numPhotos = 0;
	
	private boolean newPhotos = false;
	private boolean priv = false;
	private boolean complete = true;

	/**
	 * @return
	 */
	public int getPageOne() {
		return pageOne;
	}

	/**
	 * @return
	 */
	public int getPageTwo() {
		return pageTwo;
	}

	/**
	 * @param i
	 */
	public void setPageOne(int i) {
		pageOne = i;
	}

	/**
	 * @param i
	 */
	public void setPageTwo(int i) {
		pageTwo = i;
	}

	/**
	 * @return
	 */
	public int getNumPhotos() {
		return numPhotos;
	}
	public String getStringNumPhotos() {
		return numPhotos + "";
	}

	/**
	 * @return
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param i
	 */
	public void setNumPhotos(int i) {
		numPhotos = i;
	}

	/**
	 * @param i
	 */
	public void setRank(int i) {
		rank = i;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return getFName();
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return getLName();
	}

	/**
	 * @return
	 */
	public String getMiddleName() {
		return getMName();
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string) {
		setFName(string);
	}

	/**
	 * @param string
	 */
	public void setLastName(String string) {
		setLName(string);
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string) {
		setMName(string);
	}
	public boolean equals(Object o) {
		if (!(o instanceof Person))
			return false;
		PersonRanking p = (PersonRanking) o;
		return this.getLName().equals(p.getLName())
			&& this.getFName().equals(p.getFName())
			&& this.getMName().equals(p.getMName())
			&& this.getEmail().equals(p.getEmail())
			&& (this.getNumPhotos() == p.getNumPhotos());
	}



	public int hashCode() {
		return 31 * this.getFName().hashCode()
			+ this.getLName().hashCode()
			+ this.getMName().hashCode()
			+ this.getEmail().hashCode()
			+ numPhotos;
	}
	
	public String getPersonLNameCount(){
		return this.toString();
	}

	public String toString() {
		return super.getPersonLName() + " (" + numPhotos + ")";
	}

	public int compareTo(Object o) {
		PersonRanking p = (PersonRanking) o;
		int totalCmp = 0;
		if (this.getNumPhotos() < p.getNumPhotos()) {
			return 1;
		} else if (this.getNumPhotos() > p.getNumPhotos()) {
			return -1;
		} else {
			int lastCmp = this.getLName().compareTo(p.getLName());
			if (lastCmp != 0) {
				return lastCmp;
			} else {
				int firstCmp = this.getFName().compareTo(p.getFName());
				if (firstCmp != 0) {
					return firstCmp;
				} else {
					int middleCmp = this.getMName().compareTo(p.getMName());
					if (middleCmp != 0) {
						return middleCmp;
					} else {
						int emailCmp = this.getEmail().compareTo(p.getEmail());
						if (emailCmp != 0) {
							return emailCmp;
						} else {
							return 0;
						}
					}
				}
			}
		}
	}
	/**
	 * @return
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * @return
	 */
	public boolean isNewPhotos() {
		return newPhotos;
	}

	/**
	 * @return
	 */
	public boolean isPriv() {
		return priv;
	}

	/**
	 * @param b
	 */
	public void setComplete(boolean b) {
		complete = b;
	}

	/**
	 * @param b
	 */
	public void setNewPhotos(boolean b) {
		newPhotos = b;
	}

	/**
	 * @param b
	 */
	public void setPriv(boolean b) {
		priv = b;
	}

}
