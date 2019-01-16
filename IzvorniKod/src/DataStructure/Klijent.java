package DataStructure;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Database.KlijentDAO;

public class Klijent extends Korisnik {		//Ivan: uklonjen atr 'starost'

	private Kosarica kosarica;
	private Narudzba aktivnaNarudzba;
	
	
	// ovaj konstruktor se koristi prilikom registracije
	public Klijent (String korisnickoIme, String lozinka, String ime, String prezime, String brMobitela, String eMail) {
		
		super(korisnickoIme, lozinka, ime, prezime, brMobitela, eMail);
		this.kosarica = new Kosarica();
		this.aktivnaNarudzba = this.dohvatiAktivnuNarudzbu(this.getKorisnickiId());
	}
	
	// ovaj konstruktor se koristi prilikom prijave
	public Klijent (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.kosarica = new Kosarica();
		this.aktivnaNarudzba = this.dohvatiAktivnuNarudzbu(this.getKorisnickiId());
	}

	public PodaciKarte pratiPoziciju () {
		
		PodaciKarte lokacijaTrenutneNarudzbe = null;
		GeoLokacija lokacija = null;
		
		// prije dohvacanja pozicije updateamo aktivnu narudzbu da osiguramo azurnost informacija
		this.aktivnaNarudzba = this.dohvatiAktivnuNarudzbu(this.getKorisnickiId());
		
		// dohvati podatke karte iz baze podataka
		lokacija = this.dohvatiLokacijuTrNarudzbe(this.aktivnaNarudzba);
		
		if (lokacija != null) {
			
			lokacijaTrenutneNarudzbe = new PodaciKarte(new ArrayList<GeoLokacija>(), false);
			lokacijaTrenutneNarudzbe.dodajUListuLokacija(lokacija);
			
		}
		
		return lokacijaTrenutneNarudzbe;
	}
	
	public void predloziRestoran (String ime, GeoLokacija lokacija, String opis, BufferedImage slika, String telefon, String fax, int OIB, int IBAN, int ziroRacun, String adresa) {
		
		Restoran predlozeniRestoran = new Restoran(ime, this, lokacija, opis, slika, false, telefon, fax, OIB, IBAN, ziroRacun, adresa);
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
	
	// vraca aktivnu narudzbu ako ona postoji, inace vraca null
	private Narudzba dohvatiAktivnuNarudzbu (int korisnickiId) {
		
		int idAktivneNarudzbe;
		Narudzba aktivnaNarudzba = null;
		
		KlijentDAO dao = new KlijentDAO();
		idAktivneNarudzbe = dao.dohvatiIdAktivneNarudzbe(korisnickiId);
		
		if (idAktivneNarudzbe != -1) {
			
			aktivnaNarudzba = new Narudzba(idAktivneNarudzbe);
		}
		
		return aktivnaNarudzba;
	}
	
	// vraca lokaciju ili null, vraca null ako je doslo do greske - lokacija nije dostupna
	private GeoLokacija dohvatiLokacijuTrNarudzbe (Narudzba aktivnaNarudzba) {
		
		GeoLokacija lokacija = null;
		int idDostavljaca;
		boolean narudzbaPreuzeta;
		
		KlijentDAO dao = new KlijentDAO();
		idDostavljaca = dao.dohvatiIdDostavljaca(aktivnaNarudzba);
		
		if (idDostavljaca == 0) {
			
			lokacija = aktivnaNarudzba.getLokacijaPreuzimanja();
		}
		else {
			
			narudzbaPreuzeta = dao.narudzbaPreuzeta(aktivnaNarudzba);
			
			if (narudzbaPreuzeta) {
				
				lokacija = dao.lokacijaDostavljaca(aktivnaNarudzba);
			}
			else {
				
				lokacija = aktivnaNarudzba.getLokacijaPreuzimanja();
			}
		}
		
		return lokacija;
	}
}