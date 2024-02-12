package com.example.mfalasconi_g30_a03_hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import hangmanGame.Player;
import hangmanGame.ScoreBoard;

public class ScoreBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private ScoreBoard board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        board = intent.getSerializableExtra("b", ScoreBoard.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        Button btnBack = findViewById(R.id.btnBackScore);
        btnBack.setOnClickListener(this);
        LinearLayout players = findViewById(R.id.playersScore);
        for (int i = board.getPlayers().getLength() - 1; i >= 0; i--) {
            Player p = board.getPlayer(i);
            Button playerButton = new Button(this);
            playerButton.setText(i + 1 + " - " + "Adventurer:  " + p.getUsername() + "\nWins: " + p.getGamesWon() + "  |  " + " Losses: "
                    + (p.getGamesPlayed() - p.getGamesWon()) + " | Games Played: " + p.getGamesPlayed());
            playerButton.setOnClickListener(this);
            players.addView(playerButton);
        }
    }

    @Override
    public void onClick(View view) {
        int clicked = view.getId();
        if(clicked == R.id.btnBackScore) {
            finish();
        } else {
            Intent i = new Intent(this, HangmanGameActivity.class);
            int pressed = Character.getNumericValue(((Button) view).getText().charAt(0)) - 1;
            i.putExtra("b", board);
            i.putExtra("returning", true);
            i.putExtra("player", pressed);
            startActivity(i);
        }
    }
}