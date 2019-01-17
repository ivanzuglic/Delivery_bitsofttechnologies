package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import DataStructure.Klijent;
import DataStructure.Korisnik;
import DataStructure.PodatkovnaLjuska;
import DataStructure.Vlasnik;


/**
 * Glavni razred koji koristimo za stvaranje glavnog prozora u kojeg punimo zeljenim panelima.
 * Promjena panela se vrsi iz ocog razreda.
 * @author tonis
 *
 */

public class DefaultWindow extends JFrame{
	private VlasnikPanel vlasnikPanel;
	private KorisnikPanel korPanel;
	private KlijentPanel klijentPanel;
	protected PodatkovnaLjuska podLjuska = new PodatkovnaLjuska(); ;
	
	/**
	 * Konstruktor koji inicijalizira program kao Korisnik
	 */
	public DefaultWindow() {
		setSize(1024, 720);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Dostavljaona!");
	    
	    //vlasnikPanel = new VlasnikPanel(this, new Vlasnik("ime", "prezime"));
	    //add(vlasnikPanel);
	    
	    korPanel = new KorisnikPanel(this);
	    add(korPanel);
	}
	
	
	/**
	 * Metoda za promjenu panela sa Korisnika na KLijenta
	 */
	public void switchToKlijent(Korisnik klijent) {
		remove(korPanel);
		klijentPanel = new KlijentPanel(this, klijent);
		add(klijentPanel);
		revalidate();
	}
	
	/**
	 * Metoda za promjenu panela sa Klijenta na Korisnika
	 */
	public void switchToKorisnikFromKlijent() {
		remove(klijentPanel);
		korPanel = new KorisnikPanel(this);
		add(korPanel);
		revalidate();
	}
	
	/**
	 * Glavna main metoda koja incira program
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
	    SwingUtilities.invokeAndWait(() -> {
	      DefaultWindow window = new DefaultWindow();
	      window.setLocation(20, 20);
	      window.setVisible(true);
	    });
	  }


	public void switchToVlasnik(Korisnik trenutniKorisnik) {
		// TODO Auto-generated method stub
		
	}


	public void switchToDostavljac(Korisnik trenutniKorisnik) {
		// TODO Auto-generated method stub
		
	}


	public void switchToDispecer(Korisnik trenutniKorisnik) {
		// TODO Auto-generated method stub
		
	}


	public void switchToAdmin(Korisnik trenutniKorisnik) {
		// TODO Auto-generated method stub
		
	}

}