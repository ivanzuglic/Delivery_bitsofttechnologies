package DataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Kosarica {

	private float  ukupnaCijena;
    private Map<Artikl, Integer> odabraniProizvodi;

    
    public Kosarica () {
    	
    	this.ukupnaCijena = 0;
    	this.odabraniProizvodi = new HashMap<Artikl, Integer>();
    }
    
    public void dodajArtikl (Artikl noviArtikl, int kolicina) {
    	
    	this.odabraniProizvodi.put(noviArtikl, kolicina);
    	this.ukupnaCijena = this.izracunajCijenu();
    }

    public void promijeniKolicinu (Artikl artikl, int novaKolicina) {
    	
    	this.odabraniProizvodi.put(artikl, novaKolicina);
    	this.ukupnaCijena = this.izracunajCijenu();
    }

    public void ukloniArtikl (Artikl uklonjenArtikl) {
    	
    	this.odabraniProizvodi.remove(uklonjenArtikl);
    	this.ukupnaCijena = this.izracunajCijenu();
    }

    public void clear () {

        this.odabraniProizvodi.clear();
        this.ukupnaCijena = 0;
    }
    
    public Narudzba finalizirajNarudzbu (GeoLokacija lokacijaDostave) {

    	Narudzba finaliziranaNarudzba = null;
    	
    	// stvoriti i vratiti objekt narudzbe stvoren iz kosarice i lokacije dostave
    	
    	return finaliziranaNarudzba;
    }
    
    public float getUkupnaCijena() {
		
    	return ukupnaCijena;
	}

	public Map<Artikl, Integer> getOdabraniProizvodi() {
		
		return odabraniProizvodi;
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
