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

import model.Payment;

@Path("/Payment")

public class PaymentService {
	
	Payment paymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return paymentObj.readPayment();
	}

	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("date") String date, @FormParam("paymentMethod") String paymentMethod,
			@FormParam("paymentAmt") String paymentAmt, @FormParam("invoiceID") String invoiceID,
			@FormParam("cardNo") String cardNo) {

		String output = paymentObj.insertPayment(date, paymentMethod, paymentAmt, invoiceID, cardNo);
		return output;
	}

	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String PaymentData) {

		// Convert the input string to a JSON object

		JsonObject paymentObject = new JsonParser().parse(PaymentData).getAsJsonObject();

		// Read the values from the JSON object

		String paymentID = paymentObject.get("paymentID").getAsString();
		String date = paymentObject.get("date").getAsString();
		String paymentMethod = paymentObject.get("paymentMethod").getAsString();
		String paymentAmt = paymentObject.get("paymentAmt").getAsString();
		String invoiceID = paymentObject.get("invoiceID").getAsString();
		String cardNo = paymentObject.get("cardNo").getAsString();

		String output = paymentObj.updatePayment(paymentID, date, paymentMethod, paymentAmt, invoiceID, cardNo);

		return output;
	}

	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String PaymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());
		// Read the value from the element <itemID>
		String paymentID = doc.select("paymentID").text();
		String output = paymentObj.deletePayment(paymentID);
		return output;
	}

}
