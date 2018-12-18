package DataStructure;

import java.util.Set;

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
	
	public Restoran (String ime, Korisnik vlasnik, GeoLokacija lokacija, String opis, Image slika, int telefon) {
		this.ime=ime;
		this.vlasnik=vlasnik;
		this.lokacija=lokacija;
		this.opis=opis;
		this.slika=slika;
		this.telefon=telefon;
	}
	

	public String getIme() {
		return ime;
	}

	public void setIme(String ime, Zastavice z, Korisnik vlasnik) {
		if((z.equals("vlasnik")) && (vlasnik==this.vlasnik) )
			this.ime = ime;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public GeoLokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(GeoLokacija novaLokacija, Zastavice z, Korisnik vlasnik) {
		if(z.isVlasnik() && vlasnik==this.vlasnik) 
			this.lokacija = novaLokacija;
	}

	public Image getSlika() {
		return slika;
	}

	public void setSlika(Image slika, Zastavice z, Korisnik vlasnik) {
		if(z.isVlasnik() && vlasnik==this.vlasnik) 
			this.slika = slika;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis,Zastavice z, Korisnik vlasnik) {
		if(z.isVlasnik() && vlasnik==this.vlasnik) 
			this.opis = opis;
	}

	public Set<Artikl> getMeni() {
		return meni;
	}

	public void AddMeni(Artikl noviArtikl, Zastavice z, Korisnik vlasnik) {
		if(z.isVlasnik() && vlasnik==this.vlasnik) 
			meni.add(noviArtikl);
	}
	public void RemoveMeni(Artikl izbaciArtikl,Zastavice z, Korisnik vlasnik) {
		if(z.isVlasnik() && vlasnik==this.vlasnik) 
			meni.remove(izbaciArtikl);
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren, Zastavice z) {
		if(z.isAdministrator()) 
			this.odobren = odobren;
	}

	public int getTelefon() {
		return telefon;
	}

	public void setTelefon(int telefon, Zastavice z, Korisnik vlasnik) {
		if(z.isVlasnik() && vlasnik==this.vlasnik) 
			this.telefon = telefon;
	}
	
	
}
