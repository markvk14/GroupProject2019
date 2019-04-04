package model.persoon;


public class Docent extends Persoon {

	private int docentNummer;
	
	public Docent(String voornaam, String tussenvoegsel, String achternaam, String wachtwoord, String gebruikersnaam, int docentNummer) {
		super(voornaam, tussenvoegsel, achternaam, wachtwoord, gebruikersnaam);
		docentNummer = 0;
	}

	public int getDocentNummer() {
		return docentNummer;
	}

	public void setDocentNummer(int docentNummer) {
		this.docentNummer = docentNummer;
	}

	public boolean equals(Object obj){
		boolean gelijkenObjecten = false;
		if  (obj instanceof Docent){
			Docent andereDocent = (Docent) obj;
			if (this.docentNummer == andereDocent.docentNummer &&
					this.getVoornaam().equals(andereDocent.getVoornaam()) &&
					this.getVolledigeAchternaam().equals(andereDocent.getVolledigeAchternaam())){		
				gelijkenObjecten = true;
			}
		}
		return gelijkenObjecten;
	}
}
