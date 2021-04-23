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

import model.Order;

@Path("/Order")
public class OrderService {

	Order orderObj = new Order();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrder() {
		return orderObj.readOrder();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrder(@FormParam("itemCode") String itemCode, @FormParam("itemName") String itemName,
			@FormParam("price") String price, @FormParam("quantity") String quantity,
			@FormParam("description") String description) {

		String output = orderObj.insertOrder(itemCode, itemName, price, quantity, description);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrder(String OrderData) {

		// Convert the input string to a JSON object

		JsonObject orderObject = new JsonParser().parse(OrderData).getAsJsonObject();

		// Read the values from the JSON object

		String OID = orderObject.get("OID").getAsString();
		String itemCode = orderObject.get("itemCode").getAsString();
		String itemName = orderObject.get("itemName").getAsString();
		String price = orderObject.get("price").getAsString();
		String quantity = orderObject.get("quantity").getAsString();
		String description = orderObject.get("description").getAsString();

		String output = orderObj.updateOrder(OID, itemCode, itemName, price, quantity, description);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrder(String orderData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(orderData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String OID = doc.select("OID").text();
		String output = orderObj.deleteOrder(OID);
		return output;
	}

}
