package DataStructure;

import Database.KorisnikDAO;

public class Korisnik {

	private String korisnickoIme;
	private String lozinka;
	
	private int idKor;				 
	private String uloga;			
	private String ime;
	private String prezime;
	private String eMail;
	private String brMobitela;
	
	
	// konstruktor koji se koristi prilikom prijave
	public Korisnik (String korisnickoIme, String lozinka) {
		
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		
		this.dohvatiBP(korisnickoIme);
		this.idKor = this.dohvatiKorId(korisnickoIme);
	}
	
	// konstruktor koji se koristi prilikom registracije
	public Korisnik (String korisnickoIme, String lozinka, String ime, String prezime, String brMobitela, String eMail) {
		
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.eMail = eMail;
		this.uloga = "KLIJENT";
		this.brMobitela = brMobitela;
		
		this.pohraniBP();
		this.idKor = this.dohvatiKorId(korisnickoIme);
	}
	
	//konstruktor koji se koristi kada stvaramo korisnika da ga spremimo u neku listu
	public Korisnik (int idKor, String korisnickoIme, String lozinka, String ime, String prezime, String brMobitela, String eMail, String Uloga) {
		
		this.idKor = idKor;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.brMobitela = brMobitela;
		this.eMail = eMail;
		this.uloga = Uloga;
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

	public String getBrMobitela() {
		
		return brMobitela;
	}

	public void setBrMobitela(String brMobitela) {
		
		this.brMobitela = brMobitela;
		this.azurirajBP();
	}

	public String getEMail () {
		
		return this.eMail;
	}
	
	public void setEMail (String eMail) {
	
		this.eMail = eMail;
		this.azurirajBP();
	}

	public String getUloga () {
		
		return this.uloga;
	}

	private void pohraniBP () {
		
		// metoda koja ce novog korisnika pohranjivati u bazu podataka
		
		KorisnikDAO dao = new KorisnikDAO();
		dao.pohraniKorisnika(this);
	}
	
	private void azurirajBP () {
		
		// metoda koja ce promjene u korisniku pohranjivati u bazu podataka (UPDATE)
		
		KorisnikDAO dao = new KorisnikDAO();
		dao.azurirajKorisnika(this);
	}
	
	private void dohvatiBP (String korisnickoIme) {
		
		// metoda koja ce iz baze podataka dohvatiti preostale podatke prilikom prijave
		
		KorisnikDAO dao = new KorisnikDAO();
		this.idKor = dao.dohvatiId(korisnickoIme);
		this.uloga = dao.dohvatiUlogu(korisnickoIme);
		this.ime = dao.dohvatiIme(korisnickoIme);
		this.prezime = dao.dohvatiPrezime(korisnickoIme);
		this.eMail = dao.dohvatiEMail(korisnickoIme);
		this.brMobitela = dao.dohvatiMobitel(korisnickoIme);
	}
	
	private int dohvatiKorId (String korisnickoIme) {
		
		// metoda koja iz baze podataka dohvaca korisnicki ID nakon  sto je on isti bio generiran u njoj
		
		int korId;
		
		KorisnikDAO dao = new KorisnikDAO();
		korId = dao.dohvatiId(korisnickoIme);
		
		return korId;
	}
}
