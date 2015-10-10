//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.PersonFactory;
import net.wgbv.photov.dam.UserFactory;
import net.wgbv.photov.form.RegistrationForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-21-2004
 * 
 * XDoclet definition:
 * @struts:action path="/registration" name="registrationForm" input="/form/registration.jsp" validate="true"
 * @struts:action-forward name="/form/registration.jsp" path="/form/registration.jsp"
 */
public class RegistrationAction extends Action {

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
		RegistrationForm registrationForm = (RegistrationForm) form;
		String strForward = new String(Constants.REGISTRATION_FORWARD);

		User user = null;

		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		String action = null;
		if (request.getParameter(Constants.ACTION) != null) {
			action = request.getParameter(Constants.ACTION);
		}
		if (user != null) {
			// User can't do this, just kick them back to the start page
			strForward = Constants.NO_USER_FORWARD;
		} else {
			if ((registrationForm.getUsername() == null)) {
				registrationForm.setPeople(
					(ArrayList) PersonFactory.getRegistrationPeople());

			} else if (
				(action == null)
					|| (!action.equalsIgnoreCase(Constants.ACTION_SUBMIT))) {
				registrationForm.setPeople(
					(ArrayList) PersonFactory.getRegistrationPeople());
				registrationForm.setEmail(
					PersonFactory
						.getPersonByPersonId(registrationForm.getPersonId())
						.getEmail());

			} else if (
				(registrationForm.getPassword() != null)
					&& (registrationForm.getPassword2() != null)
					&& (registrationForm.getUsername() != null)) {
				ActionErrors ae = new ActionErrors();
				if (registrationForm.getUsername().length() < 1) {
					ae.add(
						"username",
						new ActionError("error.login.username.required"));
				} else if (
					UserFactory.isUsernameExist(
						registrationForm.getUsername())) {
					ae.add(
						"username",
						new ActionError("error.registration.username"));
				}
				if (registrationForm.getPassword().length() < 1) {
					ae.add(
						"password",
						new ActionError("error.login.password.required"));
				}
				if (!registrationForm
					.getPassword()
					.equalsIgnoreCase(registrationForm.getPassword2())) {
					ae.add(
						"password2",
						new ActionError("error.login.password.mismatch"));
				}
				if (registrationForm.getEmail().length() < 1) {
					ae.add(
						"email",
						new ActionError("error.registration.email"));
				}
				saveErrors(request, ae);
				if (ae.isEmpty()) {
					UserFactory.setRegistrationForm(registrationForm);
					ae.add(
						ActionErrors.GLOBAL_MESSAGE,
						new ActionError("registration.success"));
					saveErrors(request, ae);
					registrationForm.reset(mapping,request);
					
				}
			}
		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));
	}

}
