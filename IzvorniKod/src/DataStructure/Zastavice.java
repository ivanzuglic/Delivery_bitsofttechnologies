package DataStructure;

public class Zastavice {

	private boolean klijent;
	private boolean vlasnik;
	private boolean dispecer;
	private boolean dostavljac;
	private boolean administrator;
	
	
	public Zastavice () {
		
		this.klijent = false;
		this.vlasnik = false;
		this.dispecer = false;
		this.dostavljac = false;
		this.administrator = false;
	}
	
	public void resetirajZastavice () {
		
		this.klijent = false;
		this.vlasnik = false;
		this.dispecer = false;
		this.dostavljac = false;
		this.administrator = false;
	}

	public boolean isKlijent() {
		
		return klijent;
	}

	public void setKlijent(boolean klijent) {
		
		this.klijent = klijent;
	}

	public boolean isVlasnik() {
		
		return vlasnik;
	}

	public void setVlasnik(boolean vlasnik) {
		
		this.vlasnik = vlasnik;
	}

	public boolean isDispecer() {
		
		return dispecer;
	}

	public void setDispecer(boolean dispecer) {
		
		this.dispecer = dispecer;
	}

	public boolean isDostavljac() {
		
		return dostavljac;
	}

	public void setDostavljac(boolean dostavljac) {
		
		this.dostavljac = dostavljac;
	}

	public boolean isAdministrator() {
		
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		
		this.administrator = administrator;
	}
}
