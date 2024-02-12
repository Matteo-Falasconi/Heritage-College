package HangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testNewPlayer() {
		Player p = new Player("Ben");
		assertEquals("Ben", p.getUsername());
		assertEquals(0, p.getGamesWon());
		assertEquals(0, p.getGamesPlayed());
		assertNotEquals("B", p.getUsername());
		assertNotEquals(1, p.getGamesWon());
		assertNotEquals(1, p.getGamesPlayed());
	} // testNewPlayer()
	
	@Test
	void testReturningPlayer() {
		Player p = new Player("Ryan", 0, 3);
		assertEquals("Ryan", p.getUsername());
        assertEquals(0, p.getGamesPlayed());
        assertEquals(3, p.getGamesWon());
        assertNotEquals("R", p.getUsername());
        assertNotEquals(3, p.getGamesPlayed());
        assertNotEquals(0, p.getGamesWon());
	} // testReturningPlayer()
	
	@Test
	void testAddOneGamePlayed() {
		Player p = new Player("Claude");
		int played = p.getGamesPlayed();
        p.addGamesPlayed();
        assertEquals(played + 1, p.getGamesPlayed());
        assertNotEquals(played, p.getGamesPlayed());
	} // testAddOneGamePlayed()
	
	@Test
	void testAddOneGameWon() {
		Player p = new Player("Claude");
		int won = p.getGamesWon();
        p.addGamesWon();
        assertEquals(won + 1, p.getGamesWon());
        assertNotEquals(won, p.getGamesWon());
	} // testAddOneGameWon()

} // PlayerTest
