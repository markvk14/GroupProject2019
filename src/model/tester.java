package model;
import java.util.ArrayList;

import model.les.les;
import model.persoon.Docent;
import model.persoon.Student;
public class tester {

public static void main(String[] args) {
	ArrayList<les> test = new ArrayList<les>();
	PrIS infoSysteem = new PrIS();
	int bram = 1743650;
	Student bram1 = infoSysteem.getStudent(bram);
	bram1.getStudentNummer();
}
}