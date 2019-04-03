package model;
import java.util.ArrayList;

import model.klas.Klas;
import model.les.les;
import model.persoon.Docent;
import model.persoon.Student;
public class tester {

public static void main(String[] args) {
	ArrayList<les> test = new ArrayList<les>();
	PrIS infoSysteem = new PrIS();
	Student bram = infoSysteem.getStudent(1748635);
	infoSysteem.vulLessen(test);
	for (int i=0; i < test.size(); i++) {
		System.out.println(test.get(i).returnLes());
	}
}
}