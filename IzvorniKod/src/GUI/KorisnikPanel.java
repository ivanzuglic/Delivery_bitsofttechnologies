package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SpringLayout;
import javax.swing.Timer;

import DataStructure.Artikl;
import DataStructure.Klijent;
import DataStructure.Korisnik;
import DataStructure.Kosarica;
import DataStructure.Restoran;
import jdk.nashorn.internal.ir.BlockLexicalContext;

/**
 * Razred koji definira izgled i funkcije panela za Korisnika
 * @author tonis
 *
 */

public class KorisnikPanel extends JPanel {
	private JPanel buttonsPanel;
	private JScrollPane showScrollPane;
	private JPanel logoPanel;
	private JPanel northPanel;
	private JPanel usrInfoPanel;
	private JPanel centerPanel;
	private ActionListener listaListener;
	private ActionListener prijaviSeListener;
	private ActionListener registrirajSeListener;
	private DefaultWindow window;
	private Set<Restoran> listaRestorani;
	
	
	/**
	 * Defaultni konstruktor, konstruktor mora primiti referencu na instancu DefaultWindow radi promjene panela
	 * @param window
	 */
	public KorisnikPanel(DefaultWindow window) {
		this.window = window;
		this.showScrollPane = new JScrollPane();
		setLayout(new BorderLayout());
		
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		centerPanel.setBackground(Color.white);
		add(centerPanel, BorderLayout.CENTER);
		
		//Definicija Listenera za gumbove za Prijavu i Registraciju
		prijaviSeListener = (actionEvent) -> {
			prijaviSeWindow();
		};
		registrirajSeListener = (actionEvent) -> {
			registrirajSeWindow();
		};
		listaListener = (actionEvent) -> {
			showPanelfill();
		};
		
		//Definicija gumba za listu restorana
		JButton listaRestorana = new JButton();
		listaRestorana.setMaximumSize(new Dimension(120, 120));
		listaRestorana.addActionListener(listaListener);
		ImageIcon listaImg = new ImageIcon(getClass().getResource("/images/listaMini.png"));
		listaRestorana.setIcon(listaImg);
		listaRestorana.setBackground(Color.white);
		
		//Definicija gumba PrijaviSe
		JButton PrijaviSe = new JButton("    Prijavi se    ");
		PrijaviSe.setMaximumSize(new Dimension(120, 120));
		PrijaviSe.addActionListener(prijaviSeListener);
		ImageIcon image1 = new ImageIcon(getClass().getResource("/images/PrijaviSeLogoMini.png"));
		PrijaviSe.setIcon(image1);
		
		//Definicija gumba RegistrirajSe
		JButton RegistrirajSe = new JButton("Registriraj se");
		RegistrirajSe.addActionListener(registrirajSeListener);
		RegistrirajSe.setMaximumSize(new Dimension (120, 120));
		ImageIcon image2  = new ImageIcon(getClass().getResource("/images/RegistrirajSELogoMini.png"));
		RegistrirajSe.setIcon(image2);
		
		//Definicija panela sa gumbovima
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.add(listaRestorana);
		buttonsPanel.add(PrijaviSe);
		buttonsPanel.add(RegistrirajSe);
		buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		buttonsPanel.setBackground(Color.white);
		buttonsPanel.setPreferredSize(new Dimension(120, 500));
		add(buttonsPanel, BorderLayout.EAST);
		
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
		usrName.setText("\n Prijavljen kao:\n Anonimni korisnik");
		usrInfoPanel.add(usrName, BorderLayout.CENTER);
		usrInfoPanel.setPreferredSize(new Dimension(120, 75));
		usrInfoPanel.setBackground(Color.white);
		usrInfoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		northPanel.add(usrInfoPanel, BorderLayout.EAST);
		
		showPanelfill();
		
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
				restoranPanel.add(new JLabel(slikaRestoran), BorderLayout.WEST);
			} catch (Exception e) {
				ImageIcon slikaRestoran = new ImageIcon(getClass().getResource("/images/DefaultRestoranMini.png"));
				restoranPanel.add(new JLabel(slikaRestoran), BorderLayout.WEST);
			}
			restoranPanel.add(new JTextArea("Naziv: " + restoran.getIme() + "\nOpis: " + restoran.getOpis()), BorderLayout.CENTER);
			JButton naruci = new JButton("Pregledaj");
			
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
						
