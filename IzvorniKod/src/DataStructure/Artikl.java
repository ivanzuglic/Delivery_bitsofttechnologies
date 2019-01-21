package DataStructure;

import Database.ArtiklDAO;

public class Artikl implements Comparable<Artikl> {
	
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
	
	public Artikl (int idArtikl) {
		
		this.idArtikl = idArtikl;
		this.ucitajDB();
	}

    public int getIdArtikl() {
		
    	return this.idArtikl;
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
    
    public void setRestoran(Restoran restoran) {    // dodani za ArtiklDAO.ucitajArtikl
    	this.restoran = restoran;
    }
    
    public String getOpis () {
		
    	return this.opis;
	}

	public void setOpis(String opis) {
		
		this.opis = opis;
		this.azurirajDB();
	}
	
    private void ucitajDB () {
    	
    	// metoda koja dohvaca artikl za dani idArtikl
    	
    	ArtiklDAO dao = new ArtiklDAO();	
    	Artikl art = dao.ucitajArtikl(this.idArtikl);
    	
    	this.naziv = art.getNaziv();
    	this.cijena = art.getCijena();
    	this.vrijemePripravljanjaMin = art.getVrijemePripravljanja();
    	this.restoran = art.getRestoran();
    	this.opis= art.getOpis();  	
    	
    }
    
    private void azurirajDB () {
    	
    	ArtiklDAO dao = new ArtiklDAO();
    	dao.azurirajArtikl(this);
    }
    
    private void dohvatiId () {
    	
    	int id;
    	ArtiklDAO dao = new ArtiklDAO();
    	id = dao.dohvatiIdArtikla(this);
    	
    	if (id > 0) {
    		
    		this.idArtikl = id;
    	}
    }

	@Override
	public int compareTo(Artikl o) {
		
		return ((Integer)o.getIdArtikl()).compareTo((Integer)this.getIdArtikl());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArtikl;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikl other = (Artikl) obj;
		if (idArtikl != other.idArtikl)
			return false;
		return true;
	}
}