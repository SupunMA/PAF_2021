package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund?useTimezone=true&serverTimezone=UTC", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	
	
	
	public String readFund() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Fund Provider</th>" + "<th>Research Name</th><th>Amount</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from fundmanage";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String fundID = Integer.toString(rs.getInt("fundID"));
				String fundProvider = rs.getString("fundProvider");
				String researchName = rs.getString("researchName");
				String amount = Double.toString(rs.getDouble("amount"));
				

				// Add a row into the html table
				output += "<tr><td>" + fundProvider + "</td>";
				output += "<td>" + researchName + "</td>";
				output += "<td>" + amount + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' " + " type='button' value='Update' class='btn btn-danger'></td>"
						+ "<td><form method='post' action=''>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='paymentID' type='hidden' class='form-control' " + " value='" + fundID + "'>"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	
	public String insertFund(String fundProvider, String researchName, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into fundmanage(`fundID`,`fundProvider`,`researchName`,`amount`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fundProvider);
			preparedStmt.setString(3, researchName);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	public String updateFund(String fundID, String fundProvider, String researchName, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE fundmanage SET fundProvider=?,researchName=?,amount=? WHERE fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, fundProvider);
			preparedStmt.setString(2, researchName);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setInt(6, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteFund(String fundID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from fundmanage where fundID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}



}
