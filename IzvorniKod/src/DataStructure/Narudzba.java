package DataStructure;

import java.util.HashMap;

import com.sun.jmx.snmp.Timestamp;

public class Narudzba {
	
	private int cijena;
	private HashMap<Artikl, Integer> odabraniProizvodi;
	private GeoLokacija lokacijaPreuzimanja;
	private GeoLokacija lokacijaDostavljanja;
	private Korisnik kupac;
	private Korisnik dostavljac;
	private Timestamp vrijemeSpremnosti;
	private Timestamp vrijemeZavrsetka;
	private boolean aktivna= true;
	
	public Narudzba(int cijena,HashMap<Artikl, Integer> proizvodi, GeoLokacija lokacijaPreuzimanja,GeoLokacija lokacijaDostavljanja) {
		this.cijena=cijena;
		this.odabraniProizvodi=proizvodi;
		this.lokacijaPreuzimanja=lokacijaPreuzimanja;
		this.lokacijaDostavljanja=lokacijaDostavljanja;
	}

	public Korisnik getDostavljac() {
		return dostavljac;
	}

	public void setDostavljac(Korisnik dostavljac, Zastavice z) {
		if(z.isDispecer())
			this.dostavljac = dostavljac;
	}

	public Timestamp getVrijemeSpremnosti() {
		return vrijemeSpremnosti;
	}

	public void setVrijemeSpremnosti(Timestamp vrijemeSpremnosti,Zastavice z) {
		if(z.isVlasnik())
			this.vrijemeSpremnosti = vrijemeSpremnosti;
	}

	public Timestamp getVrijemeZavrsetka() {
		return vrijemeZavrsetka;
	}

	public void setVrijemeZavrsetka(Timestamp vrijemeZavrsetka,Zastavice z) {
		if(z.isDostavljac())
			this.vrijemeZavrsetka = vrijemeZavrsetka;
	}

	public boolean isAktivna() {
		return aktivna;
	}

	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}
// ne znam jel ja tu racunam cijenu narudbze ili cemo negdje drugdje
	public int getCijena(HashMap<Artikl, Integer> proizvodi) {
		for(Integer cijenaProiz : proizvodi.values()) {
			cijena=cijena+cijenaProiz;
		}
		return cijena;
	}

	public HashMap<Artikl, Integer> getOdabraniProizvodi() {
		return odabraniProizvodi;
	}

	public GeoLokacija getLokacijaPreuzimanja() {
		return lokacijaPreuzimanja;
	}

	public GeoLokacija getLokacijaDostavljanja() {
		return lokacijaDostavljanja;
	}

	public Korisnik getKupac() {
		return kupac;
	}
// treba zavrsiti ovu metodu	
//	public PodaciKarte stvoriRutu() {
		
	
	
	
	
	
}
