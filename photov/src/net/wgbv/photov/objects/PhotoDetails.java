/*
 * Created on Jun 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhotoDetails extends Photo implements Comparable, Photov {
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
	private boolean contNew = false;
	private boolean contComplete = false;
	private boolean contPrivate = false;

	public PhotoDetails() {
		new PhotoDetails(
			1,
			5,
			2,
			13,
			28,
			26,
			2,
			1,
			8,
			"20 inch Rainbow Trout.  <br> Black Caddis Fly on a 5-weight Orvis Rod.",
			"99yell_20inch.jpg",
			"99yell_20inch_thumb.jpg",
			false,
			false,
			true,
			null,
			null,
			null);
	}

	public PhotoDetails(
		int photoId,
		int siteId,
		int topicId,
		int categoryId,
		int headingId,
		int placeId,
		int photographerId,
		int cameraId,
		int filmId,
		String title,
		String url,
		String thumbUrl,
		boolean vertical,
		boolean privatePhoto,
		boolean complete,
		Date date,
		Timestamp tstamp,
		String notes) {

		setPhotoId(photoId);
		setPhotographerId(photographerId);
		setPlaceId(placeId);
		setCameraId(cameraId);
		setTopicId(topicId);
		setCategoryId(categoryId);
		setHeadingId(headingId);
		setFilmId(filmId);
		setTitle(title);
		setUrl(url);
		setThumbUrl(thumbUrl);
		setSiteId(siteId);
		setVertical(vertical);
		setPrivatePhoto(privatePhoto);
		setComplete(complete);
		setDate(date);
		setTstamp(tstamp);
		setNotes(notes);
	}

	public PhotoDetails(Photo photo) {
		setPhotoId(photo.getPhotoId());
		setPhotographerId(photo.getPhotographerId());
		setPlaceId(photo.getPlaceId());
		setCameraId(photo.getCameraId());
		setTopicId(photo.getTopicId());
		setCategoryId(photo.getCategoryId());
		setHeadingId(photo.getHeadingId());
		setFilmId(photo.getFilmId());
		setTitle(photo.getTitle());
		setUrl(photo.getUrl());
		setThumbUrl(photo.getThumbUrl());
		setSiteId(photo.getSiteId());
		setVertical(photo.isVertical());
		setPrivatePhoto(photo.isPrivatePhoto());
		setComplete(photo.isComplete());
		setDate(photo.getDate());
		setTstamp(photo.getTstamp());
		setNotes(photo.getNotes());
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
	public String getFilmName() {
		return filmName;
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
	public Collection getPeople() {
		return people;
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
	public String getPlaceName() {
		return placeName;
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
	 * @param string
	 */
	public void setCameraName(String string) {
		cameraName = string;
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
	public void setFilmName(String string) {
		filmName = string;
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
	 * @param collection
	 */
	public void setPeople(Collection collection) {
		people = new ArrayList(collection);
	}

	/**
	 * @param string
	 */
	public void setPhotographerName(String string) {
		photographerName = string;
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
	 * @param list
	 */
	public void setPeople(ArrayList list) {
		people = list;
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
	public String getPlaceStateAbbr() {
		return placeStateAbbr;
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
	 * @param string
	 */
	public void setPlaceStateAbbr(String string) {
		placeStateAbbr = string;
	}

	/**
	 * @return
	 */
	public boolean isContComplete() {
		return contComplete;
	}

	/**
	 * @return
	 */
	public boolean isContNew() {
		return contNew;
	}

	/**
	 * @return
	 */
	public boolean isContPrivate() {
		return contPrivate;
	}

	/**
	 * @param b
	 */
	public void setContComplete(boolean b) {
		contComplete = b;
	}

	/**
	 * @param b
	 */
	public void setContNew(boolean b) {
		contNew = b;
	}

	/**
	 * @param b
	 */
	public void setContPrivate(boolean b) {
		contPrivate = b;
	}

	/* Stuff for Comparable
	 * 
	 * */

	public boolean equals(Object o) {
		if (!(o instanceof PhotoDetails))
			return false;
		PhotoDetails pd = (PhotoDetails) o;
		return this.getPhotoId() == pd.getPhotoId()
			&& this.getTstamp().equals(pd.getTstamp());

	}

	public int hashCode() {
		return 31 * getTstamp().hashCode() + getPhotoId();
	}

	public String toString() {
		return getPhotoId() + "";
	}

	public int compareTo(Object o) {
		PhotoDetails pd = (PhotoDetails) o;
		int tstampCmp = this.getTstamp().compareTo(pd.getTstamp());
		if (tstampCmp != 0) {
			return -tstampCmp;
		} else {
			int photoIdCmp =
				(this.getPhotoId() > pd.getPhotoId()
					? -1
					: (this.getPhotoId() < pd.getPhotoId() ? 1 : 0));
			if (photoIdCmp != 0) {
				return photoIdCmp;
			} else {
				return 0;
			}
		}
	}

}
