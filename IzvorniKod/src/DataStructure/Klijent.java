package DataStructure;

public class Klijent extends Korisnik {

	private Kosarica kosarica;
	private Narudzba aktivnaNarudzba;
	
	
	// ovaj konstruktor se koristi prilikom registracije
	public Klijent (String korisnickoIme, String lozinka, String ime, String prezime, String eMail, int starost) {
		
		super(korisnickoIme, lozinka, ime, prezime, eMail, starost);
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
	
	public void predloziRestoran () {
		
		// metoda za predlaganje restorana
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
