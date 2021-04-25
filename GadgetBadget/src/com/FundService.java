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

import model.Fund;

@Path("/Fund")
public class FundService {
	
	Fund fundObj = new Fund();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFund() {
		return fundObj.readFund();
	}

	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("fundProvider") String fundProvider, @FormParam("researchName") String researchName,
			@FormParam("amount") String amount) {

		String output = fundObj.insertFund(fundProvider, researchName, amount);
		return output;
	}

	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String FundData) {

		// Convert the input string to a JSON object

		JsonObject fundObject = new JsonParser().parse(FundData).getAsJsonObject();

		// Read the values from the JSON object

		String fundID = fundObject.get("fundID").getAsString();
		String fundProvider = fundObject.get("fundProvider").getAsString();
		String researchName = fundObject.get("researchName").getAsString();
		String amount = fundObject.get("amount").getAsString();
		

		String output = fundObj.updateFund(fundID, fundProvider, researchName, amount);

		return output;
	}

	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String FundData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(FundData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String fundID = doc.select("fundID").text();
		String output = fundObj.deleteFund(fundID);
		return output;
	}


}
