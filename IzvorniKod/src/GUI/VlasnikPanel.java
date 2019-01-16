package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DataStructure.Klijent;
import DataStructure.Vlasnik;

public class VlasnikPanel extends JPanel {
	private JPanel buttonsPanel1;
	private JPanel toolbar;
	private JScrollPane showScrollPane;
	private JPanel logoPanel;
	private JPanel northPanel;
	private JPanel usrInfoPanel;
	private ActionListener listaListener;
	private ActionListener kosaricaListener;
	private ActionListener pratiListener;
	private ActionListener urediListener;
	private ActionListener odjavaListener;
	private DefaultWindow window;
	private Vlasnik trenutniKlijent;
	
	public Integer brojacChar = 180;
	
	
	
	public VlasnikPanel(DefaultWindow window, Vlasnik klijent) {
		this.window = window;
		this.trenutniKlijent = klijent;
		setLayout(new BorderLayout());
		
		//Definicije listenera za 4 glavna gumba
		listaListener = (actionEvent) -> {
			setPanelList();
		};
		
		odjavaListener = (actionEvent) -> {
			window.switchToKorisnikFromKlijent();
		};
		
		urediListener = (actionEvent) -> {
			urediPanel();
		};
		
		//Definicija Toolbar-a
		toolbar = new JPanel();
		toolbar.setLayout(new BorderLayout());
		
		//Definicija prvog (gornjeg) panela sa gumbovima koji ide u istocni panel
		buttonsPanel1 = new JPanel();
		buttonsPanel1.setLayout(new BoxLayout(buttonsPanel1, BoxLayout.Y_AXIS));
		buttonsPanel1.setBackground(Color.white);
		
		//Definicija gumba za listu restorana
		JButton listaRestorana = new JButton();
		listaRestorana.setMaximumSize(new Dimension(120, 120));
		listaRestorana.addActionListener(kosaricaListener);
		ImageIcon listaImg = new ImageIcon(getClass().getResource("/images/KosaricaMini.png"));
		listaRestorana.setIcon(listaImg);
		listaRestorana.setBackground(Color.white);
		
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
		
		//Definicija gumba za uredivanje restorana
		JButton uredi = new JButton("Uredi restoran");
		uredi.setMaximumSize(new Dimension(120, 120));
		uredi.addActionListener(urediListener);
		ImageIcon restoranImg = new ImageIcon(getClass().getResource("/images/UredirestoranMini.jpg"));
		uredi.setIcon(restoranImg);
		uredi.setBackground(Color.white);
		
		//Definicija gumba za odjavu
		JButton odjaviSe = new JButton("Odjavi se");
		odjaviSe.setMaximumSize(new Dimension(120, 120));
		odjaviSe.addActionListener(odjavaListener);
		ImageIcon odjavaImg = new ImageIcon(getClass().getResource("/images/OdjaviSeLogoMini.png"));
		odjaviSe.setIcon(odjavaImg);
		odjaviSe.setBackground(Color.white);
		
		//dodavanje gumbova u panel
		buttonsPanel1.add(listaRestorana);
		buttonsPanel1.add(kosarica);
		buttonsPanel1.add(pratiNarudzbu);
		buttonsPanel1.add(uredi);
		buttonsPanel1.add(odjaviSe);
		buttonsPanel1.setPreferredSize(new Dimension(125, 3000));
		toolbar.add(buttonsPanel1, BorderLayout.NORTH);
		
		
		//Dodavanje panela s gumbovima u toolbar
		toolbar.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		toolbar.setPreferredSize(new Dimension(120, 500));
		toolbar.setBackground(Color.white);
		add(toolbar, BorderLayout.EAST);
		
		//Definicija sjevernog panela
		northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
				
		//Definicija panela sa logom
		logoPanel = new JPanel();
		logoPanel.setBackground(Color.white);
		logoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 3));
		logoPanel.setPreferredSize(new Dimension(1080, 75));
		ImageIcon imageLogo = new ImageIcon(getClass().getResource("/images/GlavniLogo.png"));
		logoPanel.add(new JLabel(imageLogo));
		northPanel.add(logoPanel, BorderLayout.CENTER);
				
		//Definicjia panela za prikaz korisnickih informacija
		usrInfoPanel = new JPanel();
		usrInfoPanel.setLayout(new BorderLayout());
		JTextArea usrName = new JTextArea();
		usrName.setForeground(new Color(0, 153, 255));
		usrName.setEditable(false);
		usrName.setFont(new Font("Arial", 0, 14));
		usrName.setText("\n Prijavljen kao:\n " + trenutniKlijent.getKorisnickoIme());
		usrInfoPanel.add(usrName, BorderLayout.CENTER);
		usrInfoPanel.setPreferredSize(new Dimension(120, 75));
		usrInfoPanel.setBackground(Color.white);
		usrInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		northPanel.add(usrInfoPanel, BorderLayout.EAST);
		
		setPanelList();
	}

	private void setPanelList() {
		//Nepotpuna definicija panela sa listom restorana
		//Potrebno dalje istraziti kako radi JScrollPane
		JPanel restorani = new JPanel();
		restorani.setLayout(new BoxLayout(restorani, BoxLayout.PAGE_AXIS));
		restorani.setBackground(Color.WHITE);
		
		//Primjer1
		JPanel restoran = new JPanel();
		restoran.setMaximumSize(new Dimension(900, 100));
		restoran.setLayout(new BorderLayout());
		restoran.add(new JLabel(new ImageIcon(getClass().getResource("/images/DodajrestoranMini.png"))), BorderLayout.WEST);
		restoran.add(new JTextField("Nekakav opis za restoran"), BorderLayout.CENTER);
		restoran.add(new JButton("Naruci"), BorderLayout.EAST);
		restorani.add(restoran);
				
		//Primjer2
		JPanel restoran2 = new JPanel();
		restoran2.setMaximumSize(new Dimension(900, 100));
		restoran2.setLayout(new BorderLayout());
		restoran2.add(new JLabel(new ImageIcon(getClass().getResource("/images/DodajrestoranMini.png"))), BorderLayout.WEST);
		restoran2.add(new JTextField("Nekakav opis za restoran"), BorderLayout.CENTER);
		restoran2.add(new JButton("Naruci"), BorderLayout.EAST);
		restorani.add(restoran2);
				
		showScrollPane = new JScrollPane(restorani);
		showScrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		add(showScrollPane, BorderLayout.CENTER);
		revalidate();
	}

	private void urediPanel() {
		ActionListener natragListener = (actionEvent) -> {
			setPanelList();
		};
		
		JPanel urediRestoran = new JPanel();
		urediRestoran.setLayout(new BorderLayout());
		JScrollPane meni = new JScrollPane();
		urediRestoran.add(meni, BorderLayout.CENTER);
		
	}

	private void puniShowPanel() {
		// TODO Auto-generated method stub
		
	}

}
