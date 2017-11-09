package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import util.DbConnector;

public class Customer {
	private String email;
	private String firstName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String postcode;
	private String country;
	private String password;
	private Boolean admin;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostCode(String postCode) {
		this.postcode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public String toString() {
		return "This customer is " + firstName + " " + lastName +". \n"
				+ "Address is " + addressLine1 + "\n"
				+ "           " + addressLine2 + "\n"
				+ "           " + postcode + "\n "
				+ "In " + country + ". Their password is " + password + ".";
	}

	public static Customer newCustomer(String email, String firstName, String lastName,
								   	   String addressLine1, String addressLine2, String postcode, 
								   	   String country, String password) {
		Customer c = new Customer();
		c.setEmail(email.toLowerCase());
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setAddressLine1(addressLine1);
		c.setAddressLine2(addressLine2);
		c.setPostCode(postcode);
		c.setCountry(country);
		c.setPassword(password);
		
		return c;
	}
	public static Customer newCustomer(ResultSet resultSet) throws SQLException {
		Customer c = new Customer();
		c.setEmail(resultSet.getString("Email"));
		c.setFirstName(resultSet.getString("FirstName"));
		c.setLastName(resultSet.getString("LastName"));
		c.setAddressLine1(resultSet.getString("AddressLine1"));
		c.setAddressLine2(resultSet.getString("AddressLine2"));
		c.setPostCode(resultSet.getString("PostCode"));
		c.setCountry(resultSet.getString("Country"));
		c.setPassword(resultSet.getString("Password"));
		c.setAdmin(resultSet.getInt("IsAdmin")==1);
		return c;
	}

	public static List<Customer> getCustomer() throws SQLException {
		List<Customer> records = new ArrayList<Customer>();

		/**
		 * Below: try-with-resources - the Connection, PreparedStatement, and ResultSet
		 * should all be correctly closed at the end of the try block's execution.
		 */
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Customer");
				ResultSet resultSet = ps.executeQuery();) {

			// Build the list of Booking objects
			while (resultSet.next()) {
				records.add(newCustomer(resultSet));
			}
		}
		return records;
	}
	
	public static Customer getCustomerByEmail(String email) throws SQLException {
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM Customer WHERE Email = ?");
			) {
			ps.setString(1, email);
			try (ResultSet resultSet = ps.executeQuery();) {
				if (resultSet.next()) {
					return newCustomer(resultSet);
				}
			}
		}
		return null;

	}

	public static String getPasswordFromUsername(String email) throws SQLException {
		try (Connection con = DbConnector.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT Password FROM Customer WHERE Email = ?");) {
			ps.setString(1, email);
			try (ResultSet resultSet = ps.executeQuery();) {
				if (resultSet.next()) {
					return resultSet.getString("Password");
				}
			}
		}
		return null;
	}

	public int writeToDb() {
		try {
			CallableStatement cs = DbConnector.getConnection().prepareCall("{call insertCustomer(?,?,?,?,?,?,?,?)}");
			cs.setString(1, email);
			cs.setString(2, firstName);
			cs.setString(3, lastName);
			cs.setString(4, addressLine1);
			cs.setString(5, addressLine2);
			cs.setString(6, postcode);
			cs.setString(7, country);
			cs.setString(8, password);
			cs.execute();

			return 1;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return -1;
	}
}
