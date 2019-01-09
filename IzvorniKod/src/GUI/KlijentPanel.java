package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.ScrollPaneLayout;

import DataStructure.Klijent;

/**
 * Razred koji definira izgled i funkcionalnosti za Klijente
 * @author tonis
 *
 */

public class KlijentPanel extends JPanel {
	
	private JPanel buttonsPanel1;
	private JPanel buttonsPanel2;
	private JPanel toolbar;
	private JScrollPane showScrollPane;
	private JPanel logoPanel;
	private JPanel northPanel;
	private JPanel usrInfoPanel;
	private ActionListener kosaricaListener;
	private ActionListener pratiListener;
	private ActionListener predloziListener;
	private ActionListener odjavaListener;
	private DefaultWindow window;
	private Klijent trenutniKlijent;
	
	public Integer brojacChar = 180;
	
	
	
	public KlijentPanel(DefaultWindow window, Klijent klijent) {
		this.window = window;
		this.trenutniKlijent = klijent;
		setLayout(new BorderLayout());
		
		//Definicije listenera za 4 glavna gumba
		odjavaListener = (actionEvent) -> {
			window.switchToKorisnikFromKlijent();
		};
		
		predloziListener = (actionEvent) -> {
			preedloziWindow();
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
		
		//Nepotpuna definicija panela sa listom restorana
		//Potrebno dalje istraziti kako radi JScrollPane
		JPanel restorani = new JPanel();
		restorani.setLayout(new BoxLayout(restorani, BoxLayout.PAGE_AXIS));
		restorani.setBackground(Color.WHITE);
		showScrollPane = new JScrollPane(restorani);
		showScrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		add(showScrollPane, BorderLayout.CENTER);
	}

	private void preedloziWindow() {
		//Kreiranje dialog prozora
		JDialog Predlozi = new JDialog();
		Predlozi.setLayout(new BorderLayout());
		
		
		//Kreiranje sredisnjeg panela
		JPanel podaci = new JPanel();
		podaci.setLayout(new FlowLayout());
		podaci.setBackground(Color.white);
		
		//Definicija panela za korisnicko ime
		JPanel restImePanel = new JPanel();
		restImePanel.setBackground(Color.white);
		restImePanel.setLayout(new FlowLayout());
		restImePanel.add(new JLabel("     Ime restorana: "));
		JTextField restImeField = new JTextField();
		restImeField.setColumns(15);
		restImePanel.add(restImeField);
		podaci.add(restImePanel);
		
		//Definicija panela za opsi restorana
		JPanel opisPanel = new JPanel();
		opisPanel.setBackground(Color.white);
		opisPanel.setLayout(new BorderLayout());
		opisPanel.add(new JLabel("Opis usluga restorana: "), BorderLayout.WEST);
		JTextArea opisField = new JTextArea(4, 20);
		opisField.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		opisField.setWrapStyleWord(true);
		opisField.setLineWrap(true);
		
		//JLabel prikazBrojaca = new JLabel();
		//prikazBrojaca.setText(Integer.toString(opisField.getLineCount()));
		
		/*Ovo treba jako puno doraditi.*/
		opisField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				//prikazBrojaca.setText(Integer.toString(opisField.getLineCount()));
				Predlozi.revalidate();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(opisField.getLineCount() > 1) {
					String temp = opisField.getText();
					while(opisField.getLineCount() > 1) {
						temp.substring(0, temp.length() - 1);
						opisField.setText(temp);
					}
					Predlozi.revalidate();
				}
			}
		});
		opisField.setPreferredSize(new Dimension(80, 90));
		opisPanel.add(opisField, BorderLayout.SOUTH);
		opisPanel.setPreferredSize(new Dimension(248, 120));
		podaci.add(opisPanel);
		//podaci.add(prikazBrojaca);
		
		//Definicija panela za dodavanje slike
		JPanel zaSliku = new JPanel();
		//zaSliku.setPreferredSize(new Dimension(300, 200));
		zaSliku.setLayout(new BorderLayout());
		JLabel slikaPreview = new JLabel();
		ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/images/DodajrestoranMini.png"));
		slikaPreview.setIcon(defaultIcon);
		zaSliku.add(slikaPreview, BorderLayout.CENTER);
		JButton dodajSliku = new JButton ("Dodaj sliku");
		JTextField URL = new JTextField();
		URL.setColumns(20);
		
		ActionListener listenerSlika = (actionEvent) -> {
			try {
			ImageIcon iconNew = new ImageIcon(getClass().getResource(URL.getText()));
			slikaPreview.setIcon(iconNew);
			Predlozi.revalidate();
			}
			
			catch(Exception e) {
				
			}
		};
		
		dodajSliku.addActionListener(listenerSlika);
		JLabel putanja = new JLabel("Upisi putanju do slike:");
		JPanel tempButtonPanel = new JPanel();
		tempButtonPanel.setLayout(new BorderLayout());
		tempButtonPanel.add(dodajSliku, BorderLayout.CENTER);
		tempButtonPanel.setBackground(Color.white);
		tempButtonPanel.add(putanja, BorderLayout.SOUTH);
		zaSliku.add(tempButtonPanel, BorderLayout.EAST);
		//zaSliku.add(putanja, BorderLayout.SOUTH);
		zaSliku.add(URL, BorderLayout.SOUTH);
		podaci.add(zaSliku);
		
		//Definicija panela za geolokaciju restorana
		JPanel geo = new JPanel();
		geo.setBackground(Color.white);
		geo.setLayout(new BorderLayout());
		geo.setPreferredSize(new Dimension(300, 60));
		JLabel info = new JLabel("Unesite GeoLokaciju vaseg restorana:");
		geo.add(info, BorderLayout.NORTH);
		JPanel x = new JPanel();
		x.setBackground(Color.WHITE);
		x.setLayout(new FlowLayout());
		JLabel xLabel = new JLabel("x:");
		JTextField xField = new JTextField();
		xField.setColumns(5);
		x.add(xLabel);
		x.add(xField);
		geo.add(x, BorderLayout.WEST);
		
		JPanel y = new JPanel();
		y.setBackground(Color.white);
		y.setLayout(new FlowLayout());
		JLabel yLabel = new JLabel("y:");
		JTextField yField = new JTextField();
		yField.setColumns(5);
		y.add(yLabel);
		y.add(yField);
		geo.add(y, BorderLayout.CENTER);
		
		podaci.add(geo);
		
		//Definicija info panela
		JPanel info2 = new JPanel();
		info2.setBackground(Color.WHITE);
		JLabel infoLabel = new JLabel("Vas ce zahtjev odobriti administrator u roku od 24 sata");
		infoLabel.setForeground(new Color(0, 153, 255));
		info2.add(infoLabel);
		podaci.add(info2);
		
		//Definicija juznog panela
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.white);
		southPanel.setLayout(new BorderLayout());
				
		//Definicjia panela za poruku koji ide u juzni panel
		JPanel poruka = new JPanel();
		poruka.setBackground(Color.white);
		poruka.setSize(opisPanel.getWidth(), opisPanel.getHeight());
		southPanel.add(poruka, BorderLayout.NORTH);
		
		//Definicjia funkcionalnosti gumba predlozi
		ActionListener PredloziDialog = actionevent -> {
			//Popuni ME!
		};
		JButton OK = new JButton("Predlozi restoran");
		OK.addActionListener(PredloziDialog);
		
		//Definicija funkcionalnsti gumba odustani
		ActionListener OdustaniDialog = (actionEvent) -> {
			Predlozi.dispatchEvent(new WindowEvent(Predlozi, WindowEvent.WINDOW_CLOSING));
		};
		JButton NOK = new JButton("Odustani");
		NOK.addActionListener(OdustaniDialog);
		
		//Definicjia panela sa gumbovima koji ide u juzni panel
		JPanel DialogButtons = new JPanel();
		DialogButtons.setBackground(Color.white);
		DialogButtons.setLayout(new FlowLayout());
		DialogButtons.add(OK);
		DialogButtons.add(NOK);
		southPanel.add(DialogButtons, BorderLayout.SOUTH);
				
		//Krajnji modifikatori za dialog prozora
		Predlozi.add(southPanel, BorderLayout.SOUTH);
		Predlozi.add(podaci, BorderLayout.CENTER);
		Predlozi.setTitle("Predlozi");
		Predlozi.setResizable(false);
		Predlozi.setSize(330, 500);
		Predlozi.setLocation(window.getX()+10, window.getY()+8);
		Predlozi.setModal(true);
		Predlozi.setVisible(true);
		
	}

	private void puniShowPanel() {
		// TODO Auto-generated method stub
		
	}

}