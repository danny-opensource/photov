//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.UserFactory;
import net.wgbv.photov.form.LoginForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 06-29-2004
 * 
 * XDoclet definition:
 * @struts:action path="/login" name="loginForm" input="/form/login.jsp" validate="true"
 */
public class LoginAction extends Action {

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

		LoginForm loginForm = (LoginForm) form;
		User user = null;
		String strUsername = new String();
		String strPassword = new String();

		ActionErrors errors = new ActionErrors();

		if ((loginForm.getUsername() == null)
			&& (loginForm.getPassword() == null)) {
			request.getSession().invalidate();
		} else if (loginForm.getUsername().length() < 1) {
			ActionError error =
				new ActionError("error.login.username.required");
			errors.add("username", error);
		} else if (loginForm.getPassword().length() < 1) {
			ActionError error =
				new ActionError("error.login.password.required");
			errors.add("password", error);
		} else {
			strUsername = loginForm.getUsername();
			strPassword = loginForm.getPassword();

			user = UserFactory.getUser(strUsername, strPassword);
			if (user == null){
				ActionError error =
					new ActionError("error.login.wrong.password");
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				request.getSession().removeAttribute(Constants.USER_KEY);
			} else if (!user.isActive()){
				ActionError error =
					new ActionError("error.login.account.deactivated");
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
				loginForm.reset(mapping,request);
				request.getSession().removeAttribute(Constants.USER_KEY);
			} else {
				request.getSession().setAttribute(Constants.USER_KEY, user);
			}
		}
		saveErrors(request, errors);

		return (mapping.findForward(Constants.SUCCESS_FORWARD));
	}

}
