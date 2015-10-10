//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_2.7.101/xslt/JavaClass.xsl

package net.wgbv.photov.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 06-29-2004
 * 
 * XDoclet definition:
 * @struts:form name="photoForm"
 */
public class PhotoForm extends ActionForm {

	private String siteName = new String();
	private String siteUrl = new String();
	private String topicName = new String();
	private String topicUrl = new String();
	private String categoryName = new String();
	private String categoryUrl = new String();
	private String headingName = new String();
	private String headingUrl = new String();
	private String filmName = new String();
	private String cameraName = new String();
	private String placeName = new String();
	private String placeCityName = new String();
	private String placeStateAbbr = new String();
	private String placeCountryAbbr = new String();
	private String photographerName = new String();
	private ArrayList people = null;
	private ArrayList allPeople = null;
	private ArrayList topics = null;
	private ArrayList categories = null;
	private ArrayList headings = null;
	private ArrayList places = null;
	private ArrayList sites = null;
	private ArrayList cameras = null;
	private ArrayList films = null;
	private ArrayList photographers = null;

	private int photoId = 0;
	private int photographerId = 0;
	private int placeId = 0;
	private int cameraId = 0;
	private int topicId = 0;
	private int categoryId = 0;
	private int headingId = 0;
	private int filmId = 0;
	private int peopleId = 0;
	private int allPeopleId = 0;

	private String title = new String();
	private String url = new String();
	private String thumbUrl = new String();

	private int siteId = 0;

	private boolean vertical = false;
	private boolean privatePhoto = false;
	private boolean complete = false;

	private String date = new String();
	private String tstamp = new String();
	private String notes = new String();
	
	private int groupId = 0;
	private ArrayList groups = null;
	private int allGroupId = 0;
	private ArrayList allGroups = null;

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
		siteName = new String();
		siteUrl = new String();
		topicName = new String();
		topicUrl = new String();
		categoryName = new String();
		categoryUrl = new String();
		headingName = new String();
		headingUrl = new String();
		filmName = new String();
		cameraName = new String();
		placeName = new String();
		placeCityName = new String();
		placeStateAbbr = new String();
		placeCountryAbbr = new String();
		photographerName = new String();
		people = null;
		allPeople = null;
		topics = null;
		categories = null;
		headings = null;
		places = null;
		sites = null;
		cameras = null;
		films = null;
		photographers = null;

		photoId = 0;
		photographerId = 0;
		placeId = 0;
		cameraId = 0;
		topicId = 0;
		categoryId = 0;
		headingId = 0;
		filmId = 0;
		peopleId = 0;
		allPeopleId = 0;

		title = new String();
		url = new String();
		thumbUrl = new String();

		siteId = 0;

		vertical = false;
		privatePhoto = false;
		complete = false;

