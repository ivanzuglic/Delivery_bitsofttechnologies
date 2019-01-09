package DataStructure;

import java.awt.image.BufferedImage;					//pogledat i dogovorit se
import java.util.Set;
import java.util.TreeSet;


public class Restoran {
	 
	private int id;										//dodano za AdministratorDAO
	
	private String ime;
	private Korisnik vlasnik;
	private GeoLokacija lokacija;
	private String opis;
	private BufferedImage slika;
	private String telefon;     						//mora biti String za slucaj ako broj telefona pocinje s nulom ili plusom
	private String fax;									//vrijedi gornja opaska
	private String adresa;
	private Set<Artikl> meni;
	
	private boolean odobren;
	
	
	// konstruktor koristen prilikom predlaganja restorana (u tom slucaju, id treba biti generiran)
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
		
		this.meni = new TreeSet<Artikl>();
		this.pohraniBP();	// prijedlog treba  biti pohranjen u bazu podataka
	}
	
	// konstruktor koristen prilikom dohvacanja iz baze podataka (u tom slucaju, id je vec odredjen i postoji)
	public Restoran (int id, String ime, Korisnik vlasnik, GeoLokacija lokacija, String opis, BufferedImage slika, boolean odobren, String telefon, String fax, String adresa) {
		
		this.id = id;
		this.ime = ime;
		this.vlasnik = vlasnik;
		this.lokacija = lokacija;
		this.opis = opis;
		this.slika = slika;
		this.odobren = odobren;
		this.telefon = telefon;
		this.fax = fax;
		this.adresa = adresa;
		
		this.meni = new TreeSet<Artikl>();

	}
	
	public int getId () {   
		
		return this.id;
	}
	
	public String getIme() {
		
		return this.ime;
	}

	public void setIme (String novoIme, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.ime = novoIme;
			this.azurirajBP();	// prommjene potrebno pohraniti u bazu podataka
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
			this.azurirajBP();	// promjene potrebno pohraniti u bazu podataka
		}	
	}

	public BufferedImage getSlika () {
		
		return this.slika;
	}

	public void setSlika (BufferedImage novaSlika, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.slika = novaSlika;
			this.azurirajBP();	// promjene potrebno pohraniti u bazu podataka
		}
	}
	
	public String getAdresa () {
		
		return this.adresa;
	}
	
	public void setAdresa (String novaAdresa, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.adresa = novaAdresa;
			this.azurirajBP();	// promjene treba pohraniti u bazu podataka
		}	
	}

	public String getFax () {
		
		return this.fax;
	}
	
	public void setFax (String noviFax, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.fax = noviFax;
			this.azurirajBP();	// promjene treba pohraniti u bazu podataka
		}	
	}

	public String getOpis () {
		
		return this.opis;
	}

	public void setOpis (String noviOpis, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.opis = noviOpis;
			this.azurirajBP(); // promjene treba pohraniti u bazu podataka
		}	
	}

	public Set<Artikl> getMeni () {
		
		return this.meni;
	}

	public void AddMeni (Artikl noviArtikl, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.meni.add(noviArtikl);
			this.azurirajBP();	// promjene treba pohraniti u bazu podataka
		}
	}
	
	public void RemoveMeni (Artikl izbaciArtikl, Zastavice z, Korisnik trenutniKorsnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorsnik)) {
			
			this.meni.remove(izbaciArtikl);
			this.azurirajBP();	// promjene treba pohraniti u bazu podataka
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
			this.azurirajBP();	// promjene  treba pohraniti u bazu podataka
		}
	}
	
	private void napuniMeni () {
		
		// iz baze podataka povuci meni restorana
	}
	
	private void pohraniBP () {
		
		// metoda koja ce restoran pohraniti u bazu podataka (potpuno novi restoran)
	}
	
	private void azurirajBP () {
		
		// metoda koja ce restoran azurirati u bazi podataka nakon sto je isti azuriran na lokalnom racunalu
	}
}