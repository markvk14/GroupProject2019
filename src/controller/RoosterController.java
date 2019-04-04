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
			ophalenKlas(conversation);
		} else if (conversation.getRequestedURI().startsWith("/rooster/ophalen/docent")) {
			ophalenDocent(conversation);
		} else if (conversation.getRequestedURI().startsWith("/rooster/ophalen")) {
			ophalenAlles(conversation);
		}
	}
	
	private void ophalenKlas(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Klas klas = informatieSysteem.getKlasVanStudent(lStudentZelf);	// klas van de student opzoeken
		String gebruikernaam = lJasonobjectIn.getString("username");
		
		
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();						// Uiteindelijk gaat er een array...
		
		for (les lles : pLessen) {									        // met daarin voor elke medestudent een JSON-object... 																			// behalve de student zelf...
			JsonObjectBuilder lJsonObjectBuilderVoorLes = Json.createObjectBuilder();

			
			
			lJsonObjectBuilderVoorLes
				.add("datum", datum)
				.add("start_tijd", startTijdVanLesString)
				.add("end_tijd", eindTijdVanLesString)
				.add("cursus", vakVanLes)
				.add("leraar", gebruikersNaamVanDocent)
				.add("locatie", locatieVanLes)
				.add("klascode", klasCodeVanLes);					     
			  
			  lJsonArrayBuilder.add(lJsonObjectBuilderVoorLes);													//voeg het JsonObject aan het array toe				     
			}
		
		String lJsonOutStr = lJsonArrayBuilder.build().toString();												// maak er een string van
		conversation.sendJSONMessage(lJsonOutStr);																				// string gaat terug naar de Polymer-GUI!
	}
	
	private void ophalenDocent(Conversation caoversation) {
		
	}
	
	private void ophalenAlles(Conversation conversation) {
		
	}
}
