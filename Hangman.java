package eg.edu.alexu.csd.datastructure.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Hangman implements IHangman {
	final int MAX=1000;
    private int i;
	private String[] dic;
	public int maxGuess=1;
	public int guessesRemained;
	public String secretWord;
	public int wordIndex;
	public char[] guessed;
	public int state =0;
	
	public String[] readFile(String fileName) {
		i=0;
		String[] words = new String[MAX];
		String line;
		try {
		BufferedReader reader = new BufferedReader (new FileReader(fileName));
		while((line=reader.readLine())!=null) {
			words[i]=line;
			i++;
		}
		reader.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}	
		return words;
	}	
	@Override
	public void setDictionary(String[] words) {
		dic=new String[i];
		for(int j=0;j<i;j++) {
			dic[j]=words[j];
		}	
	}
	@Override
	public String selectRandomSecretWord() {
		Random rand = new Random();
		int randI=rand.nextInt(i);
		secretWord =dic[randI];
		wordIndex =secretWord.length(); 
		guessed = new char[wordIndex];
		for(int k=0; k<wordIndex;k++) {
			guessed[k]='-';
		}
		return secretWord;
	}
	@Override
	public String guess(Character c) throws Exception {	
		if(!secretWord.matches("^[a-zA-Z]*$")) {
			throw new Exception ("error");
		}
		int wrongGuess=1;
		if(c==(Character) null) {
			return new String(guessed);
		}
		c=Character.toLowerCase(c);
		for(int k=0; k< wordIndex;k++){
			if(c==secretWord.charAt(k)) {
				wrongGuess=0;
				guessed[k]=c;
			}
		}
		if(wrongGuess==1) {
			guessesRemained--;
		}
		if(guessesRemained==0) {
			state = -1;
			return null;
		}
		String s = new String(guessed);
		if(s.equals(secretWord)) {
			state=1;
		}
		return s;
	}
	@Override
	public void setMaxWrongGuesses(Integer max) {
		if(max == null) {
			this.maxGuess=1;
		}
		else {
			this.maxGuess=max;
		}
		this.guessesRemained=max;	
	}
}
