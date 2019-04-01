package controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import server.Conversation;
import server.Handler;

class wachtwoordVeranderen implements Handler {
	private PrIS informatieSysteem;

	/**
	 * De LoginController klasse moet alle algemene aanvragen afhandelen. Methode
	 * handle() kijkt welke URI is opgevraagd en laat dan de juiste methode het werk
	 * doen. Je kunt voor elke nieuwe URI een nieuwe methode schrijven.
	 * 
	 * @param infoSys - het toegangspunt tot het domeinmodel
	 */
	public wachtwoordVeranderen(PrIS infoSys) {
		informatieSysteem = infoSys;
	}

	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/passwordChange")) {
			passwordChange(conversation);
		}
	}

	private void passwordChange(Conversation conversation) {
		JsonObject lJsonObjIn = (JsonObject) conversation.getRequestBodyAsJSON();

		String lnieuwWachtwoord;
		String lherhaalNieuwWachtwoord;
		String loudWachtwoord = lJsonObjIn.getString("password");
		String lRol = informatieSysteem.passwordChange(loudWachtwoord, lnieuwWachtwoord, lherhaalNieuwWachtwoord); // inloggen methode aanroepen op
																				// domeinmodel...
		System.out.println(lRol);
		JsonObjectBuilder lJsonObjectBuilder = Json.createObjectBuilder();
		lJsonObjectBuilder.add("rol", lRol); // en teruggekregen gebruikersrol als JSON-object...
		String lJsonOut = lJsonObjectBuilder.build().toString();

		conversation.sendJSONMessage(lJsonOut); // terugsturen naar de Polymer-GUI!
	}
}

