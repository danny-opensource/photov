//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.dam.PersonFactory;
import net.wgbv.photov.dam.PhotoFactory;
import net.wgbv.photov.form.PeopleSearchForm;
import net.wgbv.photov.objects.Page;
import net.wgbv.photov.objects.PageNumber;
import net.wgbv.photov.objects.Person;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-15-2004
 * 
 * XDoclet definition:
 * @struts:action path="/peopleSearch" name="peopleSearchForm" input="/form/peopleSearch.jsp" validate="true"
 */
public class PeopleSearchAction extends Action {

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
		PeopleSearchForm peopleSearchForm = (PeopleSearchForm) form;

		String strForward = new String(Constants.PEOPLE_SEARCH_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		if ((request.getParameter(Constants.ACTION) != null)
			&& (request
				.getParameter(Constants.ACTION)
				.equalsIgnoreCase(Constants.ACTION_CREATE))) {
			// Doing INSERT
			peopleSearchForm.reset(mapping, request);
			PersonFactory.populatePeopleSearchForm(peopleSearchForm, user);
		} else if (
			(peopleSearchForm.getPersonIdOne() > 0)
				&& (peopleSearchForm.getPersonIdTwo() > 0)) {
			ArrayList coll = new ArrayList();
			ArrayList pageColl = new ArrayList();
			Person personOne =
				OtherFactory.getPersonByPersonId(
					peopleSearchForm.getPersonIdOne());
			Person personTwo =
				OtherFactory.getPersonByPersonId(
					peopleSearchForm.getPersonIdTwo());

			coll =
				(ArrayList) PhotoFactory.getPhotosByPersonWith(
					personOne,
					personTwo,
					user);
			Collections.sort(coll);
			Page page = new Page(0, 0);

			if (request.getParameter(Constants.PAGE_KEY) != null) {
				page.setCurrentPage(
					Integer.parseInt(
						(String) request.getParameter(Constants.PAGE_KEY)));
			}

			int intCount = coll.size();
			if (intCount % Constants.PER_PAGE == 0) {
				intCount = intCount / Constants.PER_PAGE;
			} else {
				intCount = (intCount / Constants.PER_PAGE) + 1;
			}
			for (int i = 0; i < intCount; i++) {
				pageColl.add(
					new PageNumber(
						i,
						page.getCurrentPage(),
						peopleSearchForm.getPersonIdOne(),
						peopleSearchForm.getPersonIdTwo(),
						Constants.PERSON_ID_ONE_KEY));
			}
			ArrayList coll1 = new ArrayList();
			int t = Constants.PER_PAGE * page.getCurrentPage();
			for (int i = 0; i < coll.size(); i++) {
				if ((i >= t) && (i < (t + Constants.PER_PAGE))) {
					coll1.add(coll.get(i));
				}
			}

			personTwo.setPeople((ArrayList) PersonFactory.getRelatedPeople(personOne,user));
			request.getSession().setAttribute(
				Constants.PERSON_ONE_KEY,
				personOne);
			request.getSession().setAttribute(
				Constants.PERSON_TWO_KEY,
				personTwo);

			request.getSession().setAttribute(
				Constants.PAGE_COLL_KEY,
				pageColl);
			request.getSession().setAttribute(Constants.PHOTO_KEY, coll1);
			strForward = Constants.PEOPLE_WITH_FORWARD;

		} else {
			PersonFactory.populatePeopleSearchForm(peopleSearchForm, user);
		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));

	}

}
