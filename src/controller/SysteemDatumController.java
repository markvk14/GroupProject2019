package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import model.les.Les;
import model.rooster.Rooster;
import server.Conversation;
import server.Handler;

public class SysteemDatumController implements Handler {
	private PrIS informatieSysteem;
	
	/**
	 * De SysteemDatumController klasse moet alle systeem (en test)-gerelateerde aanvragen
	 * afhandelen. Methode handle() kijkt welke URI is opgevraagd en laat
	 * dan de juiste methode het werk doen. Je kunt voor elke nieuwe URI
	 * een nieuwe methode schrijven.
	 * 
	 * @param infoSys - het toegangspunt tot het domeinmodel
	 */
	public SysteemDatumController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}

	public void handle(Conversation conversation) {
	  if (conversation.getRequestedURI().startsWith("/systeemdatum/lesinfo")) {
			ophalenLesInfo(conversation);
		}
	}
	
  private void ophalenLesInfo(Conversation conversation) {
  	Rooster hetRooster = informatieSysteem.getRooster();
  	ArrayList<Les> deLessen = hetRooster.getLessen();
  	deLessen.sort(null);
  	//<to do> begin
  	//De volgende statements moeten gewijzigd worden zodat daadwerkelijk de eerste en laatste lesdatum wordt bepaald
  	LocalDate lEersteLesDatum = (LocalDate) deLessen.get(0).getInfo().get(0);
		LocalDate lLaatsteLesDatum = (LocalDate) deLessen.get(deLessen.size() -1).getInfo().get(0);
    //<to do> end
		
		JsonObjectBuilder lJsonObjectBuilder = Json.createObjectBuilder();
		//Deze volgorde mag niet worden gewijzigd i.v.m. JS. (Hier mag dus ook geen andere JSON voor komen.)
		lJsonObjectBuilder 
			.add("eerste_lesdatum", lEersteLesDatum.toString())
			.add("laatste_lesdatum", lLaatsteLesDatum.toString());

		String lJsonOut = lJsonObjectBuilder.build().toString();
		
		conversation.sendJSONMessage(lJsonOut);										// terugsturen naar de Polymer-GUI!	}
  }
}
