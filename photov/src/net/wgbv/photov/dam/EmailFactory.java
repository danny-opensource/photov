/*
 * Created on Jul 30, 2004
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
import java.util.Collection;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.wgbv.photov.action.Constants;
import net.wgbv.photov.form.MessageForm;
import net.wgbv.photov.objects.Message;
import net.wgbv.photov.objects.User;

import org.apache.log4j.*;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EmailFactory {

	static Logger l = Logger.getLogger(EmailFactory.class);
	static Logger mailLogger = Logger.getLogger("mail");

	private static void sendEmail(
		ArrayList toEmail,
		ArrayList ccEmail,
		ArrayList bccEmail,
		Message m) {

		InternetAddress from;
		InternetAddress to;

		Properties props = new Properties();
		props.put("mail.smtp.host", Constants.EMAIL_HOST);
		Session s = Session.getInstance(props, null);
		MimeMessage mimeMessage = new MimeMessage(s);
		try {
			from = new InternetAddress("administrator@wgbv.com");
			mimeMessage.setFrom(from);
			if (toEmail != null) {
				for (int i = 0; i < toEmail.size(); i++) {
					to = new InternetAddress((String) toEmail.get(i));
					mimeMessage.addRecipient(
						javax.mail.Message.RecipientType.TO,
						to);
					l.debug("TO Email Addresses");
					l.debug(to);
					l.debug(
						"Email " + m.getMessageSubject() + " sent to: " + to);
				}
			} else {
				return;
			}
			if (ccEmail != null) {
				for (int i = 0; i < ccEmail.size(); i++) {
					to = new InternetAddress((String) ccEmail.get(i));
					mimeMessage.addRecipient(
						javax.mail.Message.RecipientType.CC,
						to);
					l.debug("CC Email Addresses");
					l.debug(to);
				}
			}
			if (bccEmail != null) {
				for (int i = 0; i < bccEmail.size(); i++) {
					to = new InternetAddress((String) bccEmail.get(i));
					mimeMessage.addRecipient(
						javax.mail.Message.RecipientType.BCC,
						to);
					l.debug("BCC Email Addresses");
					l.debug(to);
				}
			}

			mimeMessage.setSubject(m.getMessageSubject());
			l.debug(m.getMessageSubject());
			mimeMessage.setText(m.getMessageText());
			l.debug(m.getMessageText());

			Transport.send(mimeMessage);
		} catch (AddressException ae) {
			l.error(
				"Address Exception in sendEmail(ArrayList toEmail, ArrayList ccEmail, ArrayList bccEmail,Message m)");
			l.error(ae);
		} catch (MessagingException me) {
			l.error(
				"Messaging Exception in sendEmail(ArrayList toEmail, ArrayList ccEmail, ArrayList bccEmail,Message m)");
			l.error(me);
		} catch (Exception e) {
			l.error(
				"Exception in sendEmail(ArrayList toEmail, ArrayList ccEmail, ArrayList bccEmail,Message m)");
			l.error(e);
		}
	}

	public static void sendRegistrationEmail(User user) {
		sendEmail(user, Constants.EMAIL_REGISTRATION);
	}

	public static void sendForgotPasswordEmail(User user) {
		sendEmail(user, Constants.EMAIL_PASSWORD);
	}

	public static void sendActivateEmail(User user) {
		sendEmail(user, Constants.EMAIL_ACTIVATE);
	}

	public static void sendQueueEmail() {
		ArrayList toEmail = new ArrayList();
		PersonFactory.populateAdminEmails(toEmail);
		net.wgbv.photov.objects.Message message =
			new net.wgbv.photov.objects.Message();
		populateMessage(Constants.EMAIL_AUDIT_QUEUE, message);
		sendEmail(toEmail, null, null, message);
		l.debug(" Queue Email Sent");

	}

	private static void sendEmail(User user, String emailType) {
		ArrayList toEmail = new ArrayList();
		toEmail.add(user.getEmail());
		ArrayList ccEmail = new ArrayList();
		ArrayList bccEmail = new ArrayList();
		PersonFactory.populateAdminEmails(bccEmail);

		net.wgbv.photov.objects.Message message =
			new net.wgbv.photov.objects.Message();
		populateMessage(emailType, message);
		sendEmail(toEmail, ccEmail, bccEmail, message);

	}

	public static void sendTestEmail(User user) {
		ArrayList toEmail = new ArrayList();
		PersonFactory.populateAdminEmails(toEmail);
		sendEmail(toEmail, null, null, new Message());
		l.debug(" TEST EMAIL SENT ");
		Log4jInit.auditLog(" Audit Test Email Sent ", user);
	}

	private static void populateMessage(String messageName, Message message) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query =
				" SELECT message_id, message_subject, message_name, message_text "
					+ " FROM message "
					+ " WHERE message_name = ? ";

			conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, messageName);
			rs = ps.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					message.setMessageId(rs.getInt("message_id"));
					message.setMessageName(rs.getString("message_name"));
					message.setMessageSubject(rs.getString("message_subject"));
					message.setMessageText(rs.getString("message_text"));
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in populateMessage(String messageName, Message message)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static void populateMessageForm(MessageForm mf) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query =
				" SELECT message_text, message_name, message_subject, message_id "
					+ " FROM message "
					+ " WHERE message_id = ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, mf.getMessageId());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					mf.setMessageId(rs.getInt("message_id"));
					mf.setMessageName(rs.getString("message_name"));
					mf.setMessageSubject(rs.getString("message_subject"));
					mf.setMessageText(rs.getString("message_text"));
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateMessageForm(MessageForm mf) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static void setMessageForm(MessageForm mf) {
		if (mf != null) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				conn = ConnectionFactory.getConnection();
				String query = new String();
				if (mf.getMessageId() < 1) {
					query =
						"INSERT INTO message "
							+ " (message_name, "
							+ " message_subject,"
							+ " message_text) "
							+ " VALUES "
							+ " (?,?,?) ";
				} else {
					query =
						"UPDATE message "
							+ " SET "
							+ " message_name = ?, "
							+ " message_subject = ?,"
							+ " message_text = ? "
							+ " WHERE "
							+ " message_id = "
							+ mf.getMessageId();
				}
				ps = conn.prepareStatement(query);

				ps.setString(1, mf.getMessageName());
				ps.setString(2, mf.getMessageSubject());
				ps.setString(3, mf.getMessageText());

				ps.executeUpdate();

				getMessageId(mf);

				PhotoFactory.closeConn(rs, ps, conn);
			} catch (SQLException sqle) {
				l.error(" SQL Exception in setMessageForm(MessageForm mf) ");
				l.error(sqle);
			} finally {
				PhotoFactory.closeConn(rs, ps, conn);
			}
		}
	}

	private static void getMessageId(MessageForm mf) {
		if ((mf.getMessageId() > 0)
			|| (mf.getMessageName().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query =
				"SELECT message_id FROM message WHERE message_name = ? "
					+ " ORDER BY message_id DESC ";
			ps = conn.prepareStatement(query);
			ps.setString(1, mf.getMessageName());
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					mf.setMessageId(rs.getInt("message_id"));
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getMessageId(MessageForm mf) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static Collection getAllMessages() {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			String query =
				" SELECT message_id, message_subject, message_name, message_text FROM message ";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Message m = new Message();
					m.setMessageId(rs.getInt("message_id"));
					m.setMessageName(rs.getString("message_name"));
					m.setMessageSubject(rs.getString("message_subject"));
					m.setMessageText(rs.getString("message_text"));
					coll.add(m);
				}
			}
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllMessages() ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}

		return coll;
	}

}
