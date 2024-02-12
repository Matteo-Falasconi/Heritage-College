package HangmanGame;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class DictionaryTest {

	@Test
	void testRemoveWord() {
		Player p = new Player(null);
		try {
			Game g = new Game(p);
			Dictionary d = new Dictionary();
			String removed = d.wordPicker();
			int prevLength = d.getWordList().getLength();
			d.removeWord();
			boolean isRemoved = true;
			for (int i = 0; i < d.getWordList().getLength(); i++) {
				if (d.getWordList().getElementAt(i) == removed) {
					isRemoved = false;
				}
			}
			assertTrue(isRemoved);
			assertTrue(prevLength != d.getWordList().getLength());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // testRemoveWord()

	@Test
	void testNoNextWord() {
		assertThrows(IllegalArgumentException.class, () -> {
			Player p = new Player(null);
			try {
				Game g = new Game(p);
				Dictionary d = new Dictionary();
				d.read("emptyFile.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	} // textNoNextWord()
	
	@Test
    void testValidWord() throws FileNotFoundException, IllegalArgumentException, IOException {
		Dictionary d = new Dictionary();
        assertTrue(d.validWord("example"));
        assertTrue(d.validWord("A' 2BF"));
        assertTrue(d.validWord("ABCDEFG12#@"));
        assertFalse(d.validWord("ABCDE"));
        assertFalse(d.validWord(""));
        assertFalse(d.validWord("gv#kj12h3vj1jkdjkejkj"));
        assertFalse(d.validWord("1234567"));
        assertFalse(d.validWord("12345"));
	} // testValidWord()
	
	@Test
    void testWordPicker() throws FileNotFoundException, IllegalArgumentException, IOException {
		Dictionary d = new Dictionary();
        String word = d.wordPicker();
        assertNotNull(word);
        boolean inWord = false;
		for (int i = 0; i < d.getWordList().getLength(); i++) {
			if (d.getWordList().getElementAt(i) == word) {
				inWord = true;
			}
		}
		assertTrue(inWord);
    } // testWordPicker()
	
	@Test
	void testFileNotFound() {
		assertThrows(FileNotFoundException.class, () -> {
				Dictionary d = new Dictionary();
				d.read("emptee.txt");
		});
	} // testFileNotFound()
} // DictionaryTest
