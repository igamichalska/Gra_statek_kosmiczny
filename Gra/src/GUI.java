import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class GUI extends JFrame {

	JPanel mainPanel;
	ImagePanel central;
	GameEngine engine;
	JButton startButton;
	JMenuBar menuBar;
	JMenu author;
	JMenuItem show;
	
	public GUI(String pic) throws HeadlessException {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Kosmiczne Podró¿e");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

			mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
			
			central = new ImagePanel(new ImageIcon(pic).getImage());
			central.setAlignmentX(Component.CENTER_ALIGNMENT);
			mainPanel.add(central);
			
			startButton = new JButton("NOWA GRA");
		    startButton.setMaximumSize(new Dimension(150,100));
		    startButton.addActionListener(new ActionListener() {
		    	@Override
		    	public void actionPerformed(ActionEvent arg0) {
		    		StartWindow okno = new StartWindow();
		    		okno.setLocationRelativeTo(null);
		    		okno.setVisible(true);
		    		dispose();
		    	}
		    });
		    
		    mainPanel.add(Box.createRigidArea(new Dimension(20,20)));
		    
		    startButton.setMinimumSize(new Dimension(300,100));
		    startButton.setPreferredSize(new Dimension(300,100));
		    startButton.setMaximumSize(new Dimension(300,100));
			startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			mainPanel.add(startButton);
			
		this.add(mainPanel);
		
			menuBar = new JMenuBar();
			author = new JMenu ("Autor");
			show = new JMenuItem ("Wyœwietl informacje");
			show.addActionListener(authorListener);
			author.add(show);
			menuBar.add(author);
			
		this.setJMenuBar(menuBar);
		this.pack();
	}
	
	class ImagePanel extends JPanel {

		  private Image img;

		  public ImagePanel(String img) {
		    this(new ImageIcon(img).getImage());
		  }

		  public ImagePanel(Image img) {
			  
		    this.img = img;
		    Dimension size = new Dimension(755, 560);
		    
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
	
	ActionListener authorListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "Iga Michalska, Jan £oziñski");
		}
	};

	public static void main(String[] args) {
		
			SwingUtilities.invokeLater(
				 new Runnable(){
					public void run() {
						GUI frame = new GUI("pic.png");
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
					}
				 }
			);
		
	}


}
