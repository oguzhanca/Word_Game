import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

public class OyunEkrani extends JPanel implements ActionListener {

	static javax.swing.Timer t1;


	JPanel panelCells = new JPanel();
	public static JLabel[] cells;

	/**
	 * Defining Countdown variables 
	 */
	static int second;
	static int minute;
	static boolean continueToCount = true;
	static boolean timeUp = false;
	static JLabel time = new JLabel(new ImageIcon("images/sandglass.png"), 0);
	
	
	static JOptionPane a;
	JLabel def = new JLabel("", SwingConstants.LEFT);
	String dialog; 
	String word;
	int numberOfQ = 1;


	static JMenuBar mb = new JMenuBar();

	/**
	 * Buttons
	 */
	JButton letter = new JButton("", new ImageIcon("images/abc48.png"));
	JButton answer = new JButton("Answer!", new ImageIcon(
			"images/answer128.png"));
	JButton next = new JButton(new ImageIcon("images/next4.png"));


	/**
	 * Score
	 */
	public static int point;
	public static int score = 0;
	JLabel scoreL = new JLabel("", new ImageIcon("images/star96.png"), 0);
	JLabel pointL = new JLabel("", new ImageIcon("images/star48.png"), 0);


	static int randomIndex;
	static ArrayList<Integer> randomIndexArr = new ArrayList<Integer>();
	static char hintLetter;

	/**
	 * Sound variables
	 */
	static Sounds sound = new Sounds();
	static boolean SoundOn = false;
	static ImageIcon soundOnIcon = new ImageIcon("images/Sound-on.png");
	static ImageIcon soundOffIcon = new ImageIcon("images/Sound-off.png");
	static JButton soundOnButton = new JButton(soundOffIcon);

	Font myFont15 = new Font("Serif", Font.BOLD, 15);
	Font myFont20 = new Font("Serif", Font.BOLD, 20);
	Font myFont30 = new Font("Serif", Font.BOLD, 30);

	
	/********************************************* CONSTRUCTOR **************************************************************/

