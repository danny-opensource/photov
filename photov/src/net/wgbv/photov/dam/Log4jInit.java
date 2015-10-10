/*
 * Created on Aug 4, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.dam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.objects.User;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class Log4jInit extends HttpServlet {

	public void init() {
		String prefix = getServletContext().getRealPath("/");
		String file = getInitParameter("log4j-init-file");
		// if the log4j-init-file is not set, then no point in trying
		if (file != null) {
			PropertyConfigurator.configureAndWatch(prefix + file);
			//PropertyConfigurator.
		}
		Logger l = Logger.getRootLogger();
		l.info("Logger Loaded");
	}

	public static void auditLog(Object o, User u){ 
		Logger l = Logger.getLogger("audit");
		l.info(" BEGIN AUDIT ENTRY " + u.getUsername());
		l.info(o);
		l.info(" END   AUDIT ENTRY " + u.getUsername());
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}
