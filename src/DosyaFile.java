import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DosyaFile {
	public static String aaa[][] = new String[100][2];
	public static int c;
	static String selected;
	static String selectedDef;
	static int previous=99;
	
	static File fileT = new File("kelimelerT.txt");
	static File fileE = new File("kelimelerE.txt");
	static File file=fileT;
	int count;
	public static String selectWord(int q) throws Exception {


		
		Scanner input = new Scanner(file);

		int a = 0;
		//Creating 2D array to words and their definitons.
		while (input.hasNext()) {

			aaa[a][0] = (input.nextLine());
			aaa[a][1] = (input.nextLine());

			a++;
			
			// k += 2;
			// input.reset();
		
		}
		System.out.println("a:"+a);
		
		
		System.out.println("previous:"+previous);
		do {
			while (true) {
				c = (int) (Math.random() * a); //Choosing word randomly
				
				if (c != previous) //controlling previous word's index with new index
					break;
			}
			

			selected = aaa[c][0];
			selectedDef = aaa[c][1];

		} while ((q - 1) / 2 + 4 != selected.length()); //Controlling length

		previous=c;
		System.out.println("c:"+c);

		for (int i = 0; i < 10; i++) {

			if (i < selected.length()) {
				OyunEkrani.cells[i].setText("");
				OyunEkrani.cells[i].setVisible(true);
			} else {
				OyunEkrani.cells[i].setVisible(false);
			}

		}

		return aaa[c][0];
	}

}
