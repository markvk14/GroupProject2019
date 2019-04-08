package model;
import java.util.ArrayList;

import model.klas.Klas;
import model.les.les;
import model.persoon.Docent;
import model.persoon.Student;
public class tester {

public static void main(String[] args) {

	PrIS infoSysteem = new PrIS();
	Student Bram = new Student("a", "a", "a", "a", "a", 1);
	Bram.setGroepId("V1A");
	Bram.getLessen();
	}
}
