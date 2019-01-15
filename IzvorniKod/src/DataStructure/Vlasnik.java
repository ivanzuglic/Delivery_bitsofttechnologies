package DataStructure;

import java.util.ArrayList;
import java.util.List;
import Database.VlasnikDAO;

public class Vlasnik extends Klijent {

	private Restoran vlastitiRestoran;
	private List<Narudzba> narudzbeAi2h;
	
	
	public Vlasnik (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.DohvatiVlastitiRestoran();
	}
	
	public void napuniAi2h () {
		
		this.narudzbeAi2h = this.dohvatiAi2h(this.getKorisnickiId());
	}
	
	public Restoran getVlastitiRestoran () {
		
		return vlastitiRestoran;
	}

	public List<Narudzba> getNarudzbeAi2h () {
		
		return narudzbeAi2h;
	}
	
	private Restoran DohvatiVlastitiRestoran () {
		
		Restoran restoran = null;
		
		VlasnikDAO dao = new VlasnikDAO();
		restoran = dao.DohvatiVlastitiRestoran(this);
		
		return restoran;
	}
	
	private List<Narudzba> dohvatiAi2h (int korisnickiId) {
		
		List<Integer> idNarudzbi = null;
		List<Narudzba> narudzbeAi2h = new ArrayList<>();
	
		VlasnikDAO dao = new VlasnikDAO();
		idNarudzbi = dao.dohvatiAktivneI2H(korisnickiId);
		
		for (Integer id : idNarudzbi) {
			
			Narudzba narudzbaZaDodati = new Narudzba(id);
			narudzbeAi2h.add(narudzbaZaDodati);
		}
		
		return narudzbeAi2h;
	}
}