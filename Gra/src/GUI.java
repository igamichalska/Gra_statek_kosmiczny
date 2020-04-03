import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

	JPanel lewy, gorny;
	ImagePanel prawy;
	JLabel labelszybkosc, labelpaliwo;
	JButton startbutton;
	JTextField szybkosc;
	JSlider slider;
	JMenuBar menubar;
	JMenu autor;
	JMenuItem wyswietl;
	
	public GUI() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setSize(895,650);
				this.setTitle("Kosmiczne Podró¿e");
		lewy = new JPanel();
			lewy.setLayout(new BoxLayout(lewy, 3));
			
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
					lewy.add(slider);
		this.add(lewy, BorderLayout.LINE_START);
		prawy = new ImagePanel(
		        new ImageIcon("C:/Users/igami/git/Gra_statek_kosmiczny/pic.png").getImage());
				
		this.add(prawy, BorderLayout.CENTER);
			gorny = new JPanel();
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setMinorTickSpacing(5);
			slider.setMajorTickSpacing(10);
			slider.setMaximumSize(new Dimension(150,200));
			slider.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			lewy.add(slider);
			this.add(lewy, BorderLayout.LINE_START);
			
			menubar = new JMenuBar();
			autor = new JMenu ("Autor");
			wyswietl = new JMenuItem ("Wyœwietl informacje");
			wyswietl.addActionListener(autorListener);
			autor.add(wyswietl);
			menubar.add(autor);
			this.setJMenuBar(menubar);
			this.pack();
			this.setResizable(false);
	}
	ActionListener autorListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Iga Michalska, Jan £oziñski");
		}
	};
	class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		  public ImagePanel(Image img) {
		    this.img = img;
		    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  }
	}
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

	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}



	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
