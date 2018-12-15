package DataStructure;

public class Artikl {

    private String naziv;
    private float cijena;
    private int vrijemePripravljanja;
    private Restoran restoran;

    public Artikl(String naziv, float cijena, int vrijemePripravljanja, Restoran restoran) {

      this.naziv = naziv;
      this.cijena = cijena;
      this.vrijemePripravljanja = vrijemePripravljanja;
      this.restoran = restoran;
    }

    public String getNaziv() {

      return naziv;
    }

    public int getCijena() {

      return cijena;
    }

    public int getVP() {

      return vrijemePripravljanja;
    }

    public Restoran getRestoran() {

      return restoran;
    }

    public void setNaziv(String noviNaziv, Zastavice z) {

      this.naziv = noviNaziv;
    }

    public void setCijena(String novaCijena, Zastavice z) {

      this.cijena = novaCijena;
    }

    public void setVP(String novoVP, Zastavice z) {

      this.vrijemePripravljanja= novoVP;
    }
}