		date = new String();
		tstamp = new String();
		notes = new String();
	}
	public String getPhotoUrl() {
		return (new StringBuffer(getBaseUrl())).append(getUrl()).toString();
	}
	/**
	 * @return
	 */
	public String getPhotoThumbUrl() {
		return (new StringBuffer(getBaseUrl()))
			.append(getThumbUrl())
			.toString();
	}

	/**
	 * @return
	 */
	private String getBaseUrl() {
		StringBuffer sb = new StringBuffer();

		sb.append(getSiteUrl());
		sb.append(getTopicUrl());
		sb.append(getCategoryUrl());
		sb.append(getHeadingUrl());

		return sb.toString();
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
	public String getCameraName() {
		return cameraName;
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
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @return
	 */
	public String getCategoryUrl() {
		return categoryUrl;
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
	public String getFilmName() {
		return filmName;
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
	public String getHeadingName() {
		return headingName;
	}

	/**
	 * @return
	 */
	public String getHeadingUrl() {
		return headingUrl;
	}

	/**
	 * @return
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @return
	 */
	public ArrayList getPeople() {
		return people;
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
	public String getPhotographerName() {
		return photographerName;
	}

	/**
	 * @return
	 */
	public int getPhotoId() {
		return photoId;
	}

	/**
	 * @return
	 */
	public String getPlaceCityName() {
		return placeCityName;
	}

	/**
	 * @return
	 */
	public String getPlaceCountryAbbr() {
		return placeCountryAbbr;
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
	public String getPlaceName() {
		return placeName;
	}

	/**
	 * @return
	 */
	public String getPlaceStateAbbr() {
		return placeStateAbbr;
	}

	/**
	 * @return
	 */
	public boolean isPrivatePhoto() {
		return privatePhoto;
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
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @return
	 */
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * @return
	 */
	public String getThumbUrl() {
		return thumbUrl;
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
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @return
	 */
	public String getTopicUrl() {
		return topicUrl;
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
	public String getUrl() {
		return url;
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
	 * @param string
	 */
	public void setCameraName(String string) {
		cameraName = string;
	}

	/**
	 * @param i
	 */
	public void setCategoryId(int i) {
		categoryId = i;
	}

	/**
	 * @param string
	 */
	public void setCategoryName(String string) {
		categoryName = string;
	}

	/**
	 * @param string
	 */
	public void setCategoryUrl(String string) {
		categoryUrl = string;
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
	 * @param string
	 */
	public void setFilmName(String string) {
		filmName = string;
	}

	/**
	 * @param i
	 */
	public void setHeadingId(int i) {
		headingId = i;
	}

	/**
	 * @param string
	 */
	public void setHeadingName(String string) {
		headingName = string;
	}

	/**
	 * @param string
	 */
	public void setHeadingUrl(String string) {
		headingUrl = string;
	}



	/**
	 * @param list
	 */
	public void setPeople(ArrayList list) {
		people = list;
	}

	/**
	 * @param i
	 */
	public void setPhotographerId(int i) {
		photographerId = i;
	}

	/**
	 * @param string
	 */
	public void setPhotographerName(String string) {
		photographerName = string;
	}

	/**
	 * @param i
	 */
	public void setPhotoId(int i) {
		photoId = i;
	}

	/**
	 * @param string
	 */
	public void setPlaceCityName(String string) {
		placeCityName = string;
	}

	/**
	 * @param string
	 */
	public void setPlaceCountryAbbr(String string) {
		placeCountryAbbr = string;
	}

	/**
	 * @param i
	 */
	public void setPlaceId(int i) {
		placeId = i;
	}

	/**
	 * @param string
	 */
	public void setPlaceName(String string) {
		placeName = string;
	}

	/**
	 * @param string
	 */
	public void setPlaceStateAbbr(String string) {
		placeStateAbbr = string;
	}

	/**
	 * @param b
	 */
	public void setPrivatePhoto(boolean b) {
		privatePhoto = b;
	}

	/**
	 * @param i
	 */
	public void setSiteId(int i) {
		siteId = i;
	}

	/**
	 * @param string
	 */
	public void setSiteName(String string) {
		siteName = string;
	}

	/**
	 * @param string
	 */
	public void setSiteUrl(String string) {
		siteUrl = string;
	}

	/**
	 * @param string
	 */
	public void setThumbUrl(String string) {
		thumbUrl = string;
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
	 * @param string
	 */
	public void setTopicName(String string) {
		topicName = string;
	}

	/**
	 * @param string
	 */
	public void setTopicUrl(String string) {
		topicUrl = string;
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
	public void setUrl(String string) {
		url = string;
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
	public ArrayList getCategories() {
		return categories;
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
	public ArrayList getTopics() {
		return topics;
	}

	/**
	 * @param list
	 */
	public void setCategories(ArrayList list) {
		categories = list;
	}

	/**
	 * @param list
	 */
	public void setHeadings(ArrayList list) {
		headings = list;
	}

	/**
	 * @param list
	 */
	public void setTopics(ArrayList list) {
		topics = list;
	}

	/**
	 * @return
	 */
	public boolean isComplete() {
		return complete;
	}

	/**
	 * @param b
	 */
	public void setComplete(boolean b) {
		complete = b;
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
	public ArrayList getSites() {
		return sites;
	}

	/**
	 * @param list
	 */
	public void setPlaces(ArrayList list) {
		places = list;
	}

	/**
	 * @param list
	 */
	public void setSites(ArrayList list) {
		sites = list;
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
	public ArrayList getFilms() {
		return films;
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
	public void setFilms(ArrayList list) {
		films = list;
	}

	/**
	 * @return
	 */
	public ArrayList getPhotographers() {
		return photographers;
	}

	/**
	 * @param list
	 */
	public void setPhotographers(ArrayList list) {
		photographers = list;
	}

	/**
	 * @return
	 */
	public ArrayList getAllPeople() {
		return allPeople;
	}

	/**
	 * @param list
	 */
	public void setAllPeople(ArrayList list) {
		allPeople = list;
	}

	/**
	 * @return
	 */
	public int getAllPeopleId() {
		return allPeopleId;
	}

	/**
	 * @return
	 */
	public int getPeopleId() {
		return peopleId;
	}

	/**
	 * @param i
	 */
	public void setAllPeopleId(int i) {
		allPeopleId = i;
	}

	/**
	 * @param i
	 */
	public void setPeopleId(int i) {
		peopleId = i;
	}

	/**
	 * @return
	 */
	public int getAllGroupId() {
		return allGroupId;
	}

	/**
	 * @return
	 */
	public ArrayList getAllGroups() {
		return allGroups;
	}

	/**
	 * @return
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @return
	 */
	public ArrayList getGroups() {
		return groups;
	}

	/**
	 * @param i
	 */
	public void setAllGroupId(int i) {
		allGroupId = i;
	}

	/**
	 * @param list
	 */
	public void setAllGroups(ArrayList list) {
		allGroups = list;
	}

	/**
	 * @param i
	 */
	public void setGroupId(int i) {
		groupId = i;
	}

	/**
	 * @param list
	 */
	public void setGroups(ArrayList list) {
		groups = list;
	}

	/**
	 * @param string
	 */
	public void setNotes(String string) {
		notes = string;
	}

}
