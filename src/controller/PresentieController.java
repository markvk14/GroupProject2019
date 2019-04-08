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
	
	private void ophalenPresentie(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		les lessen = informatieSysteem.getles;
	}
	
	private void opslaanPresentie(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
	}
	
	private void opslaanPersoon(Conversation concersation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
	}
}

