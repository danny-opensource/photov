/*
 * Created on Jun 30, 2004
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.wgbv.photov.action.Constants;
import net.wgbv.photov.form.GroupForm;
import net.wgbv.photov.form.PhotoForm;
import net.wgbv.photov.form.RegistrationForm;
import net.wgbv.photov.form.UserForm;
import net.wgbv.photov.objects.Group;
import net.wgbv.photov.objects.Person;
import net.wgbv.photov.objects.User;

import org.apache.log4j.Logger;
/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserFactory {

	static Logger l = Logger.getLogger(UserFactory.class);

	public static User getUser(String strUsername, String strPassword) {
		int intDUserId = 0;
		int intDPersonId = 0;
		int intDUserGrpId = 0;
		int intDUserLevel = 0;
		String strDUsername = new String();
		String strDPassword = new String();
		String strEPassword = new String();
		boolean dActive = false;
		String strDCanUpdate = new String();

		User user = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT "
					+ " user_id, person_id, grp_id, username, password, "
					+ " active, can_update, PASSWORD('"
					+ strPassword
					+ "') AS pswd "
					+ " FROM user "
					+ " WHERE username = '"
					+ strUsername
					+ "'";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					intDUserId = rs.getInt("user_id");
					intDPersonId = rs.getInt("person_id");
					intDUserGrpId = rs.getInt("grp_id");
					strDUsername = rs.getString("username");
					strDPassword = rs.getString("password");
					strEPassword = rs.getString("pswd");
					dActive =
						(rs.getString("active").equalsIgnoreCase(Constants.YES)
							? true
							: false);
					strDCanUpdate = rs.getString("can_update");
				}
			}
			if ((strDUsername.equals(strUsername))
				&& (strDPassword.substring(0,15).equals(strEPassword.substring(0,15)))) {
				// User Logged In
				user =
					new User(
						intDUserId,
						intDPersonId,
						intDUserGrpId,
						strDUsername,
						strDPassword,
						dActive,
						(strDCanUpdate.equalsIgnoreCase(Constants.YES)
							? true
							: false));
				user.setGroups(getUserGroups(user));
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in getUser(String strUsername, String strPassword)");
			l.error(sqle);
			user = null;
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return user;
	}
	public static void toggleActive(UserForm uf) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query = " SELECT active FROM user WHERE user_id = ? ";
			String queryUpdate =
				" UPDATE user SET active = ? WHERE user_id = ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, uf.getUserId());
			rs = ps.executeQuery();
			psUpdate = conn.prepareStatement(queryUpdate);

			if (rs != null) {
				if (rs.next()) {
					String str = rs.getString("active");
					boolean isEmail = false;
					if (str.equalsIgnoreCase(Constants.YES)) {
						psUpdate.setString(1, Constants.NO);
					} else {
						psUpdate.setString(1, Constants.YES);
						isEmail = true;
					}
					psUpdate.setInt(2, uf.getUserId());
					psUpdate.executeUpdate();
					if (isEmail) {
						User u = new User();
						u = getUserFromUserForm(uf);
						if (u.getEmail().equalsIgnoreCase("")) {
							l.error(" Empty Email for user:" + u.getUsername());
							uf.setActive(false);
							toggleActive(uf);
						} else {
							l.info(
								" Sending activation email to:" + u.getEmail());
							EmailFactory.sendActivateEmail(u);
						}
					}
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
			PhotoFactory.closeConn(null, psUpdate, null);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in toggleActive(UserForm uf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
			PhotoFactory.closeConn(null, psUpdate, null);
		}
	}

	public static void toggleUpdate(UserForm uf) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query = " SELECT can_update FROM user WHERE user_id = ? ";
			String queryUpdate =
				" UPDATE user SET can_update = ? WHERE user_id = ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, uf.getUserId());
			rs = ps.executeQuery();
			psUpdate = conn.prepareStatement(queryUpdate);

			if (rs != null) {
				if (rs.next()) {
					String str = new String();
					str = rs.getString("can_update");
					if (str.equalsIgnoreCase(Constants.YES)) {
						psUpdate.setString(1, Constants.NO);
					} else {
						psUpdate.setString(1, Constants.YES);
					}
					psUpdate.setInt(2, uf.getUserId());
					psUpdate.executeUpdate();
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in toggleUpdate(UserForm uf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static Collection getAllUsers() {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT "
					+ " user_id, user.person_id, grp_id, username, password, "
					+ " active, can_update "
					+ " FROM user JOIN person USING(person_id) "
					+ " ORDER BY person.lname, person.fname ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					User u = new User();
					u.setUserId(rs.getInt("user_id"));
					u.setPersonId(rs.getInt("person_id"));
					u.setUserGroupId(rs.getInt("grp_id"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setActive(
						(rs.getString("active").equalsIgnoreCase(Constants.YES)
							? true
							: false));
					u.setCanUpdate(
						(rs
							.getString("can_update")
							.equalsIgnoreCase(Constants.YES)
							? true
							: false));
					populateUserPerson(u);
					coll.add(u);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllUsers()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return coll;
	}

	public static boolean isUsernameExist(String uname) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query = " SELECT user_id FROM user WHERE username = ? ";

			ps = conn.prepareStatement(query);
			ps.setString(1, uname);

			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					return true;
				}
			}

			String queryGroups =
				" SELECT grp_name FROM grp WHERE grp_name = ? ";

			ps = conn.prepareStatement(queryGroups);
			ps.setString(1, uname);

			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					return true;
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in isUsernameExist(String uname)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}

		return false;
	}

	private static void populateUserPerson(User u) {
		Person p = PersonFactory.getPersonByPersonId(u.getPersonId());
		u.setFName(p.getFName());
		u.setMName(p.getMName());
		u.setLName(p.getLName());
		u.setEmail(p.getEmail());
		u.setGroupName(getGroup(u.getUserGroupId()).getGroupName());
	}

	public static void populateGroup(PhotoForm pform, User user) {
		ArrayList temp = getPhotoGroups(pform.getPhotoId());
		pform.setGroups(temp);
		pform.setAllGroups(getGroups(temp));

	}

	public static int getUserGroupByPersonId(int personId) {
		ArrayList coll = new ArrayList();
		int grpId = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ " grp_id "
					+ " FROM user "
					+ " WHERE person_id = '"
					+ personId
					+ "'";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					grpId = rs.getInt("grp_id");
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getUserGroupByPersonId(int personId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return grpId;
	}
	public static Group getGroup(int groupId) {
		Group g = new Group();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ " grp_id, grp_name, grp_desc "
					+ " FROM grp "
					+ " WHERE grp_id = "
					+ groupId;
			rs = stmt.executeQuery(query);
			if (rs != null) {
				if (rs.next()) {
					g =
						new Group(
							rs.getInt("grp_id"),
							rs.getString("grp_name"),
							rs.getString("grp_desc"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getGroup(int groupId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return g;
	}
	public static ArrayList getGroupsByUserId(int intUserId) {
		ArrayList coll = new ArrayList();
		int intIndex = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ " grp_id "
					+ " FROM user_grp "
					+ " WHERE user_id = '"
					+ intUserId
					+ "'";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					coll.add(rs.getString("grp_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getGroupsByUserId(int intUserId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return coll;
	}
	public static ArrayList getGroups(ArrayList grps) {
		ArrayList coll = new ArrayList();
		int intIndex = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT grp_id, grp_name, grp_desc FROM grp ORDER BY grp_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Group group =
						new Group(
							rs.getInt("grp_id"),
							rs.getString("grp_name"),
							rs.getString("grp_desc"));
					boolean addGrp = true;
					for (int i = 0; i < grps.size(); i++) {
						if (((Group) ((ArrayList) grps).get(i)).getGroupId()
							== group.getGroupId()) {
							addGrp = false;
							break;
						}
					}
					if (addGrp) {
						coll.add(group);
					}
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getGroups(ArrayList grps)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		Collections.sort(coll);
		return coll;
	}
	public static ArrayList getAllGroups() {
		ArrayList coll = new ArrayList();
		int intIndex = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT grp_id, grp_name, grp_desc FROM grp ORDER BY grp_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Group group =
						new Group(
							rs.getInt("grp_id"),
							rs.getString("grp_name"),
							rs.getString("grp_desc"));
					coll.add(group);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllGroups()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		Collections.sort(coll);
		return coll;
	}
	public static ArrayList getPhotoGroups(int photoId) {
		ArrayList coll = new ArrayList();
		int intIndex = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ " grp.grp_id, grp.grp_name, grp.grp_desc "
					+ " FROM grp, photo_grp "
					+ " WHERE "
					+ " grp.grp_id = photo_grp.grp_id AND "
					+ " photo_grp.photo_id = '"
					+ photoId
					+ "'";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					coll.add(
						new Group(
							rs.getInt("grp.grp_id"),
							rs.getString("grp.grp_name"),
							rs.getString("grp.grp_desc")));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPhotoGroups(int photoId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		Collections.sort(coll);
		return coll;
	}

	public static ArrayList getHeadingGroups(int headingId) {
		ArrayList coll = new ArrayList();
		int intIndex = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ " grp.grp_id, grp.grp_name, grp.grp_desc "
					+ " FROM grp, photo_grp, photo "
					+ " WHERE "
					+ " grp.grp_id = photo_grp.grp_id AND "
					+ " photo_grp.photo_id = photo.photo_id AND "
					+ " photo.heading_id = "
					+ headingId;
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					coll.add(
						new Group(
							rs.getInt("grp.grp_id"),
							rs.getString("grp.grp_name"),
							rs.getString("grp.grp_desc")));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getHeadingGroups(int headingId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		Collections.sort(coll);
		return coll;
	}

	public static ArrayList getUserGroups(User user) {
		ArrayList coll = new ArrayList();
		int intIndex = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ " grp.grp_id, grp.grp_name, grp.grp_desc "
					+ " FROM grp, user_grp "
					+ " WHERE "
					+ " grp.grp_id = user_grp.grp_id AND "
					+ " user_grp.user_id = '"
					+ user.getUserId()
					+ "'";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					coll.add(
						new Group(
							rs.getInt("grp.grp_id"),
							rs.getString("grp.grp_name"),
							rs.getString("grp.grp_desc")));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getUserGroups(User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return coll;
	}

	public static void populateGroupForm(GroupForm gf) {
		if (gf.getGroupId() < 1) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ "grp_id, grp_name, grp_desc "
					+ " FROM grp "
					+ " WHERE grp_id = "
					+ gf.getGroupId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					gf.setGroupId(rs.getInt("grp_id"));
					gf.setGroupName(rs.getString("grp_name"));
					gf.setGroupDesc(rs.getString("grp_desc"));
				}
			}

			populateGroupSelect(gf);
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateGroupForm(GroupForm gf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}
	public static void setGroupForm(GroupForm gf) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int categoryId = 0;
		try {
			conn = ConnectionFactory.getConnection();

			String query = new String();

			if (gf.getGroupId() < 1) {
				query =
					"INSERT INTO grp "
						+ " (grp_name, "
						+ " grp_desc ) "
						+ " VALUES "
						+ " (?,?) ";

			} else {
				query =
					"UPDATE grp "
						+ " SET "
						+ " grp_name = ?, "
						+ " grp_desc = ? "
						+ " WHERE "
						+ " grp_id = "
						+ gf.getGroupId();
			}

			ps = conn.prepareStatement(query);

			ps.setString(1, gf.getGroupName());
			ps.setString(2, gf.getGroupDesc());

			ps.executeUpdate();

			getGroupId(gf);

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setGroupForm(GroupForm gf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}
	private static void getGroupId(GroupForm gf) {
		if (gf.getGroupId() > 0)
			return;
		else
			gf.setGroupId(getGroupId(gf.getGroupName()));
	}

	private static int getGroupId(String groupname) {

		if (groupname.equalsIgnoreCase("")) {
			return 0;
		}
		int groupId = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " grp_id "
					+ " FROM "
					+ " grp "
					+ " WHERE "
					+ " grp_name = '"
					+ groupname
					+ "'"
					+ " ORDER BY grp_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					groupId = rs.getInt("grp_id");
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getGroupId(String groupname)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return groupId;
	}
	public static void populateUserForm(UserForm uf, User user) {
		if (uf.getUserId() < 1) {
			populateUserSelect(uf, user);
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT DISTINCT "
					+ "user_id, username, password, grp_id, person_id, active, can_update "
					+ " FROM user "
					+ " WHERE user_id = "
					+ uf.getUserId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					uf.setUserId(rs.getInt("user_id"));
					uf.setPersonId(rs.getInt("person_id"));
					uf.setGroupId(rs.getInt("grp_id"));
					uf.setUsername(rs.getString("username"));
					uf.setPassword(rs.getString("password"));
					uf.setPassword2(rs.getString("password"));
					uf.setActive(
						(rs.getString("active").equalsIgnoreCase("Yes")
							? true
							: false));
					uf.setUpdate(
						(rs.getString("can_update").equalsIgnoreCase("Yes")
							? true
							: false));
					uf.setPerson(
						OtherFactory.getPersonByPersonId(uf.getPersonId()));
					uf.setEmail(uf.getPerson().getEmail());
				}
			}
			populateUserSelect(uf, user);
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in populateUserForm(UserForm uf, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}
	public static void setUserForm(UserForm uf, User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int categoryId = 0;
		try {
			conn = ConnectionFactory.getConnection();

			String query = new String();

			if (uf.getUserId() < 1) {
				query =
					"INSERT INTO user "
						+ " (person_id, "
						+ " grp_id, "
						+ " username, "
						+ " password, "
						+ " active, "
						+ " can_update) "
						+ " VALUES "
						+ " (?,?,?,PASSWORD(?),?,?) ";

			} else {
				query =
					"UPDATE user "
						+ " SET "
						+ " person_id = ?, "
						+ " grp_id = ?, "
						+ " username = ?, "
						+ " password = PASSWORD(?), "
						+ " active = ?, "
						+ " can_update = ? "
						+ " WHERE "
						+ " user_id = "
						+ uf.getUserId();
			}

			ps = conn.prepareStatement(query);

			ps.setInt(1, uf.getPersonId());
			ps.setInt(2, uf.getGroupId());
			ps.setString(3, uf.getUsername());
			ps.setString(4, uf.getPassword());
			ps.setString(5, (uf.getActive() ? Constants.YES : Constants.NO));
			ps.setString(6, (uf.getUpdate() ? Constants.YES : Constants.NO));

			ps.executeUpdate();

			getUserId(uf);
			populateUserSelect(uf, user);
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setUserForm(UserForm uf, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static void setRegistrationForm(RegistrationForm rf) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int categoryId = 0;
		try {
			conn = ConnectionFactory.getConnection();

			String query = new String();

			if (rf.getUserId() < 1) {
				rf.setGroupId(
					createUserGroup(rf.getPersonId(), rf.getUsername()));
				query =
					"INSERT INTO user "
						+ " (person_id, "
						+ " grp_id, "
						+ " username, "
						+ " password, "
						+ " active, "
						+ " can_update) "
						+ " VALUES "
						+ " (?,?,?,PASSWORD(?),'No','No') ";

			} else {
				query =
					"UPDATE user "
						+ " SET "
						+ " person_id = ?, "
						+ " grp_id = ?, "
						+ " username = ?, "
						+ " password = PASSWORD(?), "
						+ " active = 'No', "
						+ " can_update = 'No' "
						+ " WHERE "
						+ " user_id = "
						+ rf.getUserId();
			}

			ps = conn.prepareStatement(query);

			ps.setInt(1, rf.getPersonId());
			ps.setInt(2, rf.getGroupId());
			ps.setString(3, rf.getUsername());
			ps.setString(4, rf.getPassword());

			ps.executeUpdate();

			query =
				" UPDATE person "
					+ " SET email = ? WHERE person_id = "
					+ rf.getPersonId();

			ps = conn.prepareStatement(query);
			ps.setString(1, rf.getEmail());

			ps.executeUpdate();

			getUserId(rf);
			populateUserSelect(rf);
			addUserGroup(rf.getUserId(), 3);
			EmailFactory.sendRegistrationEmail(getUserFromRegistrationForm(rf));
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in setRegistrationForm(RegistrationForm rf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static User getUserFromRegistrationForm(RegistrationForm rf) {
		User u = new User();
		u.setEmail(rf.getEmail());
		u.setUserGroupId(rf.getGroupId());
		u.setPersonId(rf.getPersonId());
		u.setUserId(rf.getUserId());
		u.setUsername(rf.getUsername());
		return u;
	}
	public static User getUserFromUserForm(UserForm uf) {
		User u = new User();
		u.setEmail(uf.getEmail());
		u.setUserGroupId(uf.getGroupId());
		u.setPersonId(uf.getPersonId());
		u.setUserId(uf.getUserId());
		u.setUsername(uf.getUsername());
		return u;
	}

	private static void getUserId(UserForm uf) {
		if ((uf.getUserId() > 0) || (uf.getUsername().equalsIgnoreCase(""))) {
			if (uf.getPersonId() > 0) {
				uf.setPerson(
					OtherFactory.getPersonByPersonId(uf.getPersonId()));
			}
			return;
		}
		uf.setUserId(getUserId(uf.getUsername()));

	}

	private static void getUserId(RegistrationForm rf) {
		if ((rf.getUserId() > 0) || (rf.getUsername().equalsIgnoreCase(""))) {
			return;
		}
		rf.setUserId(getUserId(rf.getUsername()));
	}
	private static int getUserId(String username) {
		if (username.equalsIgnoreCase("")) {
			return 0;
		}
		int userId = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " user_id "
					+ " FROM "
					+ " user "
					+ " WHERE "
					+ " username = '"
					+ username
					+ "'"
					+ " ORDER BY user_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					userId = rs.getInt("user_id");
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getUserId(String username)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return userId;
	}
	private static void populateUserSelect(UserForm uf, User user) {
		uf.setPeople((ArrayList) PersonFactory.getAllPeople(user));
		if (user.getCanUpdate()) {
			uf.setGroups((ArrayList) UserFactory.getAllGroups());
			uf.setSelectedGroups(getUserGroups(getUserFromUserForm(uf)));
			uf.setAllGroups(getGroups(uf.getSelectedGroups()));
		} else if (uf.getUserId() == user.getUserId()) {
			ArrayList coll = new ArrayList();
			coll.add(getGroup(uf.getGroupId()));
			uf.setGroups(coll);
		}
	}
	private static void populateUserSelect(RegistrationForm rf) {
		ArrayList temp = new ArrayList();
		temp = (ArrayList) PersonFactory.getRegistrationPeople();
		if (rf != null) {
			temp.add(0, PersonFactory.getPersonByPersonId(rf.getPersonId()));
		}
		rf.setPeople(temp);
	}

	private static void populateGroupSelect(GroupForm gf) {
		gf.setSelectedUsers(getUsers(gf.getGroupId()));
		gf.setAllUsers(getUsers(gf.getSelectedUsers()));
	}

	public static ArrayList getUsers(ArrayList users) {
		ArrayList coll = new ArrayList();
		int intIndex = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query =
				" SELECT user_id, person_id, grp_id, username, password, active, can_update FROM user ";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					User u = new User();
					u.setUserId(rs.getInt("user_id"));
					u.setPersonId(rs.getInt("person_id"));
					u.setUserGroupId(rs.getInt("grp_id"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setActive(
						(rs.getString("active").equalsIgnoreCase(Constants.YES)
							? true
							: false));
					u.setCanUpdate(
						(rs
							.getString("can_update")
							.equalsIgnoreCase(Constants.YES)
							? true
							: false));

					boolean addUser = true;
					for (int i = 0; i < users.size(); i++) {
						if (((User) ((ArrayList) users).get(i)).getUserId()
							== u.getUserId()) {
							addUser = false;
							break;
						}
					}
					if (addUser) {
						coll.add(u);
					}
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getUsers(ArrayList users)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}

		return coll;
	}

	public static ArrayList getUsers(int groupId) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query = " SELECT user_id FROM user_grp WHERE grp_id = ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, groupId);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					User u = new User();
					u = getUser(rs.getInt("user_id"));
					coll.add(u);
				}
			}

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getUsers(int groupId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}

		return coll;
	}

	public static User getUser(int userId) {

		User u = new User();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query =
				" SELECT user_id, person_id, grp_id, username, password, active, can_update "
					+ " FROM user "
					+ " WHERE user_id = ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					u.setUserId(rs.getInt("user_id"));
					u.setPersonId(rs.getInt("person_id"));
					u.setUserGroupId(rs.getInt("grp_id"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setActive(
						(rs.getString("active").equalsIgnoreCase(Constants.YES)
							? true
							: false));
					u.setCanUpdate(
						(rs
							.getString("can_update")
							.equalsIgnoreCase(Constants.YES)
							? true
							: false));

				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getUser(int userId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
		return u;
	}

	public static int createUserGroup(int personId, String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int groupId = 0;
		try {
			conn = ConnectionFactory.getConnection();

			String query =
				"INSERT INTO grp "
					+ " (grp_name, "
					+ " grp_desc ) "
					+ " VALUES "
					+ " (?,?) ";

			ps = conn.prepareStatement(query);

			ps.setString(1, username);
			ps.setString(2, Constants.USER_GROUP_INDICATOR + username);

			ps.executeUpdate();
			groupId = getGroupId(username);

			query =
				"INSERT INTO "
					+ " photo_grp (photo_id,grp_id) "
					+ " SELECT "
					+ " photo.photo_id,"
					+ groupId
					+ " FROM "
					+ " photo JOIN person_photo "
					+ " USING (photo_id) "
					+ " WHERE person_id = "
					+ personId;
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in createUserGroup(int personId, String username)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
		return groupId;
	}

	public static void addUserGroup(UserForm uf, User user) {
		addUserGroup(uf.getUserId(), uf.getAllGroupId());
		populateUserForm(uf, user);

	}
	public static void addUserGroup(GroupForm gf) {
		addUserGroup(gf.getAllUserId(), gf.getGroupId());
		populateGroupForm(gf);

	}
	private static void addUserGroup(int userId, int groupId) {
		Connection conn = null;
		PreparedStatement ps = null;
		if ((userId == 0) || (groupId == 0)) {
			return;
		}
		try {
			conn = ConnectionFactory.getConnection();

			String query =
				"INSERT INTO user_grp "
					+ " (user_id, "
					+ " grp_id ) "
					+ " VALUES "
					+ " (?,?) ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, userId);
			ps.setInt(2, groupId);

			ps.executeUpdate();

			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in addUserGroup(int userId, int groupId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}
	}

	public static void removeUserGroup(UserForm uf, User user) {
		removeUserGroup(uf.getUserId(), uf.getSelectedGroupId());
		populateUserForm(uf, user);
	}

	public static void removeUserGroup(GroupForm gf) {
		removeUserGroup(gf.getSelectedUserId(), gf.getGroupId());
		populateGroupForm(gf);
	}

	public static void removeUserGroup(int userId, int groupId) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionFactory.getConnection();

			String query =
				"DELETE FROM user_grp "
					+ " WHERE (user_id = ? AND "
					+ " grp_id = ? ) ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, userId);
			ps.setInt(2, groupId);

			ps.executeUpdate();

			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in removeUserGroup(int userId, int groupId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}

	}

}
