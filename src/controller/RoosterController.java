package controller;

import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import model.klas.Klas;
import model.les.les;
import model.persoon.Student;
import server.Conversation;
import server.Handler;

public class RoosterController implements Handler {
	private PrIS informatieSysteem;

	public RoosterController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/rooster/ophalen/klas")) {
			ophalenKlas(conversation);
		} else { System.out.println("illegal request uri");
		} 
	}
	@SuppressWarnings("null")
	private void ophalenKlas(Conversation conversation) {
		PrIS infoSysteem = new PrIS();
		ArrayList<les> pLessen = infoSysteem.returnHuidigeStudent().getLessen();
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...
		JsonObjectBuilder lJsonObjectBuilderVoorLes = Json.createObjectBuilder();
		for (les lles : pLessen) {									        // met daarin voor elke medestudent een JSON-object... 																			// behalve de student zelf...
			String[] lesInfo = lles.returnLes();
			lJsonObjectBuilderVoorLes
				.add("faculteit", lesInfo[0])
				.add("datum", lesInfo[1])
				.add("start_tijd", lesInfo[2])
				.add("end_tijd", lesInfo[3])
				.add("cursus", lesInfo[4])
				.add("leraar", lesInfo[5])
				.add("klascode", lesInfo[6]);					     
			  lJsonArrayBuilder.add(lJsonObjectBuilderVoorLes);													//voeg het JsonObject aan het array toe				     
			}
		System.out.println("als je hier komt is t ok");
		String lJsonOutStr = lJsonArrayBuilder.build().toString();												// maak er een string van
		conversation.sendJSONMessage(lJsonOutStr);																				// string gaat terug naar de Polymer-GUI!
	}
	

}
