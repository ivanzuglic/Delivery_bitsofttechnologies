package DataStructure;

public class Korisnik {				// Ivan: maknut atribut 'starost' i njegovi konstruktori

	private int idKor;				 
	private String uloga;			
	
	private String korisnickoIme;
	private String lozinka;
	
	private String ime;
	private String prezime;
	private String eMail;
	private String brMobitela;
	
	
	// konstruktor koji se koristi prilikom prijave
	public Korisnik (String korisnickoIme, String lozinka) {
		
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		
		this.dohvatiBP();
	}
	
	// konstruktor koji se koristi prilikom registracije
	public Korisnik (String korisnickoIme, String lozinka, String ime, String prezime, String brMobitela, String eMail) {
		
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.eMail = eMail;
		this.uloga = "KLIJENT";
		this.setBrMobitela(brMobitela);
		
		this.pohraniBP();
		// i vrati idKor (ako se id generira automatski, tj. ne zadaje ga sam korisnik --> dogovorit se)
		
		//this.idKor = pohraniKorisnika();
	}
	
	public int getKorisnickiId () {
		
		return this.idKor;
	}

	public String getKorisnickoIme () {
		
		return korisnickoIme;
	}

	public void setKorisnickoIme (String korisnickoIme) {
		
		this.korisnickoIme = korisnickoIme;
		this.azurirajBP();
	}

	public String getLozinka () {
		
		return this.lozinka;
	}

	public void setLozinka (String lozinka) {
		
		this.lozinka = lozinka;
		this.azurirajBP();
	}

	public String getIme () {
		
		return this.ime;
	}

	public void setIme (String ime) {
		
		this.ime = ime;
		this.azurirajBP();
	}

	public String getPrezime () {
		
		return this.prezime;
	}

	public void setPrezime (String prezime) {
		
		this.prezime = prezime;
		this.azurirajBP();
	}

	public String geteMail () {
		
		return this.eMail;
	}

	public String getBrMobitela() {
		
		return brMobitela;
	}

	public void setBrMobitela(String brMobitela) {
		
		this.brMobitela = brMobitela;
		this.azurirajBP();
	}

	public void seteMail (String eMail) {
	
		this.eMail = eMail;
		this.azurirajBP();
	}

	
	private void pohraniBP () {
		
		// metoda koja ce novog korisnika pohranjivati u bazu podataka
	}
	
	private void azurirajBP () {
		
		// metoda koja ce promjene u korisniku pohranjivati u bazu podataka (UPDATE)
	}
	
	private void dohvatiBP () {
		
		// metoda koja ce iz baze podataka dohvatiti preostale podatke prilikom prijave
	}
}
