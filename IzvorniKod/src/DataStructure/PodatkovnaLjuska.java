package DataStructure;

import java.util.Set;

public class PodatkovnaLjuska {

	private Set<Restoran> restorani;
	private Restoran trenutniRestoran = null;
	private Korisnik trenutniKorisnik = null;
	private Zastavice zastavice;
	
	
	public PodatkovnaLjuska () {
		
		this.napuniSetRestorana();
		this.zastavice = new Zastavice();
	}
	
	public void registracija (String korisnickoIme, String lozinka, String ime, String prezime, String eMail, int starost) {
		
		boolean valjan = false;
		
		// provjeri dostupnost korisnickog imena u bazi podataka
		// ako je korisnicko ime dostupno postavi valjan u true
		
		if (valjan) {
			this.trenutniKorisnik = new Klijent(korisnickoIme, lozinka, ime, prezime, eMail, starost);
			this.postaviZastavice(VrstaKorisnika.KLIJENT);
			this.postaviOnlineStatus(true);
		}
	}
	
	public void prijava (String korisnickoIme, String lozinka) {
		
		boolean valjan = false;
		VrstaKorisnika vrsta = null;
		
		// provjeri valjanost podataka u bazi podataka
		// ako su podaci valjani, dohvati vrstu korisnika iz baze podataka i postavi valjan u true
		
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
			this.postaviOnlineStatus(true);
		}
	}
	
	public void odjava () {
		
		this.postaviOnlineStatus(false);
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
	
	private void postaviOnlineStatus (boolean status) {
		
		// metoda koja ce u bazi podataka mjenjati online status korisnika
	}
	
	private void napuniSetRestorana () {
		
		// metoda koja ce iz baze podataka puniti set restorana
	}
	
	private boolean korisnickoImeDostupno () {
		
		// metoda koja provjerava je li korisnicko ime dostupno
	}
	
	
}


