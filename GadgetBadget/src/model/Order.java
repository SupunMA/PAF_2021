package model;

import java.sql.*;

public class Order {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order?useTimezone=true&serverTimezone=UTC", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readOrder() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Item Code</th>" + "<th>Item Name</th><th>Item Price</th>"
					+ "<th>Quantity</th>" + "<th>Item Description</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from ordmanage";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String OID = Integer.toString(rs.getInt("OID"));
				String itemCode = rs.getString("itemCode");
				String itemName = rs.getString("itemName");
				String price = Double.toString(rs.getDouble("price"));
				String quantity = Integer.toString(rs.getInt("quantity"));
				String description = rs.getString("description");

				// Add a row into the html table
				output += "<tr><td>" + itemCode + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + description + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' " + " type='button' value='Update' class='btn btn-danger'></td>"
						+ "<td><form method='post' action=''>" + "<input name='btnRemove' "
						+ " type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='OID' type='hidden' class='form-control' " + " value='" + OID + "'>"
						+ "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the orders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertOrder(String code, String name, String price, String quantity, String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into ordmanage(`OID`,`itemCode`,`itemName`,`price`,`quantity`,`description`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setInt(5, Integer.parseInt(quantity));
			preparedStmt.setString(6, description);

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

	public String updateOrder(String ID, String code, String name, String price, String quantity, String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE ordmanage SET itemCode=?,itemName=?,price=?,quantity=?,description=? WHERE OID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setInt(4, Integer.parseInt(quantity));
			preparedStmt.setString(5, description);
			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Orders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteOrder(String OID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from ordmanage where OID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(OID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Orders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
