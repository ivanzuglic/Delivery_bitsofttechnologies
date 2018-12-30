package DataStructure;

import java.util.List;

public class Dispecer extends Korisnik {

	private List<Dostavljac> listaAkrivnihDostavljaca;
	private List<Narudzba> listaNerasporedjenihNarudzbi;
	private Narudzba trenutnaNarudzba = null;
	private Dostavljac trenutniDostavljac = null;
	
	
	public Dispecer (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.napuniListuAktivnihDostavljaca();
		this.napuniListuNerasporedjenihNarudzbi();
	}
	
	public void dodijeliZadatak (VrsteZadataka vrstaZadatka, Zastavice z) {
		
		trenutnaNarudzba.setDostavljac(trenutniDostavljac, z);
		Zadatak noviZadatak = new Zadatak(trenutnaNarudzba, vrstaZadatka, false);
		
		// spremi novi zadatak u bazu podataka
	}
	
	public void pošaljiNaLokaciju (GeoLokacija lokacija) {
		
		Zadatak noviZadatak = new Zadatak(lokacija, false);
		
		// spremi novi zadatak u bazu podataka
	}
	
	public Narudzba getTrenutnaNarudzba() {
		
		return trenutnaNarudzba;
	}

	public void setTrenutnaNarudzba(Narudzba trenutnaNarudzba) {
		
		this.trenutnaNarudzba = trenutnaNarudzba;
	}

	public Dostavljac getTrenutniDostavljac() {
		
		return trenutniDostavljac;
	}

	public void setTrenutniDostavljac(Dostavljac trenutniDostavljac) {
		
		this.trenutniDostavljac = trenutniDostavljac;
	}

	public List<Dostavljac> getListaAkrivnihDostavljaca() {
		
		return listaAkrivnihDostavljaca;
	}

	public List<Narudzba> getListaNerasporedjenihNarudzbi() {
		
		return listaNerasporedjenihNarudzbi;
	}
	
	private void napuniListuAktivnihDostavljaca () {
		
		// metoda koja ce puniti listu aktivnih dostavljaca
	}
	
	private void napuniListuNerasporedjenihNarudzbi () {
		
		// metoda koja ce puniti listu nerasporedjenih narudzbi
	}
}
