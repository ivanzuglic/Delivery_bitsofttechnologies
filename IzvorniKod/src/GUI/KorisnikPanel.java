package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import javafx.scene.control.SplitPane;

public class KorisnikPanel extends JPanel{
	private JSplitPane mainPanel;
	private JPanel buttonsPanel;
	private JPanel showPanel;
	
	public KorisnikPanel() {
		setLayout(new BorderLayout());
		mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		add(mainPanel, BorderLayout.CENTER);
		
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(0, 1));
		mainPanel.setRightComponent(buttonsPanel);
		showPanel = new JPanel();
		mainPanel.setLeftComponent(showPanel);
		JButton Button1 = new JButton("Prvi");
		buttonsPanel.add(Button1);
		//mainPanel.setDividerLocation(400);
		mainPanel.setResizeWeight(0.90);
		
	}

}
