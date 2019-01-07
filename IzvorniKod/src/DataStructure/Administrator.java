package DataStructure;

import java.util.List;

import Database.AdministratorDAO;

public class Administrator extends Korisnik {

	private List<Restoran> listaOdobrenihRestorana;
	private List<Restoran> listaNeodobrenihRestorana;
	private List<Korisnik> listaKorisnika;
	
	
	public Administrator (String korisnickoIme, String lozinka) {
		
		super(korisnickoIme, lozinka);
		this.napuniListeRestorana();
		this.napuniListuKorisnika();
	}
	
	public void odobriRestoran (Restoran restoran, Zastavice z) {	//dodana implementacija AdministratorDAO klase	
		
		restoran.setOdobren(true, z);	// dodano da se automatski azurira i lokalni objekt - LM
		AdministratorDAO aDAO = new AdministratorDAO(this.getKorisnickoIme(), this.getLozinka());
		int rezultat = aDAO.setRestoranOdobren(restoran.getId());
		
		System.out.println("Uspjesnost upita: " + rezultat); 		
	}
	
	public void promijeniRazinuPristupa (Korisnik korisnik, VrstaKorisnika novaRazinaPristupa) {	//dodana implementacija AdministratorDAO klase
		
		AdministratorDAO aDAO = new AdministratorDAO(this.getKorisnickoIme(), this.getLozinka());
		int rezultat = aDAO.setRazinaPristupa(korisnik.getKorisnickiId(), novaRazinaPristupa.toString());
		
		System.out.println("Uspjesnost upita: " + rezultat); 	
	}

	public List<Restoran> getListaOdobrenihRestorana () {	
		
		return this.listaOdobrenihRestorana;
	}

	public List<Restoran> getListaNeodobrenihRestorana () {
		
		return this.listaNeodobrenihRestorana;
	}

	public List<Korisnik> getListaKorisnika () {	
		
		return this.listaKorisnika;
	}
	
	private void napuniListeRestorana () {
		
		AdministratorDAO aDAO = new AdministratorDAO(this.getKorisnickoIme(), this.getLozinka());
		this.listaOdobrenihRestorana = aDAO.selectRestoraniPoOdobrenju(true);
		this.listaNeodobrenihRestorana = aDAO.selectRestoraniPoOdobrenju(false);
		
	}
	
	private void napuniListuKorisnika () {
		
		AdministratorDAO aDAO = new AdministratorDAO(this.getKorisnickoIme(), this.getLozinka());
		this.listaKorisnika = aDAO.selectKorisnici();		
	}
}
