package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Formatter;
import util.DbConnector;

public class Showing {
	private int id;
	private int performanceId;
	private Date date;
	private String time;
	private List<String> seats;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(int performanceId) {
		this.performanceId = performanceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String toString() {
		return "This showing is ID "+performanceId+". The date of this showing is "+date+" at "+time+".";
	}

	private void initSeats() {
		seats = new ArrayList<String>();
		for(char row = 'A'; row <= 'H'; row++ ) {
			for(int seat = 1; seat <= 25; seat++) {
				String seatCode = String.valueOf(row) + seat;
				seats.add(seatCode);
			}
		}
	}
	
	public static List<Showing> getShowings() throws SQLException {
		List<Showing> records = new ArrayList<Showing>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Showing");
				ResultSet resultSet = ps.executeQuery();) {

			// Build the list of Booking objects
			while (resultSet.next()) {
				Showing s = new Showing();
				s.setId(resultSet.getInt("Id"));
				s.setPerformanceId(resultSet.getInt("PerformanceID"));
				s.setDate(resultSet.getDate("ShowDate"));
				s.setTime(resultSet.getString("ShowTime"));
				records.add(s);
			}
		}
		return records;
	}

	public static List<Showing> getShowingsByPerformanceId(int performanceId) throws SQLException {
		List<Showing> records = new ArrayList<Showing>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con
						.prepareStatement("SELECT * FROM Showing WHERE LOWER(PerformanceId) LIKE ?");) {
			ps.setInt(1, performanceId);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Showing s = new Showing();
				s.setId(resultSet.getInt("Id"));
				s.setPerformanceId(resultSet.getInt("PerformanceID"));
				s.setDate(resultSet.getDate("ShowDate"));
				s.setTime(resultSet.getString("ShowTime"));
				records.add(s);
			}
		}
		return records;
	}

	public static Showing getShowingById(String id) {

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Showing WHERE ShowingID = ?");) {

			ps.setString(1, id);
			try (ResultSet resultSet = ps.executeQuery();) {
				if (resultSet.next()) {
					Showing s = new Showing();
					s.setId(resultSet.getInt("Id"));
					s.setPerformanceId(resultSet.getInt("PerformanceID"));
					s.setDate(resultSet.getDate("ShowDate"));
					s.setTime(resultSet.getString("ShowTime"));

					return s;
				}
			}
		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return null;
	}

	public String getSeatOptions() {
		initSeats();
		List<String> seatsTaken = getSeatByShowingId();
		String result = "";
		for (String s : seats) {
			if (!seatsTaken.contains(s)) {
				result += "<option value='" + s +"'>" + s +"</option>";
			}
		}

		return result;
	}

	private List<String> getSeatByShowingId() {
		List<String> records = new ArrayList<String>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT seatRow, seatNumber FROM Ticket WHERE ShowingId LIKE ?");) {
			ps.setInt(1, this.id);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				String seatCode = resultSet.getString("seatRow") + resultSet.getInt("seatNumber");
				records.add(seatCode);
			}
		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return records;
	}

	public boolean writeToDB() {
		try {
			CallableStatement cs = DbConnector.getConnection().prepareCall("{call insertShowing(?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, id);
			cs.setString(3, Formatter.dateFormat(date));
			cs.setString(4, time);
			cs.execute();

			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}
}
