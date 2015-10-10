//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.dam.PersonFactory;
import net.wgbv.photov.dam.PhotoFactory;
import net.wgbv.photov.dam.UserFactory;
import net.wgbv.photov.form.PhotoForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 06-29-2004
 * 
 * XDoclet definition:
 * @struts:action path="/editPhoto" name="editPhotoForm" input="/form/editPhoto.jsp" validate="true"
 */
public class PhotoAction extends Action {

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
		PhotoForm photoForm = (PhotoForm) form;
		String strForward = new String(Constants.PHOTO_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		String action = null;
		if (request.getParameter(Constants.ACTION) != null) {
			action = request.getParameter(Constants.ACTION);
		}
		if ((user != null) && (user.getCanUpdate())) {
			if ((photoForm.getUrl() == null)
				|| (photoForm.getUrl().equalsIgnoreCase(""))) {
				PhotoFactory.populatePhotoForm(photoForm, user);
			} else if (
				(action != null)
					&& action.equalsIgnoreCase(Constants.ACTION_CREATE)) {
				photoForm.reset(mapping, request);
			} else if (
				(action != null)
					&& action.equalsIgnoreCase(Constants.ACTION_ADD)) {

				PersonFactory.addPersonPhoto(
					photoForm.getPhotoId(),
					Integer.parseInt(request.getParameter("allPeopleId")));
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_REMOVE))) {
				PersonFactory.removePersonPhoto(
					photoForm.getPhotoId(),
					Integer.parseInt(request.getParameter("peopleId")));
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_ADD))) {
				PersonFactory.addGroupPhoto(
					photoForm.getPhotoId(),
					Integer.parseInt(request.getParameter("allGroupId")));
			} else if (
				(action != null)
					&& (action.equalsIgnoreCase(Constants.ACTION_GRP_REMOVE))) {
				PersonFactory.removeGroupPhoto(
					photoForm.getPhotoId(),
					Integer.parseInt(request.getParameter("groupId")));
			} else {
				PhotoFactory.setPhotoForm(photoForm, user);
			}

			PhotoFactory.populatePhotoForm(photoForm, user);
			UserFactory.populateGroup(photoForm, user);
			photoForm.setPlaces((ArrayList) OtherFactory.getAllPlaces());
			photoForm.setSites((ArrayList) OtherFactory.getAllSites());
			photoForm.setCameras((ArrayList) OtherFactory.getAllCameras());
			photoForm.setFilms((ArrayList) OtherFactory.getAllFilms());
			photoForm.setPhotographers(
				(ArrayList) PersonFactory.getPhotographers());

			photoForm.setTopics((ArrayList) OtherFactory.getAllTopics());

			photoForm.setCategories(
				(ArrayList) OtherFactory.getCategoriesByTopicId(
					photoForm.getTopicId()));

			photoForm.setHeadings(
				(ArrayList) OtherFactory.getHeadingsByCategoryId(
					photoForm.getCategoryId()));
			photoForm.setPeople(
				(ArrayList) PersonFactory.getPeopleByPhotoId(
					photoForm.getPhotoId()));

			ArrayList peopleAL = new ArrayList();
			if (action != null) {
				if (action.equalsIgnoreCase(Constants.ACTION_PEOPLE_ALL)) {
					/* User selects ALL People
					 */
					peopleAL =
						(ArrayList) PersonFactory.getAllPeople(
							photoForm.getPeople(),
							0,
							"");
				} else if (
					action.equalsIgnoreCase(
						Constants.ACTION_PEOPLE_CATEGORY)) {
					/* User selects CAT People
					 */
					peopleAL =
						(ArrayList) PersonFactory.getAllPeople(
							photoForm.getPeople(),
							photoForm.getCategoryId(),
							Constants.CATEGORY_ID_KEY);
				} else if (
					action.equalsIgnoreCase(Constants.ACTION_PEOPLE_HEADING)) {
					/* User selects HEAD People
					 */
					peopleAL =
						(ArrayList) PersonFactory.getAllPeople(
							photoForm.getPeople(),
							photoForm.getHeadingId(),
							Constants.HEADING_ID_KEY);
				}
			}

			if (peopleAL.isEmpty()) {
				/* Try to populate People with a non-empty list.
				 * Start at Head, then Cat, the All
				 */
				peopleAL =
					(ArrayList) PersonFactory.getAllPeople(
						photoForm.getPeople(),
						photoForm.getHeadingId(),
						Constants.HEADING_ID_KEY);
				if (peopleAL.isEmpty()) {
					peopleAL =
						(ArrayList) PersonFactory.getAllPeople(
							photoForm.getPeople(),
							photoForm.getCategoryId(),
							Constants.CATEGORY_ID_KEY);
				}
				if (peopleAL.isEmpty()) {
					peopleAL =
						(ArrayList) PersonFactory.getAllPeople(
							photoForm.getPeople(),
							0,
							"");
				}
			}
			photoForm.setAllPeople(peopleAL);

		} else {
			if ((action != null)
				&& (action.equalsIgnoreCase(Constants.ACTION_CHANGE_PHOTO))) {
				if (request.getParameter(Constants.PHOTO_ID_KEY) != null) {
					request.getSession().setAttribute(
						Constants.PHOTO_ID_KEY,
						(String) request.getParameter(Constants.PHOTO_ID_KEY));
					strForward = Constants.PHOTO_CHANGE_FORWARD;
				} else {
					strForward = Constants.NO_USER_FORWARD;
				}
			} else {
				// User can't do this, just kick them back to the start page
				strForward = Constants.NO_USER_FORWARD;
			}
		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));

	}

}
