//checked
package model.persoon;
import model.les.les;

import java.util.ArrayList;

import model.PrIS;

public class Student extends Persoon {

	private int studentNummer;
	private String groepId;

	public Student(String pVoornaam, String pTussenvoegsel, String pAchternaam, String pWachtwoord,
			String pGebruikersnaam, int sStudentNummer) {
		super(pVoornaam, pTussenvoegsel, pAchternaam, pWachtwoord, pGebruikersnaam);
		this.studentNummer = sStudentNummer;
		this.setGroepId("");
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj) && obj instanceof Student) {
			Student s = (Student) obj;
			return this.studentNummer == s.studentNummer;
		} else {
			return false;
		}
	}

	public String getGroepId() {
		return this.groepId;
	}

	public void setGroepId(String pGroepId) {
		this.groepId = pGroepId;
	}

	public int getStudentNummer() {
		return this.studentNummer;
	}


	@SuppressWarnings("null")
	public ArrayList<les> getLessen() {
		PrIS infoSysteem = new PrIS();
		ArrayList<les> eigenLessen = null;
		ArrayList<les> alleLessen = infoSysteem.returnLessen();
		for (int i = 0; i < alleLessen.size(); i++) {
			String[] groepCodes = alleLessen.get(i).returnKlas().split("-");
			if (groepCodes[2] == groepId) {
				eigenLessen.add(alleLessen.get(i));
		}
		}
		return eigenLessen;
	}
}