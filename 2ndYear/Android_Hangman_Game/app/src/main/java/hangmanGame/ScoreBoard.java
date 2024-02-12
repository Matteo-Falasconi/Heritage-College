package hangmanGame;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import linked_data_structures.DoublyLinkedList;

public class ScoreBoard implements Serializable {
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

    public void setPlayers(DoublyLinkedList<Player> p) {
        players = p;
    }

    public Player getPlayer(int i) {
        return players.getElementAt(i);
    } // getPlayer(int)
} // ScoreBoard
