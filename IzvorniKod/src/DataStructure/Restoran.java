package DataStructure;

import java.util.Set;
import java.util.TreeSet;
import com.sun.prism.Image;

public class Restoran {
	 
	private String ime;
	private Korisnik vlasnik;
	private GeoLokacija lokacija;
	private String opis;
	private Image slika;
	private Set<Artikl> meni;
	private boolean odobren;
	private int telefon;
	
	
	public Restoran (String ime, Korisnik vlasnik, GeoLokacija lokacija, String opis, Image slika, Set<Artikl> meni, boolean odobren, int telefon) {
		
		this.ime = ime;
		this.vlasnik = vlasnik;
		this.lokacija = lokacija;
		this.opis = opis;
		this.slika = slika;
		this.odobren = odobren;
		this.telefon = telefon;
		
		this.meni = new TreeSet<Artikl>();
		this.napuniMeni();
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

	public Image getSlika () {
		
		return this.slika;
	}

	public void setSlika (Image novaSlika, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.slika = novaSlika;
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

	public int getTelefon () {
		
		return this.telefon;
	}

	public void setTelefon (int noviTelefon, Zastavice z, Korisnik trenutniKorisnik) {
		
		if (z.isVlasnik() && this.vlasnik.equals(trenutniKorisnik)) {
			this.telefon = noviTelefon;
		}
	}
	
	private void napuniMeni () {
		
		// iz baze podataka povuci meni restorana
		
	}
}
