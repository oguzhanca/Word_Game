import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;


public class LastScreen  extends JPanel implements ActionListener{
	
	public static JFrame frame1 = new JFrame();
	private JButton restart = new JButton("Restart");
	private JButton quit = new JButton("Quit");
	private JButton aboutUs = new JButton("About Us");
	private JButton highScores = new JButton("High Scores");
	

	public LastScreen(){
		/*** Buttons and action listeners*/

		setLayout(null);
		restart.setFocusPainted(false);
		quit.setFocusPainted(false);
		aboutUs.setFocusPainted(false);
		highScores.setFocusPainted(false);
		
		restart.setHorizontalAlignment(SwingConstants.CENTER);
		quit.setHorizontalAlignment(SwingConstants.CENTER);
		aboutUs.setHorizontalAlignment(SwingConstants.CENTER);
		highScores.setHorizontalAlignment(SwingConstants.CENTER);
		
		restart.setBounds(290, 80, 142, 50);
		aboutUs.setBounds(290, 170, 142, 50);
		highScores.setBounds(290, 260, 142, 50);
		quit.setBounds(290, 350, 142, 50);
		
		restart.setBackground(Color.WHITE);
		quit.setBackground(Color.WHITE);
		aboutUs.setBackground(Color.WHITE);
		highScores.setBackground(Color.WHITE);
		add(restart);
		add(quit);
		add(aboutUs);
		add(highScores);
		restart.addActionListener(this);
		quit.addActionListener(this);
		aboutUs.addActionListener(this);
		highScores.addActionListener(this);
		
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Image image=new ImageIcon("images/background3.png").getImage();
		g.drawImage(image, 0, 0, 750, 500, null);
			
	}	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == restart){
			
//			MainMenu.tekrar=true;
			OyunEkrani.timeUp=false;
			OyunEkrani p = new OyunEkrani();
			MainMenu.setScreen(p);
			p.run(MainMenu.frame);
			frame1.dispose();
			p.requestFocusInWindow();
			MainMenu.runM();
			OyunEkrani.continueToCount=true;
			OyunEkrani.score=0; /***/
		}
		else if(e.getSource()==highScores){
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
		
		else if (e.getSource() == quit){
			System.exit(0);
		}
		else if (e.getSource() == aboutUs){
			JOptionPane.showMessageDialog(null, "This game was created by\nAli,\nOðuzhan, \nEmine, \nZehra and \nTekin.");
		}

	}
	
	public static void RunLastScreen(JFrame frame1){
		//new LastScreen();
		
		frame1.setResizable(false);
		// frame.setUndecorated(true);
		frame1.setSize(750, 500);
		// frame.setSize(800,533);
		frame1.setContentPane(new LastScreen());
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}

}
