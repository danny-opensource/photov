//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.QueueFactory;
import net.wgbv.photov.form.PhotoChangeForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/** 
 * MyEclipse Struts
 * Creation date: 08-06-2004
 * 
 * XDoclet definition:
 * @struts:action path="/photoHide" name="photoHideForm" input="/form/photoHide.jsp" validate="true"
 */
public class PhotoChangeAction extends Action {

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
		HttpServletResponse response)
		throws Exception {
		PhotoChangeForm photoChangeForm = (PhotoChangeForm) form;
		String strForward = new String(Constants.LIST_FORWARD);
		ActionErrors errors = new ActionErrors();

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}

		if (request.getParameter(Constants.PHOTO_ID_KEY) != null) {
			photoChangeForm.setPhotoId(
				Integer.parseInt(request.getParameter(Constants.PHOTO_ID_KEY)));
		} else if (photoChangeForm.getPhotoId() == 0) {
			ActionError error = new ActionError("error.photo.hide.no.photo");
			errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			request.getSession().setAttribute(Constants.USER_KEY, user);
			return (mapping.findForward(strForward));
		}

		if (photoChangeForm.getReason() == null) {
			photoChangeForm.setReason("");
			strForward = Constants.PHOTO_CHANGE_FORWARD;
		} else {
			QueueFactory.setPhotoChangeForm(photoChangeForm, user);
			if (user != null) {
				ActionError error = new ActionError("error.photo.hide.user");
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			} else {
				ActionError error = new ActionError("error.photo.hide.no.user");
				errors.add(ActionMessages.GLOBAL_MESSAGE, error);
			}
			photoChangeForm.reset(mapping, request);
			strForward = Constants.LIST_FORWARD;
		}
		saveErrors(request, errors);

		request.getSession().setAttribute(Constants.USER_KEY, user);
		return (mapping.findForward(strForward));
	}
}
