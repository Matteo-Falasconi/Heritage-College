package HangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreBoardTest {

	@Test
	void testConstructor() {
		ScoreBoard sb = new ScoreBoard();
		assertEquals(sb.playerListLength(), 0);
		assertNotEquals(sb.playerListLength(), 1);
	} // testConstructor()
	
	@Test
	void testAddPlayer() {
		ScoreBoard sb = new ScoreBoard();
		sb.addPlayer("Ben");
		assertEquals(sb.getPlayer(0).getUsername(), "Ben");
		assertNotEquals(sb.getPlayer(0).getUsername(), "Ryan");
		assertEquals(sb.playerListLength(), 1);
		assertNotEquals(sb.playerListLength(), 0);
	} // testAddPlayer()
	
	@Test
	void testAddPlayerInMultiplePlayerList() {
		ScoreBoard sb = new ScoreBoard();
		sb.addPlayer("Ben");
		sb.addPlayer("Ryan");
		for(int i = 0; i < sb.getPlayers().getLength(); i++) {
			if(sb.getPlayer(i).getUsername().equals("Ben")) {
				assertEquals(sb.getPlayer(i).getUsername(), "Ben");
			} else {
				assertNotEquals(sb.getPlayer(i).getUsername(), "Ben");
			}
			if(sb.getPlayer(i).getUsername().equals("Ryan")) {
				assertEquals(sb.getPlayer(i).getUsername(), "Ryan");
			} else {
				assertNotEquals(sb.getPlayer(i).getUsername(), "Ryan");
			}
		}
	} // testAddPlayerInMultiplePlayerList()
} // ScoreBoardTest
