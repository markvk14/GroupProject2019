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
	public void removeZieke(Absentie a) {
		Zieken.remove(a);
	}
	public String returnLes() {
		String Les = Datum+","+Starttijd+","+Eindtijd+","+Cursuscode+","+Docenten+","+Groep;
		return Les;
		
		
	}
	public String returnKlas() {
		return Groep;
	}
	public String returnCursuscode() {
		return Cursuscode;
	}
}