						JOptionPane.showMessageDialog(window, "Morate se prijaviti kako bi izvr?ili ovu akciju", "Obavijest", 1);
						
//						JDialog regPopUp = new JDialog();
//						regPopUp.setTitle("Informacija");
//						regPopUp.setLayout(new BorderLayout());
//						
//						ActionListener infoDialog = (actionEvent3) -> {
//							regPopUp.dispatchEvent(new WindowEvent(regPopUp, WindowEvent.WINDOW_CLOSING));
//						};
//						
//						JPanel buttonPanel = new JPanel();
//						buttonPanel.setBackground(Color.white);
//						buttonPanel.setLayout(new FlowLayout());
//						JButton OK = new JButton("OK");
//						OK.addActionListener(infoDialog);
//						buttonPanel.add(OK);
//						regPopUp.add(buttonPanel, BorderLayout.SOUTH);
//						
//						JPanel text = new JPanel();
//						text.setBackground(Color.white);
//						JTextArea area = new JTextArea();
//						area.setText("\nMorate se prijavati kako\nbiste izvrsili tu akciju");
//						area.setForeground(new Color(0, 153, 255));
//						area.setEditable(false);
//						text.add(area);
//						regPopUp.add(text, BorderLayout.CENTER);
//						regPopUp.setResizable(false);
//						regPopUp.setSize(200, 130);
//						regPopUp.setLocation(window.getX()+10, window.getY()+8);
//						regPopUp.setModal(true);
//						regPopUp.setVisible(true);	
						
						
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

	private void registrirajSeWindow() {
		//Kreiranje dialog prozora
		JDialog Registracija = new JDialog();
		Registracija.setLayout(new BorderLayout());
		
		//Kreiranje sredisnjeg panela
		JPanel podaci = new JPanel();
		podaci.setLayout(new FlowLayout());
		podaci.setBackground(Color.white);
		
		//Definicija panela za korisnicko ime
		JPanel korImePanel = new JPanel();
		korImePanel.setBackground(Color.white);
		korImePanel.setLayout(new FlowLayout());
		korImePanel.add(new JLabel("     Korisnicko ime: "));
		JTextField korImeField = new JTextField();
		korImeField.setColumns(15);
		korImePanel.add(korImeField);
		podaci.add(korImePanel);
		
		//Definicija panela za lozinku
		JPanel lozinkaPanel = new JPanel();
		lozinkaPanel.setBackground(Color.white);
		lozinkaPanel.setLayout(new FlowLayout());
		lozinkaPanel.add(new JLabel("                 Lozinka : "));
		JPasswordField lozinkaField = new JPasswordField();
		lozinkaField.setColumns(15);
		lozinkaPanel.add(lozinkaField);
		podaci.add(lozinkaPanel);
		
		//Definicija panela za potvrdu lozinke
		JPanel potvrLozinkaPanel = new JPanel();
		potvrLozinkaPanel.setBackground(Color.white);
		potvrLozinkaPanel.setLayout(new FlowLayout());
		potvrLozinkaPanel.add(new JLabel("Potvrdite lozinku : "));
		JPasswordField potvrdLozinkaField = new JPasswordField();
		potvrdLozinkaField.setColumns(15);
		potvrLozinkaPanel.add(potvrdLozinkaField);
		podaci.add(potvrLozinkaPanel);
		
		//Definicija panela za ime korisnika
		JPanel imePanel = new JPanel();
		imePanel.setBackground(Color.white);
		imePanel.setLayout(new FlowLayout());
		imePanel.add(new JLabel("                         Ime: "));
		JTextField imeField = new JTextField();
		imeField.setColumns(15);
		imePanel.add(imeField);
		podaci.add(imePanel);
		
		//Definicija panela za prezime korisnkia
		JPanel prezPanel = new JPanel();
		prezPanel.setBackground(Color.white);
		prezPanel.setLayout(new FlowLayout());
		prezPanel.add(new JLabel("                Prezime: "));
		JTextField prezField = new JTextField();
		prezField.setColumns(15);
		prezPanel.add(prezField);
		podaci.add(prezPanel);
		
		//Definicija panela za dob korisnka
		JPanel brMobPanel = new JPanel();
		brMobPanel.setBackground(Color.white);
		brMobPanel.setLayout(new FlowLayout());
		brMobPanel.add(new JLabel("      Broj mobitela: "));
		JTextField brMobField = new JTextField();
		brMobField.setColumns(15);
		brMobPanel.add(brMobField);
		podaci.add(brMobPanel);
		
		//Definicija panela za unos email adrese
		JPanel mailPanel = new JPanel();
		mailPanel.setBackground(Color.white);
		mailPanel.setLayout(new FlowLayout());
		mailPanel.add(new JLabel("     E-Mail adresa: "));
		JTextField mailField = new JTextField();
		mailField.setColumns(15);
		mailPanel.add(mailField);
		podaci.add(mailPanel);
		
		//Definicija juznog panela
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.white);
		southPanel.setLayout(new BorderLayout());
		
