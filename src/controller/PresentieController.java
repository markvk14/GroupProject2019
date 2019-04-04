package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import model.afmelding.Afmelding;
import model.klas.Klas;
import model.les.Les;
import model.persoon.Persoon;
import model.persoon.Student;
import model.rooster.Rooster;
import server.Conversation;
import server.Handler;

public class PresentieController implements Handler{
	private PrIS informatieSysteem;
	
	public PresentieController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}

	@Override
	public void handle(Conversation conversation){
		if(conversation.getRequestedURI().startsWith("/les/presentie/ophalen")){
			ophalenLes(conversation);
		} else if (conversation.getRequestedURI().startsWith("/les/presentie/opslaan")){
			opslaanLes(conversation);
		} else if (conversation.getRequestedURI().startsWith("/persoon/presentie/opslaan")){
			opslaanPersoon(conversation);
		}
	}

	private void opslaanLes(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Rooster hetRooster = informatieSysteem.getRooster();
		
		// info verzamelen om de les mee op te vragen
		
		// datum en tijd ophalen
		LocalDate lDatum_Les = LocalDate.parse(lJsonObjectIn.getString("date"));
		
		LocalTime lStartTijd_Les = LocalTime.parse(lJsonObjectIn.getString("start-time"));
		
		// locatie van de les van de afmelding uit de request halen
		String lLocatie_afmelding = lJsonObjectIn.getString("location");
		
		// les ophalen waarvoor de presentie opgeslagen moet worden
		Les lLesVan_afmelding = hetRooster.getLes(lDatum_Les, lStartTijd_Les, lLocatie_afmelding);
		
		if(lLesVan_afmelding.getPresentieBijgewerkt() == true){
			lLesVan_afmelding.leegAfmeldingen();
		}
		
		JsonArray lAfmeldingen_jArray = lJsonObjectIn.getJsonArray("non-appearance");
		if(lAfmeldingen_jArray != null){
			for(int i=0;i<lAfmeldingen_jArray.size();i++){
				JsonObject lAfmelding_jsonObj = lAfmeldingen_jArray.getJsonObject(i);
				
				// info van afmelding ophalen
				int lStudentNrVanAfmelding = lAfmelding_jsonObj.getInt("number");
				Student lStudentVanAfmelding = informatieSysteem.getStudent(lStudentNrVanAfmelding);
				String lTypeVanAfmelding = lAfmelding_jsonObj.getString("type");
				
				// afmelding toevoegen aan les
				lLesVan_afmelding.voegAfmeldingToe(lStudentVanAfmelding, lTypeVanAfmelding);
			}
		}
		
		// output json object aanmaken en errorcode toevoegen
		JsonObjectBuilder lJob =	Json.createObjectBuilder(); 
	  lJob.add("errorcode", 0);
	  // nothing to return use only errorcode to signal: ready!
	  String lJsonOutStr = lJob.build().toString();
	 	conversation.sendJSONMessage(lJsonOutStr);
	}
	
	private void ophalenLes(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Rooster hetRooster = informatieSysteem.getRooster();
		
		// info verzamelen om de les mee op te vragen
		
		// calendar object converten naar LocalDate en LocalTime
		LocalDate lDatum_Les = LocalDate.parse(lJsonObjectIn.getString("date"));
		
		LocalTime lStartTijd_Les = LocalTime.parse(lJsonObjectIn.getString("start-time"));
		
		// locatie van de les de request halen
		String lLocatie_Les = lJsonObjectIn.getString("location");
		
		// les ophalen waarvoor de presentie opgehaald moet worden
		Les lLes = hetRooster.getLes(lDatum_Les, lStartTijd_Les, lLocatie_Les);
		
		if(lLes == null){
			System.out.println("les niet gevonden");
		} else {
			System.out.println("les gevonden");
		}
		
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();
		ArrayList<Afmelding> lAfmeldingenVanLes = lLes.getAfmeldingen();
		if(lLes.getPresentieBijgewerkt() == true){
  		for(Afmelding lAfmelding : lAfmeldingenVanLes){
  			Persoon tempPersoon = lAfmelding.getAfgemelde();
  			
  			// als de afgemelde niet een student is gaat hij naar de volgende afmelding
  			if(!(tempPersoon instanceof Student)){
  				continue;
  			}
  			
  			// info van afmelding ophalen
  			Student persoonVanAfmelding = (Student) tempPersoon;
  			int nummerVanPersoon = persoonVanAfmelding.getStudentNummer();
  			String naamVanPersson = persoonVanAfmelding.getVoornaam() + " " + persoonVanAfmelding.getVolledigeAchternaam();
  			
  			JsonObjectBuilder lJsonObjectBuilderVoorAfmelding = Json.createObjectBuilder();
  			
  			// info in json object zetten
  			lJsonObjectBuilderVoorAfmelding
  				.add("number", nummerVanPersoon)
  				.add("name", naamVanPersson)
  				.add("type", lAfmelding.getType());
  			
  			// json object aan json array toevoegen
  			lJsonArrayBuilder.add(lJsonObjectBuilderVoorAfmelding);
  		}
		} else{
			Klas klasVanLes = lLes.getKlas();
			if(klasVanLes == null){
				System.out.println("klas niet gevonden");
			} else {
				System.out.println("klas gevonden");
			}
			ArrayList<Student> studentenVanKlas = klasVanLes.getStudenten();
			for(Student student : studentenVanKlas){
				JsonObjectBuilder lJsonObjectBuilderVoorAfmelding = Json.createObjectBuilder();
				
				lJsonObjectBuilderVoorAfmelding
				.add("number", student.getStudentNummer())
				.add("name", student.getVoornaam()+ " " + student.getVolledigeAchternaam())
				.add("type", "aanwezig");
				
				lJsonArrayBuilder.add(lJsonObjectBuilderVoorAfmelding);
			}
		}
		
		// en dan als alle afmeldingen gecheckt zijn antwoorden met de json array
		String lJsonOutStr = lJsonArrayBuilder.build().toString();
		conversation.sendJSONMessage(lJsonOutStr);
	}

	private void opslaanPersoon(Conversation conversation){
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		Rooster hetRooster = informatieSysteem.getRooster();
		String gebruikersNaamVanPersoon = lJsonObjectIn.getString("username");
		
		// eerst proberen met de gebruikersnaam een student op te halen
		Persoon persoonVanAfmelding = informatieSysteem.getStudent(gebruikersNaamVanPersoon);
		if(persoonVanAfmelding == null){
			persoonVanAfmelding = informatieSysteem.getDocent(gebruikersNaamVanPersoon);
		}
		
		JsonObject lAfmelding_Object = lJsonObjectIn.getJsonObject("non-appearance");
		
		if(lAfmelding_Object != null){
			
			// info verzamelen om de les mee op te vragen
			
			// datum en begin tijd ophalen
			LocalDate lDatumVanAfmelding = LocalDate.parse(lAfmelding_Object.getString("date"));
			LocalTime lStartTijdVanAfmelding = LocalTime.parse(lAfmelding_Object.getString("start-time"));
			
			// locatie van de les van de afmelding uit de request halen
			String lLocatie_afmelding = lAfmelding_Object.getString("location");
			
			// les ophalen waarvoor afgemeld is
			System.out.println(lDatumVanAfmelding.toString() + lStartTijdVanAfmelding.toString() + lLocatie_afmelding);
			Les lLesVan_afmelding = hetRooster.getLes(lDatumVanAfmelding, lStartTijdVanAfmelding, lLocatie_afmelding);
			String lTypeAfmelding = lAfmelding_Object.getString("type");
			String lRedenAfmelding = lAfmelding_Object.getString("reason");
			if(lLesVan_afmelding == null){
				System.out.println("les niet gevonden");
			} else {
				System.out.println("les gevonden");
			}
			
			if(lLesVan_afmelding.getPresentieBijgewerkt() == true){
				ArrayList<Afmelding> afmeldingenVanLes = lLesVan_afmelding.getAfmeldingen();
				for(Afmelding afmelding : afmeldingenVanLes){
					if(afmelding.getAfgemelde().equals(persoonVanAfmelding)){
						afmelding.setReden(lRedenAfmelding);
						afmelding.setType(lTypeAfmelding);
						break;
					}
				}
			} else {
				Klas klasVanLes = lLesVan_afmelding.getKlas();
				ArrayList<Student> studentenVanKlas = klasVanLes.getStudenten();
				for(Student student : studentenVanKlas){
					if(student.equals(persoonVanAfmelding)){
						lLesVan_afmelding.voegAfmeldingMetRedeToe(lTypeAfmelding, lRedenAfmelding, persoonVanAfmelding);
					}
					lLesVan_afmelding.voegAfmeldingToe(student, "aanwezig");
				}
			}
			
			// afmelding toevoegen aan les
			lLesVan_afmelding.voegAfmeldingMetRedeToe(lTypeAfmelding, lRedenAfmelding, persoonVanAfmelding);
			
		
		}
		
		// output json object aanmaken en errorcode toevoegen
		JsonObjectBuilder lJob =	Json.createObjectBuilder(); 
  	lJob.add("errorcode", 0);
   	//nothing to return use only errorcode to signal: ready!
  	String lJsonOutStr = lJob.build().toString();
 		conversation.sendJSONMessage(lJsonOutStr);
	}
}
