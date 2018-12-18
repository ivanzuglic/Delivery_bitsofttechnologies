package DataStructure;

public class Zadatak {

	private Narudzba narudzba;
	private VrsteZadataka vrsta;

	
    public Zadatak (Narudzba narudzba, VrsteZadataka vrsta) {
    	
    	this.narudzba = narudzba;
    	this.vrsta = vrsta;
    }

    public GeoLokacija dohvatiLokaciju () {

    	GeoLokacija lokacija = null;
    	
    	// metoda koja ce dohvacati lokaciju zadatka
    	
    	return lokacija;
    }
    
    public VrsteZadataka getVrsta() {

    	return this.vrsta;
    }

    public Narudzba getNarudzba() {

    	return this.narudzba;
    }
}
