package DataStructure;

public class Artikl {

	private String naziv;
    private float cijena;
    private int vrijemePripravljanjaMin;
    private Restoran restoran;

    
    public Artikl (String naziv, float cijena, int vrijemePripravljanja, Restoran restoran) {

    	this.naziv = naziv;
    	this.cijena = cijena;
    	this.vrijemePripravljanjaMin = vrijemePripravljanja;
    	this.restoran = restoran;
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
}
