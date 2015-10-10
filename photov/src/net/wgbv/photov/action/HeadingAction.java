//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.dam.PersonFactory;
import net.wgbv.photov.form.HeadingForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-08-2004
 * 
 * XDoclet definition:
 * @struts:action path="/heading" name="headingForm" input="/form/heading.jsp" validate="true"
 * @struts:action-forward name="/form/headingEdit.jsp" path="/form/headingEdit.jsp"
 * @struts:action-forward name="list.do" path="list.do"
 */
public class HeadingAction extends Action {

	// --------------------------------------------------------- Instance Variables

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
		HeadingForm headingForm = (HeadingForm) form;

		String strForward = new String(Constants.HEADING_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		String action = null;
		if (request.getParameter(Constants.ACTION) != null) {
			action = request.getParameter(Constants.ACTION);
		}
		if ((user != null) && (user.getCanUpdate())) {
			if ((headingForm.getHeadingName() == null)
				|| (headingForm.getHeadingUrl() == null)
				|| (headingForm.getHeadingDate() == null)) {
				// POPULATE UPDATE
				OtherFactory.populateHeadingForm(headingForm);
			} else if (
				(request.getParameter(Constants.ACTION) != null)
					&& (request
						.getParameter(Constants.ACTION)
						.equalsIgnoreCase(Constants.ACTION_CREATE))) {
				// Doing INSERT
				headingForm.reset(mapping, request);
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_ADD))) {
				int groupId =
					Integer.parseInt(request.getParameter("allGroupId"));
				PersonFactory.addGroupPhoto(headingForm, groupId, user);
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_REMOVE))) {
				int groupId = Integer.parseInt(request.getParameter("groupId"));
				PersonFactory.removeGroupPhoto(headingForm, groupId, user);
			} else {
				OtherFactory.setHeadingForm(headingForm);
			}
		} else {
			// User can't do this, just kick them back to the start page
			strForward = Constants.NO_USER_FORWARD;

		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));

	}

}
