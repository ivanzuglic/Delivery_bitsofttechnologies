package DataStructure;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Database.NarudzbaDAO;

public class Narudzba {
	
	private float cijena;
	private Map<Artikl, Integer> odabraniProizvodi;
	private Restoran restoran;
	private GeoLokacija lokacijaPreuzimanja;
	private GeoLokacija lokacijaDostavljanja;
	private Korisnik kupac;
	private Dostavljac dostavljac = null;
	
	private Timestamp vrijemeStvaranja;
	private Timestamp vrijemeSpremnosti;
	private Timestamp vrijemeZavrsetka;
	
	private boolean aktivna = true;
	
	private int idNar;
	
	// za stvaranje narudzbe
	public Narudzba (Kosarica izvorisnaKosarica, GeoLokacija lokacijaDostavljanja, Korisnik trenutniKorisnik) {
		
		this.cijena = izvorisnaKosarica.getUkupnaCijena();
		this.odabraniProizvodi = izvorisnaKosarica.getOdabraniProizvodi();
		this.restoran = izvorisnaKosarica.getRestoran();
		this.lokacijaPreuzimanja = izvorisnaKosarica.getRestoran().getLokacija();
		this.lokacijaDostavljanja = lokacijaDostavljanja;
		this.kupac = trenutniKorisnik;
		this.vrijemeStvaranja = new Timestamp(System.currentTimeMillis());
		this.izracunajVrijemeSpremnosti();	
		
		this.idNar = this.pohraniBP();
		
	}
	
	// za ucitavanje narudzbe
	public Narudzba(int idNarudzba) {
		
		this.idNar = idNarudzba;
		this.ucitajBP();
		this.izracunajVrijemeSpremnosti();
		
	}
	
	// pomocni konstruktor za stvaranje privremene klase Narudzba u NarudzbaDAO, a ciji se atributi prenose u gornji konstruktor pomocu ucitajBP()
	public Narudzba(float cijena, Map<Artikl, Integer> odabraniProizvodi, Restoran restoran, GeoLokacija lokacijaPreuzimanja, GeoLokacija lokacijaDostavljanja,
					Korisnik kupac, Dostavljac dostavljac, Timestamp vrijemeStvaranja, Timestamp vrijemeZavrsetka, boolean aktivna) {
		
		this.cijena = cijena;
		this.odabraniProizvodi = odabraniProizvodi;
		this.restoran = restoran;
		this.lokacijaPreuzimanja = lokacijaPreuzimanja;
		this.lokacijaDostavljanja = lokacijaDostavljanja;
		this.kupac = kupac;
		this.dostavljac = dostavljac;
		this.vrijemeStvaranja = vrijemeStvaranja;
		this.vrijemeZavrsetka = vrijemeZavrsetka;
		this.aktivna = aktivna;
		
	}
	

	public void OznaciNarudzbuGotovom (Zastavice z) {
		
		if (z.isDostavljac()) {
			this.vrijemeZavrsetka = new Timestamp(System.currentTimeMillis());
			this.aktivna = false;
			
			this.oznaciGotovo();
		}
	}
	

	public PodaciKarte StvoriRutu () {
		
		List<GeoLokacija> listaZaRutu = new LinkedList<GeoLokacija>();
		PodaciKarte podaci;
		
		listaZaRutu.add(lokacijaPreuzimanja);
		listaZaRutu.add(lokacijaDostavljanja);
		podaci = new PodaciKarte(listaZaRutu, true);
		
		return podaci;
	}

	public Dostavljac getDostavljac () {
		
		return this.dostavljac;
	}

	public void setDostavljac (Dostavljac dostavljac, Zastavice z) {
		
		if(z.isDispecer()) {
			this.dostavljac = dostavljac;
			
			// spremi u bazu podataka
			this.postaviDostavljaca();
		}
	}
	
	public int getIdNar() {
		return this.idNar;
	}

	public Timestamp getVrijemeStvaranja() {
		return this.vrijemeStvaranja;
	}

	public Timestamp getVrijemeSpremnosti () {
		
		return this.vrijemeSpremnosti;
	}

	public Timestamp getVrijemeZavrsetka () {
		
		return this.vrijemeZavrsetka;
	}
	
	public boolean isAktivna () {
		
		return this.aktivna;
	}

	public float getCijena () {
		
		return this.cijena;
	}

	public Map<Artikl, Integer> getOdabraniProizvodi () {
		
		return this.odabraniProizvodi;
	}

	public GeoLokacija getLokacijaPreuzimanja () {
		
		return this.lokacijaPreuzimanja;
	}

	public GeoLokacija getLokacijaDostavljanja () {
		return this.lokacijaDostavljanja;
	}

	public Korisnik getKupac() {
		
		return this.kupac;
	}
	
	public Restoran getRestoran() {
		
		return this.restoran;
	}
	
	private void izracunajVrijemeSpremnosti () {
		
		int dodatnoVrijemeMin = 0;
		long duration = 0;
		
		for (Artikl artikl : odabraniProizvodi.keySet()) {
			if (dodatnoVrijemeMin < artikl.getVrijemePripravljanja()) {
				dodatnoVrijemeMin = artikl.getVrijemePripravljanja();
			}
		}
		
		duration = dodatnoVrijemeMin * 60 * 1000;
		this.vrijemeSpremnosti = new Timestamp(this.vrijemeStvaranja.getTime() + duration); 
	}
	
	
	private int pohraniBP() {
		NarudzbaDAO dao = new NarudzbaDAO();
		return dao.pohraniNarudzbu(this);		
		
	}
	
	private void ucitajBP() {
		NarudzbaDAO dao = new NarudzbaDAO();
		Narudzba nar = dao.ucitajNarudzbu(this.idNar);
		
		this.odabraniProizvodi = nar.getOdabraniProizvodi();
		this.cijena = nar.getCijena();
		this.aktivna = nar.isAktivna();
		this.lokacijaDostavljanja = nar.getLokacijaDostavljanja();
		this.lokacijaPreuzimanja = nar.getLokacijaPreuzimanja();
		this.vrijemeStvaranja = nar.getVrijemeStvaranja();
		this.vrijemeZavrsetka = nar.getVrijemeZavrsetka();
		this.kupac = nar.getKupac();
		this.dostavljac = nar.getDostavljac();
		this.restoran = nar.getRestoran();
		
		
	}
	
	private void postaviDostavljaca() {
		NarudzbaDAO dao = new NarudzbaDAO();
		dao.azurirajDostavljaca(this);
		
	}
	
	private void oznaciGotovo() {
		NarudzbaDAO dao = new NarudzbaDAO();
		dao.azurirajAktivnost(this);
		
	}

	
}
