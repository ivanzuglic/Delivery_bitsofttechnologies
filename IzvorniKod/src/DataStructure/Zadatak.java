package DataStructure;

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
    }
    
    public Zadatak (GeoLokacija lokacijaIdiNa, boolean gotov) {
    	
    	this.narudzba = null;
    	this.vrsta = VrsteZadataka.IDINALOKACIJU;
    	this.gotov = gotov;
    	this.lokacija = lokacijaIdiNa;
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
    	
    	// metoda koja ce zadatak u data bazi oznaciti gotovim
    }
}
