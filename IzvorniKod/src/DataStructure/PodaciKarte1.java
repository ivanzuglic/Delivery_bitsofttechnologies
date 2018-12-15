package DataStructure;

public class PodaciKarte {

    private List<GeoLokacija> listaLokacija;
    private boolean ruta = false;

    public PodaciKarte(List<GeoLokacija> listaLokacija, boolean ruta) {

      super(listaLokacija, ruta);
      this.napuniListuLokacija();
    }

    public void dodajUListuLokacija(GeoLokacija lokacija) {

      //metoda dodaje lokaciju u listu lokacija
    }

    public boolean getRuta() {

      return ruta;
    }

    public void setRuta(boolean parametar) {

      this.ruta = parametar;
    }

    private void napuniListuLokacija() {

      //metoda koja ce puniti listu lokacija
    }

}
