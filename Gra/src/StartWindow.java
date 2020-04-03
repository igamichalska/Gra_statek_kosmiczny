import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartWindow extends JFrame {

	CPanel pt, plS, plD, kol;
	JPanel glowny;
	JButton start;
	
	public StartWindow() throws HeadlessException {
		this.setTitle("Kosmiczne Podró¿e");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		glowny = new JPanel();
		glowny.setPreferredSize(new Dimension(400,300));
		glowny.setLayout(new BoxLayout(glowny, BoxLayout.PAGE_AXIS));
		
		String[] pozT = {"Latwy", "Sredni", "Trudny", "Kosmicznie trudny"};
		String[] planety = { "Merkury", "Wenus", "Ziemia", "Mars", "Jowisz", "Saturn", "Uran"};
		JComboBox wybpt = new JComboBox(pozT);
		//wybpt.setAlignmentY(Component.RIGHT_ALIGNMENT);
		JComboBox wybPlanetyS = new JComboBox(planety);
		//wybPlanetyS.setAlignmentY(Component.RIGHT_ALIGNMENT);
		JComboBox wybPlanetyD = new JComboBox(planety);
		//wybPlanetyD.setAlignmentY(Component.RIGHT_ALIGNMENT);
		
		pt = new CPanel();
		pt.add(new JLabel("Poziom trudnosci: "));
		pt.add(Box.createHorizontalGlue());
		pt.add(wybpt);
		
		plS = new CPanel();
		plS.add(new JLabel("Planeta startowa:"));
		plS.add(Box.createHorizontalGlue());
		plS.add(wybPlanetyS);
		
		plD = new CPanel();
		plD.add(new JLabel("Planeta docelowa:"));
		plD.add(Box.createHorizontalGlue());
		plD.add(wybPlanetyD);
		
		kol = new CPanel();
		kol.add(new JLabel("Kolor statku: "));
		JButton button = new JButton("Wybierz Kolor");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Kolor bêdzie zaimplementowany wewn¹trz programu
				Color c1 = JColorChooser.showDialog(null, "Choose Color", null);
				button.setBackground(c1);
			}
		});
		kol.add(Box.createHorizontalGlue());
		kol.add(button);
		
		start = new JButton("ROZPOCZNIJ GRE");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		glowny.add(pt); glowny.add(plS); glowny.add(plD); glowny.add(kol); 
		glowny.add(Box.createRigidArea(new Dimension(0,20)));
		glowny.add(start);
		this.add(glowny);
		this.pack();
	}
	
	public static void main(String[] args) {
		StartWindow frame = new StartWindow();
		frame.setVisible(true);
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
