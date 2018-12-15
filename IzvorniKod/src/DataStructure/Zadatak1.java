package DataStructure;

public class Zadatak {

    private VrsteZadatka vrsta;
    private Narudzba narudzba;

    public Zadatak() {

      this.vrsta = new VrsteZadatka();
      this.narudzba = new Narudzba();
    }

    public VrsteZadatka getVrsta() {

      return vrsta;
    }

    public Narudzba getNarudzba() {

      return narudzba;
    }

    public GeoLokacija dohvatiLokaciju() {

      //metoda koja ce dohvacati lokaciju zadatka
    }
}
