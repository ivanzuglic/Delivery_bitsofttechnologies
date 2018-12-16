package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javafx.scene.control.SplitPane;

public class KorisnikPanelAlt extends JPanel{
	
	private JPanel buttonsPanel;
	private JPanel showPanel;
	private ActionListener prijaviSeListener;
	private ActionListener registrirajSeListener;
	
	public KorisnikPanelAlt() {
		
		prijaviSeListener = (actionEvent) -> {
			prijaviSeWindow();
		};
		
		registrirajSeListener = (actionEvent) -> {
			registrirajSeWindow();
		};
		
		setLayout(new BorderLayout());
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		JButton PrijaviSe = new JButton("    Prijavi se    ");
		PrijaviSe.addActionListener(prijaviSeListener);
		JButton RegistrirajSe = new JButton("Registriraj se");
		RegistrirajSe.addActionListener(registrirajSeListener);
		buttonsPanel.add(PrijaviSe);
		buttonsPanel.add(RegistrirajSe);
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(buttonsPanel, BorderLayout.EAST);
		
		showPanel = new JPanel();
		showPanel.setLayout(new FlowLayout());
		puniShowPanel(); //Funkcija za punjenje panela sa restoranima
		showPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		add(showPanel, BorderLayout.CENTER);
	}

	private void registrirajSeWindow() {
		JDialog Registracija = new JDialog();
		Registracija.setLayout(new BorderLayout());
		JPanel podaci = new JPanel();
		podaci.setLayout(new FlowLayout());
		
		JPanel korImePanel = new JPanel();
		korImePanel.setLayout(new FlowLayout());
		korImePanel.add(new JLabel("     Korisnicko ime: "));
		JTextField korImeField = new JTextField();
		korImeField.setColumns(15);
		korImePanel.add(korImeField);
		podaci.add(korImePanel);
		
		JPanel lozinkaPanel = new JPanel();
		lozinkaPanel.setLayout(new FlowLayout());
		lozinkaPanel.add(new JLabel("                 Lozinka : "));
		JTextField lozinkaField = new JTextField();
		lozinkaField.setColumns(15);
		lozinkaPanel.add(lozinkaField);
		podaci.add(lozinkaPanel);
		
		JPanel potvrLozinkaPanel = new JPanel();
		potvrLozinkaPanel.setLayout(new FlowLayout());
		potvrLozinkaPanel.add(new JLabel("Potvrdite lozinku : "));
		JTextField potvrdLozinkaField = new JTextField();
		potvrdLozinkaField.setColumns(15);
		potvrLozinkaPanel.add(potvrdLozinkaField);
		podaci.add(potvrLozinkaPanel);
		
		JPanel imePanel = new JPanel();
		imePanel.setLayout(new FlowLayout());
		imePanel.add(new JLabel("                         Ime: "));
		JTextField imeField = new JTextField();
		imeField.setColumns(15);
		imePanel.add(imeField);
		podaci.add(imePanel);
		
		JPanel prezPanel = new JPanel();
		prezPanel.setLayout(new FlowLayout());
		prezPanel.add(new JLabel("                Prezime: "));
		JTextField prezField = new JTextField();
		prezField.setColumns(15);
		prezPanel.add(prezField);
		podaci.add(prezPanel);
		
		JPanel dobPanel = new JPanel();
		dobPanel.setLayout(new FlowLayout());
		dobPanel.add(new JLabel("                        Dob: "));
		JTextField dobField = new JTextField();
		dobField.setColumns(15);
		dobPanel.add(dobField);
		podaci.add(dobPanel);
		
		JPanel mailPanel = new JPanel();
		mailPanel.setLayout(new FlowLayout());
		mailPanel.add(new JLabel("     E-Mail adresa: "));
		JTextField mailField = new JTextField();
		mailField.setColumns(15);
		mailPanel.add(mailField);
		podaci.add(mailPanel);
		
		ActionListener RegistracijaDialog = (actionEvent) -> {
			registracijaProvjera(korImeField.getText(), lozinkaField.getText(), potvrdLozinkaField.getText(), imeField.getText(), prezField.getText(), dobField.getText(), mailField.getText()); //Metoda koja provjerava da li Korisnicko ime i Lozinka definiraju nekog korisnika u bazu podataka
		};
		JButton OK = new JButton("Registriraj se");
		OK.addActionListener(RegistracijaDialog);
		
		ActionListener OdustaniDialog = (actionEvent) -> {
			Registracija.dispatchEvent(new WindowEvent(Registracija, WindowEvent.WINDOW_CLOSING));
		};
		JButton NOK = new JButton("Odustani");
		NOK.addActionListener(OdustaniDialog);
		
		JPanel DialogButtons = new JPanel();
		DialogButtons.setLayout(new FlowLayout());
		DialogButtons.add(NOK);
		DialogButtons.add(OK);
		Registracija.add(DialogButtons, BorderLayout.SOUTH);
		
		Registracija.add(podaci, BorderLayout.CENTER);
		Registracija.setTitle("Registracija");
		Registracija.setResizable(false);
		Registracija.setSize(320, 320);
		Registracija.setModal(true);
		Registracija.setVisible(true);
		
	}

	private void registracijaProvjera(String text, String text2, String text3, String text4, String text5, String text6,
			String text7) {
		// TODO Auto-generated method stub
		
	}

	private void prijaviSeWindow() {
		JDialog Prijava = new JDialog();
		Prijava.setLayout(new BorderLayout());
		JPanel imeLozinka = new JPanel();
		imeLozinka.setLayout(new FlowLayout());
		
		JPanel ime = new JPanel();
		ime.setLayout(new FlowLayout());
		ime.add(new JLabel("Korisnicko ime: "));
		JTextField imeField = new JTextField();
		imeField.setColumns(15);
		ime.add(imeField);
		imeLozinka.add(ime);
		
		JPanel lozinka = new JPanel();
		lozinka.setLayout(new FlowLayout());
		lozinka.add(new JLabel("           Laozinka: "));
		JTextField lozinkaField = new JTextField();
		lozinkaField.setColumns(15);
		lozinka.add(lozinkaField);
		imeLozinka.add(lozinka);
		
		ActionListener PrijavaDialog = (actionEvent) -> {
			prijaviSeProvjera(imeField.getText(), lozinkaField.getText()); //Metoda koja provjerava da li Korisnicko ime i Lozinka definiraju nekog korisnika u bazu podataka
		};
		JButton OK = new JButton("Prijavi se");
		OK.addActionListener(PrijavaDialog);
		
		ActionListener OdustaniDialog = (actionEvent) -> {
			Prijava.dispatchEvent(new WindowEvent(Prijava, WindowEvent.WINDOW_CLOSING));
		};
		JButton NOK = new JButton("Odustani");
		NOK.addActionListener(OdustaniDialog);
		
		JPanel DialogButtons = new JPanel();
		DialogButtons.setLayout(new FlowLayout());
		DialogButtons.add(NOK);
		DialogButtons.add(OK);
		Prijava.add(DialogButtons, BorderLayout.SOUTH);
		
		Prijava.add(imeLozinka, BorderLayout.CENTER);
		Prijava.setTitle("Prijava");
		Prijava.setResizable(false);
		Prijava.setSize(320, 140);
		Prijava.setModal(true);
		Prijava.setVisible(true);
	}

	private void prijaviSeProvjera(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	private void puniShowPanel() {
		// TODO Auto-generated method stub
		
	}

}