		//Definicjia panela za poruku koji ide u juzni panel
		JPanel poruka = new JPanel();
		poruka.setBackground(Color.white);
		poruka.setSize(lozinkaPanel.getWidth(), lozinkaPanel.getHeight());
		southPanel.add(poruka, BorderLayout.NORTH);
		
		//Definicjia funkcionalnosti gumba za registraciju
		ActionListener RegistracijaDialog = (actionEvent) -> {
			
			if (lozinkaField.getText().equals(potvrdLozinkaField.getText())) {
				if(registracijaProvjera(korImeField.getText(), lozinkaField.getText(), imeField.getText(), prezField.getText(), brMobField.getText(), mailField.getText())) {
					poruka.removeAll();
					poruka.add(new JLabel("<html><font color='green'>Uspijeh!</font></html>"));
					poruka.revalidate();
					
					// dodano isto kao i u prijavi - LM
					if (window.podLjuska.getZastavice().isAdministrator()) {
						window.switchToAdmin(window.podLjuska.getTrenutniAdministrator());
					}
					
					if (window.podLjuska.getZastavice().isDispecer()) {
						window.switchToDispecer(window.podLjuska.getTrenutniDispecer());
					}

					
					if (window.podLjuska.getZastavice().isDostavljac()) {
						window.switchToDostavljac(window.podLjuska.getTrenutniDostavljac());
					}

					
					if (window.podLjuska.getZastavice().isKlijent()) {
						window.switchToKlijent(window.podLjuska.getTrenutniKlijent());
					}

					
					if (window.podLjuska.getZastavice().isVlasnik()) {
						window.switchToVlasnik(window.podLjuska.getTrenutniVlasnik());
					}
					
					
					Timer timer = new Timer(500, new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	Registracija.dispatchEvent(new WindowEvent(Registracija, WindowEvent.WINDOW_CLOSING));
			            }
			        });
					timer.setRepeats(false);
				    timer.start();
				}
				
