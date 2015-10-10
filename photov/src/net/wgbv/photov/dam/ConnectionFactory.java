/*
 * Created on Jun 29, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.dam;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConnectionFactory {

	static Logger l = Logger.getLogger(ConnectionFactory.class);
	static Logger mailLogger = Logger.getLogger("mail");
	static DataSource ds;

	private ConnectionFactory() {
		// Private constructor for singleton.
	}

	public static Connection getConnection() throws SQLException {
		Connection conn;
		
		if (ds == null) {
			l.debug("Datasource is null, try to create it");
			System.out.println("Datasource is null, try to create it");
			try {
				synchronized (ConnectionFactory.class) {
					l.debug("Get Context");
					System.out.println("Get Context");
					Context ctx = new InitialContext();
					l.debug("Get Datasource");
					System.out.println("Get Datasource");
					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/photov");
				}

			} catch (NamingException ne) {
				// Normally, log catches as warns, but with the DB
				// Its not a little problem, so log as fatal
				mailLogger.fatal("NamingException in getConnection()");
				System.out.println("NamingException ... " + ne.getMessage());
				ne.printStackTrace();
				//sl.fatal(ne);
			} catch (Exception e) {
				mailLogger.fatal("Exception in getConnection()");
				System.out.println("Exception ... " + e.getMessage());
				e.printStackTrace();
				//l.fatal(e);
			}
		}
		if (ds != null) {
			conn = ds.getConnection();
			l.debug("Return a Connection " + conn.toString());
			System.out.println("Return a connection" + conn.toString());
			return conn;
		} else {
			l.fatal("No Datasource Available");
			System.out.println("No Datasource available");
			return null;
		}

	}

}
