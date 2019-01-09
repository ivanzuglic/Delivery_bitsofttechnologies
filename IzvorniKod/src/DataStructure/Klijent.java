package DataStructure;

import java.awt.image.BufferedImage;

public class Klijent extends Korisnik {		//Ivan: uklonjen atr 'starost'

	private Kosarica kosarica;
	private Narudzba aktivnaNarudzba;
	
	
	// ovaj konstruktor se koristi prilikom registracije
	public Klijent (String korisnickoIme, String lozinka, String ime, String prezime, String eMail) {
		
		super(korisnickoIme, lozinka, ime, prezime, eMail);
		this.kosarica = new Kosarica();
	}
	
	// ovaj konstruktor se koristi prilikom prijave
	public Klijent (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.kosarica = new Kosarica();
	}

	public PodaciKarte pratiPoziciju () {
		
		PodaciKarte lokacijaTrenutneNarudzbe = null;
		
		// dohvati podatke karte iz baze podataka
		
		return lokacijaTrenutneNarudzbe;
	}
	
	public void predloziRestoran (String ime, GeoLokacija lokacija, String opis, BufferedImage slika, String telefon, String fax, String adresa) {
		
		Restoran predlozeniRestoran = new Restoran(ime, this, lokacija, opis, slika, false, telefon, fax, adresa);
	}
	
	public Narudzba getAktivnaNarudzba() {
		
		return aktivnaNarudzba;
	}

	public void setAktivnaNarudzba(Narudzba aktivnaNarudzba) {
		
		this.aktivnaNarudzba = aktivnaNarudzba;
	}

	public Kosarica getKosarica() {
		
		return kosarica;
	}
}
