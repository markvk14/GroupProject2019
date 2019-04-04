package controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import server.Conversation;
import server.Handler;

class LoginController implements Handler {
	private PrIS informatieSysteem;
	
	/**
	 * De LoginController klasse moet alle algemene aanvragen afhandelen. 
	 * Methode handle() kijkt welke URI is opgevraagd en laat dan de juiste 
	 * methode het werk doen. Je kunt voor elke nieuwe URI een nieuwe methode 
	 * schrijven.
	 * 
	 * @param infoSys - het toegangspunt tot het domeinmodel
	 */
	public LoginController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/login")) {
			login(conversation);
		}
	}
	
	/**
	 * Deze methode haalt eerst de opgestuurde JSON-data op. Daarna worden
	 * de benodigde gegevens uit het domeinmodel gehaald. Deze gegevens worden
	 * dan weer omgezet naar JSON en teruggestuurd naar de Polymer-GUI!
	 * 
	 * @param conversation - alle informatie over het request
	 */
	private void login(Conversation conversation) {
		JsonObject lJsonObjIn = (JsonObject) conversation.getRequestBodyAsJSON();
		
		String lGebruikersnaam = lJsonObjIn.getString("username");						// Uitlezen van opgestuurde inloggegevens... 
		String lWachtwoord = lJsonObjIn.getString("password");
		String lRol = informatieSysteem.login(lGebruikersnaam, lWachtwoord);		// inloggen methode aanroepen op domeinmodel...
		
		JsonObjectBuilder lJsonObjectBuilder = Json.createObjectBuilder();
		lJsonObjectBuilder.add("rol", lRol);																	// en teruggekregen gebruikersrol als JSON-object...
		String lJsonOut = lJsonObjectBuilder.build().toString();
		
		conversation.sendJSONMessage(lJsonOut);															// terugsturen naar de Polymer-GUI!
	}
}