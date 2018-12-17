package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class DefaultWindow extends JFrame{
	private KorisnikPanelAlt korPanelAlt = new KorisnikPanelAlt(this);
	private KlijentPanel klijentPanel = new KlijentPanel(this);
	
	public DefaultWindow() {
		setSize(1024, 720);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Dostavljaona!");
	    add(korPanelAlt);
	}
	
	public void switchToKlijent() {
		remove(korPanelAlt);
		add(klijentPanel);
		revalidate();
	}
	
	public static void main(String[] args) throws Exception {
	    SwingUtilities.invokeAndWait(() -> {
	      DefaultWindow window = new DefaultWindow();
	      window.setLocation(20, 20);
	      window.setVisible(true);
	    });
	  }

}