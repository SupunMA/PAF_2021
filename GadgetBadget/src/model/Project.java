package model;

import java.sql.*;

public class Project 
{
	
		//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectManagement", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}
		
		
		
		public String insertProject(String title, String description, String document, String video)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into project"
						+"(`projectID`,`projectTitle`,`projectDescription`,`projectProposalLink`,`projectVideoLink`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, title);
				preparedStmt.setString(3, description);
				preparedStmt.setString(4, document);
				preparedStmt.setString(5, video);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				 output = "Error while inserting the project.";
				 System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		public String readProject()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Project Title</th><th>Project Description</th>" +
				"<th>Project Proposal </th>" +
				"<th>Project Video</th>" +
				"<th>Update</th><th>Remove</th></tr>";

				String query = "select * from project";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String projectID = Integer.toString(rs.getInt("projectID"));
					String projectTitle = rs.getString("projectTitle");
					String projectDescription = rs.getString("projectDescription");
					String projectProposalLink = rs.getString("projectProposalLink");
					String projectVideoLink = rs.getString("projectVideoLink");
					// Add into the html table
					output += "<tr><td>" + projectTitle + "</td>";
					output += "<td>" + projectDescription + "</td>";
					output += "<td>" + projectProposalLink + "</td>";
					output += "<td>" + projectVideoLink + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "
							+ "class='btn btn-secondary'></td>"
									+ "<td><form method='post' action='items.jsp'>"
									+ "<input name='btnRemove' type='submit' value='Remove'"
									+ "class='btn btn-danger'>"
											+ "<input name='projectID' type='hidden' value='" + projectID
											+ "'>" + "</form></td></tr>";
				 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the projects.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		public String updateProject(String ID, String title, String description, String document, String video)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE project SET projectTitle=?,projectDescription=?,projectProposalLink=?,projectVideoLink=?"
						+"WHERE ID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, title);
				preparedStmt.setString(2, description);
				preparedStmt.setString(3, document);
				preparedStmt.setString(4, video);
				preparedStmt.setInt(5, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while updating the project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}
		
		
		
		
		public String deleteProject(String projectID)
		{
			 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				 // create a prepared statement
				 String query = "delete from project where projectID=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(projectID));
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 output = "Deleted successfully";
			 }
			 catch (Exception e)
			 {
				 output = "Error while deleting the project.";
				 System.err.println(e.getMessage());
			 }
			 return output;
		}
}
