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
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projecttest01", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}
		
		
		
		public String insertProject(String title, String summary, String document, String video)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into projectstest01"
						+"(`projectID`,`projectTitle`,`projectSummary`,`projectProposal`,`projectVideo`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, title);
				preparedStmt.setString(3, summary);
				preparedStmt.setString(4, document);
				preparedStmt.setString(5, video);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				 output = "Error while inserting the item.";
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
				output = "<table border='1'><tr><th>Project Title</th><th>Project Summary</th>" +
				"<th>Project Proposal</th>" +
				"<th>Project Video</th>" +
				"<th>Update</th><th>Remove</th></tr>";

				String query = "select * from projecttest01";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String projectID = Integer.toString(rs.getInt("projectID"));
					String projectTitle = rs.getString("projectTitle");
					String projectSummary = rs.getString("projectSummary");
					String projectProposal = rs.getString("projectProposal");
					String projectVideo = rs.getString("projectVideo");
					// Add into the html table
					output += "<tr><td>" + projectTitle + "</td>";
					output += "<td>" + projectSummary + "</td>";
					output += "<td>" + projectProposal + "</td>";
					output += "<td>" + projectVideo + "</td>";
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
		
		
		public String updateProject(String ID, String title, String summary, String document, String video)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE items SET projectTitle=?,projectSummary=?,projectProposal=?,projectVideo=?"
						+"WHERE itemID=?";
								PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, title);
				preparedStmt.setString(2, summary);
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
				 String query = "delete from projecttest01 where projectID=?";
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
