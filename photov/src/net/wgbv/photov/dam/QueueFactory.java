/*
 * Created on Aug 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.dam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.wgbv.photov.action.Constants;
import net.wgbv.photov.form.PhotoChangeForm;
import net.wgbv.photov.objects.Page;
import net.wgbv.photov.objects.PageNumber;
import net.wgbv.photov.objects.Person;
import net.wgbv.photov.objects.User;

import org.apache.log4j.Logger;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QueueFactory {

	static Logger l = Logger.getLogger(PhotoFactory.class);
	public static void getNewPhotoQueue(
		Page page,
		ArrayList collPhotod,
		ArrayList collPage,
		User user) {
		PhotoFactory.getPhotosByWhere(
			"",
			", photo_new_queue ",
			" photo.photo_id = photo_new_queue.photo_id ",
			" ORDER BY photo.photo_id DESC ",
			" LIMIT "
				+ (Constants.PER_PAGE * page.getCurrentPage() * 4)
				+ ", "
				+ Constants.PER_PAGE * 4,
			collPhotod,
			collPage,
			page.getCurrentPage(),
			0,
			Constants.NEW_QUEUE_KEY,
			user,
			Constants.PER_PAGE * 4);
	}

	public static void removePhotoQueue(int photoId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionFactory.getConnection();
			l.info(" Removing photo from queue " + photoId);
			ps =
				conn.prepareStatement(
					" DELETE FROM photo_new_queue WHERE photo_id = ? ");
			ps.setInt(1, photoId);
			ps.executeUpdate();
			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in removeFromNewPhotoQueue(int photoId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}

	}

	public static void addPhotoQueue(int photoId) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionFactory.getConnection();

			ps =
				conn.prepareStatement(
					" INSERT INTO photo_new_queue (photo_id) VALUES (?) ");
			ps.setInt(1, photoId);
			ps.executeUpdate();
			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in addPhotoQueue(int photoId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}

	}

	public static void addActionQueue(PhotoChangeForm pcf, User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionFactory.getConnection();

			pcf.setApproved(false);
			pcf.setReviewed(false);

			String query =
				"INSERT INTO audit_queue "
					+ " ( user_id, photo_id, "
					+ " completed, reviewed, approved, "
					+ " reason) "
					+ " VALUES "
					+ " (?,?,?,"
					+ " ?,?,?) ";

			ps = conn.prepareStatement(query);
			if (user != null) {
				ps.setInt(1, user.getUserId());
			} else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, pcf.getPhotoId());
			ps.setString(3, (pcf.isCompleted() ? Constants.YES : Constants.NO));
			ps.setString(4, (pcf.isApproved() ? Constants.YES : Constants.NO));
			ps.setString(5, (pcf.isReviewed() ? Constants.YES : Constants.NO));
			ps.setString(6, pcf.getReason());

			ps.executeUpdate();
			EmailFactory.sendQueueEmail();
			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in addActionQueue(PhotoChangeForm pcf, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}
	}

	public static void setPhotoChangeForm(PhotoChangeForm pcf, User user) {
		if (user != null) {
			PersonFactory.togglePrivateGroups(pcf.getPhotoId(), true);
			pcf.setCompleted(true);
		}
		QueueFactory.addActionQueue(pcf, user);
	}

	public static int getIdKeyId(String idKey) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		try {
			conn = ConnectionFactory.getConnection();

			String query =
				" SELECT id_key_id FROM id_key WHERE id_key_name = ? ";

			ps = conn.prepareStatement(query);
			ps.setString(1, idKey);

			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					i = rs.getInt(1);
				}
			}

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getIdKeyId(String idKey)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
		return i;
	}

	public static void getAuditQueue(
		Page page,
		ArrayList collPhotod,
		ArrayList collPage,
		User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int intCount = 0;
		final int intPerPage = Constants.PER_PAGE * 3;
		try {

			conn = ConnectionFactory.getConnection();
			String query =
				" SELECT "
					+ " audit_id, user_id, photo_id, "
					+ " completed, reviewed, approved, "
					+ " reason "
					+ " FROM audit_queue ";
			String limit =
				" LIMIT "
					+ (intPerPage * page.getCurrentPage())
					+ ", "
					+ intPerPage;

			ps = conn.prepareStatement(query + limit);
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					PhotoChangeForm pcf = new PhotoChangeForm();
					pcf.setQueueId(rs.getInt("audit_id"));
					pcf.setUserId(rs.getInt("user_id"));
					pcf.setPhotoId(rs.getInt("photo_id"));
					pcf.setCompleted(
						(rs
							.getString("completed")
							.equalsIgnoreCase(Constants.YES)
							? true
							: false));
					pcf.setReviewed(
						(rs
							.getString("reviewed")
							.equalsIgnoreCase(Constants.YES)
							? true
							: false));
					pcf.setApproved(
						(rs
							.getString("approved")
							.equalsIgnoreCase(Constants.YES)
							? true
							: false));
					pcf.setReason(rs.getString("reason"));
					if (pcf.getUserId() > 0) {
						pcf.setPerson(
							PersonFactory.getPersonByPersonId(pcf.getUserId()));
					} else {
						pcf.setPerson(new Person(0, "Not", "", "Logged In"));
					}
					collPhotod.add(pcf);
				}
			}

			ps = conn.prepareStatement(query);
			rs = ps.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					intCount++;
				}
			}

			if (intCount % intPerPage == 0) {
				intCount = intCount / intPerPage;
			} else {
				intCount = (intCount / intPerPage) + 1;
			}
			for (int i = 0; i < intCount; i++) {
				collPage.add(
					new PageNumber(
						i,
						page.getCurrentPage(),
						0,
						0,
						Constants.AUDIT_ID_KEY));
			}

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in getAuditQueue(Page page,ArrayList collPhotod,ArrayList collPage,User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}

	}

}
