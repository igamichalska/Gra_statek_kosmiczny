import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class GUI extends JFrame implements ActionListener {

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
			this.setSize(600,480);
				this.setTitle("Kosmiczne Podró¿e");
		lewy = new JPanel();
			lewy.setLayout(new BoxLayout(lewy, 3));
			startbutton = new JButton("START");
				startbutton.setPreferredSize(new Dimension(40, 100));
			//add ActionListener ktory bedzie wyrzucal okienko na srodek
				lewy.add(startbutton);
		labelszybkosc = new JLabel("Szybkoœæ:");
				lewy.add(labelszybkosc);
			szybkosc = new JTextField();
				lewy.add(szybkosc);
		labelpaliwo = new JLabel("Poziom paliwa:");
				lewy.add(labelpaliwo);
			slider = new JSlider(JSlider.VERTICAL, 0, 100, 20);
				slider.setPaintTicks(true);
				slider.setPaintLabels(true);
				slider.setMinorTickSpacing(5);
				slider.setMajorTickSpacing(10);
					lewy.add(slider);
		this.add(lewy, BorderLayout.LINE_START);
			prawy = new JPanel();
				prawy.setBackground(Color.white); //na razie ustawiam bialy, ale trzeba pomyslec
				//moze jakas grafike wstawimy tu?
				//tez trzeba ogarnac jak kolor statku bd zmieniac (fajnie by bylo, jakby sie zmienial na tej grafice tez)
				//albo wstawic grafike i na tej grafice wrzucic jakos zrobiony wielok¹t? XD
		this.add(prawy, BorderLayout.CENTER);
			gorny = new JPanel();
			menubar = new JMenuBar();
			autor = new JMenu ("Autor");
			wyswietl = new JMenuItem ("Wyœwietl informacje");
				wyswietl.addActionListener(autorListener);
					autor.add(wyswietl);
					menubar.add(autor);
		this.setJMenuBar(menubar);
		this.add(gorny, BorderLayout.PAGE_START);
	}
	ActionListener autorListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Iga Michalska, Jan £oziñski");
			
		}
	};

	public GUI(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public GUI(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public GUI(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

}
