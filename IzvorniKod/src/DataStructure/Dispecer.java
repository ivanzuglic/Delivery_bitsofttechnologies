package DataStructure;

import java.util.ArrayList;
import java.util.List;
import Database.DispecerDAO;

public class Dispecer extends Korisnik {

	private List<Korisnik> listaAkrivnihDostavljaca;
	private List<Narudzba> listaNerasporedjenihNarudzbi;
	private Narudzba trenutnaNarudzba = null;
	private Dostavljac trenutniDostavljac = null;
	
	
	public Dispecer (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.napuniListuAktivnihDostavljaca();
		this.napuniListuNerasporedjenihNarudzbi();
	}
	
	public void dodijeliZadatak (VrsteZadataka vrstaZadatka, Zastavice z) {
		
		trenutnaNarudzba.setDostavljac(trenutniDostavljac, z);
		Zadatak noviZadatak = new Zadatak(trenutnaNarudzba, vrstaZadatka, false);
	}
	
	public void posaljiNaLokaciju (GeoLokacija lokacija) {
		
		Zadatak noviZadatak = new Zadatak(lokacija, false);
	}
	
	public Narudzba getTrenutnaNarudzba() {
		
		return this.trenutnaNarudzba;
	}

	public void setTrenutnaNarudzba(Narudzba novaTrenutnaNarudzba) {
		
		this.trenutnaNarudzba = novaTrenutnaNarudzba;
	}

	public Dostavljac getTrenutniDostavljac() {
		
		return this.trenutniDostavljac;
	}

	public void setTrenutniDostavljac(Dostavljac noviTrenutniDostavljac) {
		
		this.trenutniDostavljac = noviTrenutniDostavljac;
	}

	public List<Korisnik> getListaAkrivnihDostavljaca() {
		
		return this.listaAkrivnihDostavljaca;
	}

	public List<Narudzba> getListaNerasporedjenihNarudzbi() {
		
		return this.listaNerasporedjenihNarudzbi;
	}
	
	private void napuniListuAktivnihDostavljaca () {
		
		DispecerDAO dao = new DispecerDAO();
		this.listaAkrivnihDostavljaca = dao.dohvatiAktivneDostavljace();
	}
	
	private void napuniListuNerasporedjenihNarudzbi () {
		
		List<Integer> idNerasporedjenihNarudzbi = new ArrayList<Integer>();
		List<Narudzba> narudzbe = new ArrayList<>();
		
		DispecerDAO dao = new DispecerDAO();
		idNerasporedjenihNarudzbi = dao.dohvatiIdNerasporedjenihNarudzbi();
		
		for (Integer id : idNerasporedjenihNarudzbi) {
			
			narudzbe.add(new Narudzba(id));
		}
		
		this.listaNerasporedjenihNarudzbi = narudzbe;
	}
}