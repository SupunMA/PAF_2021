package model;

import java.sql.*;

public class Payment {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment?useTimezone=true&serverTimezone=UTC", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	
	
	
	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Date</th>" + "<th>Payment Method</th><th>Payment Amount</th>"
					+ "<th>Invoice ID</th>" + "<th>Card Number</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from paymanage";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String date = rs.getString("date");
				String paymentMethod = rs.getString("paymentMethod");
				String paymentAmt = Double.toString(rs.getDouble("paymentAmt"));
				String invoiceID = Integer.toString(rs.getInt("invoiceID"));
				String cardNo = Integer.toString(rs.getInt("cardNo"));

				// Add a row into the html table
				output += "<tr><td>" + date + "</td>";
				output += "<td>" + paymentMethod + "</td>";
				output += "<td>" + paymentAmt + "</td>";
				output += "<td>" + invoiceID + "</td>";
				output += "<td>" + cardNo + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' " + " type='button' value='Update' class='btn btn-danger'></td>"
						+ "<td><form method='post' action=''>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='paymentID' type='hidden' class='form-control' " + " value='" + paymentID + "'>"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	
	public String insertPayment(String date, String paymentMethod, String paymentAmt, String invoiceID, String cardNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into paymanage(`paymentID`,`date`,`paymentMethod`,`paymentAmt`,`invoiceID`,`cardNo`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, paymentMethod);
			preparedStmt.setDouble(4, Double.parseDouble(paymentAmt));
			preparedStmt.setInt(5, Integer.parseInt(invoiceID));
			preparedStmt.setInt(6, Integer.parseInt(cardNo));

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

	
	
	
	
	public String updatePayment(String paymentID, String date, String paymentMethod, String paymentAmt, String invoiceID, String cardNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE paymanage SET date=?,paymentMethod=?,paymentAmt=?,invoiceID=?,cardNo=? WHERE paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, date);
			preparedStmt.setString(2, paymentMethod);
			preparedStmt.setDouble(3, Double.parseDouble(paymentAmt));
			preparedStmt.setInt(4, Integer.parseInt(invoiceID));
			preparedStmt.setString(5, cardNo);
			preparedStmt.setInt(6, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from paymanage where paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
