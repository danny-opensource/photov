/*
 * Created on Jun 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package net.wgbv.photov.dam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import net.wgbv.photov.action.Constants;
import net.wgbv.photov.form.BulkForm;
import net.wgbv.photov.form.PhotoForm;
import net.wgbv.photov.objects.Group;
import net.wgbv.photov.objects.Page;
import net.wgbv.photov.objects.PageNumber;
import net.wgbv.photov.objects.Person;
import net.wgbv.photov.objects.PhotoDetails;
import net.wgbv.photov.objects.User;

import org.apache.log4j.Logger;

/**
 * @author William_G_Brownlow
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PhotoFactory {

	static Logger l = Logger.getLogger(PhotoFactory.class);

	public static void populatePhotoForm(PhotoForm pform, User user) {
		ArrayList coll = new ArrayList(1);
		PhotoDetails pd = null;
		coll = (ArrayList) getPhotoByPhotoId(pform.getPhotoId() + "", user);
		if (coll.size() > 0) {
			pd = (PhotoDetails) coll.get(0);
			pform.setComplete(pd.isComplete());
			pform.setCameraId(pd.getCameraId());
			pform.setCameraName(pd.getCameraName());
			pform.setCategoryId(pd.getCategoryId());
			pform.setCategoryName(pd.getCategoryName());
			pform.setCategoryUrl(pd.getCategoryUrl());
			String strDate = new String();
			if (pd.getDate() != null) {
				strDate = pd.getDate().toString();
			} else {
				strDate = "0000-00-00";
			}
			pform.setDate(strDate);
			pform.setFilmId(pd.getFilmId());
			pform.setFilmName(pd.getFilmName());
			pform.setHeadingId(pd.getHeadingId());
			pform.setHeadingName(pd.getHeadingName());
			pform.setHeadingUrl(pd.getHeadingUrl());
			pform.setNotes(pd.getNotes().toString());
			pform.setPeople((ArrayList) pd.getPeople());
			pform.setPhotographerId(pd.getPhotographerId());
			pform.setPhotographerName(pd.getPhotographerName());
			pform.setPhotoId(pd.getPhotoId());
			pform.setPlaceCityName(pd.getPlaceCityName());
			pform.setPlaceCountryAbbr(pd.getPlaceCountryAbbr());
			pform.setPlaceId(pd.getPlaceId());
			pform.setPlaceName(pd.getPlaceName());
			pform.setPlaceStateAbbr(pd.getPlaceStateAbbr());
			pform.setPrivatePhoto(pd.isPrivatePhoto());
			pform.setSiteId(pd.getSiteId());
			pform.setSiteName(pd.getSiteName());
			pform.setSiteUrl(pd.getSiteUrl());
			pform.setThumbUrl(pd.getThumbUrl());
			pform.setTitle(pd.getTitle());
			pform.setTopicId(pd.getTopicId());
			pform.setTopicName(pd.getTopicName());
			pform.setTopicUrl(pd.getTopicUrl());
			pform.setTstamp(pd.getTstamp().toString());
			pform.setUrl(pd.getUrl());
			pform.setVertical(pd.isVertical());
		} else {
			pform.setCategories((ArrayList) OtherFactory.getCategoriesByHeadingId(0));
			pform.setHeadings((ArrayList) OtherFactory.getHeadingsByCategoryId(0));
			pform.setTopics((ArrayList) OtherFactory.getAllTopics());
			pform.setPlaces((ArrayList) OtherFactory.getAllPlaces());
			pform.setSites((ArrayList) OtherFactory.getAllSites());
			pform.setCameras((ArrayList) OtherFactory.getAllCameras());
			pform.setFilms((ArrayList) OtherFactory.getAllFilms());
			pform.setPhotographers((ArrayList) PersonFactory.getPhotographers());

		}
	}

	public static void populateBulkForm(BulkForm bf, User user) {
		ArrayList coll = new ArrayList(1);
		bf.setDate("0000-00-00");
		bf.setPlaces((ArrayList) OtherFactory.getAllPlaces());
		bf.setSites((ArrayList) OtherFactory.getAllSites());
		bf.setCameras((ArrayList) OtherFactory.getAllCameras());
		bf.setFilms((ArrayList) OtherFactory.getAllFilms());
		bf.setPhotographers((ArrayList) PersonFactory.getPhotographers());
		bf.setPlaces((ArrayList) OtherFactory.getAllPlaces());
		bf.setSites((ArrayList) OtherFactory.getAllSites());
		bf.setCameras((ArrayList) OtherFactory.getAllCameras());
		bf.setFilms((ArrayList) OtherFactory.getAllFilms());
		bf.setPhotographers((ArrayList) PersonFactory.getPhotographers());
		bf.setTopics((ArrayList) OtherFactory.getAllTopics());
		bf.setCategories((ArrayList) OtherFactory.getCategoriesByTopicId(bf.getTopicId()));
		bf.setHeadings((ArrayList) OtherFactory.getHeadingsByCategoryId(bf.getCategoryId()));

	}

	public static void setPhotoForm(PhotoForm pf, User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();

			String query = new String();
			String queryAllGrp = new String();
			if (pf.getPhotoId() < 1) {
				query = " INSERT photo " + " ( " + " photo.photographer_id, " + " photo.place_id, " + " photo.camera_id, " + " photo.category_id, "
						+ " photo.heading_id, " + " photo.film_id, " + " photo.title, " + " photo.url, " + " photo.thumb_url, " + " photo.date, "
						+ " photo.private, " + " photo.site_id, " + " photo.topic_id, " + " photo.vertical, " + " photo.all_people, "
						+ " photo.notes " + " ) " + " VALUES " + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			} else {
				query = " UPDATE photo " + " SET " + " photo.photographer_id = ?, " + " photo.place_id = ?, " + " photo.camera_id = ?, "
						+ " photo.category_id = ?, " + " photo.heading_id = ?, " + " photo.film_id = ?, " + " photo.title = ?, " + " photo.url = ?, "
						+ " photo.thumb_url = ?, " + " photo.date = ?, " + " photo.private = ?, " + " photo.site_id = ?, " + " photo.topic_id = ?, "
						+ " photo.vertical = ?, " + " photo.all_people = ?, " + " photo.notes = ? " + " WHERE " + " photo.photo_id = "
						+ pf.getPhotoId();
			}

			ps = conn.prepareStatement(query);

			ps.setInt(1, pf.getPhotographerId());
			ps.setInt(2, pf.getPlaceId());
			ps.setInt(3, pf.getCameraId());
			ps.setInt(4, pf.getCategoryId());
			ps.setInt(5, pf.getHeadingId());
			ps.setInt(6, pf.getFilmId());
			ps.setString(7, pf.getTitle());
			ps.setString(8, pf.getUrl());
			ps.setString(9, pf.getThumbUrl());
			ps.setString(10, pf.getDate());
			ps.setString(11, (pf.isPrivatePhoto() ? Constants.YES : Constants.NO));
			ps.setInt(12, pf.getSiteId());
			ps.setInt(13, pf.getTopicId());
			ps.setString(14, (pf.isVertical() ? Constants.YES : Constants.NO));
			ps.setString(15, (pf.isComplete() ? Constants.YES : Constants.NO));
			ps.setString(16, pf.getNotes());
			ps.executeUpdate();
			l.info("Remove Photo from new Queue? " + pf.isComplete());
			if (pf.isComplete()) {
				l.info("Try to Remove photo " + pf.getPhotoId());
				QueueFactory.removePhotoQueue(pf.getPhotoId());
			}
			getPhotoId(pf);
			setGroupByPhotoId(pf.getPhotoId(), user);
			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setPhotoForm(PhotoForm pf, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static void setBulkForm(BulkForm bf, User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();

			String query = new String();
			String queryAllGrp = new String();
			query = " INSERT photo " + " ( " + " photo.site_id, " + " photo.topic_id, " + " photo.category_id, " + " photo.heading_id, "
					+ " photo.place_id, " + " photo.photographer_id, " + " photo.camera_id, " + " photo.film_id, " + " photo.title, "
					+ " photo.date, " + " photo.private, " + " photo.vertical, " + " photo.all_people, " + " photo.url, " + " photo.thumb_url "
					+ " ) " + " VALUES " + " (?,?,?,?,?, " + "  ?,?,?,?,?, " + "  ?,?,?,?,? ) ";
			ps = conn.prepareStatement(query);

			ps.setInt(1, bf.getSiteId());
			ps.setInt(2, bf.getTopicId());
			ps.setInt(3, bf.getCategoryId());
			ps.setInt(4, bf.getHeadingId());
			ps.setInt(5, bf.getPlaceId());
			ps.setInt(6, bf.getPhotographerId());
			ps.setInt(7, bf.getCameraId());
			ps.setInt(8, bf.getFilmId());
			ps.setString(9, bf.getTitle());
			ps.setString(10, bf.getDate());
			ps.setString(11, Constants.YES); // Photo is Private
			ps.setString(12, (bf.isVertical() ? Constants.YES : Constants.NO));
			ps.setString(13, (bf.isComplete() ? Constants.YES : Constants.NO));

			ArrayList al = bf.getUrlList();
			for (int i = 0; i < al.size(); i++) {
				String url = (String) al.get(i);
				ps.setString(14, url.trim() + Constants.JPG);
				ps.setString(15, url.trim() + Constants.THUMB + Constants.JPG);
				ps.executeUpdate();
				setGroupByPhotoId(getPhotoId(), user);
				PersonFactory.addGroupPhoto(getPhotoId(), 2);
				QueueFactory.addPhotoQueue(getPhotoId());
			}

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setBulkForm(BulkForm bf, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	private static void setGroupByPhotoId(int photoId, User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ConnectionFactory.getConnection();

			String queryDeleteGroupPhoto = " DELETE FROM photo_grp " + " WHERE photo_id = ? AND " + " grp_id = ? ";

			String queryAddGroupPhoto = " INSERT INTO photo_grp " + " (photo_id,grp_id) " + " VALUES (?,?) ";

			// Add to this User's grp
			ps = conn.prepareStatement(queryDeleteGroupPhoto);
			ps.setInt(1, photoId);
			ps.setInt(2, user.getUserGroupId());
			ps.executeUpdate();
			ps = conn.prepareStatement(queryAddGroupPhoto);
			ps.setInt(1, photoId);
			ps.setInt(2, user.getUserGroupId());
			ps.executeUpdate();

			// Add to ALL grp
			ps = conn.prepareStatement(queryDeleteGroupPhoto);
			ps.setInt(1, photoId);
			ps.setInt(2, 1);
			ps.executeUpdate();
			ps = conn.prepareStatement(queryAddGroupPhoto);
			ps.setInt(1, photoId);
			ps.setInt(2, 1);
			ps.executeUpdate();

			PhotoFactory.closeConn(null, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setGroupByPhotoId(int photoId, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(null, ps, conn);
		}
	}

	public static void setPhotoPeople(PhotoForm pform) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();

			String queryDeletePhoto = " DELETE FROM person_photo " + " WHERE person_photo.photo_id = ? ";

			ps = conn.prepareStatement(queryDeletePhoto);
			ps.setInt(1, pform.getPhotoId());
			ps.executeUpdate();

			String queryTouchPhoto = " UPDATE photo " + " SET tstamp = NULL " + " WHERE photo.photo_id = ? ";
			ps = conn.prepareStatement(queryTouchPhoto);
			ps.executeUpdate();

			for (int i = 0; i < pform.getPeople().size(); i++) {
				String queryUpdatePhoto = " INSERT person_photo " + " ( person_photo.person_id, person_photo.photo_id ) " + " VALUES (?,?) ";

				ps.setInt(1, ((Person) ((ArrayList) pform.getPeople()).get(i)).getPersonId());
				ps.setInt(2, pform.getPhotoId());

				ps.executeUpdate();
			}

			PhotoFactory.closeConn(rs, ps, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setPhotoPeople(PhotoForm pform)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, ps, conn);
		}
	}

	public static void getPhotoId(PhotoForm pf) {
		if ((pf.getPhotoId() > 0) || (pf.getUrl().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query = "SELECT " + " photo_id " + " FROM " + " photo " + " WHERE " + " url = '" + pf.getUrl() + "'" + " ORDER BY photo_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					pf.setPeopleId(rs.getInt("photo_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPhotoId(PhotoForm pf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static int getPhotoId() {
		int photoId = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query = "SELECT " + " photo_id " + " FROM " + " photo " + " ORDER BY photo_id DESC " + " LIMIT 1";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					photoId = rs.getInt("photo_id");
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPhotoId() ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return photoId;
	}

	public static Collection getPhotos() {
		return getPhotos(null);
	}

	public static Collection getPhotos(User user) {
		return getPhotos(" topic.topic_id ", "", "", " GROUP BY topic.topic_id ", " ORDER BY topic_name ", " ", Constants.TOPIC_ID_KEY, user);
	}

	public static Collection getPhotosByTopicId(int intTopicId, User user) {
		return getPhotos(" category.category_id ", " topic.topic_id = " + intTopicId, "", " GROUP BY category.category_id ",
				" ORDER BY category_name ", " ", Constants.CATEGORY_ID_KEY, user);
	} // End of getPhotosByTopicId

	public static Collection getPhotosByCategoryId(int intCategoryId, User user) {
		return getPhotos(" heading.heading_id ", " category.category_id = " + intCategoryId, "", " GROUP BY heading.heading_id ",
				" ORDER BY heading_name ", " ", Constants.HEADING_ID_KEY, user);
	} // End of getPhotosByCategoryId

	public static Collection getPhotos(String strSelect, String strWhere, String strTagWhere, String strGroup, String strOrder, String strLimit,
			String strKeyType, User user) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String strAnd = new String();
		int intHeadingId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				String query = new String();
				String strUserWhere = new String();
				if ((strKeyType != null) && (!(strKeyType.equalsIgnoreCase(Constants.TOPIC_ID_KEY)))) {
					strAnd = " AND ";
				}

				query = " SELECT DISTINCT " + strSelect + " FROM category, photo, heading, topic, photo_grp " + " WHERE "
						+ " photo.topic_id = topic.topic_id AND" + " photo.category_id = category.category_id AND"
						+ " photo.photo_id = photo_grp.photo_id " + getUserWhere(user) + " heading.heading_id = photo.heading_id " + strAnd
						+ strWhere + strGroup + strOrder + strLimit;
				rs = stmt.executeQuery(query);
				if (rs != null) {
					while (rs.next()) {
						if (strKeyType.equalsIgnoreCase(Constants.HEADING_ID_KEY)) {
							coll.add(getPhotoByHeadingId(rs.getInt(1), user));
						} else if (strKeyType.equalsIgnoreCase(Constants.CATEGORY_ID_KEY)) {
							coll.add(getPhotoByCategoryId(rs.getInt(1), user));
						} else if (strKeyType.equalsIgnoreCase(Constants.TOPIC_ID_KEY)) {
							coll.add(getPhotoByTopicId(rs.getInt(1), user));
						}
					}
				}
			} else {
				coll.add(new PhotoDetails());
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPhotos(String strSelect,String strWhere,String strTagWhere,String strGroup,String strOrder,String strLimit,String strKeyType,User user)");
			l.error(sqle);
		} finally {
			closeConn(rs, stmt, conn);
		}

		return coll;
	} // End of getPhotos

	public static void getPhotosByHeadingId(int intHeadingId, Page page, ArrayList collPhotod, ArrayList collPage, User user) {
		getPhotosByWhere("", "", " photo.heading_id = " + intHeadingId, null, " LIMIT " + (Constants.PER_PAGE * page.getCurrentPage()) + ", "
				+ Constants.PER_PAGE, collPhotod, collPage, page.getCurrentPage(), intHeadingId, Constants.HEADING_ID_KEY, user, Constants.PER_PAGE);
	} // End of getPhotosByPlaceId

	public static void getPhotosByPersonId(int intPersonId, Page page, ArrayList collPhotod, ArrayList collPage, User user) {
		getPhotosByWhere("", ", person_photo ", " person_photo.photo_id = photo.photo_id AND person_photo.person_id = " + intPersonId, null,
				" LIMIT " + (Constants.PER_PAGE * page.getCurrentPage()) + ", " + Constants.PER_PAGE, collPhotod, collPage, page.getCurrentPage(),
				intPersonId, Constants.PERSON_ID_KEY, user, Constants.PER_PAGE);
	} // End of getPhotosByPlaceId

	public static void getPhotosByPlaceId(int intPlaceId, Page page, ArrayList collPhotod, ArrayList collPage, User user) {
		getPhotosByWhere("", "", " photo.place_id = " + intPlaceId, null, " LIMIT " + (Constants.PER_PAGE * page.getCurrentPage()) + ", "
				+ Constants.PER_PAGE, collPhotod, collPage, page.getCurrentPage(), intPlaceId, Constants.PLACE_ID_KEY, user, Constants.PER_PAGE);
	} // End of getPhotosByPlaceId

	public static PhotoDetails getPhotoByTopicId(int intTopicId, User user) {
		ArrayList coll = new ArrayList(10);
		getPhotosByWhere(" RAND() as randNum, ", " photo.topic_id = " + intTopicId, " ORDER BY randNum ", " LIMIT 1 ", coll, user);
		if (coll.size() > 0) {
			return (PhotoDetails) coll.get(0);
		} else {
			return new PhotoDetails();
		}
	}

	public static PhotoDetails getPhotoByCategoryId(int intCategoryId, User user) {
		ArrayList coll = new ArrayList(10);
		getPhotosByWhere(" RAND() as randNum, ", " photo.category_id = " + intCategoryId, " ORDER BY randNum ", " LIMIT 1 ", coll, user);
		if (coll.size() > 0) {
			return (PhotoDetails) coll.get(0);
		} else {
			return new PhotoDetails();
		}
	}

	public static PhotoDetails getPhotoByHeadingId(int intHeadingId, User user) {
		ArrayList coll = new ArrayList(10);
		getPhotosByWhere(" RAND() as randNum, ", " photo.heading_id = " + intHeadingId, " ORDER BY randNum ", " LIMIT 1 ", coll, user);
		if (coll.size() > 0) {
			return (PhotoDetails) coll.get(0);
		} else {
			return new PhotoDetails();
		}
	}

	public static Collection getPhotosByPersonWith(Person p1, Person p2, User user) {
		ArrayList coll = new ArrayList();
		ArrayList temp = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query = " SELECT photo_id, COUNT(person_id) AS cnt " + " FROM person_photo " + " WHERE person_id IN(" + p1.getPersonId() + ","
					+ p2.getPersonId() + ") " + " GROUP BY photo_id ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					if (rs.getInt("cnt") > 1) {
						temp = ((ArrayList) getPhotoByPhotoId(rs.getInt("photo_id"), user));
						if (temp.size() > 0) {
							coll.add(temp.get(0));
						}
					}
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPhotosByPersonWith(Person p1,Person p2,User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return coll;
	}

	public static Collection getPhotoByPhotoId(String strPhotoId, User user) {
		ArrayList coll = new ArrayList(10);
		getPhotosByWhere("", " photo.photo_id = " + strPhotoId, "", coll, user);
		return coll;
	}

	public static Collection getPhotoByPhotoId(int photoId, User user) {
		return getPhotoByPhotoId(photoId + "", user);
	}

	private static void getPhotosByWhere(String strSelect, String strWhere, String strLimit, Collection collPhotod, User user) {
		getPhotosByWhere(strSelect, "", strWhere, null, strLimit, collPhotod, null, 0, 0, null, user, Constants.PER_PAGE);
	}

	private static void getPhotosByWhere(String strSelect, String strWhere, String strOrder, String strLimit, Collection collPhotod, User user) {
		getPhotosByWhere(strSelect, "", strWhere, strOrder, strLimit, collPhotod, null, 0, 0, null, user, Constants.PER_PAGE);
	}

	public static void getPhotosByWhere(String strSelect, String strFrom, String strWhere, String strOrder, String strLimit, Collection collPhotod,
			Collection collPage, int currentPage, int intKeyId, String strKeyType, User user, int intPerPage) {
		int intCount = 0;
		boolean blContComplete = true;
		boolean blContNew = false;
		boolean blContPrivate = false;
		boolean blUseCont = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			if (strOrder == null) {
				strOrder = new String(" ORDER BY photo.tstamp DESC, photo.photo_id DESC ");
			}
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String contBase = new String();
			if ((strKeyType != null)
					&& ((strKeyType.equalsIgnoreCase(Constants.PERSON_ID_KEY)) || (strKeyType.equalsIgnoreCase(Constants.PLACE_ID_KEY))
							|| (strKeyType.equalsIgnoreCase(Constants.HEADING_ID_KEY)) || (strKeyType.equalsIgnoreCase(Constants.NEW_QUEUE_KEY)))) {
				// Dont do squat.
				blUseCont = false;
			} else {
				blUseCont = true;
				contBase = " SELECT DISTINCT photo.photo_id FROM photo WHERE ";
				String contComplete = new String(" AND photo.all_people = 'No' ");
				String contNew = new String(" AND DATE_SUB(CURDATE(),INTERVAL 15 DAY) <= photo.tstamp ");
				String contPrivate = new String(" AND private = 'Yes' ");
				contComplete = contBase + strWhere + contComplete;
				contNew = contBase + strWhere + contNew;
				contPrivate = contBase + strWhere + contPrivate;
				rs = stmt.executeQuery(contComplete);
				if (rs != null) {
					if (rs.next()) {
						blContComplete = false;
					}
				}
				rs = stmt.executeQuery(contNew);
				if (rs != null) {
					if (rs.next()) {
						blContNew = true;
					}
				}
				rs = stmt.executeQuery(contPrivate);
				if (rs != null) {
					if (rs.next()) {
						blContPrivate = true;
					}
				}
			}

			String query = new String();

			query = " SELECT DISTINCT " + strSelect + " ((NOW() - photo.tstamp ) < 15000000 ) as newTime, " + " photo.photo_id, "
					+ " photo.site_id, " + " photo.topic_id, " + " photo.category_id, " + " photo.heading_id, " + " photo.place_id, "
					+ " photo.photographer_id, " + " photo.camera_id, " + " photo.film_id, " + " photo.title, " + " photo.url, "
					+ " photo.thumb_url, " + " photo.date, " + " photo.tstamp, " + " photo.notes, " + " photo.vertical, " + " photo.private, "
					+ " photo.all_people, " + " site.site_name, " + " site.site_url, " + " topic.topic_name, " + " topic.topic_url, "
					+ " category.category_name, " + " category.category_url, " + " heading.heading_name, " + " heading.heading_url, "
					+ " place.place_name, " + " place.city, " + " state.state_abbr, " + " country.country_abbr, " + " camera.camera_name, "
					+ " film.film_name " + " FROM photo, site, topic, category, heading, " + " camera, film, place, state, country, photo_grp "
					+ strFrom + " WHERE " + " photo.site_id = site.site_id AND " + " photo.topic_id = topic.topic_id AND "
					+ " photo.category_id = category.category_id AND " + " photo.heading_id = heading.heading_id AND "
					+ " photo.place_id = place.place_id AND " + " place.state_id = state.state_id AND " + " photo.film_id = film.film_id AND "
					+ " photo.photo_id = photo_grp.photo_id AND " + " photo.camera_id = camera.camera_id AND "
					+ " state.country_id = country.country_id " + getUserWhere(user) + strWhere + strOrder;
			rs = stmt.executeQuery(query + strLimit);
			if (rs != null) {
				while (rs.next()) {
					collPhotod.add(getFullPhotoDetailsByRS(rs, blContComplete, blContNew, blContPrivate, blUseCont));
				}
			}

			if (strKeyType != null) {
				rs = stmt.executeQuery(query);
				if (rs != null) {
					while (rs.next()) {
						intCount++;
					}
				}

				if (intCount % intPerPage == 0) {
					intCount = intCount / intPerPage;
				} else {
					intCount = (intCount / intPerPage) + 1;
				}
				for (int i = 0; i < intCount; i++) {
					collPage.add(new PageNumber(i, currentPage, intKeyId, 0, strKeyType));
				}
			}

			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPhotosByWhere( " + " String strSelect, String strFrom, String strWhere, String strOrder, "
					+ " String strLimit, Collection collPhotod, Collection collPage, "
					+ " int currentPage, int intKeyId, String strKeyType, User user)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

	}

	protected static void closeConn(ResultSet rs1, Statement stmt, Connection conn) {
		if (rs1 != null) {
			try {
				rs1.close();
			} catch (SQLException sqle) {
			}
			rs1 = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqle) {
			}
			stmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqle) {
			}
			conn = null;
		}
	}

	private static PhotoDetails getFullPhotoDetailsByRS(ResultSet rs, boolean contComplete, boolean contNew, boolean contPrivate, boolean useCont)
			throws SQLException {
		ArrayList coll = new ArrayList(10);

		PhotoDetails photod = getPhotoDetailsByRS(rs);

		if (useCont) {
			photod.setContPrivate(contPrivate);
			photod.setContNew(contNew);
			photod.setContComplete(contComplete);
		} else {
			photod.setContPrivate(photod.isPrivatePhoto());
			photod.setContComplete(photod.isComplete());
		}

		photod.setPlaceName(rs.getString("place.place_name"));

		photod.setPlaceCityName(rs.getString("place.city"));
		photod.setPlaceStateAbbr(rs.getString("state.state_abbr"));
		photod.setPlaceCountryAbbr(rs.getString("country.country_abbr"));
		photod.setCameraName(rs.getString("camera.camera_name"));
		photod.setFilmName(rs.getString("film.film_name"));

		photod.setPhotographerName(PersonFactory.getPersonByPersonId(rs.getInt("photo.photographer_id")).getPersonName());
		photod.setPeople(PersonFactory.getPeopleByPhotoId(rs.getInt("photo.photo_id")));

		return photod;
	}

	private static PhotoDetails getPhotoDetailsByRS(ResultSet rs) throws SQLException {
		PhotoDetails photod = new PhotoDetails(rs.getInt("photo.photo_id"), rs.getInt("photo.site_id"), rs.getInt("photo.topic_id"),
				rs.getInt("photo.category_id"), rs.getInt("photo.heading_id"), rs.getInt("photo.place_id"), rs.getInt("photo.photographer_id"),
				rs.getInt("photo.camera_id"), rs.getInt("photo.film_id"), rs.getString("photo.title"), rs.getString("photo.url"),
				rs.getString("photo.thumb_url"), false, false, false, rs.getDate("photo.date"), rs.getTimestamp("photo.tstamp"),
				rs.getString("photo.notes"));

		photod.setVertical((rs.getString("photo.vertical").equalsIgnoreCase(Constants.YES) ? true : false));
		photod.setPrivatePhoto((rs.getString("photo.private").equalsIgnoreCase(Constants.YES) ? true : false));
		photod.setComplete((rs.getString("photo.all_people").equalsIgnoreCase(Constants.YES) ? true : false));
		String str = rs.getString("newTime");
		photod.setContNew((str.equalsIgnoreCase("1") ? true : false));
		photod.setCategoryName(rs.getString("category.category_name"));
		photod.setCategoryUrl(rs.getString("category.category_url"));
		photod.setHeadingName(rs.getString("heading.heading_name"));
		photod.setHeadingUrl(rs.getString("heading.heading_url"));
		photod.setTopicName(rs.getString("topic.topic_name"));
		photod.setTopicUrl(rs.getString("topic.topic_url"));
		photod.setSiteName(rs.getString("site.site_name"));
		photod.setSiteUrl(rs.getString("site.site_url"));

		return photod;
	}

	public static String getUserWhere(User user) {
		StringBuffer sb = new StringBuffer(" AND ");

		if (user != null) {
			ArrayList coll = new ArrayList();
			coll = user.getGroups();
			if (coll.size() > 0) {
				sb.append(" (");
				for (int i = 0; i < coll.size(); i++) {
					if (((Group) coll.get(i)).getGroupId() > 0) {
						sb.append(" photo_grp.grp_id = " + ((Group) coll.get(i)).getGroupId() + " ");
					}
					if (i != (coll.size() - 1)) {
						sb.append(" OR ");
					}
				}
				sb.append(") AND");
			}

		} else {
			sb.append(" AND photo_grp.grp_id = 3 AND ");
		}
		return sb.toString();
	}

}