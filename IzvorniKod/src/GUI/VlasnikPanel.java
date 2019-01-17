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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	private Vlasnik trenutniKlijent;
	private Set<Restoran> listaRestorani;
	private Kosarica trenKosarica;
	private GeoLokacija lokacijaDostave;
	
	public Integer brojacChar = 180;
	
	
	
	public VlasnikPanel(DefaultWindow window, Vlasnik klijent) {
		this.window = window;
		this.trenutniKlijent = klijent;
		
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
		
		urediListener = (actionEvent) -> {
			urediPanel();
		};
		
		listaListener = (actionEvent) -> {
			showPanelfill();
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
		usrName.setText("\n Prijavljen kao:\n " + trenutniKlijent.getKorisnickoIme());
		usrInfoPanel.add(usrName, BorderLayout.CENTER);
		usrInfoPanel.setPreferredSize(new Dimension(120, 75));
		usrInfoPanel.setBackground(Color.white);
		usrInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		northPanel.add(usrInfoPanel, BorderLayout.EAST);
		
		showPanelfill();

	}

	private void kosaricaPanelSwitch() {
		//JPanel kosaricaPanelMain = new JPanel();
		//kosaricaPanelMain.setLayout(new BorderLayout());
		
		centerPanel.removeAll();
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
		
		//trenKosarica = window.podLjuska.getKosarica;
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
			trenKosarica.finalizirajNarudzbu(lokacijaDostave, trenutniKlijent);
			lokacijaDostave = new GeoLokacija(Float.parseFloat(xField.getText()), Float.parseFloat(yField.getText()), labelField.getText());
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
		Map<Artikl, Integer> artikli = new HashMap<Artikl, Integer>();
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
				trenKosarica.promijeniKolicinu(artikl.getKey(), ++kolicina);
				puniKosaricu(sadrzaj);
			};
			plus.addActionListener(plusListener);
			
			
			JButton minus = new JButton(" - ");
			ActionListener minusListener = (actionListener) -> {
				Integer kolicina = artikl.getValue();
				trenKosarica.promijeniKolicinu(artikl.getKey(), --kolicina);
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
		centerPanel.removeAll();
		JPanel restorani = new JPanel();
		restorani.setLayout(new BoxLayout(restorani, BoxLayout.PAGE_AXIS));
		restorani.setBackground(Color.WHITE);
		window.podLjuska.napuniSetRestorana();
		listaRestorani = window.podLjuska.getRestorani();
		
		for (Restoran restoran : listaRestorani) {
			JPanel filler1 = new JPanel();
			filler1.setMaximumSize(new Dimension(9000, 1));
			filler1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			restorani.add(filler1);
			
			JPanel restoranPanel = new JPanel();
			restoranPanel.setBorder(BorderFactory.createLineBorder(new Color(155, 226, 255), 2));
			restoranPanel.setMaximumSize(new Dimension(9000, 100));
			restoranPanel.setLayout(new BorderLayout());
			restoranPanel.add(new JLabel(new ImageIcon(restoran.getSlika())), BorderLayout.WEST);
			restoranPanel.add(new JTextArea(restoran.getOpis()), BorderLayout.CENTER);
			JButton naruci = new JButton("Naruci");
			
			ActionListener naruciListener = (actionEvent) -> {
				JDialog regPopUp = new JDialog();
				regPopUp.setTitle("Informacija");
				regPopUp.setLayout(new BorderLayout());
				
				ActionListener infoDialog = (actionEvent2) -> {
					regPopUp.dispatchEvent(new WindowEvent(regPopUp, WindowEvent.WINDOW_CLOSING));
				};
				
				JPanel buttonPanel = new JPanel();
				buttonPanel.setBackground(Color.white);
				buttonPanel.setLayout(new FlowLayout());
				JButton OK = new JButton("OK");
				OK.addActionListener(infoDialog);
				buttonPanel.add(OK);
				regPopUp.add(buttonPanel, BorderLayout.SOUTH);
				
				JPanel text = new JPanel();
				text.setBackground(Color.white);
				JTextArea area = new JTextArea();
				area.setText("\nMorate se prijavati kako\nbiste izvrsili tu akciju");
				area.setForeground(new Color(0, 153, 255));
				area.setEditable(false);
				text.add(area);
				regPopUp.add(text, BorderLayout.CENTER);
				regPopUp.setResizable(false);
				regPopUp.setSize(200, 130);
				regPopUp.setLocation(window.getX()+10, window.getY()+8);
				regPopUp.setModal(true);
				regPopUp.setVisible(true);
			};
			
			naruci.addActionListener(naruciListener);
			restoranPanel.add(new JButton("Naruci"), BorderLayout.EAST);
			restorani.add(restoranPanel);
			
			JPanel filler2 = new JPanel();
			filler2.setMaximumSize(new Dimension(9000, 1));
			filler2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			restorani.add(filler2);
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
		
		showScrollPane = new JScrollPane(restorani);
		showScrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		centerPanel.add(showScrollPane, BorderLayout.CENTER);
		revalidate();
		
	}
	
	private void urediPanel() {
		//JPanel urediRestoran = new JPanel();
		centerPanel.removeAll();
		centerPanel.setLayout(new BorderLayout());
				
		JPanel menuSadrzaj = new JPanel();
		menuSadrzaj.setLayout(new BoxLayout(menuSadrzaj, BoxLayout.PAGE_AXIS));
		menuSadrzaj.setBackground(Color.WHITE);
		
		dohvatiMenu(menuSadrzaj);
		
		JPanel opisPanel = new JPanel();
		opisPanel.setBackground(Color.white);
		JLabel opis = new JLabel();
		opis.setText("Trenutni sadrzaj menu-a vaseg restorana: ");
		opis.setForeground(new Color(0, 153, 255));
		opisPanel.add(opis);
		
		JLabel unosAdr1 = new JLabel("Naziv artikla: ");
		JTextField nazivField = new JTextField();
		nazivField.setColumns(14);
		JLabel unosAdr2 = new JLabel("Cijena artikla: ");
		JTextField cijenaField = new JTextField();
		cijenaField.setColumns(8);
		
		ActionListener dodajListener = (actionListener) -> {
			//Napraviti		
		};
		
		JPanel urediButtonPanel = new JPanel();
		urediButtonPanel.setBackground(Color.white);
		
		urediButtonPanel.add(unosAdr1);
		urediButtonPanel.add(nazivField);
		urediButtonPanel.add(unosAdr2);
		urediButtonPanel.add(cijenaField);
		
		urediButtonPanel.setLayout(new FlowLayout());
		JButton dodaj = new JButton("Dodaj artikl");
		dodaj.addActionListener(dodajListener);
		urediButtonPanel.add(dodaj);
		
		showScrollPane = new JScrollPane(menuSadrzaj);
		
		centerPanel.add(opisPanel, BorderLayout.NORTH);
		centerPanel.add(showScrollPane, BorderLayout.CENTER);
		centerPanel.add(urediButtonPanel, BorderLayout.SOUTH);
		
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.revalidate();
		revalidate();
		
	}

	private void dohvatiMenu(JPanel menuSadrzaj) {
		// TODO Auto-generated method stub
		
	}

}

