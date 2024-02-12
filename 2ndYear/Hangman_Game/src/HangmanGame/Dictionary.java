package HangmanGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import linked_data_structures.SinglyLinkedList;

public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient Scanner hangmanReader;
	private SinglyLinkedList<String> wordList;
	private static final int MAX = 20;
	private static final int MIN = 6;
	private String guessWord;
	private int randomIndex = -1;

	public Dictionary() throws FileNotFoundException, IOException, IllegalArgumentException {
		wordList = new SinglyLinkedList<String>();
		read(null);
		guessWord = wordPicker();
	} // Dictionary()

	public String wordPicker() {
		System.out.println();
		Random rand = new Random();
		randomIndex = rand.nextInt(wordList.getLength());
		return wordList.getElementAt(randomIndex);
	} // wordPicker()

	public void setWord(String w) {
		guessWord = w;
	} // setWord(String)

	public String getWord() {
		return guessWord;
	} // getWord()

	public void read(String file) throws IOException, FileNotFoundException, IllegalArgumentException {
		if (file != null) {
			hangmanReader = new Scanner(new File(file));
		} else {
			hangmanReader = new Scanner(new File("testFile.txt"));
		}
		if (hangmanReader.hasNextLine()) {
			while (hangmanReader.hasNextLine()) {
				String line = hangmanReader.nextLine();
				if (validWord(line)) {
					wordList.add(line);
				}
			}
			hangmanReader.close();
		} else {
			throw new IllegalArgumentException();
		}
	} // read(String)

	public boolean validWord(String word) throws IllegalArgumentException {
		return (!word.equals("") && Pattern.compile("[A-Za-z]").matcher(word).find() && word.length() >= MIN
				&& word.length() <= MAX);
	} // validWord(String)

	public void removeWord() throws IllegalArgumentException {
		if (randomIndex != -1) {
			wordList.remove(randomIndex);
			guessWord = wordPicker();
		}
	} // removeWord()

	public SinglyLinkedList<String> getWordList() {
		return wordList;
	} // getWordList()

} // Dictionary
