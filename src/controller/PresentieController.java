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


public class PresentieController implements Handler {
	private PrIS informatieSysteem;

	public PresentieController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}

	@Override
	public void handle(Conversation conversation){
		if(conversation.getRequestedURI().startsWith("/les/presentie/ophalen")){
			ophalenPresentie(conversation);
		} else if (conversation.getRequestedURI().startsWith("/les/presentie/opslaan")){
			opslaanPresentie(conversation);
		} else if (conversation.getRequestedURI().startsWith("/persoon/presentie/opslaan")){
			opslaanPersoon(conversation);
		}
	}

	public void ophalenPresentie(Conversation conversation) {
		JsonObjectBuilder lJsonObjectBuilderVoorPresentie = Json.createObjectBuilder();
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		ArrayList<les> lessen = null;
		informatieSysteem.vulLessen(lessen);
		int i = 0;
		for(les lles : lessen) {
			String[] zieken = lles.returnZiekenString();
			if (zieken != null) {
				for (String zieke : zieken) {
					String Index = "zieke" + i;
					lJsonObjectBuilderVoorPresentie.add(Index, zieke);
				}
			}
			else { conversation.sendJSONMessage(null);}
		}

		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();
		lJsonArrayBuilder.add(lJsonObjectBuilderVoorPresentie);
		String lJsonOutStr = lJsonArrayBuilder.build().toString();
		conversation.sendJSONMessage(lJsonOutStr);
	}

	private void opslaanPresentie(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
	}

	public void opslaanPersoon(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
	}
}
