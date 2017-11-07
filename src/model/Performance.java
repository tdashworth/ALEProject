package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import util.Formatter;
import util.DbConnector;

public class Performance {
	private int id;
	private String title;
	private String type;
	private String description;
	private String languages;
	private String duration;
	private Double price;

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "This performance is of " + title + " and belongs to the genre of " + type + ". "
				+ "This performance is available in " + languages + " languages. " + "The show lasts for " + duration
				+ ". " + "The current ticket price is " + price + ". " + "More information on " + title + ": "
				+ description + ".";
	}

	public static Performance newPerformance(int id, String title, String description, String type, String languages,
			Double price, String duration) {
		Performance p = new Performance();
		p.setId(id);
		p.setTitle(title);
		p.setDescription(description);
		p.setType(type);
		p.setLanguages(languages);
		p.setPrice(price);
		p.setDuration(duration);
		return p;
	}

	public static Performance newPerformance(ResultSet resultSet) throws SQLException {
		Performance p = new Performance();
		p.setId(resultSet.getInt("ID"));
		p.setTitle(resultSet.getString("Title"));
		p.setType(resultSet.getString("ShowType"));
		p.setDescription(resultSet.getString("Description"));
		p.setDuration(resultSet.getString("Duration"));
		p.setLanguages(resultSet.getString("Lang"));
		p.setPrice(resultSet.getDouble("Price"));
		return p;
	}

	public String getTimingsList() throws SQLException {
		List<Showing> showings = Showing.getShowingsByPerformanceId(this.id);
		String result = "";
		for (Showing s : showings) {
			result += Formatter.dateFormat(s.getDate()) + " @ " + s.getTime() + ", ";
		}

		if (result.length() > 0) {
			return result.substring(0, result.length() - 2);
		} else {
			return "<i>No showings</i>";
		}
	}

	public String getTimingsOptions() throws SQLException {
		List<Showing> showings = Showing.getShowingsByPerformanceId(this.id);
		String result = "";
		for (Showing s : showings) {
			result += "<option value='" + s.getId() + "'>" + Formatter.dateFormat(s.getDate()) + " @ " + s.getTime()
					+ "</option>";
		}

		if (result.length() > 0) {
			return result;
		} else {
			return "<i>No showings</i>";
		}
	}

	public String getSeatOptions() throws SQLException {
		List<Showing> showings = Showing.getShowingsByPerformanceId(this.id);
		String result = "";
		for (Showing s : showings) {
			result += "<select class='form-control' style='display: none;' id='" + s.getId() + "' name='" + s.getId()
					+ "'>" + s.getSeatOptions() + "</select>";
		}

		return result;
	}

	public static List<Performance> getPerformances() throws SQLException {
		List<Performance> records = new ArrayList<Performance>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Performance");
				ResultSet resultSet = ps.executeQuery();) {

			while (resultSet.next()) {
				records.add(newPerformance(resultSet));
			}
		}
		return records;
	}

	public static List<Performance> getPerformancesSearchText(String query) throws SQLException {
		List<Performance> records = new ArrayList<Performance>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT * FROM Performance WHERE LOWER(Title) LIKE ? OR LOWER(Description) LIKE ?");) {
			String sqlSearchTerm = ("%" + query + "%").toLowerCase();
			ps.setString(1, sqlSearchTerm);
			ps.setString(2, sqlSearchTerm);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				records.add(newPerformance(resultSet));
			}
		}
		return records;
	}

	public static List<Performance> getPerformancesSearchDate(String query) throws SQLException {
		List<Performance> records = new ArrayList<Performance>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT * FROM Performance WHERE ID IN (SELECT PerformanceID FROM Showing WHERE ShowDate = ?)");) {
			ps.setString(1, query);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				records.add(newPerformance(resultSet));
			}
		}
		return records;
	}

	public static Performance getPerformanceById(String id) throws SQLException {
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Performance WHERE ID = ?");) {

			ps.setString(1, id);
			try (ResultSet resultSet = ps.executeQuery();) {
				if (resultSet.next()) {
					return newPerformance(resultSet);
				}
			}
		}
		return null;
	}

	public boolean writeToDB() {
		try {
			CallableStatement cs = DbConnector.getConnection().prepareCall("{call insertPerformance(?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, title);
			cs.setString(3, type);
			cs.setString(4, languages);
			cs.setString(5, duration);
			cs.setString(6, description);
			cs.setDouble(7, price);
			cs.execute();

			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}

}
