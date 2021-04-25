package model;

import java.sql.*;

public class UsersRoles {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/UserData?useTimezone=true&serverTimezone=UTC", "root", "");
			// For testing
			System.out.print("connected Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th> RoleID </th>" + "<th> RoleName </th></tr>";
			String query = "select * from userroles";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				//String OID = Integer.toString(rs.getInt("OID"));
				String roleid = rs.getString("roleid");
				String rolename = rs.getString("rolename");
				

				// Add a row into the html table
				output += "<tr><td>" + roleid + "</td>";
				output += "<td>" + rolename + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Role List.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String AddNewRole(String Rolename) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into userroles(`roleid`,`roleName`)"
					+ " values (?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Rolename);
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "New UserRole Added successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String UpdateRoleName(String roleid, String rolename) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE userroles SET rolename=? WHERE roleid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, rolename);
			preparedStmt.setInt(2, Integer.parseInt(roleid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the UserRole.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUserRole(String roleid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from userroles where roleid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(roleid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the UserRole.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
