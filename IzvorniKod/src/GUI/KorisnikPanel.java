package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.Timer;

/**
 * Razred koji definira izgled i funkcije panela za Korisnika
 * @author tonis
 *
 */

public class KorisnikPanel extends JPanel {
	private JPanel buttonsPanel;
	private JPanel showPanel;
	private JPanel logoPanel;
	private ActionListener prijaviSeListener;
	private ActionListener registrirajSeListener;
	private DefaultWindow window;
	
	
	/**
	 * Defaultni konstruktor, konstruktor mora primiti referencu na instancu DefaultWindow radi promjene panela
	 * @param window
	 */
	public KorisnikPanel(DefaultWindow window) {
		this.window = window;
		setLayout(new BorderLayout());
		
		//Definicija Listenera za gumbove za Prijavu i Registraciju
		prijaviSeListener = (actionEvent) -> {
			prijaviSeWindow();
		};
		registrirajSeListener = (actionEvent) -> {
			registrirajSeWindow();
		};
		
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
		buttonsPanel.add(PrijaviSe);
		buttonsPanel.add(RegistrirajSe);
		buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255), 2));
		buttonsPanel.setBackground(Color.white);
		buttonsPanel.setPreferredSize(new Dimension(120, 500));
		add(buttonsPanel, BorderLayout.EAST);
		
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
		JTextField lozinkaField = new JTextField();
		lozinkaField.setColumns(15);
		lozinkaPanel.add(lozinkaField);
		podaci.add(lozinkaPanel);
		
		//Definicija panela za potvrdu lozinke
		JPanel potvrLozinkaPanel = new JPanel();
		potvrLozinkaPanel.setBackground(Color.white);
		potvrLozinkaPanel.setLayout(new FlowLayout());
		potvrLozinkaPanel.add(new JLabel("Potvrdite lozinku : "));
		JTextField potvrdLozinkaField = new JTextField();
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
		JPanel dobPanel = new JPanel();
		dobPanel.setBackground(Color.white);
		dobPanel.setLayout(new FlowLayout());
		dobPanel.add(new JLabel("                        Dob: "));
		JTextField dobField = new JTextField();
		dobField.setColumns(15);
		dobPanel.add(dobField);
		podaci.add(dobPanel);
		
		//Definicija panela za unos email adrses
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
			if(registracijaProvjera(korImeField.getText(), lozinkaField.getText(), potvrdLozinkaField.getText(), imeField.getText(), prezField.getText(), dobField.getText(), mailField.getText())) {
				poruka.removeAll();
				poruka.add(new JLabel("<html><font color='green'>Uspijeh!</font></html>"));
				poruka.revalidate();
				window.switchToKlijent();
				Timer timer = new Timer(1500, new ActionListener() {
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
		DialogButtons.add(NOK);
		DialogButtons.add(OK);
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

	private boolean registracijaProvjera(String text, String text2, String text3, String text4, String text5, String text6,
			String text7) {
		return false; //Placeholder
		// TODO Auto-generated method stub
		
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
		JTextField lozinkaField = new JTextField();
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
				window.switchToKlijent();
				Timer timer = new Timer(1500, new ActionListener() {
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
		DialogButtons.add(NOK);
		DialogButtons.add(OK);
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

	private boolean prijaviSeProvjera(String string, String string2) {
		return true; //Placeholder
		// TODO Auto-generated method stub
		
	}

	private void puniShowPanel() {
		// TODO Auto-generated method stub
		
	}

}