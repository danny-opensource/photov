/*
 * Created on Jul 4, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.dam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.wgbv.photov.action.Constants;
import net.wgbv.photov.form.HeadingForm;
import net.wgbv.photov.form.PeopleSearchForm;
import net.wgbv.photov.form.PersonForm;
import net.wgbv.photov.objects.Person;
import net.wgbv.photov.objects.PersonRanking;
import net.wgbv.photov.objects.PhotoDetails;
import net.wgbv.photov.objects.User;

import org.apache.log4j.Logger;

/**
 * @author William_G_Brownlow
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PersonFactory {

	static Logger l = Logger.getLogger(PersonFactory.class);

	public static Collection getRankings() {
		ArrayList coll = new ArrayList();

		int intCount = 0;
		boolean blContComplete = true;
		boolean blContNew = false;
		boolean blContPrivate = false;
		Connection conn = null;
		Statement stmt = null;
		Statement stmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			String query1 = new String();
			String query2 = new String();

			query2 = " SELECT DISTINCT " + " person.fname, " + " person.mname, " + " person.lname, " + " person.email, " + " person.person_id "
					+ " FROM photo, photo_grp, person_photo, person " + " WHERE " + " person_photo.person_id = person.person_id AND "
					+ " person_photo.photo_id = photo.photo_id " + " AND photo_grp.grp_id = 3 AND " + " photo.photo_id = photo_grp.photo_id ";

			rs = stmt.executeQuery(query2); // + " LIMIT 10 "); //
			if (rs != null) {
				while (rs.next()) {
					intCount++;
					PersonRanking pr = new PersonRanking();
					query1 = " SELECT DISTINCT " + " COUNT(person.person_id) as perCount " + " FROM photo, photo_grp, person_photo, person "
							+ " WHERE " + " person_photo.person_id = person.person_id AND " + " person_photo.photo_id = photo.photo_id "
							+ " AND photo_grp.grp_id = 3 AND " + " photo.photo_id = photo_grp.photo_id " + " AND person.person_id = "
							+ rs.getInt("person.person_id");
					rs1 = stmt1.executeQuery(query1);
					if (rs1 != null) {
						if (rs1.next()) {
							pr.setNumPhotos(rs1.getInt("perCount"));
						}
					}
					pr.setFirstName(rs.getString("person.fname"));
					pr.setMiddleName(rs.getString("person.mname"));
					pr.setLastName(rs.getString("person.lname"));
					pr.setEmail(rs.getString("person.email"));
					pr.setPersonId(rs.getInt("person.person_id"));
					PersonFactory.populateFlags(pr);
					coll.add(pr);
				}
			}
			setPRColumns(coll);
			PhotoFactory.closeConn(rs, stmt, conn);
			PhotoFactory.closeConn(rs1, stmt1, null);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getRankings()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
			PhotoFactory.closeConn(rs1, stmt1, null);
		}

		return coll;
	}

	private static void setPRColumns(ArrayList coll) {
		for (int i = 0; i < coll.size(); i++) {
			PersonRanking pr = new PersonRanking();
			pr = (PersonRanking) coll.get(i);
			pr.setPageOne(coll.size() / 3);
			pr.setPageTwo((coll.size() / 3) * 2);
			coll.set(i, pr);
		}
		Collections.sort(coll);
	}

	public static void populatePeopleSearchForm(PeopleSearchForm psf, User user) {
		psf.setLetters(getLetters(user));
		psf.setAllLetters(getAllLetters());
		psf.setPeopleRanking((ArrayList) getPeopleSearch(psf, user));
	}

	private static ArrayList getAllLetters() {
		ArrayList coll = new ArrayList();
		char charA = 'A';
		for (int i = 0; i < 26; i++) {
			coll.add((char) (charA + i) + "");
		}
		return coll;
	}

	private static ArrayList getLetters(User user) {
		ArrayList coll = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String query = " SELECT DISTINCT LEFT(person.LName,1) AS letter " + " FROM person_photo, photo, person, photo_grp " + " WHERE "
				+ " person_photo.person_id = person.person_id " + PhotoFactory.getUserWhere(user) + " person_photo.photo_id = photo.photo_id AND "
				+ " photo.photo_id = photo_grp.photo_id ";
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					coll.add(rs.getString("letter"));
				}
			}

			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getLetters(User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		Collections.sort(coll);
		return coll;
	}

	private static Collection getPeopleSearch(PeopleSearchForm psf, User user) {
		ArrayList coll = new ArrayList();

		int intCount = 0;
		boolean blContComplete = true;
		boolean blContNew = false;
		boolean blContPrivate = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query1 = new String();
			String querySearch = new String();

			if ((psf.getSearchString() != null) && (!psf.getSearchString().equalsIgnoreCase(""))) {
				if ((psf.getAction() != null) && (psf.getAction().equalsIgnoreCase(Constants.ACTION_FIRSTNAME))) {

					querySearch = " person.fname like '" + psf.getSearchString() + "%'";
				} else {
					querySearch = " person.lname like '" + psf.getSearchString() + "%'";
				}
			} else {
				querySearch = " person.lname like 'A%'";
			}

			query1 = " SELECT DISTINCT " + " person.fname, " + " person.mname, " + " person.lname, " + " person.email, " + " person.person_id "
					+ " FROM photo, photo_grp, person_photo, person " + " WHERE " + " person_photo.person_id = person.person_id AND "
					+ " person_photo.photo_id = photo.photo_id " + PhotoFactory.getUserWhere(user) + " photo.photo_id = photo_grp.photo_id AND "
					+ querySearch;

			rs = stmt.executeQuery(query1); // + " LIMIT 10 "); //
			if (rs != null) {
				while (rs.next()) {
					intCount++;
					PersonRanking pr = new PersonRanking();
					pr.setFirstName(rs.getString("person.fname"));
					pr.setMiddleName(rs.getString("person.mname"));
					pr.setLastName(rs.getString("person.lname"));
					pr.setEmail(rs.getString("person.email"));
					pr.setPersonId(rs.getInt("person.person_id"));
					PersonFactory.populateFlags(pr);
					coll.add(pr);
				}
			}
			setPRColumns(coll);
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPeopleSearch(PeopleSearchForm psf,User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return coll;
	}

	private static void populateFlags(PersonRanking pr) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String queryBase = " SELECT DISTINCT photo.photo_id FROM photo JOIN person_photo USING(photo_id) WHERE person_id = ? ";
			String contComplete = new String(queryBase + " AND photo.all_people = 'No' ");
			String contNew = new String(queryBase + " AND (NOW() - photo.tstamp ) < 15000000 ");
			String contPrivate = new String(queryBase + " AND private = 'Yes' ");
			ps = conn.prepareStatement(contComplete);
			ps.setInt(1, pr.getPersonId());
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					pr.setComplete(false);
				}
			}
			ps = conn.prepareStatement(contNew);
			ps.setInt(1, pr.getPersonId());
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					pr.setNewPhotos(true);
				}
			}
			ps = conn.prepareStatement(contPrivate);
			ps.setInt(1, pr.getPersonId());
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					pr.setPriv(true);
				}
			}

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateFlags(PersonRanking pr)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static Person getPersonByPersonId(int intPersonId) {
		Person person = new Person();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = new String();
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			query = " SELECT " + " person.person_id, " + " person.FName, " + " person.MName, " + " person.LName, " + " person.email " + " FROM "
					+ " person " + " WHERE " + " person.person_id = " + intPersonId + " " + " ORDER BY person.LName, person.FName ASC ";

			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					person.setPersonId(rs.getInt("person.person_id"));
					person.setFName(rs.getString("person.fname"));
					person.setMName(rs.getString("person.mname"));
					person.setLName(rs.getString("person.lname"));
					person.setEmail(rs.getString("person.email"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPersonByPersonId(int intPersonId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return person;
	}

	public static void populatePersonForm(PersonForm pform) {
		if (pform.getPersonId() < 1) {
			return;
		}
		Person person = getPersonByPersonId(pform.getPersonId());
		pform.setEmail(person.getEmail());
		pform.setFirstName(person.getFName());
		pform.setMiddleName(person.getMName());
		pform.setLastName(person.getLName());

	}

	public static void setPersonForm(PersonForm pf) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int personId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();

			String query = new String();

			if (pf.getPersonId() < 1) {
				query = "INSERT INTO person " + " (fname, " + " mname, " + " lname, " + " email, " + " class_year ) " + " VALUES " + " (?,?,?,?,NULL) ";
			} else {
				query = "UPDATE person " + " SET " + " fname = ?, " + " mname = ?, " + " lname = ?, " + " email = ?, " + " class_year = NULL "
						+ " WHERE " + " person_id = " + pf.getPersonId();
			}

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, pf.getFirstName());
			ps.setString(2, pf.getMiddleName());
			ps.setString(3, pf.getLastName());
			ps.setString(4, pf.getEmail());

			ps.executeUpdate();

			setPersonId(pf);

			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setPersonForm(PersonForm pf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

	}

	public static Collection getPeopleByPhotoId(int intPhotoId) {
		ArrayList coll = new ArrayList(10);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = new String();
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			query = " SELECT " + " person.person_id, " + " person.FName, " + " person.MName, " + " person.LName " + " FROM "
					+ " person, person_photo " + " WHERE " + " person.person_id = person_photo.person_id AND " + " person_photo.photo_id = "
					+ intPhotoId + " " + " ORDER BY person.LName, person.FName ASC ";

			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Person person = new Person(rs.getInt("person.person_id"), rs.getString("person.fname"), rs.getString("person.mname"),
							rs.getString("person.lname"));
					coll.add(person);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPeopleByPhotoId(int intPhotoId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getPhotographers() {
		ArrayList coll = new ArrayList(10);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = new String();
		coll.add(new Person(0, "", "", "None"));
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			query = " SELECT DISTINCT " + " person.person_id, " + " person.FName, " + " person.MName, " + " person.LName " + " FROM "
					+ " person, photo " + " WHERE " + " person.person_id = photo.photographer_id " + " ORDER BY person.LName, person.FName ASC ";

			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Person person = new Person(rs.getInt("person.person_id"), rs.getString("person.fname"), rs.getString("person.mname"),
							rs.getString("person.lname"));
					coll.add(person);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPhotographers()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getRegistrationPeople() {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query = "SELECT " + " person.person_id, person.fname, " + " person.mname, person.lname " + " FROM person "
					+ " LEFT JOIN user USING (person_id) " + " WHERE user.person_id IS NULL " + " ORDER BY person.lname, person.fname ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					coll.add(new Person(rs.getInt("person.person_id"), rs.getString("person.fname"), rs.getString("person.mname"), rs
							.getString("person.lname")));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getRegistrationPeople()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getAllPeople(User user) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query = "SELECT DISTINCT " + " person.person_id, person.fname, " + " person.mname, person.lname "
					+ " FROM person, person_photo, photo_grp " + " WHERE " + " person.person_id = person_photo.person_id "
					+ PhotoFactory.getUserWhere(user) + " person_photo.photo_id = photo_grp.photo_id " + " ORDER BY person.lname, person.fname ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					coll.add(new Person(rs.getInt("person.person_id"), rs.getString("person.fname"), rs.getString("person.mname"), rs
							.getString("person.lname")));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllPeople(User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getRelatedPeople(Person p, User user) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query = "SELECT DISTINCT " + " t1.person_id, t2.person_id, " + " person.lname, person.mname, person.fname " + " FROM "
					+ " person_photo AS t1, person_photo AS t2,  " + " person, photo_grp " + " WHERE " + " t1.photo_id = t2.photo_id AND "
					+ " t2.person_id = person.person_id " + PhotoFactory.getUserWhere(user) + " t1.photo_id = photo_grp.photo_id AND "
					+ " t1.person_id = " + p.getPersonId() + " ORDER BY person.lname, person.fname";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					if (rs.getInt("t2.person_id") != p.getPersonId()) {
						coll.add(new Person(rs.getInt("t2.person_id"), rs.getString("person.fname"), rs.getString("person.mname"), rs
								.getString("person.lname")));
					}
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getRelatedPeople(Person p, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getAllPeople(Collection people, int whereId, String strAction) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query = "SELECT " + " person.person_id, person.fname, " + " person.mname, person.lname, " + " person.email " + " FROM " + " person ";
			if (strAction.equalsIgnoreCase(Constants.CATEGORY_ID_KEY)) {
				query = query + ", person_category " + "WHERE person_category.person_id = person.person_id AND " + " person_category.category_id = "
						+ whereId;
			} else if (strAction.equalsIgnoreCase(Constants.HEADING_ID_KEY)) {
				query = query + ", person_heading " + "WHERE person_heading.person_id = person.person_id AND " + " person_heading.heading_id = "
						+ whereId;
			}
			query = query + " ORDER BY person.LName, person.FName ASC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Person person = new Person(rs.getInt("person_id"), rs.getString("fname"), rs.getString("mname"), rs.getString("lname"));
					boolean addPerson = true;
					for (int i = 0; i < people.size(); i++) {
						if (((Person) ((ArrayList) people).get(i)).getPersonId() == person.getPersonId()) {
							addPerson = false;
							break;
						}
					}
					if (addPerson) {
						coll.add(person);
					}
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllPeople(Collection people,int whereId,String strAction)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static void addPersonPhoto(int photoId, int personId) {
		Connection conn = null;
		PreparedStatement ps = null;
		if ((photoId == 0) || (personId == 0)) {
			return;
		}
		try {
			conn = ConnectionFactory.getConnection();

			String queryDelete = "DELETE FROM person_photo " + " WHERE (photo_id = ? AND person_id = ? ) ";
			ps = conn.prepareStatement(queryDelete);
			ps.setInt(1, photoId);
			ps.setInt(2, personId);
			ps.executeUpdate();

			String query = "INSERT INTO person_photo " + " (photo_id, " + " person_id ) " + " VALUES " + " (?,?) ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);
			ps.setInt(2, personId);

			ps.executeUpdate();

			query = "UPDATE photo SET tstamp = NULL WHERE photo_id = ? ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);

			ps.executeUpdate();

			int grpId = UserFactory.getUserGroupByPersonId(personId);
			if (grpId > 0) {
				addGroupPhoto(photoId, grpId);
			}

			setPeople();

			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in addPersonPhoto" + "(int photoId, int personId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}
	}

	public static void addGroupPhoto(HeadingForm hf, int groupId, User user) {
		ArrayList coll = new ArrayList();
		ArrayList collP = new ArrayList();
		PhotoFactory.getPhotosByWhere("", "", " photo.heading_id = " + hf.getHeadingId(), null, "", coll, collP, 0, hf.getHeadingId(),
				Constants.HEADING_ID_KEY, user, Constants.PER_PAGE);
		for (int i = 0; i < coll.size(); i++) {
			addGroupPhoto(((PhotoDetails) coll.get(i)).getPhotoId(), groupId);
		}
		OtherFactory.populateHeadingSelect(hf);
	}

	public static void addGroupPhoto(int photoId, int groupId) {
		Connection conn = null;
		PreparedStatement ps = null;
		if ((photoId == 0) || (groupId == 0)) {
			return;
		}
		try {
			conn = ConnectionFactory.getConnection();

			String queryDeleteGroup = "DELETE FROM photo_grp " + " WHERE (photo_id = ? AND grp_id = ? ) ";
			ps = conn.prepareStatement(queryDeleteGroup);
			ps.setInt(1, photoId);
			ps.setInt(2, groupId);
			ps.executeUpdate();

			String query = "INSERT INTO photo_grp " + " (photo_id, grp_id ) " + " VALUES (?,?) ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);
			ps.setInt(2, groupId);

			ps.executeUpdate();

			query = "UPDATE photo SET tstamp = NULL WHERE photo_id = ? ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);

			ps.executeUpdate();

			if (groupId == 3) {
				togglePrivateGroups(photoId, false);
			} else if (groupId == 2) {
				togglePrivateGroups(photoId, true);
			}
			setPeople();

			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in addGroupPhoto" + "(int photoId, int groupId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}
	}

	public static void togglePrivateGroups(int photoId, boolean makePrivate) {
		Connection conn = null;
		PreparedStatement psUP = null;
		PreparedStatement psDG = null;
		PreparedStatement psDIG = null;
		PreparedStatement psIG = null;

		try {
			conn = ConnectionFactory.getConnection();

			String queryUpdatePhoto = " UPDATE photo SET private = ? WHERE photo_id = ?";

			String queryDeleteGroup = "DELETE FROM photo_grp " + " WHERE (photo_id = ? AND grp_id = ? ) ";

			String queryInsertGroup = "INSERT INTO photo_grp (photo_id, grp_id ) VALUES (?,?) ";
			psUP = conn.prepareStatement(queryUpdatePhoto);
			psDG = conn.prepareStatement(queryDeleteGroup);
			psDIG = conn.prepareStatement(queryDeleteGroup);
			psIG = conn.prepareStatement(queryInsertGroup);

			if (makePrivate) {
				/*
				 * Update the photo setting private to YES. Same logic below for
				 * the reverse transformation
				 */
				psUP.setString(1, Constants.YES);
				psUP.setInt(2, photoId);
				/*
				 * Delete this photo from Public
				 */
				psDG.setInt(1, photoId);
				psDG.setInt(2, 3);
				/*
				 * Delete previous instances of Private (to prevent duplicate
				 * Entries in the photo_grp table)
				 */
				psDIG.setInt(1, photoId);
				psDIG.setInt(2, 2);
				/*
				 * Insert the photo as a member of private group
				 */
				psIG.setInt(1, photoId);
				psIG.setInt(2, 2);
			} else {
				psUP.setString(1, Constants.NO);
				psUP.setInt(2, photoId);

				psDG.setInt(1, photoId);
				psDG.setInt(2, 2);

				psDIG.setInt(1, photoId);
				psDIG.setInt(2, 3);

				psIG.setInt(1, photoId);
				psIG.setInt(2, 3);
			}
			psUP.executeUpdate();
			psDG.executeUpdate();
			psDIG.executeUpdate();
			psIG.executeUpdate();
			PhotoFactory.closeConn(null, psUP, conn);
			PhotoFactory.closeConn(null, psDG, conn);
			PhotoFactory.closeConn(null, psDIG, conn);
			PhotoFactory.closeConn(null, psIG, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in togglePrivateGroups" + "(int photoId, boolean makePrivate)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, psUP, conn);
			PhotoFactory.closeConn(null, psDG, null);
			PhotoFactory.closeConn(null, psIG, null);
		}

	}

	public static void removePersonPhoto(int photoId, int personId) {
		Connection conn = null;
		PreparedStatement ps = null;
		int grpId = UserFactory.getUserGroupByPersonId(personId);

		try {
			conn = ConnectionFactory.getConnection();

			String query = "DELETE FROM person_photo " + " WHERE (photo_id = ? AND " + " person_id = ? ) ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);
			ps.setInt(2, personId);

			ps.executeUpdate();

			query = "UPDATE photo SET tstamp = NULL WHERE photo_id = ? ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);

			ps.executeUpdate();

			if (grpId > 0) {
				String queryDeletePersonGrp = " DELETE FROM photo_grp WHERE photo_id = ? AND grp_id = ?";
				ps = conn.prepareStatement(queryDeletePersonGrp);
				ps.setInt(1, photoId);
				ps.setInt(2, grpId);
				ps.executeUpdate();
			}

			setPeople();

			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in removePersonPhoto" + "(int photoId, int personId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}

	}

	public static void removeGroupPhoto(HeadingForm hf, int groupId, User user) {
		ArrayList coll = new ArrayList();
		ArrayList collP = new ArrayList();
		PhotoFactory.getPhotosByWhere("", "", " photo.heading_id = " + hf.getHeadingId(), null, "", coll, collP, 0, hf.getHeadingId(),
				Constants.HEADING_ID_KEY, user, Constants.PER_PAGE);
		for (int i = 0; i < coll.size(); i++) {
			removeGroupPhoto(((PhotoDetails) coll.get(i)).getPhotoId(), groupId);
		}
		OtherFactory.populateHeadingSelect(hf);
	}

	public static void removeGroupPhoto(int photoId, int groupId) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionFactory.getConnection();

			String query = "DELETE FROM photo_grp " + " WHERE (photo_id = ? AND " + " grp_id = ? ) ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);
			ps.setInt(2, groupId);

			ps.executeUpdate();

			query = "UPDATE photo SET tstamp = NULL WHERE photo_id = ? ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, photoId);

			ps.executeUpdate();

			if (groupId == 3) {
				togglePrivateGroups(photoId, true);
			} else if (groupId == 2) {
				togglePrivateGroups(photoId, false);
			}

			setPeople();

			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in removeGroupPhoto" + "(int photoId, int groupId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}

	}

	private static void setPeople() {
		String queryDeleteHeading = " DELETE FROM person_heading ";

		String queryUpdateHeading = " INSERT INTO person_heading (person_id,heading_id) " + " SELECT DISTINCT person.person_id,photo.heading_id "
				+ " FROM " + " person,person_photo,photo " + " WHERE "
				+ " (photo.photo_id = person_photo.photo_id AND person_photo.person_id = person.person_id ) " + " ORDER BY "
				+ " person.person_id,photo.heading_id ";

		String queryDeleteCategory = " DELETE FROM person_category ";

		String queryUpdateCategory = " INSERT INTO person_category (person_id,category_id) " + " SELECT DISTINCT person.person_id,photo.category_id "
				+ " FROM " + " person,person_photo,photo " + " WHERE "
				+ " (photo.photo_id = person_photo.photo_id AND person_photo.person_id = person.person_id ) " + " ORDER BY "
				+ " person.person_id,photo.category_id ";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int topicId = 0;
		try {
			conn = ConnectionFactory.getConnection();

			ps = conn.prepareStatement(queryDeleteHeading);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryUpdateHeading);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryDeleteCategory);
			ps.executeUpdate();

			ps = conn.prepareStatement(queryUpdateCategory);
			ps.executeUpdate();

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setPeople()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}

	}

	private static void setPersonId(PersonForm pform) {
		if (pform.getPersonId() > 0) {
			return;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = new String();
		try {
			conn = ConnectionFactory.getConnection();
			query = " SELECT " + " person_id " + " FROM " + " person " + " WHERE " + " (fname = ? AND " + " mname = ? AND " + " lname = ? AND "
					+ " email = ?) " + " ORDER BY person_id DESC ";
			ps = conn.prepareStatement(query);

			ps.setString(1, pform.getFirstName());
			ps.setString(2, pform.getMiddleName());
			ps.setString(3, pform.getLastName());
			ps.setString(4, pform.getEmail());

			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					pform.setPersonId(rs.getInt("person_id"));
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setPersonId(PersonForm pform)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static void populateAdminEmails(ArrayList toEmail) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = new String();
		try {
			conn = ConnectionFactory.getConnection();
			query = " SELECT " + " email " + " FROM " + " person, user " + " WHERE " + " person.person_id = user.person_id AND "
					+ " user.can_update = 'Yes' AND " + " user.active = 'Yes' ";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					toEmail.add(rs.getString("email"));
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateAdminEmails(ArrayList toEmail)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

}