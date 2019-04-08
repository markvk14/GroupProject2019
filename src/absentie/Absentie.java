package absentie;
import model.persoon.Persoon;

public class Absentie {
	private Persoon Absente;
	private String Reden;
	private String Type;


public Absentie(Persoon p, String r, String t) {
	Absente = p;
	Reden = r;
	Type = t;
}

public Absentie(Persoon p, String t) {
	Absente = p;
	Type = t;
}

public void setReden(String r) {
	Reden = r;
}

public String returnAbsentie() {
	String s = Absente.getGebruikersnaam() + Reden + Type;
	return s;
}


public String getReden() { return Reden; }
public String getType() { return Type; }
public Persoon getPersoon() { return Absente; }
}
