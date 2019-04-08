package model.les;
import java.util.Calendar;
import model.persoon.Student;
import java.util.ArrayList;

public class Lessen {
	private String Datum;
	private String Starttijd;
	private String Eindtijd;
	private String Cursuscode;
	private String Docenten;
	private String Groep;
	private ArrayList<Student> Zieken;
	
	public Lessen(String D, String ST, String ET, String Cc, String dc, String g){
		Datum = D;
		Starttijd = ST;
		Eindtijd = ET;
		Cursuscode = Cc;
		Docenten = dc;
		Groep = g;
	}
	public void addZieke(Student student) {
		Zieken.add(student);
	}
	public ArrayList<Student> returnZieken(){
		return Zieken;
	}
	public void removeZieke(Student student) {
		Zieken.remove(student);
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
