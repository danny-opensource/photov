//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.form.PlaceForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-20-2004
 * 
 * XDoclet definition:
 * @struts:action path="/place" name="placeForm" input="/form/placeEdit.jsp" validate="true"
 */
public class PlaceAction extends Action {

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
		PlaceForm placeForm = (PlaceForm) form;
		String strForward = new String(Constants.PLACE_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		if ((user != null) && (user.getCanUpdate())) {
			if ((placeForm.getCity() == null)
				|| (placeForm.getPlaceName() == null)) {
				// POPULATE UPDATE
				OtherFactory.populatePlaceForm(placeForm);
			} else if (
				(request.getParameter(Constants.ACTION) != null)
					&& (request
						.getParameter(Constants.ACTION)
						.equalsIgnoreCase(Constants.ACTION_CREATE))) {
				// Doing INSERT
				placeForm.reset(mapping, request);
			} else {
				OtherFactory.setPlaceForm(placeForm);
			}
		} else {
			// User can't do this, just kick them back to the start page
			strForward = Constants.NO_USER_FORWARD;

		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));
	}

}
