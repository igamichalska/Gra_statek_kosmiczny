import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartWindow extends JFrame {

	CPanel level, startPlanetPanel, endPlanetPanel, colorPanel;
	JPanel central;
	JButton start;
	GameEngine engine;
	GameFrame frame;
	static String cmd;
	static int lvl = 0, startPlanetInd, endPlanetInd;
	
	public StartWindow() throws HeadlessException {
		this.setTitle("Kosmiczne Podró¿e");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		central = new JPanel();
		central.setPreferredSize(new Dimension(400,300));
		central.setLayout(new BoxLayout(central, BoxLayout.PAGE_AXIS));
		
		String[] levels = {"Latwy", "Sredni", "Trudny", "Kosmicznie trudny"};
		String[] planets = { "Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran"};
		String[] colors = {"Czerwony", "Niebieski", "Szary"};
		
		JComboBox<String> lvlBox = new JComboBox<String>(levels);
		lvlBox.setSelectedIndex(0);
		JComboBox<String> startPlanetBox = new JComboBox<String>(planets);
		startPlanetBox.setSelectedIndex(0);
		JComboBox<String> endPlanetBox = new JComboBox<String>(planets);
		endPlanetBox.setSelectedIndex(0);
		JComboBox<String> colorBox = new JComboBox<String>(colors);
		colorBox.setSelectedIndex(0);
		
		level = new CPanel();
		level.add(new JLabel("Poziom trudnosci: "));
		level.add(Box.createHorizontalGlue());
		level.add(lvlBox);
		
		startPlanetPanel = new CPanel();
		startPlanetPanel.add(new JLabel("Planeta startowa:"));
		startPlanetPanel.add(Box.createHorizontalGlue());
		startPlanetPanel.add(startPlanetBox);
		
		endPlanetPanel = new CPanel();
		endPlanetPanel.add(new JLabel("Planeta docelowa:"));
		endPlanetPanel.add(Box.createHorizontalGlue());
		endPlanetPanel.add(endPlanetBox);
		
		colorPanel = new CPanel();
		colorPanel.add(new JLabel("Kolor statku: "));
		colorPanel.add(Box.createHorizontalGlue());
		
		
		colorPanel.add(colorBox);
		
		start = new JButton("ROZPOCZNIJ GRE");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				lvl = lvlBox.getSelectedIndex();
				cmd = (String) colorBox.getSelectedItem();
				startPlanetInd = startPlanetBox.getSelectedIndex();
				endPlanetInd = endPlanetBox.getSelectedIndex();
				engine = new GameEngine();
				engine.start();
				
				dispose();
				
			}
		});
		
		central.add(level); central.add(startPlanetPanel); central.add(endPlanetPanel); central.add(colorPanel); 
		central.add(Box.createRigidArea(new Dimension(0,20)));
		central.add(start);
		this.add(central);
		this.pack();
	}
	
	private class CPanel extends JPanel {
		public CPanel() 
	    {
			super();
			setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
			setMaximumSize(new Dimension(500,50));
			setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			setAlignmentX(Component.CENTER_ALIGNMENT);
	    }
	}
	
}
