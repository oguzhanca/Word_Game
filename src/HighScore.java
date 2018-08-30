import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScore {

	public static java.io.File listofscores = new java.io.File("highscore1.txt");
	public static ArrayList<String> AllScores=new ArrayList<String>();
	public static ArrayList<Integer> scores=new ArrayList<Integer>();
	public static String PlayerName;
	public static int HighestS;
	private static java.io.PrintWriter output;
	
	 public static void  setHighScore(int score,String playername) throws FileNotFoundException{
		 /*** adds new high score and player name */
		 AllScores.clear();
		 Scanner();
				System.out.println(AllScores.size());
				AllScores.add(String.valueOf(score)+" "+playername);// adding score and name to Array List.
				
				System.out.println("set:"+AllScores.size());
				
				Sort();//Sorting array 
				output = new java.io.PrintWriter(listofscores); // Writer writes new score and name to file 

				for(int i=0;i<scores.size();i++){
				output.println(AllScores.get(i));
				System.out.println("Dosyaya Yazýlan:"+AllScores.get(i));
				}
				output.close();
				
	 
	 }

	public static String getNamesScores() throws FileNotFoundException{
		AllScores.clear();
		 Scanner scrtxt = null;
			try {
				scrtxt = new Scanner(listofscores);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int count = 0;
				while(scrtxt.hasNextLine()){
					String s=scrtxt.nextLine();
					count++;
					if(s.equals(" ")){
						
						break;	
					}
				}
				System.out.println("Scanner count:"+count);
					scrtxt.close();
					AllScores.clear();
					Scanner input1 = new Scanner(listofscores);
						for(int i=0;i<count;i++)
						{
							String line=input1.nextLine(); 
							AllScores.add(line);
						System.out.println(AllScores.get(i));
						}
						System.out.println("size in Scanner:"+AllScores.size());
						input1.close();
						Sort();
						
		 String List="";
		 if (AllScores.size()>=5){
		 for (int i=0;i<5;i++){
			 String s=AllScores.get(i);
			 List+=(i+1)+")"+s+"\n";

		 }}
		 else{
			 for (int i=0;i<AllScores.size();i++){
				 String s=AllScores.get(i); //Creating list with sorted arrays.
				 List+=(i+1)+")"+s+"\n";
		 }
		 }
		return List;
	}


public static void Scanner() throws FileNotFoundException{
	
	 Scanner scrtxt = null;
		try {
			scrtxt = new Scanner(listofscores); //Opens a new scanner
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int count = 0; //Count filled lines
			while(scrtxt.hasNextLine()){
				String s=scrtxt.nextLine();
				count++;
				if(s.equals(" ")){
					
					break;	
				}
			}
			
			System.out.println("Scanner count:"+count);
				scrtxt.close();
				AllScores.clear();
				Scanner input1 = new Scanner(listofscores);//Open new scanner
					for(int i=0;i<count;i++)
					{
						String line=input1.nextLine(); //Creating a string in every line
						AllScores.add(line);//and adding to array list
					System.out.println(AllScores.get(i));
					}
					System.out.println("size in Scanner:"+AllScores.size());
					input1.close();
		
	}


public static void Sort() throws FileNotFoundException{
	scores.clear();
	String names[]=new String[AllScores.size()];
	//creating Array with Scores
	System.out.println("Before Sort:"+AllScores.size());
	for(int i=0;i<AllScores.size();i++){
		String[] s=AllScores.get(i).split(" ");
		names[i]=s[1];
		scores.add(Integer.parseInt(s[0]));
	}
	int temp;
	String temp2;
	for (int k=scores.size();k>=0;k--){
		for(int i=0;i<scores.size()-1;i++){
			if(scores.get(i)<scores.get(i+1)){
				temp=scores.get(i+1);
				scores.set(i+1,scores.get(i)); //Sorting arrays and edit names array according to scores
				scores.set(i, temp);
				
			
				temp2=names[i+1];
				names[i+1]=names[i];
				names[i]=temp2;
				
				}			}
	}
	
//	Collections.sort(scores);
//	for(int i=0;i<scores.size();i++){
//		System.out.println(scores.get(i));
//		System.out.println(names[i]);
//	}
	AllScores.clear();
	System.out.println("After sort count:"+scores.size());
	for(int i=0;i<scores.size();i++){
		String s =String.valueOf(scores.get(i))+" "+names[i];
		AllScores.add(s);
	}

 }
}



