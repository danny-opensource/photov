/*
 * Created on Jun 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

import java.util.ArrayList;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Person implements Comparable, Photov {

	private String FName = new String();
	private String MName = new String();
	private String LName = new String();
	private String email = new String();
	private String class_year = new String();
	private int personId = 0;
	private ArrayList people;


	public Person(int personId, String fname, String mname, String lname) {
		this.setPersonId(personId);
		this.setFName(fname);
		this.setMName(mname);
		this.setLName(lname);
	}
	public Person(
		int personId,
		String fname,
		String mname,
		String lname,
		String email,
		String class_year) {
		new Person(personId, fname, mname, lname);
		this.setEmail(email);
		this.setClassYear(class_year);
	}
	public Person() {
	}
	public Person(Person person) {
		this.setPersonId(person.getPersonId());
		this.setFName(person.getFName());
		this.setMName(person.getMName());
		this.setLName(person.getLName());
	}

	/**
	 * @return
	 */
	public String getClassYear() {
		return class_year;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getFName() {
		return FName;
	}

	/**
	 * @return
	 */
	public String getLName() {
		return LName;
	}

	/**
	 * @return
	 */
	public String getMName() {
		return MName;
	}

	/**
	 * @return
	 */
	public int getPersonId() {
		return personId;
	}

	/**
	 * @return
	 */
	public String getPersonName() {
		return FName + " " + LName;
	}

	/**
	 * @return
	 */
	public String getPersonLName() {
		return LName + ", " + FName;
	}

	/**
	 * @param string
	 */
	public void setClassYear(String string) {
		class_year = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 */
	public void setFName(String string) {
		FName = string;
	}

	/**
	 * @param string
	 */
	public void setLName(String string) {
		LName = string;
	}

	/**
	 * @param string
	 */
	public void setMName(String string) {
		MName = string;
	}

	/**
	 * @param i
	 */
	public void setPersonId(int i) {
		personId = i;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Person))
			return false;
		Person p = (Person) o;
		return this.LName.equals(p.getLName())
			&& this.FName.equals(p.getFName())
			&& this.MName.equals(p.getMName())
		&& this.email.equals(p.getEmail());

	}

	public int hashCode() {
		return 31 * FName.hashCode() + LName.hashCode() + MName.hashCode() + email.hashCode();
	}

	public String toString() {
		return getPersonLName();
	}
	
	public int compareTo(Object o) {
		Person p = (Person)o;
		int totalCmp = 0;
		int lastCmp = this.LName.compareTo(p.getLName());
		if (lastCmp != 0 ) {
			return lastCmp;
		} else {
			int firstCmp = this.FName.compareTo(p.getFName());
			if (firstCmp != 0 ) {
				return firstCmp;
			}
			else {
				int middleCmp = this.MName.compareTo(p.getMName());
				if (middleCmp != 0 ){
					return middleCmp;
				} else
				{
					int emailCmp = this.email.compareTo(p.getEmail());
					if (emailCmp != 0){
						return emailCmp;
					} else {
						return 0;
					}
				}
			}
		}
	}


	/**
	 * @return
	 */
	public ArrayList getPeople() {
		return people;
	}

	/**
	 * @param list
	 */
	public void setPeople(ArrayList list) {
		people = list;
	}

}
