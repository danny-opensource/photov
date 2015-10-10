/*
 * Created on Jul 3, 2004
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.wgbv.photov.action.Constants;
import net.wgbv.photov.form.CameraForm;
import net.wgbv.photov.form.CategoryForm;
import net.wgbv.photov.form.CountryForm;
import net.wgbv.photov.form.FilmForm;
import net.wgbv.photov.form.HeadingForm;
import net.wgbv.photov.form.HeirarchyForm;
import net.wgbv.photov.form.PlaceForm;
import net.wgbv.photov.form.PlaceListForm;
import net.wgbv.photov.form.SiteForm;
import net.wgbv.photov.form.StateForm;
import net.wgbv.photov.form.TopicForm;
import net.wgbv.photov.objects.Camera;
import net.wgbv.photov.objects.Category;
import net.wgbv.photov.objects.Country;
import net.wgbv.photov.objects.Film;
import net.wgbv.photov.objects.Heading;
import net.wgbv.photov.objects.Heirarchy;
import net.wgbv.photov.objects.Person;
import net.wgbv.photov.objects.PhotoDetails;
import net.wgbv.photov.objects.PlaceDetails;
import net.wgbv.photov.objects.Site;
import net.wgbv.photov.objects.State;
import net.wgbv.photov.objects.Topic;
import net.wgbv.photov.objects.User;

import org.apache.log4j.Logger;

/**
 * @author William_G_Brownlow
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OtherFactory {

	static Logger l = Logger.getLogger(OtherFactory.class);

	public static void populatePlaceForm(PlaceForm pf) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query =
				" SELECT "
					+ " place.place_id, state.state_abbr, place.city, "
					+ " country.country_id, state.state_id, "
					+ " country.country_abbr, place.place_name "
					+ " FROM place, state, country "
					+ " WHERE place.state_id = state.state_id "
					+ " AND state.country_id = country.country_id "
					+ " AND place.place_id = "
					+ pf.getPlaceId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					pf.setPlaceId(rs.getInt("place.place_id"));
					pf.setCountryId(rs.getInt("country.country_id"));
					pf.setStateId(rs.getInt("state.state_id"));
					pf.setPlaceName(rs.getString("place.place_name"));
					pf.setCity(rs.getString("place.city"));
				}
			}
			populatePlaceSelect(pf);
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populatePlaceForm(PlaceForm pf) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

	}

	private static void populatePlaceSelect(PlaceForm pf) {
		pf.setCountries((ArrayList) getAllCountries());
		pf.setStates((ArrayList) getStatesByCountryId(pf.getCountryId()));
		if (pf.getCountryId() > 0) {
			return;
		} else {
			pf.setCountryId(1);
		}

	}

	public static void populatePlaceListForm(PlaceListForm plf) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query =
				" SELECT "
					+ " place.place_id, state.state_abbr, place.city, "
					+ " country.country_id, state.state_id, "
					+ " country.country_abbr, place.place_name "
					+ " FROM place, state, country "
					+ " WHERE place.state_id = state.state_id "
					+ " AND state.country_id = country.country_id "
					+ " ORDER BY country.country_abbr, state.state_abbr, place.city, place.place_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					PlaceDetails place = new PlaceDetails();
					place.setPlaceId(rs.getInt("place.place_id"));
					place.setStateAbbr(rs.getString("state.state_abbr"));
					place.setCountryId(rs.getInt("country.country_id"));
					place.setStateId(rs.getInt("state.state_id"));
					place.setCountryAbbr(rs.getString("country.country_abbr"));
					String temp = rs.getString("place.place_name");
					if (temp.equalsIgnoreCase("")) {
						place.setPlaceName("(none)");
					} else {
						place.setPlaceName(temp);
					}
					temp = rs.getString("place.city");
					if (temp.equalsIgnoreCase("")) {
						place.setCity("(none)");
					} else {
						place.setCity(temp);
					}
					coll.add(place);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in populatePlaceListForm(PlaceListForm plf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		plf.setPlaceList(coll);
	}

	public static void populateHeirarchyForm(
		HeirarchyForm heirarchyForm,
		User user) {
		ArrayList coll = new ArrayList();
		ArrayList coll1 = null;
		ArrayList coll2 = null;
		ArrayList coll3 = null;
		PhotoDetails pd;
		PhotoDetails pd1;
		PhotoDetails pd2;
		int i = 0;
		int j = 0;
		int k = 0;

		coll1 = (ArrayList) PhotoFactory.getPhotos(user);
		for (i = 0; i < coll1.size(); i++) {
			pd = (PhotoDetails) coll1.get(i);
			coll2 =
				(ArrayList) PhotoFactory.getPhotosByTopicId(
					pd.getTopicId(),
					user);
			for (j = 0; j < coll2.size(); j++) {
				pd1 = (PhotoDetails) coll2.get(j);
				coll3 =
					(ArrayList) PhotoFactory.getPhotosByCategoryId(
						pd1.getCategoryId(),
						user);
				for (k = 0; k < coll3.size(); k++) {
					pd2 = (PhotoDetails) coll3.get(k);
					Heirarchy h = new Heirarchy();

					h.setTopicId(pd.getTopicId());
					h.setTopicName(pd.getTopicName());
					h.setTopicNew(pd.isContNew());
					h.setTopicComplete(pd.isContComplete());
					h.setTopicPriv(pd.isContPrivate());
					h.setCategoryId(pd1.getCategoryId());
					h.setCategoryName(pd1.getCategoryName());
					h.setCategoryNew(pd1.isContNew());
					h.setCategoryComplete(pd1.isContComplete());
					h.setCategoryPriv(pd1.isContPrivate());
					h.setHeadingId(pd2.getHeadingId());
					h.setHeadingName(pd2.getHeadingName());
					h.setHeadingNew(pd2.isContNew());
					h.setHeadingComplete(pd2.isContComplete());
					h.setHeadingPriv(pd2.isContPrivate());

					coll.add(h);
				}
			}
		}

		heirarchyForm.setHeirarchy(coll);
	}

	public static PlaceDetails getPlaceByPlaceId(int placeId) {
		PlaceDetails placed = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				" SELECT "
					+ " place.place_id, place.place_name, place.city, "
					+ " state.state_id, state.state_name, state.state_abbr, "
					+ " country.country_id, country.country_name, country.country_abbr "
					+ " FROM place, state, country "
					+ " WHERE "
					+ " place.state_id = state.state_id AND "
					+ " state.country_id = country.country_id AND "
					+ " place.place_id = "
					+ placeId;
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					placed =
						new PlaceDetails(
							rs.getInt("place.place_id"),
							rs.getInt("state.state_id"),
							rs.getString("place.city"),
							rs.getString("" + "place.place_name"));
					placed.setStateName(rs.getString("state.state_name"));
					placed.setStateAbbr(rs.getString("state.state_abbr"));
					placed.setCountryId(rs.getInt("country.country_id"));
					placed.setCountryName(rs.getString("country.country_name"));
					placed.setCountryAbbr(rs.getString("country.country_abbr"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPlaceByPlaceId(int placeId) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		return placed;

	}

	public static Person getPersonByPersonId(int personId) {
		Person person = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " person_id, fname, mname, lname, email "
					+ " FROM "
					+ " person "
					+ " WHERE "
					+ " person_id = "
					+ personId;
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					person =
						new Person(
							rs.getInt("person_id"),
							rs.getString("fname"),
							rs.getString("mname"),
							rs.getString("lname"));
					person.setEmail(rs.getString("email"));
					//person.setClassYear();
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPersonByPersonId(int personId) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return person;
	}

	public static Collection getHeadingsByCategoryId(int categoryId) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (categoryId < 1) {
			categoryId = 1;
		}
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT DISTINCT "
					+ " heading.heading_id, heading.heading_name, "
					+ " heading.heading_url, heading.heading_date, "
					+ " heading.category_id "
					+ " FROM "
					+ " heading "
					+ " WHERE "
					+ " heading.category_id = "
					+ categoryId
					+ " ORDER BY heading.heading_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Heading heading = new Heading();
					heading.setHeadingId(rs.getInt("heading.heading_id"));
					heading.setHeadingName(
						rs.getString("heading.heading_name"));
					heading.setHeadingUrl(rs.getString("heading.heading_url"));
					heading.setHeadingDate(
						rs.getString("heading.heading_date"));
					heading.setCategoryId(rs.getInt("heading.category_id"));
					coll.add(heading);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in getHeadingsByCategoryId(int categoryId) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static void populateHeadingForm(HeadingForm hf) {

		if (hf.getHeadingId() < 1) {
			populateHeadingSelect(hf);
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " heading.heading_id, heading.heading_name, "
					+ " heading.heading_url, heading.heading_date, "
					+ " category.category_id, category.topic_id "
					+ " FROM "
					+ " heading, category "
					+ " WHERE "
					+ " heading.category_id = category.category_id AND "
					+ " heading.heading_id = "
					+ hf.getHeadingId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					hf.setHeadingId(rs.getInt("heading.heading_id"));
					hf.setHeadingName(rs.getString("heading.heading_name"));
					hf.setHeadingUrl(rs.getString("heading.heading_url"));
					hf.setHeadingDate(rs.getString("heading.heading_date"));
					hf.setCategoryId(rs.getInt("category.category_id"));
					hf.setTopicId(rs.getInt("category.topic_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateHeadingForm(HeadingForm hf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		populateHeadingSelect(hf);

	}

	public static void setHeadingForm(HeadingForm hf) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int categoryId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();

			String query = new String();

			if (hf.getHeadingId() < 1) {
				query =
					"INSERT INTO heading "
						+ " (heading_name, "
						+ " heading_url, "
						+ " heading_date, "
						+ " category_id ) "
						+ " VALUES "
						+ " (?,?,?,?) ";

			} else {
				query =
					"UPDATE heading "
						+ " SET "
						+ " heading_name = ?, "
						+ " heading_url = ?, "
						+ " heading_date = ?, "
						+ " category_id = ? "
						+ " WHERE "
						+ " heading_id = "
						+ hf.getHeadingId();
			}

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, hf.getHeadingName());
			ps.setString(2, hf.getHeadingUrl());
			ps.setString(3, hf.getHeadingDate());
			ps.setInt(4, hf.getCategoryId());

			ps.executeUpdate();

			getHeadingId(hf);

			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setHeadingForm(HeadingForm hf) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		populateHeadingSelect(hf);
	}

	public static void populateHeadingSelect(HeadingForm hf) {
		hf.setTopics((ArrayList) OtherFactory.getAllTopics());

		if (hf.getTopicId() > 0) {
			hf.setCategories(
				(ArrayList) OtherFactory.getCategoriesByTopicId(
					hf.getTopicId()));

		} else {
			hf.setCategories(
				(ArrayList) OtherFactory.getCategoriesByHeadingId(
					hf.getHeadingId()));
		}

		hf.setAllGroups(UserFactory.getAllGroups());
		hf.setGroups(UserFactory.getHeadingGroups(hf.getHeadingId()));

	}

	private static void getHeadingId(HeadingForm hf) {
		if ((hf.getHeadingId() > 0)
			|| (hf.getHeadingName().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " heading_id, category_id, heading_name, "
					+ " heading_url, heading_date "
					+ " FROM "
					+ " heading "
					+ " WHERE "
					+ " heading_name = '"
					+ hf.getHeadingName()
					+ "'"
					+ " ORDER BY heading_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					hf.setHeadingId(rs.getInt("heading_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getHeadingId(HeadingForm hf) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void populateCategoryForm(CategoryForm cf) {
		int categoryId = cf.getCategoryId();
		cf.setTopics((ArrayList) getAllTopics());
		if (categoryId < 1) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " category_id, category_name, "
					+ " category_url, category_date, "
					+ " topic_id "
					+ " FROM "
					+ " category "
					+ " WHERE "
					+ " category_id = "
					+ categoryId;
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					cf.setCategoryId(rs.getInt("category_id"));
					cf.setCategoryName(rs.getString("category_name"));
					cf.setCategoryUrl(rs.getString("category_url"));
					cf.setCategoryDate(rs.getString("category_date"));
					cf.setTopicId(rs.getInt("topic_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateCategoryForm(CategoryForm cf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static Collection getCategoriesByHeadingId(int headingId) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		if (headingId < 1) {
			headingId = 1;
		}
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query =
				"SELECT DISTINCT "
					+ " topic.topic_id "
					+ " FROM "
					+ " category, heading, topic "
					+ " WHERE "
					+ " category.topic_id = topic.topic_id AND "
					+ " category.category_id = heading.category_id AND "
					+ " heading.heading_id = "
					+ headingId;
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					query2 =
						"SELECT DISTINCT "
							+ " category.category_id, category.category_name, "
							+ " category.category_url, category.category_date, "
							+ " category.topic_id "
							+ " FROM "
							+ " category "
							+ " WHERE "
							+ " category.topic_id = "
							+ rs.getInt("topic.topic_id")
							+ " ORDER BY category.category_name ";
					stmt1 = conn.createStatement();
					rs1 = stmt1.executeQuery(query2);
					if (rs1 != null) {
						while (rs1.next()) {
							Category category = new Category();
							category.setCategoryId(
								rs1.getInt("category.category_id"));
							category.setCategoryName(
								rs1.getString("category.category_name"));
							category.setCategoryUrl(
								rs1.getString("category.category_url"));
							category.setCategoryDate(
								rs1.getString("category.category_date"));
							category.setTopicId(
								rs1.getInt("category.topic_id"));
							coll.add(category);
						}
					}
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
			PhotoFactory.closeConn(rs1, stmt1, null);
		} catch (SQLException sqle) {
			l.error(
				" SQL Exception in getCategoriesByHeadingId(int headingId) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
			PhotoFactory.closeConn(rs1, stmt1, null);
		}
		return coll;
	}

	public static Collection getCategoriesByTopicId(int topicId) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (topicId < 1) {
			topicId = 1;
		}
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query =
				"SELECT "
					+ " category_id, category_name, "
					+ " category_url, category_date, "
					+ " topic_id "
					+ " FROM "
					+ " category "
					+ " WHERE "
					+ " topic_id = "
					+ topicId
					+ " ORDER BY category.category_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Category category = new Category();
					category.setCategoryId(rs.getInt("category_id"));
					category.setCategoryName(rs.getString("category_name"));
					category.setCategoryUrl(rs.getString("category_url"));
					category.setCategoryDate(rs.getString("category_date"));
					category.setTopicId(rs.getInt("topic_id"));
					coll.add(category);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getCategoriesByTopicId(int topicId) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getAllTopics() {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query =
				"SELECT "
					+ " topic_id, topic_name, "
					+ " topic_url, topic_date "
					+ " FROM "
					+ " topic "
					+ " ORDER BY topic_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Topic topic = new Topic();
					topic.setTopicId(rs.getInt("topic_id"));
					topic.setTopicName(rs.getString("topic_name"));
					topic.setTopicUrl(rs.getString("topic_url"));
					topic.setTopicDate(rs.getString("topic_date"));
					coll.add(topic);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllTopics()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getAllCountries() {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " country_id, country_name, "
					+ " country_abbr "
					+ " FROM "
					+ " country "
					+ " ORDER BY country_abbr ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Country c = new Country();
					c.setCountryId(rs.getInt("country_id"));
					c.setCountryName(rs.getString("country_name"));
					c.setCountryAbbr(rs.getString("country_abbr"));
					coll.add(c);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllCountries()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getStatesByCountryId(int countryId) {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (countryId == 0) {
			countryId = 1;
		}
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " state_id, country_id, "
					+ " state_name, state_abbr "
					+ " FROM "
					+ " state "
					+ " WHERE country_id = "
					+ countryId
					+ " ORDER BY state_abbr ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					State s = new State();
					s.setStateId(rs.getInt("state_id"));
					s.setCountryId(rs.getInt("country_id"));
					s.setStateName(rs.getString("state_name"));
					s.setStateAbbr(rs.getString("state_abbr"));
					coll.add(s);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getStatesByCountryId(int countryId)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getAllFilms() {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query = "SELECT film_id, film_name FROM film ORDER BY film_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Film film = new Film();
					film.setFilmId(rs.getInt("film_id"));
					film.setFilmName(rs.getString("film_name"));
					coll.add(film);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllFilms()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}
	public static Collection getAllCameras() {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query =
				"SELECT camera_id, camera_name FROM camera ORDER BY camera_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Camera camera = new Camera();
					camera.setCameraId(rs.getInt("camera_id"));
					camera.setCameraName(rs.getString("camera_name"));
					coll.add(camera);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllCameras()");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static Collection getAllSites() {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query =
				"SELECT site_id, site_name, site_url FROM site ORDER BY site_name ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Site site = new Site();
					site.setSiteId(rs.getInt("site_id"));
					site.setSiteName(rs.getString("site_name"));
					site.setSiteUrl(rs.getString("site_url"));
					coll.add(site);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllSites() ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}
	public static Collection getAllPlaces() {
		ArrayList coll = new ArrayList(10);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			String query2 = new String();
			query =
				"SELECT "
					+ " place.place_id "
					+ " FROM "
					+ " place, state, country "
					+ " WHERE "
					+ " place.state_id = state.state_id AND "
					+ " state.country_id = country.country_id "
					+ " ORDER BY place.place_name, place.city, state.state_abbr ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					PlaceDetails placed = new PlaceDetails();
					placed = getPlaceByPlaceId(rs.getInt("place.place_id"));
					coll.add(placed);
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getAllPlaces() ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return coll;
	}

	public static void setCategoryForm(CategoryForm cf) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int topicId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();

			String query = new String();

			if (cf.getCategoryId() < 1) {
				query =
					"INSERT INTO category "
						+ " (category_name, "
						+ " category_url, "
						+ " category_date, "
						+ " topic_id ) "
						+ " VALUES "
						+ " (?,?,?,?) ";
			} else {
				query =
					"UPDATE category "
						+ " SET "
						+ " category_name = ?, "
						+ " category_url = ?, "
						+ " category_date = ?, "
						+ " topic_id = ? "
						+ " WHERE "
						+ " category_id = "
						+ cf.getCategoryId();
			}

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, cf.getCategoryName());
			ps.setString(2, cf.getCategoryUrl());
			ps.setString(3, cf.getCategoryDate());
			ps.setInt(4, cf.getTopicId());

			ps.executeUpdate();

			cf.setCategoryId(getCategoryId(cf));

			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setCategoryForm(CategoryForm cf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

	}

	public static int getCategoryId(CategoryForm cf) {
		int categoryId = cf.getCategoryId();
		if (categoryId > 0) {
			return categoryId;
		} else if (cf.getCategoryName().equalsIgnoreCase("")) {
			return 0;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " category_id, topic_id, category_name, "
					+ " category_url, category_date "
					+ " FROM "
					+ " category "
					+ " WHERE "
					+ " category_name = '"
					+ cf.getCategoryName()
					+ "'"
					+ " ORDER BY category_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					categoryId = rs.getInt("category_id");
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getCategoryId(CategoryForm cf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
		return categoryId;

	}

	public static void populateTopicForm(TopicForm tf) {
		if (tf.getTopicId() < 1) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " topic_id, topic_name, "
					+ " topic_url, topic_date "
					+ " FROM "
					+ " topic "
					+ " WHERE "
					+ " topic_id = "
					+ tf.getTopicId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					tf.setTopicId(rs.getInt("topic_id"));
					tf.setTopicName(rs.getString("topic_name"));
					tf.setTopicUrl(rs.getString("topic_url"));
					tf.setTopicDate(rs.getString("topic_date"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateTopicForm(TopicForm tf)");
			l.error(sqle);

		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	private static void getTopicId(TopicForm tf) {
		if (tf.getTopicId() > 0) {
			return;
		} else if (tf.getTopicName().equalsIgnoreCase("")) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " topic_id, topic_name, "
					+ " topic_url, topic_date "
					+ " FROM "
					+ " topic "
					+ " WHERE "
					+ " topic_name = '"
					+ tf.getTopicName()
					+ "'";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					tf.setTopicId(rs.getInt("topic_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getTopicId(TopicForm tf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void setTopicForm(TopicForm tf) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int topicId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();

			if (tf.getTopicId() < 1) {
				query =
					"INSERT INTO topic "
						+ " (topic_name, "
						+ " topic_url, "
						+ " topic_date) "
						+ " VALUES "
						+ " (?,?,?) ";
			} else {
				query =
					"UPDATE topic "
						+ " SET "
						+ " topic_name = ?, "
						+ " topic_url = ?, "
						+ " topic_date = ? "
						+ " WHERE "
						+ " topic_id = "
						+ tf.getTopicId();
			}

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, tf.getTopicName());
			ps.setString(2, tf.getTopicUrl());
			ps.setString(3, tf.getTopicDate());

			ps.executeUpdate();

			getTopicId(tf);

			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in setTopicForm(TopicForm tf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void setPlaceForm(PlaceForm pf) {
		if (pf != null) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.createStatement();
				String query = new String();
				if (pf.getPlaceId() < 1) {
					query =
						"INSERT INTO place "
							+ " (place_name, "
							+ " city, "
							+ " state_id) "
							+ " VALUES "
							+ " (?,?,?) ";
				} else {
					query =
						"UPDATE place "
							+ " SET "
							+ " place_name = ?, "
							+ " city = ?, "
							+ " state_id = ? "
							+ " WHERE "
							+ " place_id = "
							+ pf.getPlaceId();
				}

				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1, pf.getPlaceName());
				ps.setString(2, pf.getCity());
				ps.setInt(3, pf.getStateId());

				ps.executeUpdate();

				getPlaceId(pf);

				populatePlaceSelect(pf);

				PhotoFactory.closeConn(rs, stmt, conn);
			} catch (SQLException sqle) {
				l.error(" SQL Exception in setPlaceForm(PlaceForm pf)");
				l.error(sqle);
			} finally {
				PhotoFactory.closeConn(rs, stmt, conn);
			}
		}
	}

	private static void getPlaceId(PlaceForm pf) {
		if (pf.getPlaceId() > 0) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " place_id "
					+ " FROM "
					+ " place "
					+ " WHERE "
					+ " place_name = '"
					+ pf.getPlaceName()
					+ "' AND city = '"
					+ pf.getCity()
					+ "' ORDER BY place_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					pf.setPlaceId(rs.getInt("place_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getPlaceId(PlaceForm pf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void populateStateForm(StateForm sf) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query =
				" SELECT "
					+ " state_abbr, state_name, "
					+ " country_id, state_id "
					+ " FROM state "
					+ " WHERE state_id = "
					+ sf.getStateId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					sf.setStateId(rs.getInt("state_id"));
					sf.setCountryId(rs.getInt("country_id"));
					sf.setStateName(rs.getString("state_name"));
					sf.setStateAbbr(rs.getString("state_abbr"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateStateForm(StateForm sf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

		sf.setCountries((ArrayList) getAllCountries());
	}
	public static void setStateForm(StateForm sf) {
		if (sf != null) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.createStatement();
				String query = new String();
				if (sf.getStateId() < 1) {
					query =
						"INSERT INTO state "
							+ " (state_name, "
							+ " state_abbr, "
							+ " country_id) "
							+ " VALUES "
							+ " (?,?,?) ";
				} else {
					query =
						"UPDATE state "
							+ " SET "
							+ " state_name = ?, "
							+ " state_abbr = ?, "
							+ " country_id = ? "
							+ " WHERE "
							+ " state_id = "
							+ sf.getStateId();
				}

				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1, sf.getStateName());
				ps.setString(2, sf.getStateAbbr());
				ps.setInt(3, sf.getCountryId());

				ps.executeUpdate();

				getStateId(sf);

				PhotoFactory.closeConn(rs, stmt, conn);
			} catch (SQLException sqle) {
				l.error(" SQL Exception in setStateForm(StateForm sf)");
				l.error(sqle);
			} finally {
				PhotoFactory.closeConn(rs, stmt, conn);
			}
			sf.setCountries((ArrayList) getAllCountries());
		}
	}

	private static void getStateId(StateForm sf) {
		if ((sf.getStateId() > 0)
			|| (sf.getStateName().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " state_id "
					+ " FROM "
					+ " state "
					+ " WHERE "
					+ " state_name = '"
					+ sf.getStateName()
					+ "'"
					+ " ORDER BY state_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					sf.setStateId(rs.getInt("state_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getStateId(StateForm sf) ");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void populateCountryForm(CountryForm cf) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query =
				" SELECT "
					+ " country_abbr, country_name, "
					+ " country_id "
					+ " FROM country "
					+ " WHERE country_id = "
					+ cf.getCountryId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					cf.setCountryId(rs.getInt("country_id"));
					cf.setCountryName(rs.getString("country_name"));
					cf.setCountryAbbr(rs.getString("country_abbr"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateCountryForm(CountryForm cf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

	}

	public static void populateSiteForm(SiteForm sf) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query =
				" SELECT "
					+ " site_id, site_name, site_url "
					+ " FROM site "
					+ " WHERE "
					+ " site_id = "
					+ sf.getSiteId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					sf.setSiteId(rs.getInt("site_id"));
					sf.setSiteName(rs.getString("site_name"));
					sf.setSiteUrl(rs.getString("site_url"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateSiteForm(SiteForm sf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}
	public static void setSiteForm(SiteForm sf) {
		if (sf != null) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.createStatement();
				String query = new String();
				if (sf.getSiteId() < 1) {
					query =
						"INSERT INTO site "
							+ " (site_name, "
							+ " site_url) "
							+ " VALUES "
							+ " (?,?) ";
				} else {
					query =
						"UPDATE site "
							+ " SET "
							+ " site_name = ?, "
							+ " site_url = ? "
							+ " WHERE "
							+ " site_id = "
							+ sf.getSiteId();
				}

				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1, sf.getSiteName());
				ps.setString(2, sf.getSiteUrl());

				ps.executeUpdate();

				getSiteId(sf);

				PhotoFactory.closeConn(rs, stmt, conn);
			} catch (SQLException sqle) {
				l.error(" SQL Exception in setSiteForm(SiteForm sf)");
				l.error(sqle);
			} finally {
				PhotoFactory.closeConn(rs, stmt, conn);
			}
		}
	}

	private static void getSiteId(SiteForm sf) {
		if ((sf.getSiteId() > 0) || (sf.getSiteName().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " site_id "
					+ " FROM "
					+ " site "
					+ " WHERE "
					+ " site_name = '"
					+ sf.getSiteName()
					+ "'"
					+ " ORDER BY site_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					sf.setSiteId(rs.getInt("site_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getSiteId(SiteForm sf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void setCountryForm(CountryForm cf) {
		if (cf != null) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.createStatement();
				String query = new String();
				if (cf.getCountryId() < 1) {
					query =
						"INSERT INTO country "
							+ " (country_name, "
							+ " country_abbr) "
							+ " VALUES "
							+ " (?,?) ";
				} else {
					query =
						"UPDATE country "
							+ " SET "
							+ " country_name = ?, "
							+ " country_abbr = ? "
							+ " WHERE "
							+ " country_id = "
							+ cf.getCountryId();
				}

				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1, cf.getCountryName());
				ps.setString(2, cf.getCountryAbbr());

				ps.executeUpdate();

				getCountryId(cf);

				PhotoFactory.closeConn(rs, stmt, conn);
			} catch (SQLException sqle) {
				l.error(" SQL Exception in setCountryForm(CountryForm cf)");
				l.error(sqle);
			} finally {
				PhotoFactory.closeConn(rs, stmt, conn);
			}
		}
	}

	private static void getCountryId(CountryForm cf) {
		if ((cf.getCountryId() > 0)
			|| (cf.getCountryName().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " country_id "
					+ " FROM "
					+ " country "
					+ " WHERE "
					+ " country_name = '"
					+ cf.getCountryName()
					+ "'"
					+ " ORDER BY country_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					cf.setCountryId(rs.getInt("country_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getCountryId(CountryForm cf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void populateFilmForm(FilmForm ff) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query =
				" SELECT "
					+ " film_name, "
					+ " film_id "
					+ " FROM film "
					+ " WHERE film_id = "
					+ ff.getFilmId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					ff.setFilmId(rs.getInt("film_id"));
					ff.setFilmName(rs.getString("film_name"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateFilmForm(FilmForm ff)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

	}

	public static void setFilmForm(FilmForm ff) {
		if (ff != null) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.createStatement();
				String query = new String();
				if (ff.getFilmId() < 1) {
					query =
						"INSERT INTO film "
							+ " (film_name) "
							+ " VALUES "
							+ " (?) ";
				} else {
					query =
						"UPDATE film "
							+ " SET "
							+ " film_name = ? "
							+ " WHERE "
							+ " Film_id = "
							+ ff.getFilmId();
				}

				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1, ff.getFilmName());

				ps.executeUpdate();

				getFilmId(ff);

				PhotoFactory.closeConn(rs, stmt, conn);
			} catch (SQLException sqle) {
				l.error(" SQL Exception in setFilmForm(FilmForm ff)");
				l.error(sqle);
			} finally {
				PhotoFactory.closeConn(rs, stmt, conn);
			}
		}
	}

	private static void getFilmId(FilmForm ff) {
		if ((ff.getFilmId() > 0) || (ff.getFilmName().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " film_id "
					+ " FROM "
					+ " film "
					+ " WHERE "
					+ " film_name = '"
					+ ff.getFilmName()
					+ "'"
					+ " ORDER BY film_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					ff.setFilmId(rs.getInt("film_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getFilmId(FilmForm ff)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}
	public static void populateCameraForm(CameraForm cf) {
		ArrayList coll = new ArrayList();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query =
				" SELECT "
					+ " camera_name, "
					+ " camera_id "
					+ " FROM camera "
					+ " WHERE camera_id = "
					+ cf.getCameraId();
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					cf.setCameraId(rs.getInt("camera_id"));
					cf.setCameraName(rs.getString("camera_name"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in populateCameraForm(CameraForm cf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}

	}

	public static void setCameraForm(CameraForm cf) {
		if (cf != null) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				conn = ConnectionFactory.getConnection();
				stmt = conn.createStatement();
				String query = new String();
				if (cf.getCameraId() < 1) {
					query =
						"INSERT INTO camera "
							+ " (camera_name) "
							+ " VALUES "
							+ " (?) ";
				} else {
					query =
						"UPDATE camera "
							+ " SET "
							+ " camera_name = ? "
							+ " WHERE "
							+ " camera_id = "
							+ cf.getCameraId();
				}

				PreparedStatement ps = conn.prepareStatement(query);

				ps.setString(1, cf.getCameraName());

				ps.executeUpdate();

				getCameraId(cf);

				PhotoFactory.closeConn(rs, stmt, conn);
			} catch (SQLException sqle) {
				l.error(" SQL Exception in setCameraForm(CameraForm cf)");
				l.error(sqle);
			} finally {
				PhotoFactory.closeConn(rs, stmt, conn);
			}
		}
	}

	private static void getCameraId(CameraForm cf) {
		if ((cf.getCameraId() > 0)
			|| (cf.getCameraName().equalsIgnoreCase(""))) {
			return;
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String query = new String();
			query =
				"SELECT "
					+ " camera_id "
					+ " FROM "
					+ " camera "
					+ " WHERE "
					+ " camera_name = '"
					+ cf.getCameraName()
					+ "'"
					+ " ORDER BY camera_id DESC ";
			rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					cf.setCameraId(rs.getInt("camera_id"));
				}
			}
			PhotoFactory.closeConn(rs, stmt, conn);
		} catch (SQLException sqle) {
			l.error(" SQL Exception in getCameraId(CameraForm cf)");
			l.error(sqle);
		} finally {
			PhotoFactory.closeConn(rs, stmt, conn);
		}
	}

	public static void clearSession(
		HttpServletRequest request,
		HttpServletResponse response) {

			if (request.getSession().getAttribute(Constants.FORM_PEOPLE_SEARCH)
				!= null) {
				request.getSession().removeAttribute(Constants.FORM_PEOPLE_SEARCH);
			}
			if (request.getSession().getAttribute(Constants.FORM_PHOTO_QUEUE)
				!= null) {
				request.getSession().removeAttribute(Constants.FORM_PHOTO_QUEUE);
			}

	}

}
