package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import DataStructure.Artikl;
import DataStructure.GeoLokacija;
import DataStructure.Klijent;
import DataStructure.Korisnik;
import DataStructure.Kosarica;
import DataStructure.Narudzba;
import DataStructure.PodaciKarte;
import DataStructure.Restoran;

/**
 * Razred koji definira izgled i funkcionalnosti za Klijente
 * @author tonis
 *
 */

public class KlijentPanel extends JPanel {
	
	private JPanel buttonsPanel1;
	private JPanel toolbar;
	private JScrollPane showScrollPane;
	private JPanel logoPanel;
	private JPanel northPanel;
	private JPanel usrInfoPanel;
	private JPanel centerPanel;
	private ActionListener listaListener;
	private ActionListener kosaricaListener;
	private ActionListener pratiListener;
	private ActionListener predloziListener;
	private ActionListener odjavaListener;
	private DefaultWindow window;
	private Klijent trenutniKlijent;
	private Set<Restoran> listaRestorani;
	private GeoLokacija lokacijaDostave;
	
	public Integer brojacChar = 180;
	
	
	
	public KlijentPanel(DefaultWindow window, Klijent klijent) {
		this.window = window;
		this.trenutniKlijent = klijent;
		this.showScrollPane = new JScrollPane();
		setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		centerPanel.setBackground(Color.white);
		add(centerPanel, BorderLayout.CENTER);
		
		//Definicije listenera za 4 glavna gumba
		odjavaListener = (actionEvent) -> {
			window.podLjuska.odjava();
			window.switchToKorisnikFromKlijent();
		};
		
		kosaricaListener = (actionEvent) -> {
			kosaricaPanelSwitch();
		};
		
		predloziListener = (actionEvent) -> {
			preedloziWindow();
		};
		
		listaListener = (actionEvent) -> {
			showPanelfill();
		};
		
		pratiListener = (ActionEvent) -> {
			kartaWindow();
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
		listaRestorana.addActionListener(listaListener);
		ImageIcon listaImg = new ImageIcon(getClass().getResource("/images/listaMini.png"));
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
				
		//dodavanje gumbova u panel
		buttonsPanel1.add(listaRestorana);
		buttonsPanel1.add(kosarica);
		buttonsPanel1.add(pratiNarudzbu);
		buttonsPanel1.add(predlozi);
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
		
		showPanelfill();

	}

	private void kartaWindow() {
		
		
		if (window.podLjuska.getTrenutniKlijent().getAktivnaNarudzba() != null) {
			
			GeoLokacija lokacija = window.podLjuska.getTrenutniKlijent().pratiPoziciju();
			
			//Stvaramo novi Map Viewer
			final JXMapViewer viewer = new JXMapViewer();
			
			//Pripremamo JFrame na koji cemo postaviti Map Viewer
			JFrame kartaFrame = new JFrame("Lokacija Narudžbe");
			kartaFrame.getContentPane().add(viewer);
			kartaFrame.setSize(600, 600);
			kartaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			kartaFrame.setLocation(window.getX() + 10, window.getY() + 10);
			kartaFrame.setVisible(true);
			
			//Stvaramo tileFactory - izvor mapa
			TileFactoryInfo tfinfo = new OSMTileFactoryInfo();
			DefaultTileFactory tileFactory = new DefaultTileFactory(tfinfo);
			viewer.setTileFactory(tileFactory);
			
			tileFactory.setThreadPoolSize(4);
			
			//Dodajemo interakcije
			MouseInputListener MIListener = new PanMouseInputListener(viewer);
			MouseWheelListener MWListener = new ZoomMouseWheelListenerCursor(viewer);

			viewer.addMouseListener(MIListener);
			viewer.addMouseMotionListener(MIListener);
			viewer.addMouseWheelListener(MWListener);
			
			//Stvaramo Listu lokacija
			GeoPosition LokacijaNarudzbe = new GeoPosition(lokacija.getGeoSirina(), lokacija.getGeoDuziina());
			
			
			Set<GeoPosition> geoPositions = new HashSet<>();
			geoPositions.add(LokacijaNarudzbe);
			
			Set<Waypoint> waypoints = new HashSet<>();
			waypoints.add(new DefaultWaypoint(LokacijaNarudzbe));
			
			WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
			waypointPainter.setWaypoints(waypoints);
			
			viewer.setAddressLocation(LokacijaNarudzbe);
			viewer.setZoom(2);
			viewer.setOverlayPainter(waypointPainter);	
		}
		else {
			
			JOptionPane.showMessageDialog(window, "Trenutno nemate aktivnih narudžbi", "Obavijest", 1);
		}
		
	}

	private void kosaricaPanelSwitch() {
		remove(showScrollPane);
		remove(centerPanel);
		
		centerPanel.removeAll();
		showScrollPane.removeAll();
		centerPanel.setLayout(new BorderLayout());
		
		JPanel kosaricaSadrzaj = new JPanel();
		kosaricaSadrzaj.setLayout(new BorderLayout());
		kosaricaSadrzaj.setLayout(new BoxLayout(kosaricaSadrzaj, BoxLayout.PAGE_AXIS));
		kosaricaSadrzaj.setBackground(Color.WHITE);
		
		JPanel opisKosaricaPanel = new JPanel();
		opisKosaricaPanel.setBackground(Color.white);
		JLabel opisKosarica = new JLabel();
		opisKosarica.setText("Trenutni sadrzaj kosarice: ");
		opisKosarica.setForeground(new Color(0, 153, 255));
		opisKosaricaPanel.add(opisKosarica);
		centerPanel.add(opisKosaricaPanel, BorderLayout.NORTH);
		
		puniKosaricu(kosaricaSadrzaj);
		
		JLabel unosAdr1 = new JLabel("Informacije o adresi: ");
		JLabel unosAdr2 = new JLabel("X: ");
		JTextField xField = new JTextField();
		xField.setColumns(7);
		JLabel unosAdr3 = new JLabel("Y: ");
		JTextField yField = new JTextField();
		yField.setColumns(7);
		JLabel unosAdr4 = new JLabel("Labela: ");
		JTextField labelField = new JTextField();
		labelField.setColumns(14);
		
		ActionListener naruciListener = (actionListener) -> {
			
			if(window.podLjuska.getTrenutniKlijent().getAktivnaNarudzba() == null) {
				
				lokacijaDostave = new GeoLokacija(Float.parseFloat(xField.getText()), Float.parseFloat(yField.getText()), labelField.getText());
				window.podLjuska.getTrenutniKlijent().getKosarica().finalizirajNarudzbu(lokacijaDostave, trenutniKlijent);
				
				JOptionPane.showMessageDialog(window, "Narudžba uspješno provedena!", "Obavijest", 1);
				
				window.podLjuska.getTrenutniKlijent().getKosarica().clear();
			}
			else {
				
				JOptionPane.showMessageDialog(window, "NEUSPJELO! Imate aktivnu narudžbu.", "Obavijest", 1);
			}

		};
		
		JPanel kosaricaButtonPanel = new JPanel();
		kosaricaButtonPanel.setLayout(new FlowLayout());
		
		kosaricaButtonPanel.add(unosAdr1);
		kosaricaButtonPanel.add(unosAdr2);
		kosaricaButtonPanel.add(xField);
		kosaricaButtonPanel.add(unosAdr3);
		kosaricaButtonPanel.add(yField);
		kosaricaButtonPanel.add(unosAdr4);
		kosaricaButtonPanel.add(labelField);
		
		JButton naruci = new JButton("Naruci");
		naruci.addActionListener(naruciListener);
		kosaricaButtonPanel.add(naruci);
		kosaricaButtonPanel.setBackground(Color.WHITE);
		centerPanel.add(kosaricaButtonPanel, BorderLayout.SOUTH);
		
		showScrollPane = new JScrollPane(kosaricaSadrzaj);
		centerPanel.add(showScrollPane, BorderLayout.CENTER);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.revalidate();
		revalidate();
	}
	
	private void puniKosaricu(JPanel sadrzaj) {

		Map<Artikl, Integer> artikli = window.podLjuska.getTrenutniKlijent().getKosarica().getOdabraniProizvodi();

		for(Map.Entry<Artikl, Integer> artikl : artikli.entrySet()) {
			JPanel artiklPanel = new JPanel();
			artiklPanel.setLayout(new BorderLayout());
			JPanel artiklInfo = new JPanel();
			artiklInfo.setLayout(new FlowLayout());
			artiklInfo.add(new JLabel(artikl.getKey().getNaziv()));
			artiklInfo.add(new JLabel(artikl.getValue().toString()));
			
			JPanel artiklKol = new JPanel();
			artiklKol.setLayout(new FlowLayout());
			
			JButton plus = new JButton(" + ");
			ActionListener plusListener = (actionListener) -> {
				Integer kolicina = artikl.getValue();
				trenutniKlijent.getKosarica().promijeniKolicinu(artikl.getKey(), ++kolicina);
				puniKosaricu(sadrzaj);
			};
			plus.addActionListener(plusListener);
			
			
			JButton minus = new JButton(" - ");
			ActionListener minusListener = (actionListener) -> {
				Integer kolicina = artikl.getValue();
				trenutniKlijent.getKosarica().promijeniKolicinu(artikl.getKey(), --kolicina);
				puniKosaricu(sadrzaj);
			};
			minus.addActionListener(minusListener);
			
			JLabel kolicinaLabel = new JLabel(" " + artikl.getValue().toString() + " ");
			
			artiklKol.add(plus);
			artiklKol.add(kolicinaLabel);
			artiklKol.add(minus);
			
			artiklPanel.add(artiklInfo, BorderLayout.CENTER);
			artiklPanel.add(artiklInfo, BorderLayout.WEST);
			sadrzaj.add(artiklPanel);
			revalidate();
		}
	}

	private void showPanelfill() {
		remove(showScrollPane);
		remove(centerPanel);
		centerPanel.removeAll();
		showScrollPane.removeAll();
		//JPanel restorani = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setBackground(Color.WHITE);
		window.podLjuska.napuniSetRestorana();
		listaRestorani = window.podLjuska.getRestorani();
		
		for (Restoran restoran : listaRestorani) {
			JPanel filler1 = new JPanel();
			filler1.setMaximumSize(new Dimension(9000, 1));
			filler1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			centerPanel.add(filler1);
			
			JPanel restoranPanel = new JPanel();
			restoranPanel.setBorder(BorderFactory.createLineBorder(new Color(155, 226, 255), 2));
			restoranPanel.setMaximumSize(new Dimension(9000, 100));
			restoranPanel.setLayout(new BorderLayout());

			try {
				ImageIcon slikaRestoran = new ImageIcon(restoran.getSlika());
				if(slikaRestoran == null) {
					slikaRestoran = new ImageIcon(getClass().getResource("/images/DefaultRestoranMini.png"));
				}
				restoranPanel.add(new JLabel(slikaRestoran), BorderLayout.WEST);
			} catch (Exception e) {
				ImageIcon slikaRestoran = new ImageIcon(getClass().getResource("/images/DefaultRestoranMini.png"));
				restoranPanel.add(new JLabel(slikaRestoran), BorderLayout.WEST);
			}
			restoranPanel.add(new JTextArea("Naziv: " + restoran.getIme() + "\nOpis: " + restoran.getOpis()), BorderLayout.CENTER);

			JButton naruci = new JButton("Naruci");
			
			ActionListener naruciListener = (actionEvent) -> {
				remove(showScrollPane);
				remove(centerPanel);
				
				centerPanel.removeAll();
				showScrollPane.removeAll();
				
				centerPanel.setLayout(new BorderLayout());
				centerPanel.setBackground(Color.WHITE);
				
				JPanel menuSadrzaj = new JPanel();
				menuSadrzaj.setLayout(new BoxLayout(menuSadrzaj, BoxLayout.PAGE_AXIS));
				menuSadrzaj.setBackground(Color.WHITE);
				
				JPanel opisMenuPanel = new JPanel();
				opisMenuPanel.setBackground(Color.white);
				JLabel opisMenu = new JLabel();
				opisMenu.setText(restoran.getIme());
				opisMenu.setForeground(new Color(0, 153, 255));
				opisMenuPanel.add(opisMenu);
				centerPanel.add(opisMenuPanel, BorderLayout.NORTH);
				
				Set<Artikl> menu = restoran.getMeni();
				for(Artikl temp : menu) {
					JPanel filler3 = new JPanel();
					filler3.setMaximumSize(new Dimension(9000, 1));
					filler3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
					menuSadrzaj.add(filler3);
					
					JPanel artiklPanel = new JPanel();
					artiklPanel.setBackground(Color.WHITE);
					artiklPanel.setLayout(new BorderLayout());
					JPanel artiklInfo = new JPanel();
					artiklInfo.setBackground(Color.WHITE);
					artiklInfo.setLayout(new BoxLayout(artiklInfo, BoxLayout.LINE_AXIS));
					Float cijena = temp.getCijena();
					JLabel tempLabel1 = new JLabel("Naziv: " + temp.getNaziv());
					tempLabel1.setMaximumSize(new Dimension(280, 90));
					JLabel tempLabel2 = new JLabel("Opis: " + temp.getOpis());
					tempLabel2.setMaximumSize(new Dimension(280, 90));
					JLabel tempLabel3 = new JLabel("Cijena: " + cijena.toString() + "kn");
					tempLabel3.setMaximumSize(new Dimension(280, 90));
					artiklInfo.add(tempLabel1);
					artiklInfo.add(tempLabel2);
					artiklInfo.add(tempLabel3);
					
					JPanel artiklNaruci = new JPanel();
					artiklNaruci.setBackground(Color.WHITE);
					artiklNaruci.setLayout(new FlowLayout());
					JButton dodajButton = new JButton("Dodaj u košaricu");
					
					ActionListener dodaj = (actionEvent2) -> {
				
					if (window.podLjuska.getTrenutniKlijent().getKosarica().getRestoran() == null || window.podLjuska.getTrenutniKlijent().getKosarica().getRestoran().equals(restoran)) {
						
						window.podLjuska.getTrenutniKlijent().getKosarica().dodajArtikl(temp, 1);
						JOptionPane.showMessageDialog(window, "Artikl uspješno dodan.", "Obavijest", 1);
					}
					else {
						JOptionPane.showMessageDialog(window, "NEUSPJELO! Jedna narudžba ne može sadržavati artikle iz dva restorana.", "Obavijest", 1);
					}
					
					};
					
					dodajButton.addActionListener(dodaj);
					artiklNaruci.add(dodajButton);
					artiklPanel.add(artiklInfo, BorderLayout.CENTER);
					artiklPanel.add(artiklNaruci, BorderLayout.EAST);
					artiklPanel.setMaximumSize(new Dimension(9000, 50));
					artiklPanel.setBorder(BorderFactory.createLineBorder(new Color(155, 226, 255), 2));
					menuSadrzaj.add(artiklPanel);
					
					JPanel filler4 = new JPanel();
					filler4.setMaximumSize(new Dimension(9000, 1));
					filler4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
					menuSadrzaj.add(filler3);
					
				}
				
				showScrollPane = new JScrollPane(menuSadrzaj);
				showScrollPane.setBackground(Color.WHITE);
				centerPanel.add(showScrollPane, BorderLayout.CENTER);
				add(centerPanel, BorderLayout.CENTER);
				centerPanel.revalidate();
				revalidate();
			};
			
			naruci.addActionListener(naruciListener);
			restoranPanel.add(naruci, BorderLayout.EAST);
			centerPanel.add(restoranPanel);
			
			JPanel filler2 = new JPanel();
			filler2.setMaximumSize(new Dimension(9000, 1));
			filler2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			centerPanel.add(filler2);
		}
		
		
		/*
		//Primjer1
		JPanel restoran = new JPanel();
		restoran.setBorder(BorderFactory.createLineBorder(new Color(155, 226, 255), 2));
		restoran.setMaximumSize(new Dimension(9000, 100));
		restoran.setLayout(new BorderLayout());
		restoran.add(new JLabel(new ImageIcon(getClass().getResource("/images/DodajrestoranMini.png"))), BorderLayout.WEST);
		restoran.add(new JTextArea("Nekakav opis za restoran"), BorderLayout.CENTER);
		JButton tempButton = new JButton("Naruci");
		//tempButton.addActionListener(naruciListener);
		restoran.add(tempButton, BorderLayout.EAST);
		restorani.add(restoran);
		
		//Filler
		JPanel filler = new JPanel();
		filler.setMaximumSize(new Dimension(9000, 1));
		filler.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		restorani.add(filler);
		
		//Primjer2
		JPanel restoran2 = new JPanel();
		restoran2.setBorder(BorderFactory.createLineBorder(new Color(155, 226, 255), 2));
		restoran2.setMaximumSize(new Dimension(9000, 100));
		restoran2.setLayout(new BorderLayout());
		restoran2.add(new JLabel(new ImageIcon(getClass().getResource("/images/DodajrestoranMini.png"))), BorderLayout.WEST);
		restoran2.add(new JTextArea("\nNekakav opis za restoran"), BorderLayout.CENTER);
		restoran2.add(new JButton("Naruci"), BorderLayout.EAST);
		restorani.add(restoran2);
		*/
		
		showScrollPane = new JScrollPane(centerPanel);
		showScrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		add(showScrollPane, BorderLayout.CENTER);
		centerPanel.revalidate();
		showScrollPane.revalidate();
		revalidate();
		
	}

	private void preedloziWindow() {
		//Kreiranje dialog prozora
		JDialog Predlozi = new JDialog();
		Predlozi.setTitle("Predlozi Restoran");
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
		
		
		//Definicija panela za OIB
		JPanel OIBPanel = new JPanel();
		OIBPanel.setBackground(Color.white);
		OIBPanel.setLayout(new FlowLayout());
		OIBPanel.add(new JLabel("    OIB restorana: "));
		JTextField OIBTextField = new JTextField();
		OIBTextField.setColumns(15);
		OIBPanel.add(OIBTextField);
		podaci.add(OIBPanel);
		
		//Definicija panela za telefon
		JPanel telefonPanel = new JPanel();
		telefonPanel.setBackground(Color.white);
		telefonPanel.setLayout(new FlowLayout());
		telefonPanel.add(new JLabel("                 Telefon: "));
		JTextField telefonTextField = new JTextField();
		telefonTextField.setColumns(15);
		telefonPanel.add(telefonTextField);
		podaci.add(telefonPanel);
				
		//Definicija panela za faks
		JPanel faksPanel = new JPanel();
		faksPanel.setBackground(Color.white);
		faksPanel.setLayout(new FlowLayout());
		faksPanel.add(new JLabel("                      Faks: "));
		JTextField faksTextField = new JTextField();
		faksTextField.setColumns(15);
		faksPanel.add(faksTextField);
		podaci.add(faksPanel);
				
		
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
		
		//Definicija panela za adresu
		JPanel AdresaPanel = new JPanel();
		AdresaPanel.setBackground(Color.white);
		AdresaPanel.setLayout(new FlowLayout());
		AdresaPanel.add(new JLabel("                 Adresa: "));
		JTextField AdresaTextField = new JTextField();
		AdresaTextField.setColumns(15);
		AdresaPanel.add(AdresaTextField);
		podaci.add(AdresaPanel);
		
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
			
			// Ovo sam ja dodal - slobodno se promijeni - takodjer, trebalo bi dodati u taj panel jos par elemenata kao sto su telefon, OIB, adresa(bas adresa, ne samo koordinate)
			
			window.podLjuska.getTrenutniKlijent().predloziRestoran(restImeField.getText(), new GeoLokacija(Float.parseFloat(xField.getText()), Float.parseFloat(yField.getText()), restImeField.getText()), 
																	opisField.getText(), null, telefonTextField.getText(), faksTextField.getText(), Integer.parseInt(OIBTextField.getText()), 0, 0, AdresaTextField.getText());	
				
			Timer timer = new Timer(500, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
		            Predlozi.dispatchEvent(new WindowEvent(Predlozi, WindowEvent.WINDOW_CLOSING));
				}
			});
			timer.setRepeats(false);
			timer.start();
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
		Predlozi.setSize(330, 630);
		Predlozi.setLocation(window.getX()+10, window.getY()+8);
		Predlozi.setModal(true);
		Predlozi.setVisible(true);
		
	}

	private void puniShowPanel() {
		// TODO Auto-generated method stub
		
	}

}
