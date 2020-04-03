import java.awt.BorderLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class GUI extends JFrame {

	JPanel lewy, prawy, gorny;
	JLabel labelszybkosc, labelpaliwo;
	JButton startbutton;
	JTextField szybkosc;
	JSlider slider;
	JMenuBar menubar;
	JMenu autor;
	JMenuItem wyswietl;
	
	public GUI() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			this.setTitle("Kosmiczne Podró¿e");
			lewy = new JPanel();
			lewy.setLayout(new BoxLayout(lewy, BoxLayout.PAGE_AXIS));
			lewy.setPreferredSize(new Dimension(200,600));
			lewy.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			
			startbutton = new JButton("START");
		    startbutton.setMaximumSize(new Dimension(150,100));
		    startbutton.addActionListener(new ActionListener() {
		    	@Override
		    	public void actionPerformed(ActionEvent arg0) {
		    		StartWindow okno = new StartWindow();
		    		okno.setVisible(true);
		    	}
		    });
			//add ActionListener ktory bedzie wyrzucal okienko na srodek
			startbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
			lewy.add(startbutton);
			
			lewy.add(Box.createRigidArea(new Dimension(0,150)));
			
			labelszybkosc = new JLabel("Szybkoœæ:");
			labelszybkosc.setAlignmentX(Component.CENTER_ALIGNMENT);
			lewy.add(labelszybkosc);
			
			szybkosc = new JTextField();
			szybkosc.setMaximumSize(new Dimension(100,40));
			szybkosc.setAlignmentX(Component.CENTER_ALIGNMENT);
			lewy.add(szybkosc);
			lewy.add(Box.createRigidArea(new Dimension(0,20)));
			
			labelpaliwo = new JLabel("Poziom paliwa:");
			labelpaliwo.setAlignmentX(Component.CENTER_ALIGNMENT);
			lewy.add(labelpaliwo);
			
			slider = new JSlider(JSlider.VERTICAL, 0, 100, 20);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setMinorTickSpacing(5);
			slider.setMajorTickSpacing(10);
			slider.setMaximumSize(new Dimension(150,200));
			slider.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			lewy.add(slider);
			this.add(lewy, BorderLayout.LINE_START);
			
			prawy = new JPanel();
			prawy.setPreferredSize(new Dimension(800,600));
			prawy.setBackground(Color.white);
			//prawy.setBorder(BorderFactory.createEmptyBorder(20,20,80,80));
			//moze jakas grafike wstawimy tu?
			this.add(prawy, BorderLayout.CENTER);
			
			//gorny = new JPanel();
			menubar = new JMenuBar();
			autor = new JMenu ("Autor");
			wyswietl = new JMenuItem ("Wyœwietl informacje");
			wyswietl.addActionListener(autorListener);
			autor.add(wyswietl);
			menubar.add(autor);
			this.setJMenuBar(menubar);
			//this.add(gorny, BorderLayout.PAGE_START);
			this.pack();
			this.setResizable(false);
	}
	ActionListener autorListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Iga Michalska, Jan £oziñski");
		}
	};

	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
