package Rooster;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.PrIS;
import model.klas.Klas;
import model.les.les;
import model.persoon.Docent;

public class Rooster {
	private ArrayList<les> Lessen = new ArrayList<les>();
	PrIS infoSysteem = new PrIS();
	public Rooster() {
		
	}
	
	public void toevoegenLessen(les les) {
		return"placeholder";
	}
	
	public les getLes() {
		return "placeholder";
	}
	
	public ArrayList<les> getLessen() {
		return Lessen;
	}
	
	public ArrayList<les> getLessenKlas(Klas Klas) {
		ArrayList<les> lessenKlas = new ArrayList<les>();
		
		for (les les : Lessen) {
			Docent lesKrijgendeKlas = (Klas)PrIS.returnLessen().get(6);
			if (lesKrijgendeKlas.equals(Klas)) {
				lessenKlas.add(les);
			}
		}
	}
	
	public ArrayList<les> getLessenDocent(Docent docent) {
		ArrayList<les> lessenDocent = new ArrayList<les>();
		infoSysteem.vulLessen(Lessen);
		for (les les : Lessen) {
			Docent lesGevendeDocent = (Docent)PrIS.returnLessen().get(4);
			if (lesGevendeDocent.equals(docent)) {
				lessenDocent.add(les);
			}
		}
	}
	
}
