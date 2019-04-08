package model.les;
import java.util.Calendar;
import absentie.Absentie;
import model.persoon.Student;
import java.util.ArrayList;

public class les {
	private String Datum;
	private String Starttijd;
	private String Eindtijd;
	private String Cursuscode;
	private String Docenten;
	private String Groep;
	private ArrayList<Absentie> Zieken;
	
	public les(String D, String ST, String ET, String Cc, String dc, String g){
		Datum = D;
		Starttijd = ST;
		Eindtijd = ET;
		Cursuscode = Cc;
		Docenten = dc;
		Groep = g;
	}
	public void addZieke(Absentie a) {
		Zieken.add(a);
	}
	public ArrayList<Absentie> returnZieken(){
		return Zieken;
	}
	
	@SuppressWarnings("null")
	public String[] returnZiekenString() {
		int i = 0;
		String[] stringZieken = null;
		if (Zieken.size() > 0) {
			for (Absentie a : Zieken) {
				stringZieken[i] = a.getPersoon().getVolledigeNaam();
				i++;
			}
			return stringZieken;
		}
		else {return null;}
	}
	public void removeZieke(Absentie a) {
		Zieken.remove(a);
	}
	public String[] returnLes() {
		String[] Les = null;
		Les[0] = Datum;
		Les[1] = Starttijd;
		Les[2] = Eindtijd;
		Les[3] = Cursuscode;
		Les[4] = Docenten;
		Les[5] = Groep;
		return Les;
		
		
	}
	public String returnKlas() {
		return Groep;
	}
	public String returnCursuscode() {
		return Cursuscode;
	}
}
