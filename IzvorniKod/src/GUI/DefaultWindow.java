package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class DefaultWindow extends JFrame{
	public DefaultWindow() {
		setSize(500, 480);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Dostavljaona!");
	    add(new KorisnikPanel());
	}
	
	public static void main(String[] args) throws Exception {
	    SwingUtilities.invokeAndWait(() -> {
	      DefaultWindow window = new DefaultWindow();
	      window.setLocation(20, 20);
	      window.setVisible(true);
	    });
	  }

}