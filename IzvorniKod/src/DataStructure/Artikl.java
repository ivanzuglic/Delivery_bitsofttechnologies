package DataStructure;

import Database.ArtiklDAO;

public class Artikl {
	
	private int idArtikl;
	private String naziv;
    private float cijena;
    private int vrijemePripravljanjaMin;
    private Restoran restoran;
    private String opis;

    
    // konstruktor koji se koristi kada se stvara novi artikl
	public Artikl (String naziv, float cijena, int vrijemePripravljanja, Restoran restoran,  String opis) {

    	this.naziv = naziv;
    	this.cijena = cijena;
    	this.vrijemePripravljanjaMin = vrijemePripravljanja;
    	this.restoran = restoran;
    	this.opis= opis;
    	
    	this.pohraniDB();
    }
	
	// konstruktor koji se koristi kada se artikl ucitava iz baze podataka
	public Artikl (int idArtikl, String naziv, float cijena, int vrijemePripravljanja, Restoran restoran,  String opis) {

		this.idArtikl= idArtikl;
    	this.naziv = naziv;
    	this.cijena = cijena;
    	this.vrijemePripravljanjaMin = vrijemePripravljanja;
    	this.restoran = restoran;
    	this.opis= opis;
    }

    public int getIdArtikl() {
		
    	return this.idArtikl;
	}

	public void setIdArtikl(int idArtikl) {
		
		this.idArtikl = idArtikl;
		this.azurirajDB();
	}

	public String getNaziv () {

    	return this.naziv;
    }

    public void setNaziv (String noviNaziv) {

        this.naziv = noviNaziv;
        this.azurirajDB();
    }
    
    public float getCijena () {

    	return this.cijena;
    }
    
    public void setCijena (float novaCijena) {

        this.cijena = novaCijena;
        this.azurirajDB();
    }

    public int getVrijemePripravljanja () {

    	return this.vrijemePripravljanjaMin;
    }
    
    public void setVrijemePripravljanja (int novoVP) {

        this.vrijemePripravljanjaMin = novoVP;
        this.azurirajDB();
    }

    public Restoran getRestoran () {

    	return this.restoran;
    }
    
    public String getOpis () {
		
    	return this.opis;
	}

	public void setOpis(String opis) {
		
		this.opis = opis;
		this.azurirajDB();
	}
	
    private void pohraniDB () {
    	
    	// metoda koja pohranjuje novi artikl u bazu podataka
    	
    	ArtiklDAO dao = new ArtiklDAO();	// od kuda da povlacimo user i password?
    	dao.pohraniArtikl(this);
    }
    
    private void azurirajDB () {
    	
    	// metoda koja azurira podatke u bazi podataka
    	ArtiklDAO dao = new ArtiklDAO();
    	dao.azurirajArtikl(this);
    }
}
