package model.klas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public List<Student> getStudenten() {
		return Collections.unmodifiableList(deStudenten);
	}

	public boolean bevatStudent(Student pStudent) {
		return this.getStudenten().contains(pStudent);
	}

	public void voegStudentToe(Student pStudent) {
		if (!this.getStudenten().contains(pStudent)) {
			this.deStudenten.add(pStudent);
		}
	}

}
