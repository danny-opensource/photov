//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.form.HeirarchyForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-19-2004
 * 
 * XDoclet definition:
 * @struts:action path="/heirarchy" name="heirarchyForm" input="/form/heirarchy.jsp" validate="true"
 */
public class HeirarchyAction extends Action {

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
		HeirarchyForm heirarchyForm =
			(HeirarchyForm) form;
			String strForward = new String(Constants.HEIRARCHY_FORWARD);

			User user = null;
			if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
				user = (User) request.getSession().getAttribute(Constants.USER_KEY);
			}
			if (heirarchyForm.getHeirarchy() == null){
				OtherFactory.populateHeirarchyForm(heirarchyForm, user);
			}
			request.getSession().setAttribute(Constants.USER_KEY, user);

			return (mapping.findForward(strForward));
			
			
			
	}

}
