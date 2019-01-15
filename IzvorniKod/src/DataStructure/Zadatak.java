package DataStructure;

import Database.ZadatakDAO;

public class Zadatak {

	private Narudzba narudzba;
	private VrsteZadataka vrsta;
	private boolean gotov;
	private GeoLokacija lokacija;

	
    public Zadatak (Narudzba narudzba, VrsteZadataka vrsta, boolean gotov) {
    	
    	this.narudzba = narudzba;
    	this.vrsta = vrsta;
    	this.gotov = gotov;
    	this.lokacija = null;
    	
    	this.porhaniDB();
    }
    
    public Zadatak (GeoLokacija lokacijaIdiNa, boolean gotov) {
    	
    	this.narudzba = null;
    	this.vrsta = VrsteZadataka.IDINALOKACIJU;
    	this.gotov = gotov;
    	this.lokacija = lokacijaIdiNa;
    	
    	this.porhaniDB();
    }
    
    // za DostavljacDAO da dobije listu neobavljenih zadataka
    public Zadatak (Narudzba narudzba, VrsteZadataka vrsta, GeoLokacija lokacija) {
    	this.narudzba = narudzba;
    	this.vrsta = vrsta;
    	this.gotov = false;
    	this.lokacija = lokacija;
    	
    }

    public GeoLokacija dohvatiLokaciju () {
    	
    	switch(this.vrsta) {
    		case POKUPI: 
    			return narudzba.getLokacijaPreuzimanja();
    		case OSTAVI:
    			return narudzba.getLokacijaDostavljanja();
    		default:
    			return this.lokacija;
    	}
    }
    
    public VrsteZadataka getVrsta () {

    	return this.vrsta;
    }

    public Narudzba getNarudzba () {

    	return this.narudzba;
    }
    
    public boolean getGotov () {
    	
    	return this.gotov;
    }
    
    public void setGotov (boolean gotov) {
    	
    	this.gotov = gotov;
    	this.azurirajDB();
    }
    
    private void azurirajDB () {
    	
    	// metoda koja u bazi podataka zadatak oznacava gotovim
    	
    	ZadatakDAO dao = new ZadatakDAO();
    	dao.postaviZadGotov(this.narudzba.getId(), this.vrsta.toString());
    }
    
    private void porhaniDB () {
    	
    	// metoda koja ce novi zadatak pohraniti u bazu podataka
    	
    	ZadatakDAO dao = new ZadatakDAO();
    	dao.pohraniZadatak(this);
    }
}