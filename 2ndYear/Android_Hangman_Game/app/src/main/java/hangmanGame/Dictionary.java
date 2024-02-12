package hangmanGame;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import linked_data_structures.SinglyLinkedList;

public class Dictionary implements Serializable {

    private transient Scanner hangmanReader;
    private SinglyLinkedList<String> wordList;
    private static final int MAX = 20;
    private static final int MIN = 6;
    private String guessWord;
    private int randomIndex = -1;

    public Dictionary(Context context) throws FileNotFoundException, IOException, IllegalArgumentException {
        wordList = new SinglyLinkedList<String>();
        read(context);
        guessWord = wordPicker();
    } // Dictionary()

    public String wordPicker() {
        Random rand = new Random();
        randomIndex = rand.nextInt(wordList.getLength());
        return wordList.getElementAt(randomIndex);
    } // wordPicker()

    public String getWord() {
        return guessWord;
    } // getWord()

    public void read(Context context) throws IOException, FileNotFoundException, IllegalArgumentException {
        InputStream inputStream = context.getAssets().open("wordslist.txt");
        hangmanReader = new Scanner(inputStream);
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
