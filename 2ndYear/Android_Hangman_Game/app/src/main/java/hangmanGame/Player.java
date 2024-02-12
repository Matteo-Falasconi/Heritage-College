package hangmanGame;

import java.io.Serializable;

public class Player implements Serializable {

    private String username;
    private int gamesPlayed;
    private int gamesWon;
    private Game game;

    public Player(String user) {
        setUsername(user);
    } // Player(String)

    public void setUsername(String user) {
        username = user;
    } // setUsername(String)

    public String getUsername() {
        return username;
    } // getUsername()

    public void setGamesPlayed(int played) {
        gamesPlayed = played;
    } // setGamesPlayed(int)

    public int getGamesPlayed() {
        return gamesPlayed;
    } // getGamesPlayed()

    public void setGamesWon(int won) {
        gamesWon = won;
    } // setGamesWon(int)

    public int getGamesWon() {
        return gamesWon;
    } // getGamesWon()

    public void addGamesPlayed() {
        gamesPlayed++;
    } // addGamesPlayed()

    public void addGamesWon() {
        gamesWon++;
    } // addGamesWon()

    public Game getGame() {
        return game;
    } // getGame()

    public void setGame(Game g) {
        game = g;
    } // setGame(Game)

} // Player