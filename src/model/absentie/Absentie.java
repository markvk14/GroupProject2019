package model.absentie;

import model.les.les;
import model.persoon.Student;

public class Absentie {
	private les AbsenteLes = null;
	private Student AbsenteStudent = null;

public Absentie() {}

public void setLes(les Les) {
	AbsenteLes = Les;
	
}

public void setStudent(Student student) {
	AbsenteStudent = student;
}

public les getLes() {
	return AbsenteLes;
}

public Student getStudent() {
	return AbsenteStudent;
}
}