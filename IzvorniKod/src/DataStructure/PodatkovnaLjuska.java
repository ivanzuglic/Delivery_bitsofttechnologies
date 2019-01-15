package DataStructure;

import java.util.Set;

import Database.PodatkovnaLjuskaDAO;

public class PodatkovnaLjuska {

	private Set<Restoran> restorani;
	private Restoran trenutniRestoran = null;
	private Korisnik trenutniKorisnik = null;
	private Zastavice zastavice;
	

	public PodatkovnaLjuska () {
		
		this.napuniSetRestorana();
		this.zastavice = new Zastavice();
	}
	
	// metoda vraca true ako je registracija uspjesno provedena, vraca false ako korisnicko ime nije dostupno
	public boolean registracija (String korisnickoIme, String lozinka, String ime, String prezime, String brMobitela, String eMail) {
		
		boolean valjan = false;
		
		// provjeri dostupnost korisnickog imena u bazi podataka - ako je korisnicko ime dostupno postavi valjan u true
		valjan = this.korisnickoImeDostupno(korisnickoIme);
		
		if (valjan) {
			
			this.trenutniKorisnik = new Klijent(korisnickoIme, lozinka, ime, prezime, brMobitela, eMail);
			this.postaviZastavice(VrstaKorisnika.KLIJENT);
			this.postaviOnlineStatus(trenutniKorisnik.getKorisnickoIme(), true);
			
			return true;
		}
		else {
			
			return false;
		}
	}

	// metoda vraca true ako je prijava uspjesno provedena, vraca false ako nije
	public boolean prijava (String korisnickoIme, String lozinka) {
		
		boolean valjan = false;
		VrstaKorisnika vrsta = null;
		
		// provjeri valjanost podataka u bazi podataka
		valjan = this.imeILozinkaIspravni(korisnickoIme, lozinka);
		
		// ako su podaci valjani, dohvati vrstu korisnika iz baze podataka i postavi valjan u true
		vrsta = dohvatiVrstu(korisnickoIme);
		
		if (valjan) {
			
			if (vrsta == VrstaKorisnika.KLIJENT) {
				this.trenutniKorisnik = new Klijent(korisnickoIme, lozinka);
			}
			else if (vrsta == VrstaKorisnika.VLASNIK) {
				this.trenutniKorisnik = new Vlasnik(korisnickoIme, lozinka);
			}
			else if (vrsta == VrstaKorisnika.DISPECER) {
				this.trenutniKorisnik = new Dispecer(korisnickoIme, lozinka);
			}
			else if (vrsta == VrstaKorisnika.DOSTAVLJAC) {
				this.trenutniKorisnik = new Dostavljac(korisnickoIme, lozinka);
			}
			else if (vrsta == VrstaKorisnika.ADMIN) {
				this.trenutniKorisnik = new Administrator(korisnickoIme, lozinka);
			}
			
			this.postaviZastavice(vrsta);
			this.postaviOnlineStatus(trenutniKorisnik.getKorisnickoIme(), true);
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public void odjava () {
		
		this.postaviOnlineStatus(trenutniKorisnik.getKorisnickoIme(), false);
		this.zastavice.resetirajZastavice();
		this.trenutniKorisnik = null;
	}
	

	public Restoran getTrenutniRestoran() {
		
		return trenutniRestoran;
	}
	

	public void setTrenutniRestoran(Restoran noviRestoran) {
		
		this.trenutniRestoran = noviRestoran;
	}
	

	public Set<Restoran> getRestorani() {
		
		return restorani;
	}
	

	public Korisnik getTrenutniKorisnik() {
		
		return trenutniKorisnik;
	}
	

	public Zastavice getZastavice() {
		
		return zastavice;
	}
	
	private void postaviZastavice(VrstaKorisnika vrsta) {
		
		if (vrsta == VrstaKorisnika.KLIJENT) {
			this.zastavice.setKlijent(true);
		}
		else if (vrsta == VrstaKorisnika.VLASNIK) {
			this.zastavice.setVlasnik(true);
		}
		else if (vrsta == VrstaKorisnika.DISPECER) {
			this.zastavice.setDispecer(true);
		}
		else if (vrsta == VrstaKorisnika.DOSTAVLJAC) {
			this.zastavice.setDostavljac(true);
		}
		else if (vrsta == VrstaKorisnika.ADMIN) {
			this.zastavice.setAdministrator(true);
		}
	}
	
	private void postaviOnlineStatus (String korisnickoIme, boolean status) {
		
		PodatkovnaLjuskaDAO dao = new PodatkovnaLjuskaDAO();
		dao.postaviOnlineStatus(korisnickoIme, status);
	}
	
	private void napuniSetRestorana () {
		
		// metoda koja ce iz baze podataka puniti set restorana
		
		PodatkovnaLjuskaDAO dao = new PodatkovnaLjuskaDAO();
		this.restorani = dao.ucitajRestorane();
	}
	
	private boolean korisnickoImeDostupno (String korisnickoIme) {
		
		// metoda koja provjerava je li korisnicko ime dostupno
		
		boolean postoji = false;
		
		PodatkovnaLjuskaDAO dao = new PodatkovnaLjuskaDAO();
		postoji = dao.korisnickoImePostoji(korisnickoIme);
		
		if (postoji) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean imeILozinkaIspravni (String korisnickoIme, String lozinka) {
		
		// metoda koja provjerava jesu li korisnicko ime i lozinka ispravni
		
		boolean lozinkaIspravna = false;
		boolean postoji = false;
		
		PodatkovnaLjuskaDAO dao = new PodatkovnaLjuskaDAO();
		postoji = dao.korisnickoImePostoji(korisnickoIme);
		
		if (postoji) {
			
			lozinkaIspravna = dao.lozinkaIspravna(korisnickoIme, lozinka);
			
			if (lozinkaIspravna) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	private VrstaKorisnika dohvatiVrstu (String korisnickoIme) {
		
		// metoda koja dohvaca vrstu korisnika iz baze podataka
		
		String vrsta;
		
		PodatkovnaLjuskaDAO dao = new PodatkovnaLjuskaDAO();
		vrsta = dao.vrstaKorisnika(korisnickoIme);
		if (vrsta == null) {
			return null;
		}
		else if (vrsta.equals(VrstaKorisnika.ADMIN.toString())) {
			return VrstaKorisnika.ADMIN;
		}
		else if (vrsta.equals(VrstaKorisnika.DISPECER.toString())) {
			return VrstaKorisnika.DISPECER;
		}
		else if (vrsta.equals(VrstaKorisnika.DOSTAVLJAC.toString())) {
			return VrstaKorisnika.DOSTAVLJAC;
		}
		else if (vrsta.equals(VrstaKorisnika.KLIJENT.toString())) {
			return VrstaKorisnika.KLIJENT;
		}
		else if (vrsta.equals(VrstaKorisnika.VLASNIK.toString())) {
			return VrstaKorisnika.VLASNIK;
		}
		else {
			return null;
		}
	}
}