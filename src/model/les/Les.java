package model.les;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.afmelding.Afmelding;
import model.klas.Klas;
import model.persoon.Docent;
import model.persoon.Persoon;

public class Les implements Comparable<Les>{
	private LocalDate datum;
	private LocalTime startTijd;
	private LocalTime eindTijd;
	private String vak;
	private Docent deDocent;
	private String locatie;
	private Klas deKlas;
	private ArrayList<Afmelding> deAfmeldingen;
	private boolean presentieVerwerkt;
	
	public Les(LocalDate iDatum, LocalTime iStartTijd, LocalTime iEindTijd, String iVak, Docent iDeDocent, String iLocatie, Klas iDeKlas){
		datum = iDatum;
		startTijd = iStartTijd;
		eindTijd = iEindTijd;
		vak = iVak;
		deDocent = iDeDocent;
		locatie = iLocatie;
		deKlas = iDeKlas;
		deAfmeldingen = new ArrayList<Afmelding>();
		presentieVerwerkt = false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList getInfo(){
		ArrayList infoList = new ArrayList();
		
		infoList.add(datum);
		infoList.add(startTijd);
		infoList.add(eindTijd);
		infoList.add(vak);
		infoList.add(deDocent);
		infoList.add(locatie);
		infoList.add(deKlas);
		
		return infoList;
	}
	
	public Klas getKlas(){return deKlas;}
	
	public boolean getPresentieBijgewerkt(){
		return presentieVerwerkt;
	}
	
	public void voegAfmeldingToe(Persoon deAfgemelde, String type){
		Afmelding afmelding = new Afmelding(type, deAfgemelde);
		
		if (!(deAfmeldingen.contains(afmelding))){
			deAfmeldingen.add(afmelding);
			if(!(presentieVerwerkt)){
				presentieVerwerkt = true;
			}
		}
	}
	
	public void voegAfmeldingMetRedeToe(String type, String reden, Persoon afgemelde){
		Afmelding afmelding = new Afmelding(type, reden, afgemelde);
		
		if (!(deAfmeldingen.contains(afmelding))){
			deAfmeldingen.add(afmelding);
			if(!(presentieVerwerkt)){
				presentieVerwerkt = true;
			}
		}
	}
	
	public ArrayList<Afmelding> getAfmeldingen(){
		return deAfmeldingen;
	}
	
	public void leegAfmeldingen(){
		deAfmeldingen.clear();
	}
	
	public boolean equals(Object object){
		boolean gelijkeObjecten = false;
		
		if( object instanceof Les){
			Les andereLes = (Les)object;
			
			if(this.datum.equals(andereLes.datum) &&
					this.startTijd.equals(andereLes.startTijd) &&
					this.eindTijd.equals(andereLes.eindTijd) &&
					this.vak.equals(andereLes.vak) &&
					this.deDocent.equals(andereLes.deDocent) &&
					this.locatie.equals(andereLes.locatie) &&
					this.deKlas.equals(andereLes.deKlas) &&
					this.deAfmeldingen.equals(andereLes.deAfmeldingen)){
				gelijkeObjecten = true;
			}
		}
		
		return gelijkeObjecten;
	}
	
	public int compareTo(Les andereLes){
		if(this.datum.isAfter(andereLes.datum)){
			return 1; //deze les is later
		} else if(this.datum.isBefore(andereLes.datum)){
			return -1; //deze les is eerder
		} else if(this.datum.equals(andereLes.datum)){
			if(this.startTijd.isAfter(andereLes.startTijd)){
				return 1; //deze les begint later op dezelfde dag
			} else if(this.startTijd.isBefore(andereLes.startTijd)){
				return -1; //deze les begint eerder op dezelfde dag
			}
		}
		
		return 0; //de Lessen zijn op de zelfde datum en tijd
	}
}
