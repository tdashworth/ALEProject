package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.DbConnector;

public class Ticket {
	private String id;
	private String email;
	private String showingId;
	private Date showingDate;
	private String showingTime;
	private String performanceTitle;
	private String type;
	private Double price;
	private String seatRow;
	private int seatNumber;
	private Boolean isInBasket;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getShowingId() {
		return showingId;
	}

	public void setShowingId(String showingId) {
		this.showingId = showingId;
	}

	public Date getShowingDate() {
		return showingDate;
	}

	public void setShowingDate(Date showingDate) {
		this.showingDate = showingDate;
	}

	public String getShowingTime() {
		return showingTime;
	}

	public void setShowingTime(String showingTime) {
		this.showingTime = showingTime;
	}

	public String getPerformanceTitle() {
		return performanceTitle;
	}

	public void setPerformanceTitle(String performanceTitle) {
		this.performanceTitle = performanceTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int i) {
		this.seatNumber = i;
	}

	public Boolean getIsInBasket() {
		return isInBasket;
	}

	public void setIsInBasket(Boolean isInBasket) {
		this.isInBasket = isInBasket;
	}

	public String toString() {
		return "This ticket is linked to " + email + "'s account. " + "It's for the showing " + id
				+ " and the ticket is of type " + type + ". " + "This ticket is valid for " + seatRow + seatNumber
				+ ".\r\n" + "The current basket status of this ticket is: " + isInBasket;
	}

	public static boolean addToBasket(String email, int showingId, String type, double price, String seatRow,
			int seatNumber) {
		try {
			CallableStatement cs = DbConnector.getConnection().prepareCall("{call insertBasketTicket(?,?,?,?,?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, email);
			cs.setInt(3, showingId);
			cs.setString(4, type);
			cs.setDouble(5, price);
			cs.setString(6, seatRow);
			cs.setInt(7, seatNumber);
			cs.execute();

			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}

	public static List<Ticket> getTickets() throws SQLException {
		List<Ticket> records = new ArrayList<Ticket>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Ticket");
				ResultSet resultSet = ps.executeQuery();) {

			while (resultSet.next()) {
				Ticket p = new Ticket();
				p.setId(resultSet.getString("TicketID"));
				p.setEmail(resultSet.getString("Email"));
				p.setShowingId(resultSet.getString("ShowingID"));
				p.setType(resultSet.getString("TicketType"));
				p.setSeatRow(resultSet.getString("SeatRow"));
				p.setSeatNumber(resultSet.getInt("SeatNumber"));
				p.setIsInBasket(resultSet.getInt("IsInBasket") == 1);
				records.add(p);
			}
		}
		return records;
	}

	public static List<Ticket> getBasketTicketsByEmail(String email) throws SQLException {
		List<Ticket> records = new ArrayList<Ticket>();

		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement(
						"SELECT Ticket.Id, Ticket.Type, Ticket.Price, Performance.Title, Showing.ShowDate, Showing.ShowTime, Ticket.SeatRow, Ticket.SeatNumber "
								+ "FROM Ticket JOIN Showing ON Ticket.ShowingId = Showing.Id "
								+ "JOIN Performance ON Showing.PerformanceId = Performance.Id "
								+ "WHERE LOWER(Ticket.Email) LIKE ? AND Ticket.IsInBasket = 1");) {
			ps.setString(1, email.toLowerCase());
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Ticket p = new Ticket();
				p.setId(resultSet.getString("ID"));
				p.setType(resultSet.getString("Type"));
				p.setPrice(resultSet.getDouble("Price"));
				p.setShowingDate(resultSet.getDate("ShowDate"));
				p.setShowingTime(resultSet.getString("ShowTime"));
				p.setPerformanceTitle(resultSet.getString("Title"));
				p.setSeatRow(resultSet.getString("SeatRow"));
				p.setSeatNumber(resultSet.getInt("SeatNumber"));
				records.add(p);
			}
		}
		return records;
	}

	public static Double getPriceOfBasket(List<Ticket> tickets) {
		Double price = 0.0;

		for (Ticket t : tickets) {
			price += t.getPrice();
		}

		return price;
	}

	public static Boolean isDeliverable(List<Ticket> tickets) {
		for (Ticket t : tickets) {
			Calendar c = Calendar.getInstance();
			c.setTime(c.getTime());
			c.add(Calendar.DATE, 7);
			if (c.getTime().compareTo(t.getShowingDate()) > 0) {
				return false;
			}
		}

		return true;
	}

	public static double getDeliveryPriceOfBasket(List<Ticket> tickets) {
		int nonConsessionCount = 0;

		for (Ticket t : tickets) {
			if (t.getType().equals("Adult")) {
				nonConsessionCount++;
			}
		}

		// everyone is adult
		if (nonConsessionCount == tickets.size()) {
			return tickets.size();
			// all concessions
		} else if (nonConsessionCount == 0) {
			return 0.0;
			// There is at least one concession ticket
		} else if (nonConsessionCount != (tickets.size())) {
			return 1.0;
		}

		return 0.0;
	}

	public static boolean makePayment(String email) {
		try {
			CallableStatement cs = DbConnector.getConnection().prepareCall("{call payForBasketTickets(?)}");
			cs.setString(1, email);
			cs.execute();
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}
	
	public static void remove(int id) throws SQLException {
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("DELETE FROM Ticket WHERE ID = ?");) {

			ps.setInt(1, id);
			ps.executeQuery();
		}
	}

	public static Ticket getTicketById(String id) throws SQLException {
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Ticket WHERE ID = ?");) {

			ps.setString(1, id);
			try (ResultSet resultSet = ps.executeQuery();) {
				// Can be hard to determine number of results without reading full ResultSet
				if (resultSet.next()) {
					Ticket p = new Ticket();
					p.setId(resultSet.getString("TicketID"));
					p.setEmail(resultSet.getString("Email"));
					p.setShowingId(resultSet.getString("ShowingID"));
					p.setType(resultSet.getString("TicketType"));
					p.setSeatRow(resultSet.getString("SeatRow"));
					p.setSeatNumber(resultSet.getInt("SeatNumber"));
					p.setIsInBasket(resultSet.getInt("IsInBasket") == 1);

					return p;
				}
			}
		}
		return null;
	}
}
