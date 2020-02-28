package eg.edu.alexu.csd.datastructure.hangman;


import java.util.Scanner;

public class Main {
	public static void main( String[] args) throws Exception {
		try {
			char c;
			String g;
			Scanner scan = new Scanner(System.in);
			Hangman game = new Hangman();
			game.setDictionary(game.readFile("places.txt"));
			game.selectRandomSecretWord();
			game.setMaxWrongGuesses(5);
			System.out.println(game.secretWord);
			System.out.println(game.guessed);
			do {
				System.out.println("\nwrong guesses allowed: "+ game.guessesRemained);
				System.out.println("enter a letter:");
				c=scan.nextLine().charAt(0);
				if((g=game.guess(c))!=null)
				System.out.println(g);
			}while(game.state==0);
			if(game.state==1) {
				System.out.println("you won!");
			}
			else {
				System.out.println("you lost..");
			}
			scan.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
