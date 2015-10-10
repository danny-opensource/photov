//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-22-2004
 * 
 * XDoclet definition:
 * @struts:form name="bulkForm"
 */
public class BulkForm extends ActionForm {

	private ArrayList topics = null;
	private ArrayList categories = null;
	private ArrayList headings = null;
	private ArrayList places = null;
	private ArrayList sites = null;
	private ArrayList cameras = null;
	private ArrayList films = null;
	private ArrayList photographers = null;

	private int photographerId = 0;
	private int placeId = 0;
	private int cameraId = 0;
	private int topicId = 0;
	private int categoryId = 0;
	private int headingId = 0;
	private int filmId = 0;

	private String title = new String();
	private String urls = new String();

	private ArrayList urlList = new ArrayList();

	private int siteId = 0;

	private boolean vertical = false;
	private boolean submitCheck = false;
	private boolean complete = false;

	private String date = new String();
	private String tstamp = new String();

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {
		return new ActionErrors();
	}

	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		topics = null;
		categories = null;
		headings = null;
		places = null;
		sites = null;
		cameras = null;
		films = null;
		photographers = null;
		
		urlList = null;

		photographerId = 0;
		placeId = 0;
		cameraId = 0;
		topicId = 0;
		categoryId = 0;
		headingId = 0;
		filmId = 0;

		title = new String();
		urls = new String();

		siteId = 0;

		vertical = false;
		complete = false;
		submitCheck = false;

		date = new String();
		tstamp = new String();
	}
	/**
	 * @return
	 */
	public int getCameraId() {
		return cameraId;
	}

	/**
	 * @return
	 */
	public ArrayList getCameras() {
		return cameras;
	}

	/**
	 * @return
	 */
	public ArrayList getCategories() {
		return categories;
	}

	/**
	 * @return
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @return
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public int getFilmId() {
		return filmId;
	}

	/**
	 * @return
	 */
	public ArrayList getFilms() {
		return films;
	}

	/**
	 * @return
	 */
	public int getHeadingId() {
		return headingId;
	}

	/**
	 * @return
	 */
	public ArrayList getHeadings() {
		return headings;
	}

	/**
	 * @return
	 */
	public int getPhotographerId() {
		return photographerId;
	}

	/**
	 * @return
	 */
	public ArrayList getPhotographers() {
		return photographers;
	}

	/**
	 * @return
	 */
	public int getPlaceId() {
		return placeId;
	}

	/**
	 * @return
	 */
	public ArrayList getPlaces() {
		return places;
	}

	/**
	 * @return
	 */
	public int getSiteId() {
		return siteId;
	}

	/**
	 * @return
	 */
	public ArrayList getSites() {
		return sites;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return
	 */
	public int getTopicId() {
		return topicId;
	}

	/**
	 * @return
	 */
	public ArrayList getTopics() {
		return topics;
	}

	/**
	 * @return
	 */
	public String getTstamp() {
		return tstamp;
	}

	/**
	 * @return
	 */
	public String getUrls() {
		return urls;
	}

	/**
	 * @return
	 */
	public boolean isVertical() {
		return vertical;
	}

	/**
	 * @param i
	 */
	public void setCameraId(int i) {
		cameraId = i;
	}

	/**
	 * @param list
	 */
	public void setCameras(ArrayList list) {
		cameras = list;
	}

	/**
	 * @param list
	 */
	public void setCategories(ArrayList list) {
		categories = list;
	}

	/**
	 * @param i
	 */
	public void setCategoryId(int i) {
		categoryId = i;
	}

	/**
	 * @param b
	 */
	public void setComplete(boolean b) {
		complete = b;
	}

	/**
	 * @param string
	 */
	public void setDate(String string) {
		date = string;
	}

	/**
	 * @param i
	 */
	public void setFilmId(int i) {
		filmId = i;
	}

	/**
	 * @param list
	 */
	public void setFilms(ArrayList list) {
		films = list;
	}

	/**
	 * @param i
	 */
	public void setHeadingId(int i) {
		headingId = i;
	}

	/**
	 * @param list
	 */
	public void setHeadings(ArrayList list) {
		headings = list;
	}

	/**
	 * @param i
	 */
	public void setPhotographerId(int i) {
		photographerId = i;
	}

	/**
	 * @param list
	 */
	public void setPhotographers(ArrayList list) {
		photographers = list;
	}

	/**
	 * @param i
	 */
	public void setPlaceId(int i) {
		placeId = i;
	}

	/**
	 * @param list
	 */
	public void setPlaces(ArrayList list) {
		places = list;
	}

	/**
	 * @param i
	 */
	public void setSiteId(int i) {
		siteId = i;
	}

	/**
	 * @param list
	 */
	public void setSites(ArrayList list) {
		sites = list;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string) {
		title = string;
	}

	/**
	 * @param i
	 */
	public void setTopicId(int i) {
		topicId = i;
	}

	/**
	 * @param list
	 */
	public void setTopics(ArrayList list) {
		topics = list;
	}

	/**
	 * @param string
	 */
	public void setTstamp(String string) {
		tstamp = string;
	}

	/**
	 * @param string
	 */
	public void setUrls(String string) {
		urls = string;
		StringTokenizer st = new StringTokenizer(string,"\r\n");
		ArrayList tempAL = new ArrayList();
		while (st.hasMoreTokens()){
			tempAL.add(st.nextToken());
		}
		this.setUrlList(tempAL);
	}

	/**
	 * @param b
	 */
	public void setVertical(boolean b) {
		vertical = b;
	}
	


	/**
	 * @return
	 */
	public ArrayList getUrlList() {
		return urlList;
	}

	/**
	 * @param list
	 */
	public void setUrlList(ArrayList list) {
		urlList = list;
	}

	/**
	 * @return
	 */
	public boolean isSubmitCheck() {
		return submitCheck;
	}

	/**
	 * @param b
	 */
	public void setSubmitCheck(boolean b) {
		submitCheck = b;
	}

}
