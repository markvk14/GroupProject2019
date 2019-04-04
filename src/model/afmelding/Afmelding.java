package model.afmelding;

import model.persoon.Persoon;

public class Afmelding {
	private String type;
	private String reden;
	private Persoon deAfgemelde;
	
	public Afmelding(String iType, Persoon afgemelde){
		type = iType;
		deAfgemelde = afgemelde;
	}
	
	public Afmelding(String iType, String iReden, Persoon afgemelde){
		this(iType, afgemelde);
		reden = iReden;
	}
	
	public void setReden(String nieuweReden){
		reden = nieuweReden;
	}
	
	public String getReden(){	return reden;}
	
	public void setType(String lType){
		type = lType;
	}
	
	public String getType(){ return type;}
	
	public Persoon getAfgemelde(){ return deAfgemelde;}
	
	public boolean equals(Object object){
		boolean gelijkeObjecten = false;
		
		if(object instanceof Afmelding){
			Afmelding andereAfmelding = (Afmelding)object;
			
			if(this.type.equals(andereAfmelding.getType()) &&
					this.deAfgemelde.equals(andereAfmelding.getAfgemelde())){
				gelijkeObjecten = true;
			}
		}
		
		return gelijkeObjecten;
	}
}
