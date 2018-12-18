package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 * Razred koji definira izgled i funkcionalnosti za Klijente
 * @author tonis
 *
 */

public class KlijentPanel extends JPanel {
	
	private JPanel buttonsPanel1;
	private JPanel buttonsPanel2;
	private JPanel toolbar;
	private JPanel showPanel;
	private JPanel logoPanel;
	private ActionListener kosaricaListener;
	private ActionListener pratiListener;
	private ActionListener predloziListener;
	private ActionListener odjavaListener;
	private DefaultWindow window;
	
	public KlijentPanel(DefaultWindow window) {
		this.window = window;
		setLayout(new BorderLayout());
		
		//Definicije listenera za 4 glavna gumba
		odjavaListener = (actionEvent) -> {
			window.switchToKorisnikFromKlijent();
		};
		
		//Definicija Toolbar-a
		toolbar = new JPanel();
		toolbar.setLayout(new BorderLayout());
		
		//Definicija prvog (gornjeg) panela sa gumbovima koji ide u istocni panel
		buttonsPanel1 = new JPanel();
		buttonsPanel1.setLayout(new BoxLayout(buttonsPanel1, BoxLayout.Y_AXIS));
		buttonsPanel1.setBackground(Color.white);
		
		//Definicija gumba za kosaricu
		JButton kosarica = new JButton();
		kosarica.setMaximumSize(new Dimension(120, 120));
		kosarica.addActionListener(kosaricaListener);
		ImageIcon kosaricaImg = new ImageIcon(getClass().getResource("/images/KosaricaMini.png"));
		kosarica.setIcon(kosaricaImg);
		kosarica.setBackground(Color.white);
		
		//Definicija gumba za mapu
		JButton pratiNarudzbu = new JButton();
		pratiNarudzbu.setMaximumSize(new Dimension(120, 120));
		pratiNarudzbu.addActionListener(pratiListener);
		ImageIcon mapaImg = new ImageIcon(getClass().getResource("/images/MapMini.png"));
		pratiNarudzbu.setIcon(mapaImg);
		pratiNarudzbu.setBackground(Color.white);
		
		//dodavanje gumbova u prvi (gornji) panel
		buttonsPanel1.add(kosarica);
		buttonsPanel1.add(pratiNarudzbu);
		buttonsPanel1.setPreferredSize(new Dimension(125, 280));
		toolbar.add(buttonsPanel1, BorderLayout.NORTH);
		
		//Definicija drugog (donjeg) panela sa gumbovima koji ide u istocni panel
		buttonsPanel2 = new JPanel();
		buttonsPanel2.setBackground(Color.white);
		buttonsPanel2.setLayout(new BoxLayout(buttonsPanel2, BoxLayout.Y_AXIS));
		
		//Definicija gumba za predlaganje restorana
		JButton predlozi = new JButton("Predlozi restoran");
		predlozi.setMaximumSize(new Dimension(120, 120));
		predlozi.addActionListener(predloziListener);
		ImageIcon restoranImg = new ImageIcon(getClass().getResource("/images/DodajrestoranMini.png"));
		predlozi.setIcon(restoranImg);
		predlozi.setBackground(Color.white);
		
		//Definicija gumba za odjavu
		JButton odjaviSe = new JButton("Odjavi se");
		odjaviSe.setMaximumSize(new Dimension(120, 120));
		odjaviSe.addActionListener(odjavaListener);
		ImageIcon odjavaImg = new ImageIcon(getClass().getResource("/images/OdjaviSeLogoMini.png"));
		odjaviSe.setIcon(odjavaImg);
		odjaviSe.setBackground(Color.white);
		
		//Dodavanje gumba u drugi (donji) panel
		buttonsPanel2.setPreferredSize(new Dimension(125, 275));
		buttonsPanel2.add(predlozi);
		buttonsPanel2.add(odjaviSe);
		toolbar.add(buttonsPanel2, BorderLayout.SOUTH);
		
		//Dodavanje gornjeg i donjeg panela s gumbovima u toolbar
		toolbar.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		toolbar.setPreferredSize(new Dimension(120, 500));
		toolbar.setBackground(Color.white);
		add(toolbar, BorderLayout.EAST);
		
		//Definicija panela sa logom
		logoPanel = new JPanel();
		logoPanel.setBackground(Color.white);
		logoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
		logoPanel.setPreferredSize(new Dimension(1080, 75));
		ImageIcon imageLogo = new ImageIcon(getClass().getResource("/images/GlavniLogo.png"));
		logoPanel.add(new JLabel(imageLogo));
		add(logoPanel, BorderLayout.NORTH);
		
		//Nepotpuna definicija panela sa listom restorana
		//Potrebno dalje istraziti kako radi JScrollPane
		showPanel = new JPanel();
		showPanel.setLayout(new ScrollPaneLayout());
		JScrollPane showScrollPane = new JScrollPane();
		showPanel.add(showScrollPane);
		showPanel.setBackground(Color.white);
		showPanel.setLayout(new FlowLayout());
		puniShowPanel(); //Funkcija za punjenje panela sa restoranima
		showPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		add(showPanel, BorderLayout.CENTER);
	}

	private void puniShowPanel() {
		// TODO Auto-generated method stub
		
	}

}
