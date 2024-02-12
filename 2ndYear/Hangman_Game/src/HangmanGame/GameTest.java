package HangmanGame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void letterIsInWord() {
		Player p = new Player(null);
		try {
			Game game = new Game(p);
			game.getDic().setWord("Game");
			assertNotEquals(game.letterInWord('g').size(), 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // letterIsInWord()

	@Test
	void letterIsNotInWord() {
		Player p = new Player(null);
		try {
			Game g = new Game(p);
			g.getDic().setWord("Game");
			assertEquals(g.letterInWord('y').size(), 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // letterIsNotInWord()

	@Test
	void testHintLetterInWord() {
		Player p = new Player(null);
		try {
			Game g = new Game(p);
			char hint = g.hint();
			boolean hintInWord = false;
			for (int i = 0; i < g.getRemainingLength(); i++) {
				if (g.getLettersRemaining().getElementAt(i) == hint) {
					hintInWord = true;
					break;
				}
			}
			assertTrue("This is to test whether the letter is in the remaining letters to be guessed", hintInWord);
			assertFalse(!hintInWord);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // testHintLetterInWord()

	@Test
	void testDistinctWords() {
		Player p = new Player(null);
		try {
			Game g = new Game(p);
			assertEquals(g.countDistinctLetters("Food"), 3);
			assertEquals(g.countDistinctLetters("definitive"), 7);
			assertNotEquals(g.countDistinctLetters("Game"), 5);
			assertNotEquals(g.countDistinctLetters("Hangman"), 6);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // testDistinctWords()
} // GameTest
