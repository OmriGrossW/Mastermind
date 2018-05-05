import java.util.LinkedList;
import java.util.ArrayList;

public class Mastermind{
	public static final int NUM_OF_SLOTS = 4;
	public static final int NUM_OF_COLORS = 6;	/* Color values marked by numbers, range 1 to 6 */
	public static final int NUM_OF_POSSIBLE_GUESSES = Math.pow(NUM_OF_COLORS, NUM_OF_SLOTS);	/* in our case, 6 to the power of 4 = 1296 */	
	
//	public static LinkedList S = new LinkedList();
	public static int[][] S = new int[NUM_OF_POSSIBLE_GUESSES][NUM_OF_SLOTS];	/* Represents the set of all possible color value combinations */
	public static int[] cipher = new int[NUM_OF_SLOTS];	/* Represents the code to break - the cipher as an array of color values */
	public static LinkedList<int> guessesMadeIndexes = new LinkedList<int>();	/* List of indexes of the guesses made from S */
	public static LinkedList<int> removedCodesIndexes = new LinkedList<int>();	/* List of the codes that were removed out of S so far */ 


	
	
	
//	public static int[] guessesMadeIndexes;		/* Indicates which guesses were chosen out of S already by thier index in the array */
//	public static int numOfGuessesMade;			/* Indicates the number of guesses made so far */
	
	
	
	
	
	

	public static void main(String[] args){
		//generateAllCombinations();
		generateCipher();
		Knuth();
	}
	
	public static int[] tear(int num, int digits){
		int[] torenCode = new int[NUM_OF_SLOTS];
		int tmp = 1;
		
		for (int i = digits - 1 ; i >= 0 ; i--){
			torenCode[i] = (num / tmp) % 10;
			tmp = tmp * 10;
		}
		
		return torenCode;
	}

	public static boolean checkIfCodeIsLegit(int[] code){
		for (int i = 0 ; i < NUM_OF_SLOTS ; i++){
			if(code[i] < 1 || code[i] > NUM_OF_COLORS)	/* If the color value is out of the color values range */
				return false;
		}
		return true;
	}
	
	public static void generateS(){
		int num;
		int i = 0;
		int[] tmpCode = new int[NUM_OF_SLOTS];
		
		
		for (num = 1111 ; num <= 1111 * NUM_OF_COLORS ; num++){		/* in our case, 1111 * NUM_OF_COLORS == 6666 */
			tmpCode = tear(num, NUM_OF_SLOTS);		/* break the number into digits and return the digits in an array */
			if (checkIfCodeIsLegit(tmpCode)){		/* notice - we skip irrelevant numbers using checkIfCodeIsLegit function */
				for (int j = 0 ; j < NUM_OF_SLOTS ; j++){ 		
					S[i][j] = tmpCode[j];
				}
				i++;
			}
			
		}
		
	}
	
	public static void generateCipher(){
		for (int i = 0 ; i < NUM_OF_SLOTS ; i++){
			cipher[i] = (int)(Math.random() * NUM_OF_COLORS) + 1;
		}
	}
	
	public static int[] getScoreForGuess(int[] guess){
		int[] score = new int[2];
		boolean[] visited = new boolean[NUM_OF_SLOTS];		/* Marks what slots of the cipher were already spotted and counted as bulls or cows - preventing duplicates */
		int bulls = 0;	/* Bulls - slots with exact guess*/
		int cows = 0;	/* Cows - slots with right colors that are not in place */
		
		
		for (int i = 0 ; i < NUM_OF_SLOTS ; i++)
			visited[i] = false;
		
		for (int i = 0 ; i < NUM_OF_SLOTS ; i++){
			if (guess[i] == cipher[i]){
				visited[i] = true;
				bulls++;
			}
			else{
				for (int j = 0 ; j < NUM_OF_SLOTS ; j++){
					if (i != j && visited[j] == false && guess[i] == cipher[j]){
						visited[j] = true;
						cows++;
					}
				}
			}
		}
		
		score[0] = bulls;
		score[1] = cows;
		return score;
	}
	
	
	
	
	
	
	
	public static void Knuth(){
		int[] guess = new int[NUM_OF_SLOTS];
		int[] score = new int[2];
		int numOfGuesses = 0;
		score[0] = 0;
		score[1] = 0;
		
	//	for (int[] code : allCombinations) {	/* (1) */
		//	S.add(Arrays.asList(code));			/* (1) */
	//	}										/* (1) */
		
		//S = new LinkedList(Arrays.asList(allCombinations));	/* (1) */
		
		generateS();	/* (1) */
		
		guess[0] = 1;	/* (2) */
		guess[1] = 1;	/* (2) */
		guess[2] = 2;	/* (2) */
		guess[3] = 2;	/* (2) */
		numOfGuesses++; 
		
		guessesMadeIndexes.add(7)	/* The index of '1122' */
		//removedCodesIndexes
		
		while (score[0] < NUM_OF_SLOTS){							/* while the guess is not correct yet - not enough "BULLS" */
			score = getScoreForGuess(guess); 	/* (3) */
			if (score[0] == NUM_OF_SLOTS){		/* (4) */
				System.out.println("Well done Knuth!\nIt took you only " + numOfGuesses + " guesses!\n");	/* (4) */
				return;							/* (4) */
			}
			
			numOfGuesses++; 
		}
		
		
		
	}
}
