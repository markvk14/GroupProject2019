package model.persoon;

public abstract class Persoon {

	private String voornaam, tussenvoegsel, achternaam, wachtwoord, gebruikersnaam;

	public Persoon(String voornaam, String tussenvoegsel, String achternaam, String wachtwoord, String gebruikersnaam) {
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.wachtwoord = wachtwoord;
		this.gebruikersnaam = gebruikersnaam;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		boolean gelijk = false;
		if (obj instanceof Persoon) {
			Persoon p = (Persoon) obj;
			gelijk = this.voornaam.equals(p.voornaam) && this.tussenvoegsel.equals(p.tussenvoegsel)
					&& this.achternaam.equals(p.achternaam) && this.wachtwoord.equals(p.wachtwoord)
					&& this.gebruikersnaam.equals(gebruikersnaam);
		}
		return gelijk;
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
		String lVolledigeAchternaam = "";
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
}
