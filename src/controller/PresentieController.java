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
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Les lessen = informatieSysteem.getles;
	}
	
	public void opslaanPresentie(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
	}
	
	public void opslaanPersoon(Conversation concersation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
	}
}

