//checked
package model.persoon;



public class Student extends Persoon implements Comparable<Student>{
	private int studentNummer;
	private String groepId;

	public Student(
		String pVoornaam, 
		String pTussenvoegsel, 
		String pAchternaam, 
		String pWachtwoord, 
		String pGebruikersnaam,
		int pStudentNummer) {
  		super(
  			pVoornaam, 
  			pTussenvoegsel, 
  			pAchternaam, 
  			pWachtwoord, 
  			pGebruikersnaam);
  		this.setStudentNummer(pStudentNummer);
  		this.setGroepId("");
  	}


 public String getGroepId() {
    return this.groepId;	
  }
 
  public void setGroepId(String pGroepId) {
    this.groepId= pGroepId;	
  }
 
	public int getStudentNummer() {
		return this.studentNummer;
	}

	private void setStudentNummer(int pStudentNummer) {
		this.studentNummer = pStudentNummer;
	}
	
	public boolean equals(Object obj){
		boolean gelijkenObjecten = false;
		if  (obj instanceof Student){
			Student andereStudent = (Student) obj;
			if (this.studentNummer == andereStudent.studentNummer &&
					this.getVolledigeAchternaam().equals(andereStudent.getVolledigeAchternaam()) &&
					this.getVoornaam().equals(andereStudent.getVoornaam())){
				gelijkenObjecten = true;
			}
		}
		return gelijkenObjecten;
	}
	
	public int compareTo(Student andereStudent){
		int res = this.getVolledigeAchternaam().compareToIgnoreCase(andereStudent.getVolledigeAchternaam());
		if(res != 0){
			return res;
		}
		return this.getVoornaam().compareToIgnoreCase(andereStudent.getVoornaam());
	}
}
