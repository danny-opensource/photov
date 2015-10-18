//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.dam.OtherFactory;
import net.wgbv.photov.form.CategoryForm;
import net.wgbv.photov.objects.User;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-08-2004
 * 
 * XDoclet definition:
 * @struts:action path="/category" name="categoryForm" input="/form/category.jsp" validate="true"
 * @struts:action-forward name="/list.do" path="/list.do"
 */
public class CategoryAction extends Action {

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
		CategoryForm categoryForm = (CategoryForm) form;

		String strForward = new String(Constants.CATEGORY_EDIT_FORWARD);

		User user = null;
		if (request.getSession().getAttribute(Constants.USER_KEY) != null) {
			user = (User) request.getSession().getAttribute(Constants.USER_KEY);
		}
		categoryForm.setTopics((ArrayList)OtherFactory.getAllTopics());
		if ((user != null) && (user.getCanUpdate())) {
			if ((categoryForm.getCategoryName() == null)
				|| (categoryForm.getCategoryUrl() == null)
				|| (categoryForm.getCategoryDate() == null)) {
				// POPULATE UPDATE
				OtherFactory.populateCategoryForm(categoryForm);
			} else if (
				(request.getParameter(Constants.ACTION) != null)
					&& (request
						.getParameter(Constants.ACTION)
						.equalsIgnoreCase(Constants.ACTION_CREATE))) {
				// Doing INSERT
				categoryForm.reset(mapping, request);
			} else {
				OtherFactory.setCategoryForm(categoryForm);
				
			}
		} else {
			// User can't do this, just kick them back to the start page
			strForward = Constants.SUCCESS_FORWARD;
		}

		request.getSession().setAttribute(Constants.USER_KEY, user);

		return (mapping.findForward(strForward));
	}

}
