package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.HashSet;
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
import DataStructure.Korisnik;
import DataStructure.Kosarica;
import DataStructure.Restoran;
import DataStructure.Vlasnik;
import jdk.nashorn.internal.codegen.OptimisticTypesPersistence;

public class VlasnikPanel extends JPanel {

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
	private ActionListener urediListener;
	private ActionListener odjavaListener;
	private DefaultWindow window;
	private Vlasnik trenutniVlasnik;
	private Set<Restoran> listaRestorani;
	private GeoLokacija lokacijaDostave;
	private Restoran trenRestoran;
	
	public Integer brojacChar = 180;
	
	
	
	public VlasnikPanel(DefaultWindow window, Vlasnik klijent) {
		this.window = window;
		this.trenutniVlasnik = klijent;
		this.trenRestoran = klijent.getVlastitiRestoran();
		this.showScrollPane = new JScrollPane();
		
		setLayout(new BorderLayout());
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		centerPanel.setBackground(Color.white);
		
		//Definicije listenera za 4 glavna gumba
		odjavaListener = (actionEvent) -> {
			window.podLjuska.odjava();
			window.switchToKorisnikFromVlasnik();
		};
		
		kosaricaListener = (actionEvent) -> {
			kosaricaPanelSwitch();
		};
		
		urediListener = (actionEvent) -> {
			urediPanel();
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
		usrName.setText("\n Prijavljen kao:\n " + trenutniVlasnik.getKorisnickoIme());
		usrInfoPanel.add(usrName, BorderLayout.CENTER);
		usrInfoPanel.setPreferredSize(new Dimension(120, 75));
		usrInfoPanel.setBackground(Color.white);
		usrInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		northPanel.add(usrInfoPanel, BorderLayout.EAST);
		
		showPanelfill();

	}

	private void kartaWindow() {
		
		
		if (window.podLjuska.getTrenutniVlasnik().getAktivnaNarudzba() != null) {
			
			GeoLokacija lokacija = window.podLjuska.getTrenutniKlijent().pratiPoziciju();
			
			//Stvaramo novi Map Viewer
			final JXMapViewer viewer = new JXMapViewer();
			
			//Pripremamo JFrame na koji cemo postaviti Map Viewer
			JFrame kartaFrame = new JFrame("Lokacija Narudžbe");
			kartaFrame.getContentPane().add(viewer);
			kartaFrame.setSize(600, 600);
			kartaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			kartaFrame.setLocation(30, 30);
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
		//JPanel kosaricaPanelMain = new JPanel();
		//kosaricaPanelMain.setLayout(new BorderLayout());
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
			lokacijaDostave = new GeoLokacija(Float.parseFloat(xField.getText()), Float.parseFloat(yField.getText()), labelField.getText());
			trenutniVlasnik.getKosarica().finalizirajNarudzbu(lokacijaDostave, trenutniVlasnik);
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
		Map<Artikl, Integer> artikli = trenutniVlasnik.getKosarica().getOdabraniProizvodi();
		for(Map.Entry<Artikl, Integer> artikl : artikli.entrySet()) {
			JPanel artiklPanel = new JPanel();
			artiklPanel.setLayout(new BorderLayout());
			JPanel artiklInfo = new JPanel();
			artiklInfo.setLayout(new FlowLayout());
			artiklInfo.add(new JLabel(artikl.toString()));
			artiklInfo.add(new JLabel(artikl.getValue().toString()));
			
			JPanel artiklKol = new JPanel();
			artiklKol.setLayout(new FlowLayout());
			
			JButton plus = new JButton(" + ");
			ActionListener plusListener = (actionListener) -> {
				Integer kolicina = artikl.getValue();
				trenutniVlasnik.getKosarica().promijeniKolicinu(artikl.getKey(), ++kolicina);
				puniKosaricu(sadrzaj);
			};
			plus.addActionListener(plusListener);
			
			
			JButton minus = new JButton(" - ");
			ActionListener minusListener = (actionListener) -> {
				Integer kolicina = artikl.getValue();
				trenutniVlasnik.getKosarica().promijeniKolicinu(artikl.getKey(), --kolicina);
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
			
			//privremeno - LM
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
					JButton dodajButton = new JButton("Dodaj");
					ActionListener dodaj = (actionEvent2) -> {
						trenutniVlasnik.getKosarica().dodajArtikl(temp, 1);
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
	
	private void urediPanel() {
		remove(showScrollPane);
		remove(centerPanel);
		centerPanel.setLayout(new BorderLayout());
				
		JPanel menuSadrzaj = new JPanel();
		menuSadrzaj.setLayout(new BoxLayout(menuSadrzaj, BoxLayout.PAGE_AXIS));
		menuSadrzaj.setBackground(Color.WHITE);
		
		dohvatiMenu(menuSadrzaj);
		
		JPanel opisPanel = new JPanel();
		opisPanel.setBackground(Color.white);
		JLabel opis = new JLabel();
		opis.setText("Trenutni sadrzaj menu-a vaseg restorana (" + trenRestoran.getIme() + "): ");
		opis.setForeground(new Color(0, 153, 255));
		opisPanel.add(opis);
		
		JLabel unosAdr1 = new JLabel("Naziv artikla: ");
		JTextField nazivField = new JTextField();
		nazivField.setColumns(8);
		JLabel unosAdr2 = new JLabel("Cijena artikla: ");
		JTextField cijenaField = new JTextField();
		cijenaField.setColumns(8);
		JLabel unosAdr3 = new JLabel("Opis artikla: ");
		JTextField opisField = new JTextField();
		opisField.setColumns(28);
		JLabel unosAdr4 = new JLabel("Vrijeme pripravljanja artikla u minutama: ");
		JTextField vrijemeField = new JTextField();
		vrijemeField.setColumns(4);
		
		ActionListener dodajListener = (actionListener) -> {
				String naziv = nazivField.getText();
				float cijena = Float.parseFloat(cijenaField.getText());
				String opisArt = opisField.getText();
				int vrijeme = Integer.parseInt(vrijemeField.getText());
				trenRestoran.AddMeni(new Artikl(naziv, cijena, vrijeme, trenRestoran, opisArt), window.podLjuska.getZastavice(), trenutniVlasnik);
				urediPanel();
		};
		
		JPanel urediButtonPanel = new JPanel();
		urediButtonPanel.setBackground(Color.white);
		urediButtonPanel.setPreferredSize(new Dimension(3000, 60));
		
		//JPanel urediButtonPanel2 = new JPanel();
		//urediButtonPanel2.setBackground(Color.white);
		
		urediButtonPanel.add(unosAdr1);
		urediButtonPanel.add(nazivField);
		urediButtonPanel.add(unosAdr2);
		urediButtonPanel.add(cijenaField);
		urediButtonPanel.add(unosAdr3);
		urediButtonPanel.add(opisField);
		urediButtonPanel.add(unosAdr4);
		urediButtonPanel.add(vrijemeField);
		
		urediButtonPanel.setLayout(new FlowLayout());
		JButton dodaj = new JButton("Dodaj artikl");
		dodaj.addActionListener(dodajListener);
		urediButtonPanel.add(dodaj);
		
		showScrollPane = new JScrollPane(menuSadrzaj);
		
		centerPanel.removeAll();
		showScrollPane.removeAll();
		showScrollPane.setBackground(Color.white);
		centerPanel.add(opisPanel, BorderLayout.NORTH);
		centerPanel.add(showScrollPane, BorderLayout.CENTER);
		centerPanel.add(urediButtonPanel, BorderLayout.SOUTH);
		
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.revalidate();
		revalidate();
		
	}

	private void dohvatiMenu(JPanel menuSadrzaj) {
		Set<Artikl> menu = trenRestoran.getMeni();
		for(Artikl temp : menu) {
			JPanel artiklInfo = new JPanel();
			Float cijena = temp.getCijena();
			Integer vrijemePripravljanja = temp.getVrijemePripravljanja();
			artiklInfo.setLayout(new FlowLayout());
			artiklInfo.add(new JLabel(temp.getNaziv()));
			artiklInfo.add(new JLabel(cijena.toString()));
			artiklInfo.add(new JLabel("Vrijeme pripravljanja: " + vrijemePripravljanja.toString() + "'"));
			artiklInfo.add(new JLabel("Opis: " + temp.getOpis()));
			
			ActionListener ukloniListener = (actionEvent) -> {
				trenRestoran.RemoveMeni(temp, window.podLjuska.getZastavice(), trenutniVlasnik);
				urediPanel();
			};
			
			JButton ukloni = new JButton("Ukloni");
			ukloni.addActionListener(ukloniListener);
			artiklInfo.add(ukloni);
			
			menuSadrzaj.add(artiklInfo);
			
		}
	}

}

