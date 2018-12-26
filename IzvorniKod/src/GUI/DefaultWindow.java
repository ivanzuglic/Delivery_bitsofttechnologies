package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * Glavni razred koji koristimo za stvaranje glavnog prozora u kojeg punimo zeljenim panelima.
 * Promjena panela se vrsi iz ocog razreda.
 * @author tonis
 *
 */

public class DefaultWindow extends JFrame{
	private KorisnikPanel korPanel;
	private KlijentPanel klijentPanel;
	
	/**
	 * Konstruktor koji inicijalizira program kao Korisnik
	 */
	public DefaultWindow() {
		setSize(1024, 720);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Dostavljaona!");
	    korPanel = new KorisnikPanel(this);
	    add(korPanel);
	}
	
	
	/**
	 * Metoda za promjenu panela sa Korisnika na KLijenta
	 */
	public void switchToKlijent() {
		remove(korPanel);
		klijentPanel = new KlijentPanel(this);
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

}