	public OyunEkrani() {

		if (!MainMenu.tekrar) {
			/***if game is restarted,timer object won't created again.  */
			t1 = new Timer(1000, this);
			MainMenu.tekrar = true;
		}

		setLayout(null);
		setFocusable(true);
		/**
		 * Time variables
		 */
		OyunEkrani.minute = 4;
		OyunEkrani.second = 0;
	

		def.setBackground(new Color(128, 0, 0)); // BORDO
		def.setForeground(Color.white);
		time.setForeground(Color.WHITE);
		pointL.setForeground(Color.WHITE);
		pointL.setBackground(Color.WHITE);

		
		panelCells.setOpaque(false);
		time.setOpaque(false);
		pointL.setOpaque(false);
		next.setOpaque(false);
		soundOnButton.setOpaque(false);
		scoreL.setOpaque(false);
		answer.setOpaque(true);
		def.setOpaque(true);
		letter.setOpaque(true);

		soundOnButton.setBounds(20, 20, 50, 50);
		panelCells.setBounds(60, 240, 450, 50);
		def.setBounds(50, 300, 500, 120);
		next.setBounds(600, 320, 100, 100);
		answer.setBounds(250, 20, 200, 200);
		letter.setBounds(600, 220, 100, 80);
		time.setBounds(37, 160, 150, 80);
		pointL.setBounds(600, 140, 120, 50);
		scoreL.setBounds(600, 20, 100, 100);

		def.setFont(myFont20);
		time.setFont(myFont30);
		answer.setFont(myFont30);
		pointL.setFont(myFont20);
		scoreL.setFont(myFont20);

		letter.setContentAreaFilled(false);
		letter.setBorderPainted(false);
		letter.setFocusPainted(false);
		letter.setRolloverIcon(new ImageIcon("images/abc64.png"));

		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setFocusPainted(false);
		next.setPressedIcon(new ImageIcon("images/next3.png"));

		soundOnButton.setContentAreaFilled(false);
		soundOnButton.setBorderPainted(false);
		soundOnButton.setFocusPainted(false);

		answer.setContentAreaFilled(false);
		answer.setBorderPainted(false);
		answer.setFocusPainted(false);

		pointL.setHorizontalAlignment(JLabel.LEFT);
		scoreL.setHorizontalTextPosition(0);
		answer.setHorizontalTextPosition(0);
		def.setVerticalTextPosition(SwingConstants.TOP);
		def.setVerticalAlignment(JLabel.TOP);

		def.setBorder(new TitledBorder(null, numberOfQ + ". DEFINATION", 0, 0,
				myFont15, Color.white));

	
		letter.setToolTipText("Makes a letter display from the word");
		soundOnButton.setToolTipText("Sound On/Off");
		pointL.setToolTipText("Shows point that you can get from  the question"); 
		scoreL.setToolTipText("Shows your score");
		
		add(next);
		add(answer);
		add(letter);
		add(time);
		add(panelCells);
		add(def);
		add(pointL);
		add(scoreL);
		add(soundOnButton);

		/***ADDING LÝSTENERS  */
		answer.addActionListener(this);
		letter.addActionListener(this);
		next.addActionListener(this);
		soundOnButton.addActionListener(this);

		cells = new JLabel[10];
		panelCells.setLayout((new GridLayout(1, 6)));

		/***Hexagonal Cells where letters are written  */
		for (int i = 0; i < 10; i++) {
			cells[i] = new JLabel("", new ImageIcon("images/hexagon48.png"), 0);
			cells[i].setHorizontalTextPosition(0);
			cells[i].setFont(myFont30);
			panelCells.add(cells[i]);
		}

		/***Word Selection  */
		try {
			word = DosyaFile.selectWord(numberOfQ);
			randomIndexArr.clear();
			def.setText("<html>" + DosyaFile.selectedDef + "<html>");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/***Cells are not visible if their length greater than word's length  */
		for (int k = 0; k < 10; k++) {
			if (k < word.length())
				cells[k].setVisible(true);
			else
				cells[k].setVisible(false);
		}

		/***Calculating points   */
		point = word.length() * 100;
		pointL.setText("" + point);
		scoreL.setText("0");

	}

	/********************************************* ACTION PERFORMED **************************************************************/
	@SuppressWarnings({ "static-access" })
	public void actionPerformed(ActionEvent e) {
		

		countDown(continueToCount);
		/***Time controller  */
		if (minute == 0 && second == 0 && !timeUp) {

			JOptionPane.showMessageDialog(null, "GAMEOVER!");
			try {
				HighScore.setHighScore(score, MainMenu.name); //Saves high score
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			t1.stop(); //stops timer

			timeUp = true;
			/*** opens Last screen */
			LastScreen over = new LastScreen(); 
			MainMenu.setScreen(over);
			MainMenu.frame.dispose();
			LastScreen.RunLastScreen(LastScreen.frame1);

		}

		// THIS PART GIVES A LETTER RANDOMLY.
		if (e.getSource() == letter) {

			/*** A letter opens randomly and stored in array, not to open same letter*/
			if (randomIndexArr.size() != word.length()) {
				randomIndex = (int) (Math.random() * word.length());

				while (randomIndexArr.contains(randomIndex))
					randomIndex = (int) (Math.random() * word.length());

				hintLetter = word.charAt(randomIndex);
				cells[randomIndex].setText("" + hintLetter);
				randomIndexArr.add(randomIndex); 

				if (point > 0) { //Calculating points according to opened letters.
					point -= 100;
					pointL.setText(point + "");
				}
				if (point == 0) {
					letter.setEnabled(false);
					answer.setEnabled(false);
				}
			}
		}

		else if (e.getSource() == answer) {
			/*
			 * Timer is stopped and option pane opened to answer the questiýn
			 */
			continueToCount = false;
			letter.setEnabled(false);
			answer.setEnabled(false);

			sound.Sound("sounds/ooooo.wav", SoundOn);//Sounds
			sound.start();
			dialog = a.showInputDialog(null,
					"Enter your answer:\n", "ANSWER",
					JOptionPane.WARNING_MESSAGE);
			
						for (int k = 0; k < word.length(); k++)
				cells[k].setText(word.charAt(k) + "");
			if (dialog != null) {
				// If the answer does not match with the input.. 
				if (!dialog.toUpperCase(Locale.ENGLISH).equals( 
						word.toUpperCase(Locale.ENGLISH))) {

					sound.stop();
					sound.Sound("sounds/aaaaa.wav", SoundOn);
					sound.start();

					JOptionPane.showMessageDialog(null,
							"You have answered wrong!");
				} else {
					sound.stop();
					sound.Sound("sounds/gülme.wav", SoundOn);
					sound.start();
					JOptionPane.showMessageDialog(null, "Congratulations!");
					score += point;
					scoreL.setText(score + "");
				}
			} else {
				continueToCount = false;
				sound.stop();
			}

			// Questions are finished
			if (numberOfQ == 14) {
				minute = 0;
				second = 0;//Game over 

			}

			pointL.setText("");
			sound.stop();

		} else if (e.getSource() == next) {

			letter.setEnabled(true);
			answer.setEnabled(true);

			continueToCount = true;

			numberOfQ++;

			def.setBorder(new TitledBorder(null, numberOfQ + ". DEFINATION", 0,
					0, myFont15, Color.white));

			if (numberOfQ == 14) //Controls question numbers.
				next.setEnabled(false);

			try {
				word = DosyaFile.selectWord(numberOfQ);
				randomIndexArr.clear();
				def.setText("<html>" + DosyaFile.selectedDef + "<html>");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				//Point
			point = word.length() * 100;
			pointL.setText(point + "");
		}

		// SOUND BUTTON LISTENER (ON/OFF)
		else if (e.getSource() == soundOnButton) {

			if (SoundOn == true) {
				soundOnButton.setIcon(soundOffIcon);
				sound.stop();
				SoundOn = false;
			}

			else {
				soundOnButton.setIcon(soundOnIcon);
				SoundOn = true;
			}
		}

	}

	/*
	 * This method calls by MainMenu Class.
	 */
	public static void run(final JFrame frame) {
		new MyMenuBar();
		frame.setJMenuBar(MyMenuBar.mb);
		frame.setSize(750, 500);
		frame.setVisible(true);

	}

	// THIS METHOD COUNTS DOWN TO 0 AND DETERMINES WHEN TO STOP. 
	public static void countDown(boolean continueToCount) {
		if (continueToCount == true) {
			if (second > 0)
				second--;

			if (second == 0 && minute != 0) {
				minute--;
				second = 59;
			}
			if (second > 9)
				time.setText(minute + ":" + second);
			else
				time.setText(minute + ":0" + second);
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Image image = new ImageIcon("images/background3.png").getImage();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

	}

}
