package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Users;

@Path("/User")
public class UserService {

	Users userOBJ = new Users();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return userOBJ.readUsers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrder(@FormParam("UName") String UName, @FormParam("UEmail") String UEmail,
			@FormParam("UPassword") String UPassword, @FormParam("UContactNumber") String UContactNumber,@FormParam("Urole") String URole) {

		String output = userOBJ.RegisterUser(UName, UEmail, UPassword, UContactNumber, URole);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String UserDetails) {

		// Convert the input string to a JSON object

		JsonObject userObject = new JsonParser().parse(UserDetails).getAsJsonObject();

		// Read the values from the JSON object

		String uID = userObject.get("uid").getAsString();
		String name = userObject.get("uname").getAsString();
		String email = userObject.get("uemail").getAsString();
		String password = userObject.get("upassword").getAsString();
		String contact = userObject.get("ucontactnumber").getAsString();
		String urole = userObject.get("urole").getAsString();

		String output = userOBJ.updateUser(uID, name, email, password, contact, urole);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrder(String UserData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(UserData, "", Parser.xmlParser());
		// Read the value from the element <uid>
		String uID = doc.select("uid").text();
		String output = userOBJ.deleteUser(uID);
		return output;
	}
	
	

}
