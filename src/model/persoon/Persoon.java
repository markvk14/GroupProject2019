package model.persoon;

public abstract class Persoon{

	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private String wachtwoord;
	private String gebruikersnaam;

	public Persoon(String voornaam, String tussenvoegsel, String achternaam, String wachtwoord, String gebruikersnaam) {
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.wachtwoord = wachtwoord;
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	private String getAchternaam() {
		return this.achternaam;
	}

	protected String getWachtwoord() {
		return this.wachtwoord;
	}

	public String getGebruikersnaam() {
		return this.gebruikersnaam;
	}

	public String getVolledigeAchternaam() {
		String lVolledigeAchternaam="";
		if (this.tussenvoegsel != null && this.tussenvoegsel != "" && this.tussenvoegsel.length() > 0) {
			lVolledigeAchternaam += this.tussenvoegsel + " ";
		}
		lVolledigeAchternaam += this.getAchternaam();
		return lVolledigeAchternaam;
	}

	public boolean komtWachtwoordOvereen(String pWachtwoord) {
		boolean lStatus = false;
		if (this.getWachtwoord().equals(pWachtwoord)) {
			lStatus = true;
		}
		return lStatus;
	}
	
	public boolean equals(Object obj){
		boolean gelijkenObjecten = false;
		if  (obj instanceof Persoon){
			Persoon anderePersoon = (Persoon) obj;
			if (voornaam == anderePersoon.getVoornaam() &&
					getVolledigeAchternaam() == anderePersoon.getVolledigeAchternaam() &&
					wachtwoord == anderePersoon.getWachtwoord() &&
					gebruikersnaam == anderePersoon.getGebruikersnaam() ){		
				gelijkenObjecten = true;
			}
		}
		return gelijkenObjecten;
	}
}
