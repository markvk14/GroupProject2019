package controller;

import java.util.Calendar;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import server.Conversation;
import server.Handler;

public class SysteemDatumController implements Handler {
	
	/**
	 * De SysteemDatumController klasse moet alle systeem (en test)-gerelateerde aanvragen
	 * afhandelen. Methode handle() kijkt welke URI is opgevraagd en laat
	 * dan de juiste methode het werk doen. Je kunt voor elke nieuwe URI
	 * een nieuwe methode schrijven.
	 * 
	 * @param infoSys - het toegangspunt tot het domeinmodel
	 */
	public SysteemDatumController(PrIS infoSys) {
	}

	public void handle(Conversation conversation) {
	  if (conversation.getRequestedURI().startsWith("/systeemdatum/lesinfo")) {
			ophalenLesInfo(conversation);
		}
	}
	
  private void ophalenLesInfo(Conversation conversation) {
  	//<to do> begin
  	//De volgende statements moeten gewijzigd worden zodat daadwerkelijk de eerste en laatste lesdatum wordt bepaald
  	Calendar lEersteLesDatum = Calendar.getInstance();
		Calendar lLaatsteLesDatum = Calendar.getInstance();
    //<to do> end
		
		JsonObjectBuilder lJsonObjectBuilder = Json.createObjectBuilder();
		//Deze volgorde mag niet worden gewijzigd i.v.m. JS. (Hier mag dus ook geen andere JSON voor komen.)
		lJsonObjectBuilder 
			.add("eerste_lesdatum", PrIS.calToStandaardDatumString(lEersteLesDatum))
			.add("laatste_lesdatum", PrIS.calToStandaardDatumString(lLaatsteLesDatum));

		String lJsonOut = lJsonObjectBuilder.build().toString();
		
		conversation.sendJSONMessage(lJsonOut);										// terugsturen naar de Polymer-GUI!	}
  }
}