				else {
					poruka.removeAll();
					poruka.add(new JLabel("<html><font color='red'>Neispravni podaci</font></html>"));
					poruka.revalidate();
				}
			}
			
			else {
				
				poruka.removeAll();
				poruka.add(new JLabel("<html><font color='red'>Lozinka i potvrda lozinke se ne poklapaju</font></html>"));
				poruka.revalidate();
			}
			
		};
		JButton OK = new JButton("Registriraj se");
		OK.addActionListener(RegistracijaDialog);
		
		//Definicija funkcionalnsti gumba odustani
		ActionListener OdustaniDialog = (actionEvent) -> {
			Registracija.dispatchEvent(new WindowEvent(Registracija, WindowEvent.WINDOW_CLOSING));
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
		Registracija.add(southPanel, BorderLayout.SOUTH);
		Registracija.add(podaci, BorderLayout.CENTER);
		Registracija.setTitle("Registracija");
		Registracija.setResizable(false);
		Registracija.setSize(320, 340);
		Registracija.setLocation(window.getX()+10, window.getY()+8);
		Registracija.setModal(true);
		Registracija.setVisible(true);
		
	}

	private boolean registracijaProvjera(String korisnickoIme, String lozinka, String ime, String prezime, String brMob, String mail) {
		return window.podLjuska.registracija(korisnickoIme, lozinka, ime, prezime, brMob, mail);
		
	}

	private void prijaviSeWindow() {
		//Kreiranje dialog prozora
		JDialog Prijava = new JDialog();
		Prijava.setLayout(new BorderLayout());
		
		//Kreiranje sredisnjeg panela
		JPanel imeLozinka = new JPanel();
		imeLozinka.setLayout(new FlowLayout());
		imeLozinka.setBackground(Color.white);
		
		//Definicija panela za korisnicko ime
		JPanel ime = new JPanel();
		ime.setBackground(Color.white);
		ime.setLayout(new FlowLayout());
		ime.add(new JLabel("Korisnicko ime: "));
		JTextField imeField = new JTextField();
		imeField.setColumns(15);
		ime.add(imeField);
		imeLozinka.add(ime);
		
		//Definicija panela za lozinku
		JPanel lozinka = new JPanel();
		lozinka.setBackground(Color.white);
		lozinka.setLayout(new FlowLayout());
		lozinka.add(new JLabel("           Laozinka: "));
		JPasswordField lozinkaField = new JPasswordField();
		lozinkaField.setColumns(15);
		lozinka.add(lozinkaField);
		imeLozinka.add(lozinka);
		
		//Definicija juznog panela
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.white);
		southPanel.setLayout(new BorderLayout());
		
		//Definicija panela za poruku koji ide u juzni panel
		JPanel poruka = new JPanel();
		poruka.setBackground(Color.white);
		poruka.setSize(lozinka.getWidth(), lozinka.getHeight());
		southPanel.add(poruka, BorderLayout.NORTH);
		
		//Defincija funkcionalnosti gumba za prijavu
		ActionListener PrijavaDialog = (actionEvent) -> {
			if(prijaviSeProvjera(imeField.getText(), lozinkaField.getText())) {
				poruka.removeAll();
				poruka.add(new JLabel("<html><font color='green'>Uspijeh!</font></html>"));
				poruka.revalidate();

				//window.switchToKlijent(getUsr(imeField.getText(), lozinkaField.getText()));
				//window.switchToKlijent(window.podLjuska.getTrenutniKlijent());

				
				if (window.podLjuska.getZastavice().isAdministrator()) {
					window.switchToAdmin(window.podLjuska.getTrenutniAdministrator());
				}
				
				if (window.podLjuska.getZastavice().isDispecer()) {
					window.switchToDispecer(window.podLjuska.getTrenutniDispecer());
				}

				
				if (window.podLjuska.getZastavice().isDostavljac()) {
					window.switchToDostavljac(window.podLjuska.getTrenutniDostavljac());
				}

				
				if (window.podLjuska.getZastavice().isKlijent()) {
					window.switchToKlijent(window.podLjuska.getTrenutniKlijent());
				}

				
				if (window.podLjuska.getZastavice().isVlasnik()) {
					window.switchToVlasnik(window.podLjuska.getTrenutniVlasnik());
				}

				Timer timer = new Timer(500, new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	Prijava.dispatchEvent(new WindowEvent(Prijava, WindowEvent.WINDOW_CLOSING));
		            }
		        });
				timer.setRepeats(false);
			    timer.start();
			}
			
			else {
				poruka.removeAll();
				poruka.add(new JLabel("<html><font color='red'>Neispravni podaci</font></html>"));
				poruka.revalidate();
			}
		};
		JButton OK = new JButton("Prijavi se");
		OK.addActionListener(PrijavaDialog);
		
		//Definicija funkcionalnosti gumba odustani
		ActionListener OdustaniDialog = (actionEvent) -> {
			Prijava.dispatchEvent(new WindowEvent(Prijava, WindowEvent.WINDOW_CLOSING));
		};
		JButton NOK = new JButton("Odustani");
		NOK.addActionListener(OdustaniDialog);
		
		//Definicja panela s gumbovima koji ulazi u juzni panel
		JPanel DialogButtons = new JPanel();
		DialogButtons.setBackground(Color.white);
		DialogButtons.setLayout(new FlowLayout());
		DialogButtons.add(OK);
		DialogButtons.add(NOK);
		southPanel.add(DialogButtons, BorderLayout.SOUTH);
		Prijava.add(southPanel, BorderLayout.SOUTH);
		
		//Krajnji modifikatori za dialog prozora
		Prijava.add(imeLozinka, BorderLayout.CENTER);
		Prijava.setTitle("Prijava");
		Prijava.setResizable(false);
		Prijava.setSize(320, 170);
		Prijava.setLocation(window.getX()+10, window.getY()+8);
		Prijava.setModal(true);
		Prijava.setVisible(true);
	}

	private boolean prijaviSeProvjera(String korisnickoIme, String lozinka) {
		return window.podLjuska.prijava(korisnickoIme, lozinka);
		
	}

	private void puniShowPanel() {
		// TODO Auto-generated method stub
		
	}

}