//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.form.CameraForm;
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
 * @struts:action path="/camera" name="cameraForm" input="/form/camera.jsp" validate="true"
 */
public class CameraAction extends Action {

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
		CameraForm cameraForm = (CameraForm) form;
		String strForward = new String(Constants.CAMERA_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		if ((user != null) && (user.getCanUpdate())) {
			if ((cameraForm.getCameraName() == null)) {
				// POPULATE UPDATE
				OtherFactory.populateCameraForm(cameraForm);
			} else if (
				(request.getParameter(Constants.ACTION) != null)
					&& (request
						.getParameter(Constants.ACTION)
						.equalsIgnoreCase(Constants.ACTION_CREATE))) {
				// Doing INSERT
				cameraForm.reset(mapping, request);
			} else {
				OtherFactory.setCameraForm(cameraForm);
			}
		} else {
			// User can't do this, just kick them back to the start page
			strForward = Constants.NO_USER_FORWARD;

		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));
	}

}
