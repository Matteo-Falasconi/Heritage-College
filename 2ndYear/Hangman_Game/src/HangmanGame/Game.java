package HangmanGame;

import linked_data_structures.SinglyLinkedList;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {

	private static final long serialVersionUID = 1L;
	private SinglyLinkedList<Character> lettersGuessed;
	private SinglyLinkedList<Character> lettersRemaining;
	private char guess;
	private int wrongGuesses;
	private Dictionary dic;
	private Player player;

	public Game(Player p) throws FileNotFoundException, IOException, IllegalArgumentException {
		dic = new Dictionary();
		setDefaults();
		player = p;
		player.setGame(Game.this);
	} // Game(Player)

	public Player player() {
		return player;
	} // player()

	public Dictionary getDic() {
		return dic;
	} // getDic()

	public void setDefaults() {
		lettersGuessed = new SinglyLinkedList<Character>();
		lettersRemaining = new SinglyLinkedList<Character>();
		guess = 0;
		wrongGuesses = 0;
		String word = dic.getWord().toUpperCase();
		for (int i = dic.getWord().length() - 1; i >= 0; i--) {
			char current = word.charAt(i);
			if (Character.isLetter(current)) {
				lettersRemaining.add(Character.toUpperCase(current));
			}
		}
	} // setDefaults()

	public void newGame() throws IllegalArgumentException {
		dic.removeWord();
		setDefaults();
	} // newGame()

	public char hint() {
		Random rand = new Random();
		int i = rand.nextInt(lettersRemaining.getLength());
		wrongGuesses++;
		char letter = lettersRemaining.getElementAt(i);
		return letter;
	} // hint()

	public ArrayList<Integer> letterInWord(char g) {
		guess = Character.toLowerCase(g);
		boolean inLettersGuessed = false;
		for (int i = 0; i < lettersGuessed.getLength(); i++) {
			inLettersGuessed = lettersGuessed.getElementAt(i) == guess;
			if (inLettersGuessed) {
				break;
			}
		}
		ArrayList<Integer> indexes = new ArrayList<>();
		if (!inLettersGuessed) {
			lettersGuessed.add(guess);
		}
		for (int i = 0; i < wordLength(); i++) {
			if (guess == Character.toLowerCase(dic.getWord().charAt(i))) {
				indexes.add(i);
			}
		}
		if (!indexes.isEmpty()) {
			for (int i = 0; i < indexes.size(); i++) {
				for (int j = 0; j < lettersRemaining.getLength(); j++) {
					if (Character.toLowerCase(lettersRemaining.getElementAt(j)) == guess) {
						lettersRemaining.remove(j);
					}
				}
			}
		} else if (!inLettersGuessed) {
			wrongGuesses++;
		}
		return indexes;
	} // letterInWord(char)

	public int countDistinctLetters(String word) {
		Set<Character> distinctLetters = new HashSet<>();
		if(word != null) {
			for (int i = 0; i < word.length(); i++) {
				distinctLetters.add(word.charAt(i));
			}
			return distinctLetters.size();
		} 
		for (int i = 0; i < lettersRemaining.getLength(); i++) {
			distinctLetters.add(lettersRemaining.getElementAt(i));
		}
		return distinctLetters.size();
	} // countDistinctLetters(String)

	public int life() {
		return 6 - wrongGuesses;
	} // life()

	public int wordLength() {
		return dic.getWord().length();
	} // wordLength()

	public int getRemainingLength() {
		return lettersRemaining.getLength();
	} // getRemainingLength()

	public SinglyLinkedList<Character> getLettersGuessed() {
		return lettersGuessed;
	} // getLettersGuessed()

	public SinglyLinkedList<Character> getLettersRemaining() {
		return lettersRemaining;
	} // getLettersRemaining()
} // Game
