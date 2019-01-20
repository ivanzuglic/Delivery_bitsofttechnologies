package DataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Kosarica {

	private float ukupnaCijena;
	private Restoran restoran = null;
    private Map<Artikl, Integer> odabraniProizvodi;

    
    public Kosarica () {
    	
    	this.ukupnaCijena = 0;
    	this.odabraniProizvodi = new HashMap<Artikl, Integer>();
    }
    
    // metoda vraca 1 ako je artikl uspjesno dodan, ako nije, vraca -1
    public int dodajArtikl (Artikl noviArtikl, int kolicina) {
    	
    	if (this.restoran == null || (this.restoran.equals(noviArtikl.getRestoran()))) {
    		
    		this.odabraniProizvodi.put(noviArtikl, kolicina);
        	this.ukupnaCijena = this.izracunajCijenu();
        	
        	if (this.restoran == null) {
        		this.restoran = noviArtikl.getRestoran();
        	}
        	
        	return 1;
    	}
    	else {
    		
    		return -1;
    	}
    }

    public void promijeniKolicinu (Artikl artikl, int novaKolicina) {
    	
    	this.odabraniProizvodi.put(artikl, novaKolicina);
    	this.ukupnaCijena = this.izracunajCijenu();
    }

    public void ukloniArtikl (Artikl uklonjenArtikl) {
    	
    	this.odabraniProizvodi.remove(uklonjenArtikl);
    	this.ukupnaCijena = this.izracunajCijenu();
    	
    	if (this.odabraniProizvodi.isEmpty()) {
    		this.restoran = null;
    	}
    }

    public void clear () {

        this.odabraniProizvodi.clear();
        this.ukupnaCijena = 0;
        this.restoran = null;
    }
    
    // pri finaliziranju narudzbe treba tu narudzbu postaviti za trenutnu narudzbu aktivnog klijenta
    public Narudzba finalizirajNarudzbu (GeoLokacija lokacijaDostave, Korisnik trenutniKorisnik) {

    	Narudzba finaliziranaNarudzba = new Narudzba(this, lokacijaDostave, trenutniKorisnik);
    		
    	return finaliziranaNarudzba;
    }
    
    public float getUkupnaCijena () {
		
    	return this.ukupnaCijena;
	}

	public Map<Artikl, Integer> getOdabraniProizvodi () {
		
		return this.odabraniProizvodi;
	}
	
	public Restoran getRestoran () {
		
		return this.restoran;
	}

    private float izracunajCijenu () {
    	
    	Set<Artikl> setArtikala = null;
    	float cijena = 0;
    	
    	if (this.odabraniProizvodi.isEmpty()) {
    		
    		return cijena;
    	}
    	else {
    		
    		setArtikala = this.odabraniProizvodi.keySet();
    		
    		for (Artikl artikl : setArtikala) {
    			
    			cijena = cijena + (artikl.getCijena() * this.odabraniProizvodi.get(artikl));
    		}
    		
    		return cijena;
    	}
    }
}