package controller;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import model.klas.Klas;
import model.les.les;
import model.persoon.Docent;
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
			ophalenKlas(conversation, null);
		} else if (conversation.getRequestedURI().startsWith("/rooster/ophalen/docent")) {
			ophalenDocent(conversation);
		} 
	}
	@SuppressWarnings("null")
	private void ophalenKlas(Conversation conversation, Student StudentZelf) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Klas klas = informatieSysteem.getKlasVanStudent(StudentZelf);	// klas van de student opzoeken
		String gebruikernaam = lJsonObjectIn.getString("username");
		PrIS infoSysteem = new PrIS();
		ArrayList<les> pLessen = null;
		infoSysteem.vulLessen(pLessen);
	
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...
		
		for (les lles : pLessen) {									        // met daarin voor elke medestudent een JSON-object... 																			// behalve de student zelf...
			JsonObjectBuilder lJsonObjectBuilderVoorLes = Json.createObjectBuilder();
			String[] lesInfo = lles.returnLes();
			
			
			lJsonObjectBuilderVoorLes
				.add("datum", lesInfo[0])
				.add("start_tijd", lesInfo[1])
				.add("end_tijd", lesInfo[2])
				.add("cursus", lesInfo[3])
				.add("leraar", lesInfo[4])
				.add("klascode", lesInfo[5]);					     
			  
			  lJsonArrayBuilder.add(lJsonObjectBuilderVoorLes);													//voeg het JsonObject aan het array toe				     
			}
		
		String lJsonOutStr = lJsonArrayBuilder.build().toString();												// maak er een string van
		conversation.sendJSONMessage(lJsonOutStr);																				// string gaat terug naar de Polymer-GUI!
	}
	
	private void ophalenDocent(Conversation conversation) {
		
	}
	

}
