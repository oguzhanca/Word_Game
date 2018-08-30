import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;

/*** CREATING A MENU BAR*/


public class MyMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JMenuBar mb;
	
	public  MyMenuBar()  {
		//Creating and adding menu items.
		mb = new JMenuBar();
		this.add(mb); 
		
		System.out.println("Edit Bar!");

		JMenu file = new JMenu("File");
		JMenuItem restart = new JMenuItem("Restart");
		JMenuItem saveLoad = new JMenuItem("Save/Load");
		JMenuItem higSco = new JMenuItem("High Scores");

		file.add(restart);
		file.add(saveLoad);
		file.add(higSco);
		mb.add(file);

		JMenu edit = new JMenu("Edit");
		JMenuItem language = new JMenuItem("Change Word Language");
		JMenuItem countDown = new JMenuItem("CountDown");
		JMenuItem rename = new JMenuItem("Rename");

		edit.add(language);
		edit.add(countDown);
		edit.add(rename);
		mb.add(edit);

		JMenu help = new JMenu("Help");
		JMenuItem update = new JMenuItem("Check For Update");
		JMenuItem support = new JMenuItem("Get Online Support");

		help.add(update);
		help.add(support);
		mb.add(help);

		JMenu about = new JMenu("About");

		mb.add(about);

		JMenuItem exit = new JMenuItem("Exit");

		mb.add(exit);
		
		//Exits the game
				exit.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						System.exit(0);
					}});
				
			//Change language	
				language.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						if(DosyaFile.file==DosyaFile.fileE){
							DosyaFile.file=DosyaFile.fileT;
							JOptionPane.showMessageDialog(null, "The language of words has been changed to Turkish!");
						}
						else{
							DosyaFile.file=DosyaFile.fileE;
							JOptionPane.showMessageDialog(null, "The language of words has been changed to English!");
						}
						
					}});
				
				//Goes to main menu 
				restart.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						OyunEkrani.timeUp=false;
						OyunEkrani p = new OyunEkrani();
						MainMenu.setScreen(p);
						p.run(MainMenu.frame);
						p.requestFocusInWindow();
						MainMenu.runM();
						OyunEkrani.continueToCount=true;
						OyunEkrani.score=0; /***/
					}});
				
				
				update.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						JOptionPane.showMessageDialog(null, "This is the latest version 1.0.0\n(In any case, this the one and only version)");
					}});
				
				
				support.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						JOptionPane.showMessageDialog(null, "Is there any problem??\nCall me: (+90) 554 260 8617");
					}});
				
	
				/*** Writes high scores.*/

				higSco.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						try {
							JOptionPane.showMessageDialog(null, HighScore.getNamesScores());
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}});
		
		

	}



}
