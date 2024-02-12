package HangmanGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import linked_data_structures.DoublyLinkedList;

public class ScoreBoard {
	private static DoublyLinkedList<Player> players;

	public ScoreBoard() {
		players = new DoublyLinkedList<>();
	} // ScoreBoard()

	public int addPlayer(String username) {
		int i = 0;
		if (players.getLength() == 0) {
			players.add(new Player(username));
		} else {
			Player p = new Player(username);
			String newPlayer = p.getUsername().toLowerCase();
			while (i < players.getLength()) {
				String existingPlayer = players.getElementAt(i).getUsername().toLowerCase();
				if (newPlayer.compareTo(existingPlayer) > 0) {
					break;
				}
				i++;
			}
			players.add(p, i);
		}
		return i;
	} // addPlayer(String)

	public DoublyLinkedList<Player> getPlayers() {
		return players;
	} // getPlayers()

	public Player getPlayer(int i) {
		return players.getElementAt(i);
	} // getPlayer(int)

	public int playerListLength() {
		return players.getLength();
	} // playerListLength()

	public void savePlayerListData() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("playersData.dat");
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(players);
		objOut.close();
		fileOut.close();
	} // savePlayerListData()

	public void loadPlayerListData() throws IOException, ClassNotFoundException {
		File file = new File("playersData.dat");
		if (!file.exists() || file.length() == 0) {
			file.createNewFile();
		} else {
			FileInputStream fileIn = new FileInputStream("playersData.dat");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			players = (DoublyLinkedList<Player>) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
	} // loadPlayerListData()
} // ScoreBoard
