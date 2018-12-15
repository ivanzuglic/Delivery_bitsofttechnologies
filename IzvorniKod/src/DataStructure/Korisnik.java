package DataStructure;

public class Korisnik {

	private String korisnickoIme;
	private String lozinka;
	
	private String ime;
	private String prezime;
	private String eMail;
	private int starost;
	
	
	// konstruktor koji se koristi prilikom prijave
	public Korisnik (String korisnickoIme, String lozinka) {
		
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		
		// dohvati ostatak podataka iz baze podataka
	}
	
	// konstruktor koji se koristi prilikom registracije
	public Korisnik (String korisnickoIme, String lozinka, String ime, String prezime, String eMail, int starost) {
		
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.eMail = eMail;
		this.starost = starost;
		
		// pohrani korisnika u bazu podataka
	}

	public String getKorisnickoIme() {
		
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		
		this.lozinka = lozinka;
	}

	public String getIme() {
		
		return ime;
	}

	public void setIme(String ime) {
		
		this.ime = ime;
	}

	public String getPrezime() {
		
		return prezime;
	}

	public void setPrezime(String prezime) {
		
		this.prezime = prezime;
	}

	public String geteMail() {
		
		return eMail;
	}

	public void seteMail(String eMail) {
	
		this.eMail = eMail;
	}

	public int getStarost() {
		
		return starost;
	}

	public void setStarost(int starost) {
		
		this.starost = starost;
	}
}
