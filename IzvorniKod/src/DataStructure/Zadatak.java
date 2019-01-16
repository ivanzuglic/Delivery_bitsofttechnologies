package DataStructure;

import Database.ArtiklDAO;
import Database.ZadatakDAO;

public class Zadatak {

	private int idZadatka;
	private Narudzba narudzba;
	private VrsteZadataka vrsta;
	private boolean gotov;
	private GeoLokacija lokacija;

	
	// konstruktor za stvaranje zadatka POKUPI ili DOSTAVI
    public Zadatak (Narudzba narudzba, VrsteZadataka vrsta, boolean gotov) {
    	
    	this.narudzba = narudzba;
    	this.vrsta = vrsta;
    	this.gotov = gotov;
    	this.lokacija = null;
    	
    	this.porhaniDB();
    	this.dohvatiId();
    }
    
    // konstruktor za stvaranje zadatka IDINALOKACIJU
    public Zadatak (GeoLokacija lokacijaIdiNa, boolean gotov) {
    	
    	this.narudzba = null;
    	this.vrsta = VrsteZadataka.IDINALOKACIJU;
    	this.gotov = gotov;
    	this.lokacija = lokacijaIdiNa;
    	
    	this.porhaniDB();
    	this.dohvatiId();
    }
    
    // za DostavljacDAO da dobije listu neobavljenih zadataka
    public Zadatak (int id, Narudzba narudzba, VrsteZadataka vrsta, GeoLokacija lokacija) {
    	
    	this.idZadatka = id;
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
    
    private int dohvatiId () {
    	
    	int id;
    	ZadatakDAO dao = new ZadatakDAO();
    	id = dao.dohvatiIdZadatka(this);
    	
    	if (id > 0) {
    		
    		this.idZadatka = id;
    	}
    	
    	return id;
    }
}