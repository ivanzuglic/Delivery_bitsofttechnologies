package DataStructure;

public class Artikl {
	
	private int idArtikl;
	private String naziv;
    private float cijena;
    private int vrijemePripravljanjaMin;
    private Restoran restoran;
    private String opis;

    
    public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Artikl (String naziv, float cijena, int vrijemePripravljanja, Restoran restoran) {

    	this.naziv = naziv;
    	this.cijena = cijena;
    	this.vrijemePripravljanjaMin = vrijemePripravljanja;
    	this.restoran = restoran;
    }

    public int getIdArtikl() {
		return idArtikl;
	}

	public void setIdArtikl(int idArtikl) {
		this.idArtikl = idArtikl;
	}

	public String getNaziv () {

    	return this.naziv;
    }

    public void setNaziv (String noviNaziv) {

        this.naziv = noviNaziv;
    }
    
    public float getCijena () {

    	return this.cijena;
    }
    
    public void setCijena (float novaCijena) {

        this.cijena = novaCijena;
    }

    public int getVrijemePripravljanja () {

    	return this.vrijemePripravljanjaMin;
    }
    
    public void setVrijemePripravljanja (int novoVP) {

        this.vrijemePripravljanjaMin = novoVP;
    }

    public Restoran getRestoran () {

    	return this.restoran;
    }
    
    public void pohraniBP() {
    	
    }
}
