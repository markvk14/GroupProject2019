package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import model.rooster.Rooster;
import model.klas.Klas;
import model.les.Les;
import model.persoon.Docent;
import server.Conversation;
import server.Handler;

public class RoosterController implements Handler {
	private PrIS informatieSysteem;
	
	public RoosterController(PrIS infoSys){
		informatieSysteem = infoSys;
	}

	@Override
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("/rooster/ophalen/klas")){
			ophalenKlas(conversation);
		} else if (conversation.getRequestedURI().startsWith("/rooster/ophalen/docent")){
			ophalenDocent(conversation);
		} else if (conversation.getRequestedURI().startsWith("/rooster/ophalen")){
			ophalenAlles(conversation);
		}
	}

	private void ophalenKlas(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Rooster hetRooster = informatieSysteem.getRooster();
		
		// info om juiste rooster op te halen verzamelen
		String gebruikersnaam = lJsonObjectIn.getString("username");
		Klas opgevraagdeKlas = informatieSysteem.getKlasVanStudent(informatieSysteem.getStudent(gebruikersnaam));
		
		// rooster van de klas ophalen en sorteren
		ArrayList<Les> roosterVanKlas = hetRooster.getLessenVanKlas(opgevraagdeKlas);
		roosterVanKlas.sort(null);
		
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();
		
		for(Les lLes : roosterVanKlas){
			JsonObjectBuilder lJsonObjectBuilderVoorLes = Json.createObjectBuilder();

			// verzamel de info van de les
			@SuppressWarnings("rawtypes")
			ArrayList lesInfo = lLes.getInfo();
			String datumVanLesString = lesInfo.get(0).toString();
			String startTijdVanLesString = lesInfo.get(1).toString();
			String eindTijdVanLesString = lesInfo.get(2).toString();
			String vakVanLes = (String)lesInfo.get(3);
			Docent docentVanLes = (Docent)lesInfo.get(4);
			String gebruikersNaamVanDocent = docentVanLes.getGebruikersnaam();
			String locatieVanLes = (String)lesInfo.get(5);
			Klas klasVanLes = (Klas)lesInfo.get(6);
			String klasCodeVanLes = klasVanLes.getKlasCode();
			
			// voeg de info toe aan het json object
			lJsonObjectBuilderVoorLes
				.add("date", datumVanLesString)
				.add("start_time", startTijdVanLesString)
				.add("end_time", eindTijdVanLesString)
				.add("course", vakVanLes)
				.add("teacher", gebruikersNaamVanDocent)
				.add("location", locatieVanLes)
				.add("class_code", klasCodeVanLes);
			
			// voeg het sson object van de les toe aan de json array
			lJsonArrayBuilder.add(lJsonObjectBuilderVoorLes);
		}
		
		// en dan als alle afmeldingen gecheckt zijn antwoorden met de json array
		String lJsonOutStr = lJsonArrayBuilder.build().toString();
		conversation.sendJSONMessage(lJsonOutStr);
	}

	private void ophalenDocent(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Rooster hetRooster = informatieSysteem.getRooster();
		
		// info om juiste rooster op te halen verzamelen
		String gebruikersnaam = lJsonObjectIn.getString("username");
		Docent docent = informatieSysteem.getDocent(gebruikersnaam);
		System.out.println( "test" + docent);
		
		// rooster van de docent ophalen en sorteren
		ArrayList<Les> roosterVanDocent = hetRooster.getLessenVanDocent(docent);
		roosterVanDocent.sort(null);
		
		System.out.println(roosterVanDocent.size());
		
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();
		
		for(Les lLes : roosterVanDocent){
			
			JsonObjectBuilder lJsonObjectBuilderVoorLes = Json.createObjectBuilder();

			// verzamel de info van de les
			@SuppressWarnings("rawtypes")
			ArrayList lesInfo = lLes.getInfo();
			String datumVanLesString = lesInfo.get(0).toString();
			String startTijdVanLesString = lesInfo.get(1).toString();
			String eindTijdVanLesString = lesInfo.get(2).toString();
			String vakVanLes = (String)lesInfo.get(3);
			Docent docentVanLes = (Docent)lesInfo.get(4);
			String gebruikersNaamVanDocent = docentVanLes.getGebruikersnaam();
			String locatieVanLes = (String)lesInfo.get(5);
			Klas klasVanLes = (Klas)lesInfo.get(6);
			String klasCodeVanLes = klasVanLes.getKlasCode();
			
			// voeg de info toe aan het json object
			lJsonObjectBuilderVoorLes
				.add("date", datumVanLesString)
				.add("start_time", startTijdVanLesString)
				.add("end_time", eindTijdVanLesString)
				.add("course", vakVanLes)
				.add("teacher", gebruikersNaamVanDocent)
				.add("location", locatieVanLes)
				.add("class_code", klasCodeVanLes);
			
			// voeg het sson object van de les toe aan de json array
			lJsonArrayBuilder.add(lJsonObjectBuilderVoorLes);
		}
		
		// en dan als alle afmeldingen gecheckt zijn antwoorden met de json array
		String lJsonOutStr = lJsonArrayBuilder.build().toString();
		conversation.sendJSONMessage(lJsonOutStr);
	}

	private void ophalenAlles(Conversation conversation) {
		Rooster hetRooster = informatieSysteem.getRooster();
		// alle lessen in het rooster ophalen en sorteren
		ArrayList<Les> alleLessen = hetRooster.getLessen();
		alleLessen.sort(null);
		
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();
		
		for(Les lLes : alleLessen){
			JsonObjectBuilder lJsonObjectBuilderVoorLes = Json.createObjectBuilder();

			// verzamel de info van de les
			@SuppressWarnings("rawtypes")
			ArrayList lesInfo = lLes.getInfo();
			String datumVanLesString = lesInfo.get(0).toString();
			String startTijdVanLesString = lesInfo.get(1).toString();
			String eindTijdVanLesString = lesInfo.get(2).toString();
			String vakVanLes = (String)lesInfo.get(3);
			Docent docentVanLes = (Docent)lesInfo.get(4);
			String gebruikersNaamVanDocent = docentVanLes.getGebruikersnaam();
			String locatieVanLes = (String)lesInfo.get(5);
			Klas klasVanLes = (Klas)lesInfo.get(6);
			String klasCodeVanLes = klasVanLes.getKlasCode();
			
			// voeg de info toe aan het json object
			lJsonObjectBuilderVoorLes
				.add("date", datumVanLesString)
				.add("start_time", startTijdVanLesString)
				.add("end_time", eindTijdVanLesString)
				.add("course", vakVanLes)
				.add("teacher", gebruikersNaamVanDocent)
				.add("location", locatieVanLes)
				.add("class_code", klasCodeVanLes);
			
			// voeg het sson object van de les toe aan de json array
			lJsonArrayBuilder.add(lJsonObjectBuilderVoorLes);
		}
		
		// en dan als alle afmeldingen gecheckt zijn antwoorden met de json array
		String lJsonOutStr = lJsonArrayBuilder.build().toString();
		conversation.sendJSONMessage(lJsonOutStr);
	}
}
