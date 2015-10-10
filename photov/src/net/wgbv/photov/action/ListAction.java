//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.EmailFactory;
import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.dam.PersonFactory;
import net.wgbv.photov.dam.PhotoFactory;
import net.wgbv.photov.dam.UserFactory;
import net.wgbv.photov.objects.Page;
import net.wgbv.photov.objects.Person;
import net.wgbv.photov.objects.Place;
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
 * @struts:action path="/viewTopic" name="viewTopicForm" input="/form/viewTopic.jsp" validate="true"
 */
public class ListAction extends Action {

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
		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}

		ArrayList coll = new ArrayList(10);
		ArrayList pageColl = new ArrayList(10);
		Place place = null;
		Person person = null;
		Page page = new Page(0, 0);

		OtherFactory.clearSession(request,response);

		if (request.getParameter(Constants.PAGE_KEY) != null) {
			page.setCurrentPage(
				Integer.parseInt(
					(String) request.getParameter(Constants.PAGE_KEY)));
		}

		String forwardString = new String(Constants.TOPIC_FORWARD);

		if (request.getParameter(Constants.TOPIC_ID_KEY) != null) {
			coll =
				(ArrayList) PhotoFactory.getPhotosByTopicId(
					Integer.parseInt(
						(String) request.getParameter(Constants.TOPIC_ID_KEY)),
					user);
			forwardString = Constants.CATEGORY_FORWARD;
		} else if (request.getParameter(Constants.CATEGORY_ID_KEY) != null) {
			coll =
				(ArrayList) PhotoFactory.getPhotosByCategoryId(
					Integer.parseInt(
						(String) request.getParameter(
							Constants.CATEGORY_ID_KEY)),
					user);
			forwardString = Constants.HEADING_FORWARD;
		} else if (request.getParameter(Constants.HEADING_ID_KEY) != null) {
			int headingId =
				Integer.parseInt(
					(String) request.getParameter(Constants.HEADING_ID_KEY));
			PhotoFactory.getPhotosByHeadingId(
				headingId,
				page,
				coll,
				pageColl,
				user);
			forwardString = Constants.HEADING_DETAIL_FORWARD;
		} else if (request.getParameter(Constants.PLACE_ID_KEY) != null) {
			int placeId =
				Integer.parseInt(
					(String) request.getParameter(Constants.PLACE_ID_KEY));
			PhotoFactory.getPhotosByPlaceId(
				placeId,
				page,
				coll,
				pageColl,
				user);
			place = OtherFactory.getPlaceByPlaceId(placeId);
			request.getSession().setAttribute(Constants.PLACE_KEY, place);
			forwardString = Constants.PLACE_FORWARD;
		} else if (request.getParameter(Constants.PERSON_ID_KEY) != null) {
			int personId =
				Integer.parseInt(
					(String) request.getParameter(Constants.PERSON_ID_KEY));
			PhotoFactory.getPhotosByPersonId(
				personId,
				page,
				coll,
				pageColl,
				user);
			person = OtherFactory.getPersonByPersonId(personId);
			person.setPeople((ArrayList) PersonFactory.getRelatedPeople(person,user));
			request.getSession().setAttribute(Constants.PERSON_KEY, person);
			forwardString = Constants.PERSON_FORWARD;
		} else if (request.getParameter(Constants.SITE_ID_KEY) != null) {
			if ((user != null) && (user.getCanUpdate())) {
				coll = (ArrayList) OtherFactory.getAllSites();
				forwardString = Constants.SITE_FORWARD;
			} else {
				coll = (ArrayList) PhotoFactory.getPhotos(user);
				forwardString = Constants.TOPIC_FORWARD;
			}
		} else if (request.getParameter(Constants.GROUP_ID_KEY) != null) {
			if ((user != null) && (user.getCanUpdate())) {
				coll = (ArrayList) UserFactory.getAllGroups();
				forwardString = Constants.GROUP_FORWARD;
			} else {
				coll = (ArrayList) PhotoFactory.getPhotos(user);
				forwardString = Constants.TOPIC_FORWARD;
			}
		} else if (request.getParameter(Constants.CAMERA_ID_KEY) != null) {
			if ((user != null) && (user.getCanUpdate())) {
				coll = (ArrayList) OtherFactory.getAllCameras();
				forwardString = Constants.CAMERA_FORWARD;
			} else {
				coll = (ArrayList) PhotoFactory.getPhotos(user);
				forwardString = Constants.TOPIC_FORWARD;
			}
		} else if (request.getParameter(Constants.FILM_ID_KEY) != null) {
			if ((user != null) && (user.getCanUpdate())) {
				coll = (ArrayList) OtherFactory.getAllFilms();
				forwardString = Constants.FILM_FORWARD;
			} else {
				coll = (ArrayList) PhotoFactory.getPhotos(user);
				forwardString = Constants.TOPIC_FORWARD;
			}
		} else if (request.getParameter(Constants.USER_ID_KEY) != null) {
			if ((user != null) && (user.getCanUpdate())) {
				coll = (ArrayList) UserFactory.getAllUsers();
				forwardString = Constants.USER_FORWARD;
			} else {
				coll = (ArrayList) PhotoFactory.getPhotos(user);
				forwardString = Constants.TOPIC_FORWARD;
			}
		} else if (request.getParameter(Constants.MESSAGE_ID_KEY) != null) {
			if ((user != null) && (user.getCanUpdate())) {
				coll = (ArrayList) EmailFactory.getAllMessages();
				forwardString = Constants.MESSAGE_FORWARD;
			} else {
				coll = (ArrayList) PhotoFactory.getPhotos(user);
				forwardString = Constants.TOPIC_FORWARD;
			}
		} else if (request.getParameter(Constants.EMAIL_ID_KEY) != null) {
			EmailFactory.sendTestEmail(user);
		} else if (request.getParameter(Constants.PHOTO_ID_KEY) != null) {
			coll =
				(ArrayList) PhotoFactory.getPhotoByPhotoId(
					(String) request.getParameter(Constants.PHOTO_ID_KEY),
					user);
			forwardString = Constants.PHOTO_FORWARD;
		} else {
			coll = (ArrayList) PhotoFactory.getPhotos(user);
			forwardString = Constants.TOPIC_FORWARD;
		}
		request.getSession().setAttribute(Constants.PHOTO_KEY, coll);
		request.getSession().setAttribute(Constants.PAGE_COLL_KEY, pageColl);
		request.getSession().setAttribute(Constants.USER_KEY, user);

		return mapping.findForward(forwardString);
	}

}
