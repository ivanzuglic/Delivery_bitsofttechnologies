package DataStructure;

import java.awt.image.BufferedImage;					
import java.util.Set;
import Database.RestoranDAO;

public class Restoran {
	 
	private int id;
	private String ime;
	private Korisnik vlasnik;
	private GeoLokacija lokacija;
	private String opis;
	private BufferedImage slika;
	private String telefon;     						
	private String fax;									
	private int OIB;
	private int IBAN;
	private int ziroRacun;
	private String adresa;
	private Set<Artikl> meni;
	private boolean odobren;
	
	
	// konstruktor koristen prilikom predlaganja restorana (u tom slucaju, id treba biti generiran)
	public Restoran (String ime, Korisnik vlasnik, GeoLokacija lokacija, String opis, BufferedImage slika, boolean odobren, String telefon, String fax, int OIB, int IBAN, int ziroRacun, String adresa) {
		
		this.ime = ime;
		this.vlasnik = vlasnik;
		this.lokacija = lokacija;
		this.opis = opis;
		this.slika = slika;
		this.telefon = telefon;
		this.fax = fax;
		this.OIB = OIB;
		this.IBAN = IBAN;
		this.ziroRacun = ziroRacun;
		this.adresa = adresa;
		this.odobren = odobren;
		
		this.id = this.pohraniBP();	// prijedlog treba  biti pohranjen u bazu podataka
	}
	
	// konstruktor koristen prilikom dohvacanja iz baze podataka za AdministratorDAO i RestoranDAO
	public Restoran (int id, String ime, Korisnik vlasnik, GeoLokacija lokacija, String opis, BufferedImage slika, boolean odobren, String telefon, String fax, int OIB, int IBAN, int ziroRacun, String adresa) {
		
		this.id = id;
		this.ime = ime;
		this.vlasnik = vlasnik;
		this.lokacija = lokacija;
		this.opis = opis;
		this.slika = slika;
		this.telefon = telefon;
		this.fax = fax;
		this.OIB = OIB;
		this.IBAN = IBAN;
		this.ziroRacun = ziroRacun;
		this.adresa = adresa;
		this.odobren = odobren;
		
		this.meni = this.napuniMeni();
	}
	
	public Restoran (int id) {
		this.id = id;
		this.ucitajBP();
		this.meni = this.napuniMeni();
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

	public int getOIB () {
		
		return this.OIB;
	}

	public void setOIB (int noviOIB, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.OIB = noviOIB;
			this.azurirajBP(); // promjene treba pohraniti u bazu podataka
		}	
	}

	public int getIBAN () {
		
		return this.IBAN;
	}

	public void setIBAN (int noviIBAN, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.IBAN = noviIBAN;
			this.azurirajBP(); // promjene treba pohraniti u bazu podataka
		}	
	}

	public int getZiroRacun () {
		
		return this.ziroRacun;
	}

	public void setZiroRacun (int noviZiroRacun, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			
			this.ziroRacun = noviZiroRacun;
			this.azurirajBP(); // promjene treba pohraniti u bazu podataka
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
			this.dodajUMeni(noviArtikl);	// promjene treba pohraniti u bazu podataka
		}
	}
	
	public void RemoveMeni (Artikl izbaciArtikl, Zastavice z, Korisnik trenutniKorsnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorsnik)) {
			
			this.meni.remove(izbaciArtikl);
			this.skiniSMenija(izbaciArtikl);	// promjene treba pohraniti u bazu podataka
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
	
	private Set<Artikl> napuniMeni () {
		
		// metoda koja ce iz baze podataka dohvatiti meni resotrana
		
		RestoranDAO dao = new RestoranDAO();
		Set<Artikl> meni = dao.dohvatiMeni(this);
		
		return meni;
	}
	
	private int pohraniBP () {
		
		// metoda koja ce restoran pohraniti u bazu podataka (potpuno novi restoran)
		
		RestoranDAO dao = new RestoranDAO();
		return dao.pohraniRestoran(this);
	}
	
	private void azurirajBP () {
		
		// metoda koja ce restoran azurirati u bazi podataka nakon sto je isti azuriran na lokalnom racunalu
		
		RestoranDAO dao = new RestoranDAO();
		dao.azurirajRestoran(this);
	}
	
	private void ucitajBP() {
		
		// metoda koja ucitava restoran za zadan ID
		
		RestoranDAO dao = new RestoranDAO();
		Restoran rest = dao.ucitajRestoran(this.id);
		
		this.ime = rest.getIme();
		this.vlasnik = rest.getVlasnik();
		this.lokacija = rest.getLokacija();
		this.opis = rest.getOpis();
		this.slika = rest.getSlika();
		this.telefon = rest.getTelefon();
		this.fax = rest.getFax();
		this.OIB = rest.getOIB();
		this.IBAN = rest.getIBAN();
		this.ziroRacun = rest.getZiroRacun();
		this.adresa = rest.getAdresa();
		this.odobren = rest.isOdobren();
		
	}
	
	private void dodajUMeni (Artikl noviArtikl) {
		
		// metoda koja ce u meni restorana u bazi podataka dodati novi artikl
		
		RestoranDAO dao = new RestoranDAO();
		dao.dodajUMeni(noviArtikl);
	}
	
	private void skiniSMenija (Artikl artikl) {
		
		// metoda koja ce s menija restorana u bazi podataka ukloniti artikl
		
		RestoranDAO dao = new RestoranDAO();
		dao.skiniSMenija(artikl);
	}
}