package DataStructure;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	private boolean aktivna= true;
	
	
	public Narudzba (Kosarica izvorisnaKosarica, GeoLokacija lokacijaDostavljanja, Korisnik trenutniKorisnik) {
		
		this.cijena = izvorisnaKosarica.getUkupnaCijena();
		this.odabraniProizvodi = izvorisnaKosarica.getOdabraniProizvodi();
		this.restoran = izvorisnaKosarica.getRestoran();
		this.lokacijaPreuzimanja = izvorisnaKosarica.getRestoran().getLokacija();
		this.lokacijaDostavljanja = lokacijaDostavljanja;
		this.kupac = trenutniKorisnik;
		this.vrijemeStvaranja = new Timestamp(System.currentTimeMillis());
		this.izracunajVrijemeSpremnosti();
		
		// spremi narudzbu u bazu podataka
	}
	
	public void OznaciNarudzbuGotovom (Zastavice z) {
		
		if (z.isDostavljac()) {
			this.vrijemeZavrsetka = new Timestamp(System.currentTimeMillis());
			this.aktivna = false;
			
			// spremi u bazu podataka
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
		}
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
}