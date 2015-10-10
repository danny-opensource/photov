//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.PersonFactory;
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
 * @struts:action path="/peopleRanking" name="peopleRankingForm" input="/form/peopleRanking.jsp" validate="true"
 */
public class PeopleRankingAction extends Action {

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
		//PeopleRankingForm peopleRankingForm = (PeopleRankingForm) form;

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		String forwardString = new String(Constants.PEOPLE_RANKING_FORWARD);

		ArrayList coll = new ArrayList();
		
		coll = (ArrayList) PersonFactory.getRankings();

		request.getSession().setAttribute(Constants.RANKING_KEY, coll);
		request.getSession().setAttribute(Constants.USER_KEY, user);


		return mapping.findForward(forwardString);

	}

}
