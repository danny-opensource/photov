//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.EmailFactory;
import net.wgbv.photov.dam.UserFactory;
import net.wgbv.photov.form.UserForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-21-2004
 * 
 * XDoclet definition:
 * @struts:action path="/user" name="userForm" input="/form/user.jsp" validate="true"
 * @struts:action-forward name="/form/user.jsp" path="/form/user.jsp"
 */
public class UserAction extends Action {

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
		UserForm userForm = (UserForm) form;
		String strForward = new String(Constants.USER_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		String action = null;
		if (request.getParameter(Constants.ACTION) != null) {
			action = request.getParameter(Constants.ACTION);
		}
		if ((user != null)
			&& ((user.getCanUpdate()
				|| (user.getUserId() == userForm.getUserId())))) {
			if ((action != null)
				&& action.equalsIgnoreCase(Constants.ACTION_ACTIVE)) {
				UserFactory.populateUserForm(userForm, user);
				UserFactory.toggleActive(userForm);

			} else if (
				(action != null)
					&& action.equalsIgnoreCase(Constants.ACTION_UPDATE)) {
				UserFactory.populateUserForm(userForm, user);
				UserFactory.toggleUpdate(userForm);
			} else if (
				(action != null)
					&& action.equalsIgnoreCase(Constants.ACTION_EMAIL_ACTIVATE)) {
				EmailFactory.sendActivateEmail(
					UserFactory.getUserFromUserForm(userForm));
				UserFactory.populateUserForm(userForm, user);
				strForward = Constants.NO_USER_FORWARD;
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_ADD))) {
				UserFactory.addUserGroup(userForm, user);
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_REMOVE))) {
				UserFactory.removeUserGroup(userForm, user);
			} else if ((userForm.getUsername() == null)) {
				// POPULATE UPDATE
				UserFactory.populateUserForm(userForm, user);
			} else if (
				(request.getParameter(Constants.ACTION) != null)
					&& (request
						.getParameter(Constants.ACTION)
						.equalsIgnoreCase(Constants.ACTION_CREATE))) {
				// Doing INSERT
				userForm.reset(mapping, request);
			} else {
				UserFactory.setUserForm(userForm, user);
			}
		} else {
			// User can't do this, just kick them back to the start page
			strForward = Constants.NO_USER_FORWARD;

		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));
	}

}
