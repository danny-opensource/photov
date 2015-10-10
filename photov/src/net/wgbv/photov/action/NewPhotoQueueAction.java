//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.dam.QueueFactory;
import net.wgbv.photov.form.NewPhotoQueueForm;
import net.wgbv.photov.objects.Page;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-05-2004
 * 
 * XDoclet definition:
 * @struts:action path="/newPhotoQueue" name="newPhotoQueueForm" input="/form/newPhotoQueue.jsp" validate="true"
 */
public class NewPhotoQueueAction extends Action {

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

		ArrayList coll = new ArrayList(10);
		ArrayList pageColl = new ArrayList(10);
		Page page = new Page(0, 0);
		String forwardString = new String(Constants.NO_USER_FORWARD);

		OtherFactory.clearSession(request, response);

		NewPhotoQueueForm newPhotoQueueForm = (NewPhotoQueueForm) form;
		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		String action = null;
		if (request.getParameter(Constants.ACTION) != null) {
			action = request.getParameter(Constants.ACTION);
		}

		if ((user != null) && (user.getCanUpdate())) {
			if (request.getParameter(Constants.PAGE_KEY) != null) {
				page.setCurrentPage(
					Integer.parseInt(
						(String) request.getParameter(Constants.PAGE_KEY)));
			}

			if (action != null) {
				if (request.getParameter(Constants.PHOTO_ID_KEY) != null) {
					String str =
						(String) request.getParameter(Constants.PHOTO_ID_KEY);
					if (action
						.equalsIgnoreCase(Constants.ACTION_QUEUE_REMOVE)) {
						QueueFactory.removePhotoQueue(Integer.parseInt(str));
					} else if (
						action.equalsIgnoreCase(Constants.ACTION_QUEUE_ADD)) {
						QueueFactory.addPhotoQueue(Integer.parseInt(str));
					}
				}
			}
			QueueFactory.getNewPhotoQueue(page, coll, pageColl, user);
			
			/* Added this to reduce errors from the New Photo Queue when the 
			 * queue is empty.
			 */
			if (coll.isEmpty()){
				forwardString = Constants.LIST_FORWARD;
			} else {
				forwardString = Constants.NEW_PHOTO_QUEUE_FORWARD;
				request.getSession().setAttribute(Constants.PHOTO_KEY, coll);
				request.getSession().setAttribute(
					Constants.PAGE_COLL_KEY,
					pageColl);
			}
		} else {
			// User can't do this, just kick them back to the start page
			forwardString = Constants.NO_USER_FORWARD;
		}
		request.getSession().setAttribute(Constants.USER_KEY, user);

		return mapping.findForward(forwardString);

	}

}
