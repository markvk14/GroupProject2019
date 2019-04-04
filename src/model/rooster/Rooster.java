package model.rooster;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.klas.Klas;
import model.les.Les;
import model.persoon.Docent;

public class Rooster {	
	private ArrayList<Les> deLessen = new ArrayList<Les>();
	
	public Rooster() {
	}
	
	public void voegLesToe(Les les) {
		if(!(deLessen.contains(les))){
			getLessen().add(les);
		}
	}
	
	public Les getLes(LocalDate datum, LocalTime startTijd, String locatie){
		for(Les les : deLessen){
			@SuppressWarnings("rawtypes")
			ArrayList lesinfo = les.getInfo();
			LocalDate lDatum = (LocalDate) lesinfo.get(0);
			LocalTime lStartTijd = (LocalTime) lesinfo.get(1);
			String lLocatie = (String) lesinfo.get(5);
			
			if(lDatum.equals(datum) &&
					lStartTijd.equals(startTijd) &&
					lLocatie.equals(locatie)){
				return les;
			}
		}
		return null;
	}
			
	public ArrayList<Les> getLessen() {
			return deLessen;
	}
	
	public ArrayList<Les> getLessenVanKlas(Klas klas) {
		ArrayList<Les> lessenVanKlas = new ArrayList<Les>();
		
			for (Les les : deLessen) {
				Klas klasVanLes = (Klas)les.getInfo().get(6);
				if (klasVanLes.equals(klas)) {
					lessenVanKlas.add(les);
				}
		}
		return lessenVanKlas;
		
	}
	
	public ArrayList<Les> getLessenVanDocent(Docent docent) {
		ArrayList<Les> lessenVanDocent = new ArrayList<Les>();
		
		for (Les les : deLessen) {
			Docent docentVanLes = (Docent)les.getInfo().get(4);
			if (docentVanLes.equals(docent)) {
				lessenVanDocent.add(les);
			}
	}
		
	return lessenVanDocent;
	}
}
