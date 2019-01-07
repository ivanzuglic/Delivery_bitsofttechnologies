package DataStructure;

import java.awt.image.BufferedImage;	//pogledat i dogovorit se
import java.util.Set;
import java.util.TreeSet;


public class Restoran {
	 
	private int id;					//dodano za AdministratorDAO
	private String ime;
	private Korisnik vlasnik;
	private GeoLokacija lokacija;
	private String opis;
	private BufferedImage slika;
	private String fax;				//
	private String adresa;			//
	private Set<Artikl> meni;
	private boolean odobren;
	private String telefon;     //mora biti String za slucaj ako broj telefona pocinje s nulom ili plusom
	
	
	public Restoran (String ime, Korisnik vlasnik, GeoLokacija lokacija, String opis, BufferedImage slika, boolean odobren, String telefon, String fax, String adresa) {

		
		this.ime = ime;
		this.vlasnik = vlasnik;
		this.lokacija = lokacija;
		this.opis = opis;
		this.slika = slika;
		this.odobren = odobren;
		this.telefon = telefon;
		this.fax = fax;
		this.adresa = adresa;
		
		this.meni = new TreeSet<Artikl>();   // ne treba gore u konstruktoru traziti cijeli meni nego se dodaje u posebnoj metodi nakon sto se stvori restoran
		this.napuniMeni();
	}
	
	public int getId() {   			//dodano za AdministratorDAO
		return this.id;
	}
	
	public String getIme() {
		
		return this.ime;
	}

	public void setIme (String novoIme, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.ime = novoIme;
		}
	}

	public Korisnik getVlasnik () {
		
		return this.vlasnik;
	}

	public GeoLokacija getLokacija () {
		
		return this.lokacija;
	}

	public void setLokacija (GeoLokacija novaLokacija, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.lokacija = novaLokacija;
		}	
	}

	public BufferedImage getSlika () {
		
		return this.slika;
	}

	public void setSlika (BufferedImage novaSlika, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.slika = novaSlika;
		}
	}
	
	public String getAdresa() {			//novododano
		return this.adresa;
	}
	
	public void setAdresa(String novaAdresa, Zastavice z, Korisnik trenutniKorisnik) {  //novododano
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.adresa = novaAdresa;
		}	
	}
	
	public String getFax() {  			//novododano
		return this.fax;
	}
	
	public void setFax(String noviFax, Zastavice z, Korisnik trenutniKorisnik) {	//novododano
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.fax = noviFax;
		}	
	}

	public String getOpis () {
		
		return this.opis;
	}

	public void setOpis (String noviOpis, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.opis = noviOpis;
		}	
	}

	public Set<Artikl> getMeni () {
		
		return this.meni;
	}

	public void AddMeni (Artikl noviArtikl, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.meni.add(noviArtikl);
		}
	}
	
	public void RemoveMeni (Artikl izbaciArtikl, Zastavice z, Korisnik trenutniKorsnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorsnik)) {
			this.meni.remove(izbaciArtikl);
		}	
	}

	public boolean isOdobren () {
		
		return this.odobren;
	}

	public void setOdobren (boolean odobren, Zastavice z) {
		
		if (z.isAdministrator()) {
			this.odobren = odobren;
		}
	}

	public String getTelefon () {
		
		return this.telefon;
	}

	public void setTelefon (String noviTelefon, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.telefon = noviTelefon;
		}
	}
	
	private void napuniMeni () {
		
		// iz baze podataka povuci meni restorana
		
	}
}
