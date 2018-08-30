import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import javax.swing.*;




public class MainMenu extends JPanel implements ActionListener {
	/**
	 * Main Screen of the Word Game
	 */
	private static final long serialVersionUID = 1L;
	public static String name;
	public static JFrame frame = new JFrame();
	private ImageIcon MainPic = new ImageIcon("images/anasayfa2.jpg");
	private Image MainImg = MainPic.getImage();
	private JButton play = new JButton("Play", new ImageIcon("images/play.png"));
	private JButton help = new JButton("Info",
			new ImageIcon("images/info2.png"));
	private JButton score = new JButton("High Score", new ImageIcon(
			"images/score.png"));
	private JButton quit = new JButton("Quit", new ImageIcon("images/mainquit.png"));



	public static String firstWord;
	public static String firstDef;
	
	static int hýz =0;
	static boolean tekrar=false;

	public MainMenu() {
		/**
		 *Buttons of Main Screen
		 */
		setLayout(null);
		play.setFocusPainted(false);
		score.setFocusPainted(false);
		help.setFocusPainted(false);
		quit.setFocusPainted(false);
		play.setHorizontalAlignment(SwingConstants.LEFT);
		score.setHorizontalAlignment(SwingConstants.LEFT);
		help.setHorizontalAlignment(SwingConstants.LEFT);
		quit.setHorizontalAlignment(SwingConstants.LEFT);
		
		play.setBounds(460, 85, 142, 50);
		play.setBackground(Color.WHITE);

		score.setBounds(460, 175, 142, 50);
		score.setBackground(Color.WHITE);

		help.setBounds(460, 265, 142, 50);
		help.setBackground(Color.WHITE);
		
		quit.setBounds(460, 355, 142, 50);
		quit.setBackground(Color.WHITE);
		
		add(play);
		add(score);
		add(help);
		add(quit);

		play.addActionListener(this);
		help.addActionListener(this); 
		score.addActionListener(this);
		quit.addActionListener(this);
	}

	
	public static void setScreen(JPanel screen) {
		frame.getContentPane().removeAll();
		frame.setContentPane(screen);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		frame.setResizable(false);
		//frame.setUndecorated(true);
		frame.setSize(750, 500);
		frame.setContentPane(new MainMenu());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}

	public static void runM() {

		frame.setResizable(false);
		// frame.setUndecorated(true);
		frame.setSize(750, 500);
		frame.setContentPane(new MainMenu());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		hýz+=1000;

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(100, 205, 255));

		g.drawImage(MainImg, 0, 0, getWidth(), getHeight(), null);
	}
	//ACTION LISTENERS
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == score) {
			try {
				JOptionPane.showMessageDialog(null, HighScore.getNamesScores());
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if(e.getSource()==help){
			JOptionPane.showMessageDialog(null, "Word Game is adapted from a TV show,named 'Kelime Oyunu'");
		}
		else if (e.getSource() == play){
			
			OyunEkrani.score=0;
			name = JOptionPane.showInputDialog(frame,
					"Please enter your name: ");
			if (name != null) {
				while (name.isEmpty() ) {
					name = JOptionPane.showInputDialog(frame,
							"Please enter your name: ");

				}
				if (!name.isEmpty() ) {
					System.out.println(name);
					OyunEkrani p = new OyunEkrani();
					setScreen(p);
					p.run(frame);
					p.requestFocusInWindow();
					OyunEkrani.t1.start();
				}
			
			}
			
		
		}
		else if (e.getSource() == quit){
			System.exit(0);
		}
		
		
	}

}

