//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.EmailFactory;
import net.wgbv.photov.form.MessageForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2004
 * 
 * XDoclet definition:
 * @struts:action path="/message" name="messageForm" input="/form/messageEdit.jsp" validate="true"
 * @struts:action-forward name="/form/message.jsp" path="/form/message.jsp"
 */
public class MessageAction extends Action {

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
		MessageForm messageForm = (MessageForm) form;
		String strForward = new String(Constants.MESSAGE_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		if ((user != null) && (user.getCanUpdate())) {
			if ((messageForm.getMessageName() == null) || (messageForm.getMessageName().equalsIgnoreCase(""))) {
				// POPULATE UPDATE
				EmailFactory.populateMessageForm(messageForm);
			} else if (
				(request.getParameter(Constants.ACTION) != null)
					&& (request
						.getParameter(Constants.ACTION)
						.equalsIgnoreCase(Constants.ACTION_CREATE))) {
				// Doing INSERT
				messageForm.reset(mapping, request);
			} else {
				EmailFactory.setMessageForm(messageForm);
			}
		} else {
			// User can't do this, just kick them back to the start page
			strForward = Constants.NO_USER_FORWARD;
		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));

	}

}
