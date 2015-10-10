/*
 * Created on Jun 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.objects;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Photo  implements Photov {
	private int photo_id;
	private int photographer_id = 0;
	private int place_id = 0;
	private int camera_id = 0;
	private int topic_id = 0;
	private int category_id = 0;
	private int heading_id = 0;
	private int film_id = 0;

	private String title = new String();
	private String url = new String();
	private String thumb_url = new String();

	private int site_id = 0;

	private boolean vertical = false;
	private boolean privatePhoto = false;
	private boolean all_people = false;

	private Date date;
	private Timestamp tstamp;
	private StringBuffer notes = new StringBuffer();

	public Photo() {
	}

	public Photo(
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
		int intUserLevel,
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

	public void setPhotoId(int photo_id) {
		this.photo_id = photo_id;
	}

	public int getPhotoId() {
		return photo_id;
	}

	public void setPhotographerId(int photographer_id) {
		this.photographer_id = photographer_id;
	}

	public int getPhotographerId() {
		return photographer_id;
	}

	public void setPlaceId(int place_id) {
		this.place_id = place_id;
	}

	public int getPlaceId() {
		return place_id;
	}

	public void setCameraId(int camera_id) {
		this.camera_id = camera_id;
	}

	public int getCameraId() {
		return camera_id;
	}

	public void setTopicId(int topic_id) {
		this.topic_id = topic_id;
	}

	public int getTopicId() {
		return topic_id;
	}

	public void setCategoryId(int category_id) {
		this.category_id = category_id;
	}

	public int getCategoryId() {
		return category_id;
	}

	public void setHeadingId(int heading_id) {
		this.heading_id = heading_id;
	}

	public int getHeadingId() {
		return heading_id;
	}

	public void setFilmId(int film_id) {
		this.film_id = film_id;
	}

	public int getFilmId() {
		return film_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setThumbUrl(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public String getThumbUrl() {
		return thumb_url;
	}

	public void setSiteId(int site_id) {
		this.site_id = site_id;
	}

	public int getSiteId() {
		return site_id;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

	public boolean isVertical() {
		return vertical;
	}

	public void setPrivatePhoto(boolean privatePhoto) {
		this.privatePhoto = privatePhoto;
	}

	public boolean isPrivatePhoto() {
		return privatePhoto;
	}

	public void setComplete(boolean all_people) {
		this.all_people = all_people;
	}

	public boolean isComplete() {
		return all_people;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setTstamp(Timestamp tstamp) {
		this.tstamp = tstamp;
	}

	public Timestamp getTstamp() {
		return tstamp;
	}

	public void setNotes(StringBuffer notes) {
		this.notes = notes;
	}

	public void setNotes(String notes) {
		if (notes != null){
			this.notes = new StringBuffer(notes);
		}
	}

	public StringBuffer getNotes() {
		return notes;
	}


}
