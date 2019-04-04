package model.klas;
import java.util.ArrayList;

import model.persoon.Student;

public class Klas {

	private String klasCode;
	private String naam;
	private ArrayList<Student> deStudenten = new ArrayList<Student>();

	public Klas(String klasCode, String naam) {
		this.klasCode = klasCode;
		this.naam = naam;
	}
	
	public String getKlasCode() {
		return klasCode;
	}
	
	public String getNaam() {
		return naam;
	}

	public ArrayList<Student> getStudenten() {
		return this.deStudenten;
	}
	
	public boolean bevatStudent(Student pStudent) {
		for (Student lStudent : this.getStudenten()) {
			if (lStudent==pStudent) {
				return true;
			}
		}
		return false;
	}

	public void voegStudentToe(Student pStudent) {
		if (!this.getStudenten().contains(pStudent)) {
			this.getStudenten().add(pStudent);
		}
	}
	public boolean equals(Object obj){
		boolean gelijkenObjecten = false;
		if  (obj instanceof Klas){
			Klas andereKlas = (Klas) obj;
			if (klasCode == andereKlas.klasCode &&  naam == andereKlas.naam && deStudenten == andereKlas.deStudenten){
				gelijkenObjecten = true;
			}
		}
		return gelijkenObjecten;
	}
}
