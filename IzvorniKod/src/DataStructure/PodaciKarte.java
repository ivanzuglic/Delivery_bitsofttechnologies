package DataStructure;

import java.util.List;

public class PodaciKarte {
	
	private List<GeoLokacija> listaLokacija;
    private boolean ruta = false;
    
    
    public PodaciKarte (List<GeoLokacija> listaLokacija, boolean ruta) {

		this.listaLokacija = listaLokacija;
		this.ruta = ruta;
    }

    public void dodajUListuLokacija (GeoLokacija lokacija) {

		this.listaLokacija.add(lokacija);
    }

    public boolean getRuta () {

		return ruta;
    }

    public void setRuta (boolean parametar) {

		this.ruta = parametar;
    }

	public List<GeoLokacija> getListaLokacija () {
		
		return listaLokacija;
	}
}
