//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.dam.UserFactory;
import net.wgbv.photov.form.GroupForm;
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
 * @struts:action path="/group" name="groupForm" input="/form/group.jsp" validate="true"
 */
public class GroupAction extends Action {

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
		GroupForm groupForm = (GroupForm) form;
		String strForward = new String(Constants.GROUP_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		String action = null;
		if (request.getParameter(Constants.ACTION) != null) {
			action = request.getParameter(Constants.ACTION);
		}
		if ((user != null) && (user.getCanUpdate())) {
			if ((groupForm.getGroupName() == null)
				|| (groupForm.getGroupDesc() == null)) {
				// POPULATE UPDATE
				UserFactory.populateGroupForm(groupForm);
			} else if (
				(request.getParameter(Constants.ACTION) != null)
					&& (request
						.getParameter(Constants.ACTION)
						.equalsIgnoreCase(Constants.ACTION_CREATE))) {
				// Doing INSERT
				groupForm.reset(mapping, request);
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_ADD))) {
				UserFactory.addUserGroup(groupForm);
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_REMOVE))) {
				UserFactory.removeUserGroup(groupForm);
			} else {
				UserFactory.setGroupForm(groupForm);
				
			}
		} else {
			// User can't do this, just kick them back to the start page
			strForward = Constants.NO_USER_FORWARD;

		}
		groupForm.setAllUsers((ArrayList) UserFactory.getAllUsers());
		groupForm.setSelectedUsers((ArrayList) UserFactory.getAllUsers());
		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));
	}

}
