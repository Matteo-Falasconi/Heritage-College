package com.example.mfalasconi_g30_a03_hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import hangmanGame.Player;
import hangmanGame.ScoreBoard;
import linked_data_structures.DoublyLinkedList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private EditText txtName;
    private ScoreBoard board = new ScoreBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        txtName = findViewById(R.id.txtName);
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        try {
            ImageView imageView = findViewById(R.id.imageView);
            InputStream stream = getAssets().open("witch.PNG");
            Drawable drawable = Drawable.createFromStream(stream, null);
            imageView.setImageDrawable(drawable);
            stream.close();
            File file = new File(getFilesDir(), "playersData.ser");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            board.setPlayers((DoublyLinkedList<Player>) objIn.readObject());
            objIn.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
            AlertDialog.Builder alertError = new AlertDialog.Builder(this);
            alertError.setTitle("Error with File")
                    .setMessage("The file has some corruption, please select another file to begin your adventure!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                            System.exit(0);
                        }
                    })
                    .show();
        } catch (IOException e) {}
        LinearLayout players = findViewById(R.id.playersScore);
        for (int i = board.getPlayers().getLength() - 1; i >= 0; i--) {
            Player p = board.getPlayer(i);
            Button playerButton = new Button(this);
            playerButton.setText(i + 1 + " " + "Adventurer:  " + p.getUsername() + "\nWins: " + p.getGamesWon() + "  |  " + " Losses: "
                    + (p.getGamesPlayed() - p.getGamesWon()));
            playerButton.setOnClickListener(this);
            players.addView(playerButton);
        }

        btnStart.setEnabled(false);

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length() <= 15){
                    btnStart.setEnabled(true);
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(charSequence) && charSequence.length() <= 15){
                    btnStart.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        int clicked = view.getId();
        Intent i = new Intent(this, HangmanGameActivity.class);
        if(clicked == R.id.btnStart) {
            int index = board.addPlayer(txtName.getText().toString().trim());
            txtName.setText("");
            i.putExtra("b", board);
            i.putExtra("returning", false);
            i.putExtra("player", index);
            startActivity(i);
        } else {
            int pressed = Character.getNumericValue(((Button) view).getText().charAt(0)) - 1;
            i.putExtra("b", board);
            i.putExtra("returning", true);
            i.putExtra("player", pressed);
            startActivity(i);
        }
    } // onClick(View)
} // Login