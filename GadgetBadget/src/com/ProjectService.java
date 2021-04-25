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

//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
import com.google.gson.*;

import model.Project;

@Path("/Projects")
public class ProjectService {

	Project projectObj = new Project();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProject() {
		return projectObj.readProject();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectID") String projectID, @FormParam("projectTitle") String projectTitle,
			@FormParam("projectDescription") String projectDescription, @FormParam("projectProposalLink") String projectProposalLink,
			@FormParam("projectVideoLink") String projectVideoLink) {

		String output = projectObj.insertProject(projectTitle, projectDescription, projectProposalLink, projectVideoLink);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData) {

		// Convert the input string to a JSON object

		JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();

		// Read the values from the JSON object

		String projectID = projectObject.get("projectID").getAsString();
		String projectTitle = projectObject.get("projectTitle").getAsString();
		String projectDescription = projectObject.get("projectDescription").getAsString();
		String projectProposalLink = projectObject.get("projectProposalLink").getAsString();
		String projectVideoLink = projectObject.get("projectVideoLink").getAsString();

		String output = projectObj.updateProject(projectID, projectTitle, projectDescription, projectProposalLink, projectVideoLink);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String projectData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());
		// Read the value from the element <projectID>
		String projectID = doc.select("projectID").text();
		String output = projectObj.deleteProject(projectID);
		return output;
	}

}
