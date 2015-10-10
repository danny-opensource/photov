//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.PhotoFactory;
import net.wgbv.photov.form.BulkForm;
import net.wgbv.photov.objects.User;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-22-2004
 * 
 * XDoclet definition:
 * @struts:action path="/bulk" name="bulkForm" input="/form/bulk.jsp" validate="true"
 */
public class BulkAction extends Action {

	// --------------------------------------------------------- Instance Variables

	static Logger l = Logger.getLogger(BulkAction.class);

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		BulkForm bulkForm = (BulkForm) form;
		String strForward = new String(Constants.BULK_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
			l.debug("Getting user" + user.getUsername());
		}
		String action = null;
		if (request.getParameter(Constants.ACTION) != null) {
			action = request.getParameter(Constants.ACTION);
			l.debug("Getting action" + action);
		}
		if ((user != null) && (user.getCanUpdate())) {
			if ((action != null)
				&& action.equalsIgnoreCase(Constants.ACTION_CREATE)) {
				l.debug("Reseting bulkForm");
				bulkForm.reset(mapping, request);
			} else if (bulkForm.isSubmitCheck()) {
				l.debug("Submitting bulkForm");
				PhotoFactory.setBulkForm(bulkForm, user);
				bulkForm.reset(mapping, request);
			}
			l.debug("Populating bulkForm");
			PhotoFactory.populateBulkForm(bulkForm, user);
		} else {
			// User can't do this, just kick them back to the start page
			if (user != null) {
				l.warn("Unauthorized Access: " + user.getUsername());
			} else {
				l.warn("Unauthorized Access: No User");
			}
			strForward = Constants.NO_USER_FORWARD;
		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));
	}

}
