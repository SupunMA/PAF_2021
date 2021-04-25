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

import model.UsersRoles;;

@Path("/UserRole")
public class UserRoleService {

	UsersRoles userRoleOBJ = new UsersRoles();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRoles() {
		return userRoleOBJ.readUsers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRole(@FormParam("roleName") String Rolename) {

		String output = userRoleOBJ.AddNewRole(Rolename);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateRole(String RoleDetails) {

		// Convert the input string to a JSON object

		JsonObject userObject = new JsonParser().parse(RoleDetails).getAsJsonObject();

		// Read the values from the JSON object

		String roleid = userObject.get("roleid").getAsString();
		String rolename = userObject.get("rolename").getAsString();
		
		String output = userRoleOBJ.UpdateRoleName(roleid, rolename);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrder(String UserData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(UserData, "", Parser.xmlParser());
		// Read the value from the element <roleid>
		String roleid = doc.select("roleid").text();
		String output = userRoleOBJ.deleteUserRole(roleid);
		return output;
	}
	
	

}
